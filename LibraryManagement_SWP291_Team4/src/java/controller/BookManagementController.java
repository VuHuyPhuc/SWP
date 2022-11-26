/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookDAO;
import dao.CategoryDAO;
import dao.impl.BookDAOImpl;
import dao.impl.CategoryDAOImpl;
import entity.Book;
import entity.Category;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@MultipartConfig
@WebServlet(name = "BookManagementController", urlPatterns = {"/bookmanagement"})
public class BookManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO bookDAO = new BookDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        List<Category> categoryList = categoryDAO.getAllCategory();
        List<Book> booklist = bookDAO.getAllBook();
        request.setAttribute("categorylist", categoryList);
        request.setAttribute("booklist", booklist);
        request.getRequestDispatcher("bookmanagement.jsp").forward(request, response);
    }

    //edit and add a book
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part part = request.getPart("newimage");
        String fileName = part.getSubmittedFileName();
        String path = getServletContext().getRealPath("/img/books"+File.separator+fileName);
        InputStream is = part.getInputStream();
        uploadFile(is, path);
        String bookId = request.getParameter("id");
        String bookTitle = request.getParameter("title");
        String bookCategory = request.getParameter("category");
        String bookPublisher = request.getParameter("publisher");
        String bookDate = request.getParameter("date");
        String bookQuantity = request.getParameter("quantity");
        String bookImage = "img/books/"+fileName;
        String bookRate = request.getParameter("rate");
        String bookStatus = request.getParameter("status").equals("1")?"true":"false";
        String bookIntroduction = request.getParameter("introduction");
        String bookDescription = request.getParameter("description");
        BookDAO bookDAO = new BookDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        try {
            int bookID = Integer.parseInt(bookId==null?"0":bookId);
            Category category = categoryDAO.get(Integer.parseInt(bookCategory));
            int quantity = Integer.parseInt(bookQuantity);
            boolean status = Boolean.valueOf(bookStatus);
            int rate = Integer.parseInt(bookRate);
            Date date = Date.valueOf(bookDate);
            Book book = new Book(bookID, bookTitle, category, bookPublisher, date, quantity, bookImage, rate, status, bookIntroduction, bookDescription);
            if(bookId!=null)           
            bookDAO.updateBook(book);
            else
                bookDAO.insert(book);
            response.sendRedirect("bookmanagement");
        } catch (NumberFormatException e) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, e);

        }

    }
    public void uploadFile(InputStream is, String path){     
        try {
            byte[] byt = new byte[is.available()];
            is.read(byt);
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
