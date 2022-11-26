package controller;

import entity.Category;

import dao.impl.CategoryDAOImpl;
import dao.impl.UserDAOImpl;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/account"})
public class AccountController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
            List<User> userList;
            int perPage = 6;
            
            List<Category> categorylist = categoryDAOImpl.getAllCategory();
            request.setAttribute("categorylist", categorylist);

            // view loan detail
            if (request.getParameter("id") != null) {
                
                int userID = 0;
                try {
                    userID = Integer.parseInt(request.getParameter("id"));
                } catch (Exception e) {
                    request.setAttribute("mess", "User ID param must be number");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                
                User user = userDAO.getByID(userID);
                request.setAttribute("account", user);
                request.getRequestDispatcher("accountDetail.jsp").forward(request, response);
            }

            // view borrowing list
            int totalUser = userDAO.countUser();
            int maxIndex = totalUser % 6 == 0 ? totalUser / 6 : (totalUser / 6) + 1;
            
            String indexParam = request.getParameter("index");
            int index = 1;
            if (indexParam != null) {
                try {
                    index = Integer.parseInt(indexParam);
                } catch (NumberFormatException e) {
                    index = 1;
                }
            }
            
            userList = userDAO.getPaggedUsers(index, perPage);
            
            request.setAttribute("index", index);
            request.setAttribute("maxIndex", maxIndex);
            request.setAttribute("accounts", userList);
            request.getRequestDispatcher("accounts.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mess", "Internal Server Error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
}
