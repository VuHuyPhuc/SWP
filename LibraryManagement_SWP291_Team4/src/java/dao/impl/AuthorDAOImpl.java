/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.AuthorDAO;
import dao.DBContext;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.Author;
import java.sql.Connection;
import java.util.List;

public class AuthorDAOImpl extends DBContext implements AuthorDAO {

    @Override
    public List<Author> getAllAuthor() {

        List<Author> list = new ArrayList<>();
        String sql = "select * from Author";
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Author(rs.getInt("author_id"), rs.getString("fullname"), rs.getString("image"), rs.getString("infomation"), rs.getString("brief_infomaton")));
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            closeConnection(connection, st, rs);
        }
        return list;

    }

    @Override
    public Author getAuthorById(int id) {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from Author where author_id=?";
        try {
            connection = getConnection();

            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Author b = new Author(rs.getInt("author_id"), rs.getString("fullname"), rs.getString("image"), rs.getString("infomation"), rs.getString("brief_infomaton"));
                return b;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return null;
    }

    @Override
    public Author getAuthorByBook(int bookid) {
        String sql = "select * from Book_Author where book_id=?";
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, bookid);
            rs = st.executeQuery();
            if (rs.next()) {
                Author b = getAuthorById(rs.getInt(2));
                return b;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return null;
    }

    @Override
    public Author get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Author> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Author t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Author t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        AuthorDAOImpl dao = new AuthorDAOImpl();
        System.out.println(dao.getAuthorById(1));
    }

    
    public ArrayList<Author> pagingAuthor(int index) {
        Connection connection=null;
        PreparedStatement st=null;
        ResultSet rs =null;
        
        ArrayList<Author> authors = new ArrayList<>();
        String sql = ("select * from Author order by author_id\n"
                + "                       OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY");
        try {
            connection = getConnection();
             st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 5);//lay 5 tac gia trong 1 trang
            rs = st.executeQuery();
            while (rs.next()) {
                authors.add(new Author(rs.getInt("author_id"), rs.getString("fullname"), rs.getString("image"), rs.getString("infomation"), rs.getString("brief_infomaton")));
            }
        } catch (Exception e) {
        }
        finally{
             closeConnection(connection, st, rs);
        }
        return authors;
    }

    
    public int getNumberPage() {
        Connection connection=null;
        PreparedStatement st =null;
        ResultSet rs=null;
        String sql = "select COUNT(*) from Author";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 5;
                if (total % 5 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }finally{
            closeConnection(connection, st, rs);
        }
        return 0;
    }
}
