/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookReviewDAO;
import dao.impl.BookReviewDAOImpl;
import entity.Book;
import entity.Book_Review;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BookReviewController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookReviewController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookReviewController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String appointmentId = request.getParameter("appointmentId");
            String reservationId = request.getParameter("reservationId");
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Book book = new Book();
            book.setId(bookId);

            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = new User(userId, "", "", "", "", "", true);

            String dateString = "" + java.time.LocalDate.now();
            Date date = Date.valueOf(dateString);

            String comment = request.getParameter("comment").trim();
            if (!comment.isEmpty()) {
                Book_Review bookReview = new Book_Review();
                bookReview.setBook(book);
                bookReview.setUser(user);
                bookReview.setDate(date);
                bookReview.setRate(0);
                bookReview.setComment(comment);

                BookReviewDAO bookReviewDAO = new BookReviewDAOImpl();
                if (bookReviewDAO.insert(bookReview) == 1) { //insert successed
                        if (appointmentId != null) {
                            request.getRequestDispatcher("myAppoinmentDetail?appointmentId=" + appointmentId).forward(request, response);
                        }
                        if (reservationId != null) {
                            request.getRequestDispatcher("myReservationDetail?reservationId=" + reservationId).forward(request, response);
                        }
                } else { //insert fail
                    try ( PrintWriter out = response.getWriter()) {
                        out.println("<h1 style=\"color: red;\">" + "added review failed. You reviewed this book" + "</h1>");
                        if (appointmentId != null) {
                            out.println("<a href=\"myAppoinmentDetail?appointmentId=" + appointmentId+"\">Back to AppointmentDetail</a>");
                        }
                        if (reservationId != null) {
                            out.println("<a href=\"myReservationDetail?reservationId=" + reservationId+"\">Back to ReservationDetail</a>");
                        }
                    }
                }
            } else {
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<h1 style=\"color: red;\">" + "Can not comment with space" + "</h1>");
                    if (appointmentId != null) {
                        out.println("<a href=\"myAppoinmentDetail?appointmentId=" + appointmentId+"\">Back to AppointmentDetail</a>");
                    }
                    if (reservationId != null) {
                        out.println("<a href=\"myReservationDetail?reservationId=" + reservationId+"\">Back to ReservationDetail</a>");
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(BookReviewController.class.getName()).log(Level.SEVERE, null, e);
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

}
