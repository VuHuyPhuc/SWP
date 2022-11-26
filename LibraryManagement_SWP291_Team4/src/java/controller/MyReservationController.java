/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package controller;

import dao.BookDAO;
import dao.ReservationDAO;
import dao.impl.BookDAOImpl;
import dao.impl.CategoryDAOImpl;
import dao.impl.ReservationDAOImpl;
import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import entity.Category;
import entity.Reservation;
import entity.User;

import jakarta.servlet.http.HttpSession;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MyReservationController extends HttpServlet {

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
        int userId;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) { //case: cannot get user when end of login session
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // user logined and session did not end
            try {
                userId = user.getId();

                ReservationDAO reservationDAO = new ReservationDAOImpl();

                //get reservation list for user and book by reservationId and userId
                List<Reservation> lsReservation = reservationDAO.getAllReservationByUserIdAndStatus(userId, "pending");

                request.setAttribute("lsReservation", lsReservation);
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }

        CategoryDAOImpl c = new CategoryDAOImpl();
        List<Category> categorylist = c.getAllCategory();
        request.setAttribute("categorylist", categorylist);

        request.getRequestDispatcher("myReservation.jsp").forward(request, response);
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
        String sort = request.getParameter("sort");
        if (sort == null) { //did not sort
            String action = request.getParameter("action");
            if ((action != null) && (action.equalsIgnoreCase("delete"))) { // case update reservation status to rejected
                try {
                    int reservationId = Integer.parseInt(request.getParameter("reservationId"));

                    ReservationDAO reservationDAO = new ReservationDAOImpl();

                    Reservation reservation = reservationDAO.get(reservationId);
                    reservation.setStatus("rejected");

                    reservationDAO.update(reservation);
                } catch (NumberFormatException e) {
                    Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if ((action != null) && (action.equalsIgnoreCase("addToReservationList"))) { // case add reservation to database
                try {
                    HttpSession session = request.getSession();
                    User user = (User) session.getAttribute("user");

                    int bookId = Integer.parseInt(request.getParameter("bookId"));

                    String dateString = "" + java.time.LocalDate.now();
                    Date date = Date.valueOf(dateString);

                    BookDAO bookDAO = new BookDAOImpl();
                    ReservationDAO reservationDAO = new ReservationDAOImpl();

                    Reservation reservation = new Reservation();
                    reservation.setBook(bookDAO.getBookbyId(bookId));
                    reservation.setDate(date);
                    reservation.setStatus("pending");
                    reservation.setUser_id(user.getId());

                    reservationDAO.insert(reservation);
                    response.setContentType("text/html;charset=UTF-8");
                    try ( PrintWriter out = response.getWriter()) {
                        out.println("<h1 style=\"color: blue;\">" + "added book to reservation in database" + "</h1>");
                        out.println("<a href=\"book\">Back to book list</a>");
                    }
                } catch (NumberFormatException e) {
                    Logger.getLogger(AppointmentListCartController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            processRequest(request, response);
        }

        int userId;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) { //case: cannot get user when end of login session
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // user logined and session did not end
            try {
                userId = user.getId();

                ReservationDAO reservationDAO = new ReservationDAOImpl();

                //get reservation list for user and book by reservationId and userId
                List<Reservation> lsReservation = reservationDAO.getAllReservationByUserIdAndStatus(userId, "pending");
                if (sort.equalsIgnoreCase("lastest")) {
                    Collections.sort(lsReservation);
                } else {
                    Collections.sort(lsReservation, Collections.reverseOrder());
                }

                request.setAttribute("lsReservation", lsReservation);
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }

        CategoryDAOImpl c = new CategoryDAOImpl();
        List<Category> categorylist = c.getAllCategory();

        request.setAttribute("categorylist", categorylist);

        request.getRequestDispatcher("myReservation.jsp").forward(request, response);
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
        String[] lsCategoryName = request.getParameterValues("category"); //List name of category use to filter appointment list

        CategoryDAOImpl c = new CategoryDAOImpl();
        List<Category> categorylist = c.getAllCategory();
        request.setAttribute("categorylist", categorylist);

        int userId;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) { //case: cannot get user when end of login session
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // user logined and session did not end
            try {
                userId = user.getId();

                ReservationDAO reservationDAO = new ReservationDAOImpl();

                //get Reservation list for user and book by userId and status
                List<Reservation> lsReservation = reservationDAO.getAllReservationByUserIdAndStatus(userId, "pending");

                List<Reservation> filteredListReservationByCategory = filterListReservationByCategory(lsCategoryName, lsReservation);
                request.setAttribute("lsReservation", filteredListReservationByCategory);
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
        request.getRequestDispatcher("myReservation.jsp").forward(request, response);
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

    private List<Reservation> filterListReservationByCategory(String[] lsCategoryName, List<Reservation> lsReservation) {
        List<Reservation> listFilteredReservation = new ArrayList<>();

        if (lsCategoryName[0].equalsIgnoreCase("allBook")) { // case user get all type of category
            return lsReservation;
        }
        //loop all lsReservation to find reservation have category match with category in lsCategoryName
        for (Reservation reservation : lsReservation) {
            for (String categoryName : lsCategoryName) {
                //get categoryId to filter in lsCategoryName
                if (reservation.getBook().getCategory_id().getName().equalsIgnoreCase(categoryName)) {
                    //add reservation have category name match with category name in lsCategoryName
                    listFilteredReservation.add(reservation);
                }
            }
        }

        return listFilteredReservation;
    }

}
