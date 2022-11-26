/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AppointmentDAO;
import dao.BookDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.BookDAOImpl;
import dao.impl.CategoryDAOImpl;
import entity.Appointment;
import entity.Book;
import entity.Category;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AppointmentListCartController extends HttpServlet {

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
        } else {
            // user logined and session did not end
            try {
                List<Integer> listBookIdForAppointmentListCart = (List<Integer>) session.getAttribute("listBookIdForAppointmentListCart");
                List<Book> listBook = new ArrayList<>();
                List<Appointment> listAppointment = new ArrayList<>();

                if (listBookIdForAppointmentListCart == null) { // fix errer cause by null value
                    listBookIdForAppointmentListCart = new ArrayList<>();
                }
                for (Integer bookId : listBookIdForAppointmentListCart) {
                    BookDAO bookDAO = new BookDAOImpl();
                    Book book = bookDAO.getBookbyId(bookId);

                    String dateString = "" + java.time.LocalDate.now();
                    Date date = Date.valueOf(dateString);

                    Appointment appointment = new Appointment();
                    appointment.setBook(book);
                    appointment.setDate(date);
                    appointment.setStatus("pending");
                    appointment.setUser_id(user.getId());

                    listAppointment.add(appointment);
                }

                request.setAttribute("lsAppointment", listAppointment);
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }

        CategoryDAOImpl c = new CategoryDAOImpl();
        List<Category> categorylist = c.getAllCategory();
        request.setAttribute("categorylist", categorylist);

        request.getRequestDispatcher("appointmentListCart.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        if ((action != null) && action.equalsIgnoreCase("addToCart")) {
            //add bookId to list for appointment cart in sesstion
            addBookIdToAppointmentListCartInSession(request, response);
        } else if ((action != null) && action.equalsIgnoreCase("delete")) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            deleteItemFromAppointmentListCartByBookId(bookId, request, response);
            processRequest(request, response);
        } else {
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
        String action = request.getParameter("action");
        if ((action != null) && action.equalsIgnoreCase("filterByCategory")) {
            List<Appointment> listAppointmentFilterByCategory = listFilterAppointmentListCartByCategory(request, response);
            if (listAppointmentFilterByCategory != null) {
                request.setAttribute("lsAppointment", listAppointmentFilterByCategory);

                CategoryDAOImpl c = new CategoryDAOImpl();
                List<Category> categorylist = c.getAllCategory();
                request.setAttribute("categorylist", categorylist);

                request.getRequestDispatcher("appointmentListCart.jsp").forward(request, response);
            }
        }
        if ((action != null) && action.equalsIgnoreCase("addAppointmentToDataBase")) {
            
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                if (isAddAppointmentsToDataBase(request, response)) {
                    out.println("<h1 style=\"color: blue;\">" + "added appointment to database" + "</h1>");
                    out.println("<a href=\"book\">Back to book list</a>");
                } else {
                    out.println("<h1 style=\"color: red;\">" + "Fail to add appointment to database" + "</h1>");
                    out.println("<a href=\"book\">Back to book list</a>");
                    
                }
            }
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

    private boolean isAddBookIdToAppointmentCartInSession(int bookId, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        //get list of book id of appointmentListCart from session
        List<Integer> listBookIdForAppointmentListCart = (List<Integer>) session.getAttribute("listBookIdForAppointmentListCart");

        if (listBookIdForAppointmentListCart == null) { // fix error cause by list null for for loop
            listBookIdForAppointmentListCart = new ArrayList<>();
        }
        for (Integer id : listBookIdForAppointmentListCart) {
            if (id == bookId) { // bookId existed in list
                session.setAttribute("listBookIdForAppointmentListCart", listBookIdForAppointmentListCart);
                return false;
            }
        }
        listBookIdForAppointmentListCart.add(bookId); //add after book id did not exist in list
        session.setAttribute("listBookIdForAppointmentListCart", listBookIdForAppointmentListCart);
        return true;
    }

    private void addBookIdToAppointmentListCartInSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                int bookId = Integer.parseInt(request.getParameter("bookId"));

                BookDAO bookDAO = new BookDAOImpl();
                Book book = bookDAO.getBookbyId(bookId);

                if (isAddBookIdToAppointmentCartInSession(bookId, request, response)) {
                    out.println("<h1 style=\"color: blue;\">" + "added " + book.getTitle() + " book to appointment cart" + "</h1>");
                    out.println("<a href=\"book\">Back to book list</a>");
                } else {
                    out.println("<h1 style=\"color: red;\">" + book.getTitle() + "book is already exist in appointment cart" + "</h1>");
                    out.println("<a href=\"book\">Back to book list</a>");
                }
            } catch (NumberFormatException e) {
                out.println("<h1 style=\"color: red;\">" + "cannot parse bookId to integer" + "</h1>");
                out.println("<a href=\"book\">Back to book list</a>");
            }
        }
    }

    private List<Appointment> listFilterAppointmentListCartByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] lsCategoryName = request.getParameterValues("category"); //List name of category use to filter appointment list

        HttpSession session = request.getSession();
        List<Integer> listBookIdForAppointmentListCart = (List<Integer>) session.getAttribute("listBookIdForAppointmentListCart");

        List<Appointment> listAppointmentFiltered = new ArrayList<>();

        if (lsCategoryName[0].equalsIgnoreCase("allBook")) { // case user get all type of category
            processRequest(request, response);
        } else {
            for (Integer bookId : listBookIdForAppointmentListCart) {
                BookDAO bookDAO = new BookDAOImpl();
                Book book = bookDAO.getBookbyId(bookId);

                for (String categoryName : lsCategoryName) {
                    if (book.getCategory_id().getName().equalsIgnoreCase(categoryName)) {
                        //categoryName of book match with category name use to filter
                        String dateString = "" + java.time.LocalDate.now();
                        Date date = Date.valueOf(dateString);

                        Appointment appointment = new Appointment();
                        appointment.setBook(book);
                        appointment.setDate(date);
                        appointment.setStatus("pending");

                        listAppointmentFiltered.add(appointment);
                    }
                }
            }
            return listAppointmentFiltered;
        }
        return null;
    }

    private void deleteItemFromAppointmentListCartByBookId(int bookId, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Integer> listBookIdForAppointmentListCart = (List<Integer>) session.getAttribute("listBookIdForAppointmentListCart");
        List<Integer> listAfterDelete = new ArrayList<>();
        for (Integer id : listBookIdForAppointmentListCart) {
            if (!(id == bookId)) {
                listAfterDelete.add(id);
            }
        }
        session.setAttribute("listBookIdForAppointmentListCart", listAfterDelete);
    }

    private boolean isAddAppointmentsToDataBase(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            int amountOfAppointment = Integer.parseInt(request.getParameter("amountOfAppointment"));
            for (int i = 1; i <= amountOfAppointment; i++) {
                int bookId = Integer.parseInt(request.getParameter("bookId" + i));
                Date date = Date.valueOf(request.getParameter("dateToGetBook" + i));

                User user = (User) session.getAttribute("user");

                if (user != null) {
                    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
                    BookDAO bookDAO = new BookDAOImpl();

                    Appointment appointment = new Appointment();
                    appointment.setBook(bookDAO.getBookbyId(bookId));
                    appointment.setDate(date);
                    appointment.setStatus("pending");
                    appointment.setUser_id(user.getId());

                    appointmentDAO.insert(appointment);
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            }
            List<Integer> lsEmpty = new ArrayList<>();
            session.setAttribute("listBookIdForAppointmentListCart", lsEmpty);
            return true;
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(AppointmentListCartController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
