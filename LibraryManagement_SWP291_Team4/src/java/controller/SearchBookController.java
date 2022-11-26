// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package controller;

import dao.impl.BookDAOImpl;
import dao.impl.CategoryDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author s
 */
@WebServlet(urlPatterns = {"/search-book"})
public class SearchBookController extends HttpServlet {

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
        try {
             String search = request.getParameter("search");
             response.sendRedirect(request.getContextPath()+"/book?search=" + search);
//            BookDAOImpl b = new BookDAOImpl();
//            CategoryDAOImpl c = new CategoryDAOImpl();
//
//            request.setAttribute("categorylist", c.getAllCategory());
//
//            int count = b.countBook();
//            int page = count % 6 == 0 ? count / 6 : (count / 6) + 1;
//            String indexRaw = request.getParameter("index");
//            int index = 1;
//            if (indexRaw != null) {
//                try {
//                    index = Integer.parseInt(indexRaw);
//                } catch (NumberFormatException e) {
//                    System.out.println(e);
//                }
//            }
//
//            String search = request.getParameter("search");
//            
//            if (search == null) {
//                request.setAttribute("booklist", b.getBookListWithAuthor(index));
//            } else {
//                request.setAttribute("booklist", b.getSearchedBookListWithAuthor(search));
//            }
//            
//            request.setAttribute("index", index);
//            System.out.println(index);
//            request.setAttribute("page", page);
//            request.getRequestDispatcher("books.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
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
