package controller;

import entity.Category;

import dao.impl.CategoryDAOImpl;
import dao.impl.LoanDAOImpl;
import entity.Loan;
import entity.OverdueLoan;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/overdue-list"})
public class OverdueListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            LoanDAOImpl loanDAO = new LoanDAOImpl();
            CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
            List<OverdueLoan> loans;
            int perPage = 6;
            int finePerDay = 2000;

            List<Category> categorylist = categoryDAOImpl.getAllCategory();
            request.setAttribute("categorylist", categorylist);

            // view loan detail
            if (request.getParameter("id") != null) {

                int loanID = 0;
                try {
                    loanID = Integer.parseInt(request.getParameter("id"));
                } catch (Exception e) {
                    request.setAttribute("mess", "");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }

                OverdueLoan loan = loanDAO.getOverdueByID(loanID, finePerDay);
                request.setAttribute("loan", loan);
                request.getRequestDispatcher("overdueLoanDetail.jsp").forward(request, response);
            }

            // view overdue list
            int count = loanDAO.countOverdueLoan();
            int maxIndex = count % 6 == 0 ? count / 6 : (count / 6) + 1;
            
            if(maxIndex <= 0){
                maxIndex = 1;
            }

            String indexParam = request.getParameter("index");
            int index = 1;
            if (indexParam != null) {
                try {
                    index = Integer.parseInt(indexParam);
                } catch (NumberFormatException e) {
                    index = 1;
                }
            }

            loans = loanDAO.getOverdueList(index, perPage, 2000);

            request.setAttribute("index", index);
            request.setAttribute("maxIndex", maxIndex);
            request.setAttribute("loans", loans);
            request.getRequestDispatcher("overdueList.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mess", "Internal Server Error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
