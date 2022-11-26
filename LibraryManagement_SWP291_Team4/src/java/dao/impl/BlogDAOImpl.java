// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao.impl;

import dao.BlogDAO;
import dao.DBContext;
import entity.Blog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlogDAOImpl extends DBContext implements BlogDAO {

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        String sql = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            sql = "select * from Blog";
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("blog_id"), rs.getString("title"), rs.getString("detail"), rs.getString("author"), rs.getString("image"), rs.getInt("user_id"), rs.getBoolean("status")));
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            closeConnection(connection, st, rs);
        }
        return list;
    }

    @Override
    public Blog getBlogbyId(int id) {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from Blog where blog_id=?";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Blog b = new Blog(rs.getInt("blog_id"), rs.getString("title"), rs.getString("detail"), rs.getString("author"), rs.getString("image"), rs.getInt("user_id"), rs.getBoolean("status"));
                return b;
            }
            closeConnection(connection, st, rs);
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            closeConnection(connection, st, rs);
        }
        return null;
    }

    @Override
    public Blog get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Blog> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Blog t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Blog t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<Blog> pagingBlog(int index) {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = ("select * from Blog order by blog_id  OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY");
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 3);
            rs = st.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(rs.getInt("blog_id"), rs.getString("title"), rs.getString("detail"), rs.getString("author"), rs.getString("image"), rs.getInt("user_id"), rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            closeConnection(connection, st, rs);
        }
        return blogs;
    }
    @Override
    public int getNumberPage() {
                int count = 0;

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select COUNT(*) from Blog";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            closeConnection(connection, st, rs);
        }
        return count;
    }

    
}
