// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com

package controller;

import dao.BlogDAO;
import dao.impl.BlogDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.Blog;
import java.util.List;


@WebServlet(name="BlogController", urlPatterns={"/blog"})
public class BlogController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        BlogDAO blogDAO = new BlogDAOImpl();
        int count = blogDAO.getNumberPage();
                int page = count % 3 == 0 ? count / 3 : (count / 3) + 1;

        String indexPage = request.getParameter("index");
        if(indexPage == null){
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        List<Blog> blogs = blogDAO.pagingBlog(index);
        request.setAttribute("list", blogs);
        request.setAttribute("numberPage", count);
                request.setAttribute("numpage", page);
                request.setAttribute("index", index);

        request.getRequestDispatcher("blog.jsp").forward(request, response);
        
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
