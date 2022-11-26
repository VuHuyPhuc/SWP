/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.BookReviewDAO;
import dao.DBContext;
import entity.Appointment;
import entity.Book;
import entity.Book_Review;
import entity.Category;
import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BookReviewDAOImpl extends DBContext implements BookReviewDAO{

    @Override
    public Book_Review get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Book_Review> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Book_Review review) {
        String sql = " INSERT INTO [Book_Review] "
                                    + "([user_id], "
                                    + "[date], "
                                    + "[rate], "
                                    + "[comment], "
                                    + "[book_id]) "
                            + "VALUES "
                                    + "(?, "
                                    + "?, "
                                    + "?, "
                                    + "?, "
                                    + "?) ";

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, review.getUser().getId());
            st.setDate(2, review.getDate());
            st.setInt(3, review.getRate());
            st.setString(4, review.getComment());
            st.setInt(5, review.getBook().getId());

            if (st.executeUpdate() == 0) {
                throw new SQLException("Did not add new appointment to database");
            }
        } catch (SQLException e) {
            Logger.getLogger(BookReviewDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0; // add failed case
        } finally {
            closeConnection(connection, st, rs);
        }
        return 1; //add success case
    }

    @Override
    public int update(Book_Review t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Book_Review> getAllBookReviewByBookId(int bookId) {
        List<Book_Review> listBookReview = new ArrayList<>();

        String sql = "SELECT u.user_id, "
                + "u.fullname, "
                + "[date], "
                + "[rate], "
                + "[comment], "
                + "[book_id] "
                + "FROM [Book_Review] AS br JOIN [User] AS u "
                + "ON br.user_id = u.user_id "
                + "WHERE book_id = ?";
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);

            st.setInt(1, bookId);

            rs = st.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                Date date = rs.getDate("date");
                int rate = rs.getInt("rate");
                String comment = rs.getString("comment");

                Book book = new Book();
                book.setId(bookId);
                
                String userFullname = rs.getString("fullname");
                User user = new User(userId, userFullname, "", "", "", "", true);

                listBookReview.add(new Book_Review(user, date, rate, comment, book));
            }
        } catch (SQLException e) {
            Logger.getLogger(BookReviewDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return listBookReview;
    }
}
