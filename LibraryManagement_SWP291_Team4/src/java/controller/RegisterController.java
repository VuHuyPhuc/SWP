// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com

package controller;

import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import entity.User;

/**
 *
 * @author s
 */
public class RegisterController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if(username.trim().equals("")){
                request.setAttribute("mess", "Please input username");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if(password.trim().equals("")){
                request.setAttribute("mess", "Please input password");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if(email.trim().equals("")){
                request.setAttribute("mess", "Please input email");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            // check email
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);
            if(!m.matches()){
                request.setAttribute("mess", "Invalid email");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if (d.getByUsername(username) != null) {
                request.setAttribute("mess", "Username existed");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if(d.getByEmail(email)!=null){
                request.setAttribute("mess", "Email existed");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

            d.register(fullname, username, password, email, role, true);
            request.setAttribute("mess", "success");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mess", "Internal server error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
