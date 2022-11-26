/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ReservationDAO;
import dao.impl.CategoryDAOImpl;
import dao.impl.ReservationDAOImpl;
import entity.Book;
import entity.Category;
import entity.Reservation;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ReservationListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // get user in current login session

        if (user == null) { //case: cannot get user when end of login session
            response.sendRedirect(request.getContextPath() + "/login");
        } else if ((user.getRole().equalsIgnoreCase("librarian")) //case: user is allow to get in with suitable account role
                || (user.getRole().equalsIgnoreCase("admin"))) {
            ReservationDAO reservationDAO = new ReservationDAOImpl();

            List<Reservation> reservations = reservationDAO.getAll();//this list will contain all reservations of all user

            CategoryDAOImpl c = new CategoryDAOImpl();
            List<Category> categorylist = c.getAllCategory();
            request.setAttribute("categorylist", categorylist);

            request.setAttribute("listReservation", reservations);
            request.getRequestDispatcher("reservationList.jsp").forward(request, response);
        } else { //case wrong account role
            response.sendRedirect(request.getContextPath() + "/logout");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReservationDAO reservationDAO = new ReservationDAOImpl();
        Reservation reservation = new Reservation(); // reservation will get hold info for delete action
        String action = request.getParameter("action");
        if ((action != null) && (action.equalsIgnoreCase("delete"))) { // case update status reservation to rejected with action delete
            try ( PrintWriter out = response.getWriter()) {
                if (IsDeletedReservation(reservation, reservationDAO, request, response)) {
                    out.println("<h1 style=\"color: blue;\">" + "Successed to delete reservation" + "</h1>");
                    out.println("<a href=\"reservationList\">Back to reservation list</a>");
                } else {
                    out.println("<h1 style=\"color: red;\">" + "Failed to delete reservation" + "</h1>");
                    out.println("<a href=\"reservationList\">Back to reservation list</a>");
                }
            }
        } else { //case action null
            processRequest(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReservationDAO reservationDAO = new ReservationDAOImpl();
        Reservation reservation = new Reservation(); // reservation will get hold info for add or edit action
        String action = request.getParameter("action");
        if ((action != null) && (action.equalsIgnoreCase("add"))) { // case: action for add reservation
            try ( PrintWriter out = response.getWriter()) {
                if (IsAddedReservation(reservation, reservationDAO, request, response)) {
                    out.println("<h1 style=\"color: blue;\">" + "Successed to add reservation" + "</h1>");
                    out.println("<a href=\"reservationList\">Back to reservation list</a>");
                } else {
                    out.println("<h1 style=\"color: red;\">" + "Failed to add reservation. BookId or MemberId did not exist" + "</h1>");
                    out.println("<a href=\"reservationList\">Back to reservation list</a>");
                }
            }
        } else if ((action != null) && (action.equalsIgnoreCase("edit"))) { // case: edit for add reservation
            try ( PrintWriter out = response.getWriter()) {
                if (IsEditedReservation(reservation, reservationDAO, request, response)) {
                    out.println("<h1 style=\"color: blue;\">" + "Successed to edit reservation" + "</h1>");
                    out.println("<a href=\"reservationList\">Back to reservation list</a>");
                } else {
                    out.println("<h1 style=\"color: red;\">" + "Failed to edit reservation. BookId or MemberId did not exist" + "</h1>");
                    out.println("<a href=\"reservationList\">Back to reservation list</a>");
                }
            }
        } else if ((action != null) && (action.equalsIgnoreCase("search"))) { // case: search for reservation
            List<Reservation> listAfterSearch = listReservationSearchByTitleUserCategoryDateStatus(reservationDAO, request, response);

            CategoryDAOImpl c = new CategoryDAOImpl();
            List<Category> categorylist = c.getAllCategory();
            request.setAttribute("categorylist", categorylist);

            request.setAttribute("listReservation", listAfterSearch);
            request.getRequestDispatcher("reservationList.jsp").forward(request, response);
        } else { // case: action got null value
            processRequest(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean IsDeletedReservation(Reservation reservation, ReservationDAO reservationDAO, HttpServletRequest request, HttpServletResponse response) {
        try {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));

            reservation = reservationDAO.get(reservationId);
            reservation.setStatus("rejected");

            return (reservationDAO.update(reservation) == 1); //return true if edited success false if did not
        } catch (NumberFormatException e) {
            Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private boolean IsAddedReservation(Reservation reservation, ReservationDAO reservationDAO, HttpServletRequest request, HttpServletResponse response) {
        try {
            int bookId = Integer.parseInt(request.getParameter("bookId")); // id of book in reservation
            int userId = Integer.parseInt(request.getParameter("userId")); // id of user in reservation
            Date dateToGet = Date.valueOf(request.getParameter("dateToGet")); //  date to get book in reservation
            String status = request.getParameter("status"); // status for reservation

            Book book = new Book();
            book.setId(bookId);

            reservation = new Reservation(0, userId, dateToGet, status, book);

            return (reservationDAO.insert(reservation) == 1); //return true if added success false if did not
        } catch (NumberFormatException e) {
            Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private boolean IsEditedReservation(Reservation reservation, ReservationDAO reservationDAO, HttpServletRequest request, HttpServletResponse response) {
        try {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            int bookId = Integer.parseInt(request.getParameter("bookId")); // id of book in reservation
            int userId = Integer.parseInt(request.getParameter("userId")); // id of user in reservation
            Date dateToGet = Date.valueOf(request.getParameter("dateToGet")); //  date to get book in reservation
            String status = request.getParameter("status"); // status for reservation

            Book book = new Book();
            book.setId(bookId);

            reservation = new Reservation(reservationId, userId, dateToGet, status, book);

            return (reservationDAO.update(reservation) == 1); //return true if edited success false if did not
        } catch (NumberFormatException e) {
            Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private List<Reservation> listReservationSearchByTitleUserCategoryDateStatus(ReservationDAO reservationDAO, HttpServletRequest request, HttpServletResponse response) {
        String bookTitle = request.getParameter("bookTitle");
        String userFullname = request.getParameter("userFullname");
        String category = request.getParameter("categoryId"); //get value all or categoryId
        Date dateFrom = Date.valueOf(request.getParameter("dateFrom"));
        Date dateTo = Date.valueOf(request.getParameter("dateTo"));
        String status = request.getParameter("status"); // get value all or pending, rejected or completed

        List<Reservation> listReservation = reservationDAO.getAll();
        List<Reservation> listReservationFiltered = listReservation;

        if (!bookTitle.trim().isEmpty()) {
            listReservationFiltered = listReservationFilterByTitle(listReservationFiltered, bookTitle);
        }
        if (!userFullname.trim().isEmpty()) {
            listReservationFiltered = listReservationFilterByUserFullname(listReservationFiltered, userFullname);
        }
        if (!category.trim().equalsIgnoreCase("all")) {
            listReservationFiltered = listReservationFilterByCategory(listReservationFiltered, category);
        }
        if (!status.trim().equalsIgnoreCase("all")) {
            listReservationFiltered = listReservationFilterByStatus(listReservationFiltered, status);
        }
        listReservationFiltered = listReservationFilterByDate(listReservationFiltered, dateFrom, dateTo);

        return listReservationFiltered;
    }

    private List<Reservation> listReservationFilterByTitle(List<Reservation> listReservationFiltered, String bookTitle) {
        List<Reservation> listFilterd = new ArrayList<>();
        for (Reservation reservation : listReservationFiltered) {
            if (reservation.getBook().getTitle().toLowerCase().contains(bookTitle.toLowerCase())) {
                listFilterd.add(reservation);
            }
        }
        return listFilterd;
    }

    private List<Reservation> listReservationFilterByUserFullname(List<Reservation> listReservationFiltered, String userFullname) {
        List<Reservation> listFilterd = new ArrayList<>();
        for (Reservation reservation : listReservationFiltered) {
            if (reservation.getUser_fullname().toLowerCase().contains(userFullname.toLowerCase())) {
                listFilterd.add(reservation);
            }
        }
        return listFilterd;
    }

    private List<Reservation> listReservationFilterByCategory(List<Reservation> listReservationFiltered, String category) {
        List<Reservation> listFilterd = new ArrayList<>();
        int categoryId = Integer.parseInt(category);
        for (Reservation reservation : listReservationFiltered) {
            if (reservation.getBook().getCategory_id().getId() == categoryId) {
                listFilterd.add(reservation);
            }
        }
        return listFilterd;
    }

    private List<Reservation> listReservationFilterByStatus(List<Reservation> listReservationFiltered, String status) {
        List<Reservation> listFilterd = new ArrayList<>();
        for (Reservation reservation : listReservationFiltered) {
            if (reservation.getStatus().equalsIgnoreCase(status)) {
                listFilterd.add(reservation);
            }
        }
        return listFilterd;
    }

    private List<Reservation> listReservationFilterByDate(List<Reservation> listReservationFiltered, Date dateFrom, Date dateTo) {
        List<Reservation> listFilterd = new ArrayList<>();
        for (Reservation reservation : listReservationFiltered) {
            if ((reservation.getDate().after(dateFrom)) && (reservation.getDate().before(dateTo))) {
                listFilterd.add(reservation);
            }
        }
        return listFilterd;
    }

}
