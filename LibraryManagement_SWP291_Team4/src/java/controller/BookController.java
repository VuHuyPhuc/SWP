// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package controller;

import entity.Category;
import entity.Book_Author;
import dao.impl.BookDAOImpl;
import dao.impl.CategoryDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s
 */
public class BookController extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAOImpl bookDAOImpl = new BookDAOImpl();
        CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
        List<Book_Author> booklist = new ArrayList<>();
        int perPage = 6;

        List<Category> categorylist = categoryDAOImpl.getAllCategory();
        request.setAttribute("categorylist", categorylist);

        // view list books searched
        if (request.getParameter("search") != null) {

            String search = request.getParameter("search");
            int count = bookDAOImpl.countBooksSearched(search);
            int page = count % perPage == 0 ? count / perPage : (count / perPage) + 1;
            String indexRaw = request.getParameter("index");
            int index = 1;
            if (indexRaw != null) {
                try {
                    index = Integer.parseInt(indexRaw);
                } catch (NumberFormatException e) {
                    index = 1;
                }
            }

            try {
                booklist = bookDAOImpl.getSearchedBookListWithAuthor(search, index, perPage);
            } catch (Exception e) {
                Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, e);
            }

            request.setAttribute("index", index);
            request.setAttribute("page", page);
            request.setAttribute("booklist", booklist);
            request.getRequestDispatcher("books.jsp").forward(request, response);
        }

        // view list books gotten by category
        if (request.getParameter("cid") != null) {

            int cid = Integer.parseInt(request.getParameter("cid"));
            int count = bookDAOImpl.countBookByCategory(cid);
            int page = count % perPage == 0 ? count / perPage : (count / perPage) + 1;
            String indexRaw = request.getParameter("index");
            int index = 1;
            if (indexRaw != null) {
                try {
                    index = Integer.parseInt(indexRaw);
                } catch (NumberFormatException e) {
                    index = 1;
                }
            }

            try {
                booklist = bookDAOImpl.getBookWithAuthorByCategory(cid, index, perPage);
            } catch (Exception e) {
            }
            request.setAttribute("cid", cid);
            request.setAttribute("index", index);
            request.setAttribute("page", page);
            request.setAttribute("booklist", booklist);
            request.getRequestDispatcher("books.jsp").forward(request, response);
        }
        
        // get books
        int count = bookDAOImpl.countBook();
        int page = count % 6 == 0 ? count / 6 : (count / 6) + 1;
        String indexRaw = request.getParameter("index");
        int index = 1;
        if (indexRaw != null) {
            try {
                index = Integer.parseInt(indexRaw);
            } catch (NumberFormatException e) {
                index = 1;
            }
        }

        booklist = bookDAOImpl.getBookListWithAuthor(index, perPage);

        request.setAttribute("index", index);
        request.setAttribute("page", page);
        request.setAttribute("booklist", booklist);
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAOImpl bookDAOImpl = new BookDAOImpl();
        CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
        List<Book_Author> booklist = new ArrayList<>();
        int perPage = 6;

        List<Category> categorylist = categoryDAOImpl.getAllCategory();
        request.setAttribute("categorylist", categorylist);

        // view list books searched
        if (request.getParameter("search") != null) {

            String search = request.getParameter("search");
            int count = bookDAOImpl.countBooksSearched(search);
            int page = count % perPage == 0 ? count / perPage : (count / perPage) + 1;
            String indexRaw = request.getParameter("index");
            int index = 1;
            if (indexRaw != null) {
                try {
                    index = Integer.parseInt(indexRaw);
                } catch (NumberFormatException e) {
                    index = 1;
                }
            }

            try {
                booklist = bookDAOImpl.getSearchedBookListWithAuthor(search, index, perPage);
            } catch (Exception e) {
                Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, e);
            }

            request.setAttribute("index", index);
            request.setAttribute("page", page);
            request.setAttribute("booklist", booklist);
            request.getRequestDispatcher("books.jsp").forward(request, response);
        }

        // view list books gotten by category
        if (request.getParameter("cid") != null) {

            int cid = Integer.parseInt(request.getParameter("cid"));
            int count = bookDAOImpl.countBookByCategory(cid);
            int page = count % perPage == 0 ? count / perPage : (count / perPage) + 1;
            String indexRaw = request.getParameter("index");
            int index = 1;
            if (indexRaw != null) {
                try {
                    index = Integer.parseInt(indexRaw);
                } catch (NumberFormatException e) {
                    index = 1;
                }
            }

            try {
                booklist = bookDAOImpl.getBookWithAuthorByCategory(cid, index, perPage);
            } catch (Exception e) {
            }
            request.setAttribute("cid", cid);
            request.setAttribute("index", index);
            request.setAttribute("page", page);
            request.setAttribute("booklist", booklist);
            request.getRequestDispatcher("books.jsp").forward(request, response);
        }
        
        // get books
        int count = bookDAOImpl.countBook();
        int page = count % 6 == 0 ? count / 6 : (count / 6) + 1;
        String indexRaw = request.getParameter("index");
        int index = 1;
        if (indexRaw != null) {
            try {
                index = Integer.parseInt(indexRaw);
            } catch (NumberFormatException e) {
                index = 1;
            }
        }

        booklist = bookDAOImpl.getBookListWithAuthor(index, perPage);

        request.setAttribute("index", index);
        request.setAttribute("page", page);
        request.setAttribute("booklist", booklist);
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }

    
    

}
