package controller;

// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com

import dao.CategoryDAO;
import dao.impl.CategoryDAOImpl;
import dao.impl.LoanDAOImpl;
import entity.Category;
import entity.Loan;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author s
 */
@WebServlet(urlPatterns = {"/loan"})
public class LoanController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoanController() {
        super();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        try {
            LoanDAOImpl loanDAO = new LoanDAOImpl();
            CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
            List<Category> categories = categoryDAO.getAllCategory();
            request.setAttribute("categorylist", categories);

            if (request.getParameter("id") != null) {
                int loanID =  Integer.parseInt(request.getParameter("id"));
                Loan loan = loanDAO.getByID(loanID);
                request.setAttribute("loan", loan);
                request.getRequestDispatcher("loanDetail.jsp").forward(request, response);
            }
            User user = (User) request.getSession().getAttribute("user");
            List<Loan> loans = loanDAO.getByUserID(user.getId());
            request.setAttribute("loans", loans);
            request.getRequestDispatcher("loan.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
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
        doGet(request, response);
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
