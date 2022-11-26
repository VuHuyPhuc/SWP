/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.BookDAO;
import dao.LoanDAO;
import dao.UserDAO;
import dao.impl.BookDAOImpl;
import dao.impl.LoanDAOImpl;
import dao.impl.UserDAOImpl;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="DashboardController", urlPatterns={"/dashboard"})
public class DashboardController extends HttpServlet {
   
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        LoanDAO loanDAO = new LoanDAOImpl();
        UserDAO userDAO = new UserDAOImpl();
        int countuser = userDAO.countUser();
        int[]arr = loanDAO.countBorrowPerMonth();
        BookDAO bookDAO = new BookDAOImpl();
        int countBook = bookDAO.countBook();
        int countBorrow = loanDAO.countBorrowedToday();
        request.setAttribute("countborrowing", countBorrow);
        request.setAttribute("countuser", countuser);
        int countReturned = loanDAO.countReturnedToday();
        request.setAttribute("countreturn", countReturned);
        request.setAttribute("countbook", countBook);
        request.setAttribute("arraydashboard", arr);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
   

}
