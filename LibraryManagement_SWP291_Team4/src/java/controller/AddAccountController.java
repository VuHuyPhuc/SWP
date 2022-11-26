package controller;

import dao.impl.UserDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/add-account"})
public class AddAccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addAccount.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAOImpl d = new UserDAOImpl();
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String email = request.getParameter("email");
            
            if(fullname.trim().equals("")){
                request.setAttribute("mess", "Please input full name");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
            if(username.trim().equals("")){
                request.setAttribute("mess", "Please input username");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
            if(password.trim().equals("")){
                request.setAttribute("mess", "Please input password");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
            if(email.trim().equals("")){
                request.setAttribute("mess", "Please input email");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
            // check email
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);
            if(!m.matches()){
                request.setAttribute("mess", "Invalid email");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
            if (d.getByUsername(username) != null) {
                request.setAttribute("mess", "Username existed");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
            if(d.getByEmail(email)!=null){
                request.setAttribute("mess", "Email existed");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }

            d.register(fullname, username, password, email, role, true);
            request.setAttribute("mess", "success");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mess", "Internal server error");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
        }
    }

}
