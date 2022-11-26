/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AppointmentDAO;
import dao.BookReviewDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.BookReviewDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.Appointment;
import entity.Book_Review;
import entity.User;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MyAppoinmentDetailController extends HttpServlet {

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
        try {
            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

            int userId;

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null) { //case: cannot get user when end of login session
                response.sendRedirect(request.getContextPath() + "/login");
            } else { // user logined and session did not end
                try {
                    userId = user.getId();

                    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

                    //get appointmetn detail for user and book by appointmentId
                    Appointment appointment = appointmentDAO.getAppointmentDetailByAppointmentIdAndUserIdAndStatus(appointmentId, userId, "pending");
                    
                    BookReviewDAO bookReviewDAO = new BookReviewDAOImpl();
                    List<Book_Review> listBookReview = bookReviewDAO.getAllBookReviewByBookId(appointment.getBook().getId());
                    
                    request.setAttribute("listBookReview", listBookReview);
                    request.setAttribute("appointmentDetail", appointment);
                } catch (Exception e) {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            }
            request.getRequestDispatcher("myAppointmentDetail.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(MyAppoinmentDetailController.class.getName()).log(Level.SEVERE, null, e);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
