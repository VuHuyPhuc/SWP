/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AppointmentDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.CategoryDAOImpl;
import entity.Appointment;
import entity.Book;
import entity.Category;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AppointmentListController extends HttpServlet {

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
        User user = (User) session.getAttribute("user");

        if (user == null) { //case: cannot get user when end of login session
            response.sendRedirect(request.getContextPath() + "/login");
        } else if ((user.getRole().equalsIgnoreCase("librarian")) //case: user is allow to get in with suitable account role
                || (user.getRole().equalsIgnoreCase("admin"))) {
            AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

            List<Appointment> appointments = appointmentDAO.getAll();//this list will contain all appointment of all user

            CategoryDAOImpl c = new CategoryDAOImpl();
            List<Category> categorylist = c.getAllCategory();
            request.setAttribute("categorylist", categorylist);

            request.setAttribute("listAppointment", appointments);
            request.getRequestDispatcher("appointmentList.jsp").forward(request, response);
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
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        Appointment appointment = new Appointment(); // appointment will get hold info for add or edit action
        String action = request.getParameter("action");
        if ((action != null) && (action.equalsIgnoreCase("delete"))) { // case update status appointment to rejected with action delete
            try ( PrintWriter out = response.getWriter()) {
                if (IsDeletedAppointment(appointment, appointmentDAO, request, response)) {
                    out.println("<h1 style=\"color: blue;\">" + "Successed to delete appointment" + "</h1>");
                    out.println("<a href=\"appointmentList\">Back to appointment list</a>");
                } else {
                    out.println("<h1 style=\"color: red;\">" + "Failed to delete appointment" + "</h1>");
                    out.println("<a href=\"appointmentList\">Back to appointment list</a>");
                }
            }
        } else { //action null
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
        response.setContentType("text/html;charset=UTF-8");
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        Appointment appointment = new Appointment(); // appointment will get hold info for add or edit action
        String action = request.getParameter("action");
        if ((action != null) && (action.equalsIgnoreCase("add"))) { // case: action for add appointment
            try ( PrintWriter out = response.getWriter()) {
                if (IsAddedAppointment(appointment, appointmentDAO, request, response)) {
                    out.println("<h1 style=\"color: blue;\">" + "Successed to add new appointment" + "</h1>");
                    out.println("<a href=\"appointmentList\">Back to appointment list</a>");
                } else {
                    out.println("<h1 style=\"color: red;\">" + "Failed to add new appointment. BookId or MemberId did not exist" + "</h1>");
                    out.println("<a href=\"appointmentList\">Back to appointment list</a>");
                }
            }
        } else if ((action != null) && (action.equalsIgnoreCase("edit"))) { // case: edit for appointment
            try ( PrintWriter out = response.getWriter()) {
                if (IsEditedAppointment(appointment, appointmentDAO, request, response)) {
                    out.println("<h1 style=\"color: blue;\">" + "Successed to edit appointment" + "</h1>");
                    out.println("<a href=\"appointmentList\">Back to appointment list</a>");
                } else {
                    out.println("<h1 style=\"color: red;\">" + "Failed to edit appointment. BookId or MemberId did not exist" + "</h1>");
                    out.println("<a href=\"appointmentList\">Back to appointment list</a>");
                }
            }
        } else if ((action != null) && (action.equalsIgnoreCase("search"))) { // case: search for appointment
            List<Appointment> listAfterSearch = listAppointmentSearchByTitleUserCategoryDateStatus(appointmentDAO, request, response);

            CategoryDAOImpl c = new CategoryDAOImpl();
            List<Category> categorylist = c.getAllCategory();
            request.setAttribute("categorylist", categorylist);

            request.setAttribute("listAppointment", listAfterSearch);
            request.getRequestDispatcher("appointmentList.jsp").forward(request, response);
        } else { // action null
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

    private boolean IsAddedAppointment(Appointment appointment, AppointmentDAO appointmentDAO, HttpServletRequest request, HttpServletResponse response) {
        try {
            int bookId = Integer.parseInt(request.getParameter("bookId")); // id of book in appointment
            int userId = Integer.parseInt(request.getParameter("userId")); // id of user in appointment
            Date dateToGet = Date.valueOf(request.getParameter("dateToGet")); //  date to get book in appointment
            String status = request.getParameter("status"); // status for appointment

            Book book = new Book();
            book.setId(bookId);

            appointment = new Appointment(0, userId, dateToGet, status, book);

            return (appointmentDAO.insert(appointment) == 1); //return true if added success false if did not
        } catch (NumberFormatException e) {
            Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private boolean IsEditedAppointment(Appointment appointment, AppointmentDAO appointmentDAO, HttpServletRequest request, HttpServletResponse response) {
        try {
            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
            int bookId = Integer.parseInt(request.getParameter("bookId")); // id of book in appointment
            int userId = Integer.parseInt(request.getParameter("userId")); // id of user in appointment
            Date dateToGet = Date.valueOf(request.getParameter("dateToGet")); //  date to get book in appointment
            String status = request.getParameter("status"); // status for appointment

            Book book = new Book();
            book.setId(bookId);

            appointment = new Appointment(appointmentId, userId, dateToGet, status, book);

            return (appointmentDAO.update(appointment) == 1); //return true if edited success false if did not
        } catch (NumberFormatException e) {
            Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    //chang status of appointment to rejected so user can not see
    private boolean IsDeletedAppointment(Appointment appointment, AppointmentDAO appointmentDAO, HttpServletRequest request, HttpServletResponse response) {
        try {
            int appoitmentId = Integer.parseInt(request.getParameter("appointmentId"));

            appointment = appointmentDAO.get(appoitmentId);
            appointment.setStatus("rejected");

            return (appointmentDAO.update(appointment) == 1); //return true if edited success false if did not
        } catch (NumberFormatException e) {
            Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private List<Appointment> listAppointmentSearchByTitleUserCategoryDateStatus(AppointmentDAO appointmentDAO, HttpServletRequest request, HttpServletResponse response) {
        String bookTitle = request.getParameter("bookTitle");
        String userFullname = request.getParameter("userFullname");
        String category = request.getParameter("categoryId"); //get value all or categoryId
        Date dateFrom = Date.valueOf(request.getParameter("dateFrom"));
        Date dateTo = Date.valueOf(request.getParameter("dateTo"));
        String status = request.getParameter("status"); // get value all or pending, rejected or completed

        List<Appointment> listAppointment = appointmentDAO.getAll();
        List<Appointment> listAppointmentFiltered = listAppointment;

        if (!bookTitle.trim().isEmpty()) {
            listAppointmentFiltered = listAppointmentFilterByTitle(listAppointmentFiltered, bookTitle);
        }
        if (!userFullname.trim().isEmpty()) {
            listAppointmentFiltered = listAppointmentFilterByUserFullname(listAppointmentFiltered, userFullname);
        }
        if (!category.trim().equalsIgnoreCase("all")) {
            listAppointmentFiltered = listAppointmentFilterByCategory(listAppointmentFiltered, category);
        }
        if (!status.trim().equalsIgnoreCase("all")) {
            listAppointmentFiltered = listAppointmentFilterByStatus(listAppointmentFiltered, status);
        }
        listAppointmentFiltered = listAppointmentFilterByDate(listAppointmentFiltered, dateFrom, dateTo);

        return listAppointmentFiltered;
    }

    private List<Appointment> listAppointmentFilterByTitle(List<Appointment> listAppointment, String bookTitle) {
        List<Appointment> listFilterd = new ArrayList<>();
        for (Appointment appointment : listAppointment) {
            if (appointment.getBook().getTitle().toLowerCase().contains(bookTitle.toLowerCase())) {
                listFilterd.add(appointment);
            }
        }
        return listFilterd;
    }

    private List<Appointment> listAppointmentFilterByUserFullname(List<Appointment> listAppointment, String userFullname) {
        List<Appointment> listFilterd = new ArrayList<>();
        for (Appointment appointment : listAppointment) {
            if (appointment.getUser_fullname().toLowerCase().contains(userFullname.toLowerCase())) {
                listFilterd.add(appointment);
            }
        }
        return listFilterd;
    }

    private List<Appointment> listAppointmentFilterByCategory(List<Appointment> listAppointment, String category) {
        List<Appointment> listFilterd = new ArrayList<>();
        int categoryId = Integer.parseInt(category);
        for (Appointment appointment : listAppointment) {
            if (appointment.getBook().getCategory_id().getId() == categoryId) {
                listFilterd.add(appointment);
            }
        }
        return listFilterd;
    }

    private List<Appointment> listAppointmentFilterByStatus(List<Appointment> listAppointment, String status) {
        List<Appointment> listFilterd = new ArrayList<>();
        for (Appointment appointment : listAppointment) {
            if (appointment.getStatus().equalsIgnoreCase(status)) {
                listFilterd.add(appointment);
            }
        }
        return listFilterd;
    }

    private List<Appointment> listAppointmentFilterByDate(List<Appointment> listAppointment, Date dateFrom, Date dateTo) {
        List<Appointment> listFilterd = new ArrayList<>();
        for (Appointment appointment : listAppointment) {
            if ((appointment.getDate().after(dateFrom)) && (appointment.getDate().before(dateTo))) {
                listFilterd.add(appointment);
            }
        }
        return listFilterd;
    }
}
