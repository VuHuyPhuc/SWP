/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.DBContext;
import dao.ReservationDAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Book;
import entity.Category;
import entity.Reservation;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ReservationDAOImpl extends DBContext implements ReservationDAO {

    @Override
    public List<Reservation> getAllReservationByUserIdAndStatus(int userId, String status) {

        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT r.reservation_id,"
                + " r.date,"
                + " r.status,"
                + " r.user_id,"
                + " b.*, "
                + " c.category_name "
                + " FROM [dbo].[Reservation] AS r JOIN [Book] AS b ON r.book_id=b.book_id "
                + " JOIN [Category] AS c ON c.category_id = b.category_id "
                + " WHERE r.user_id = ? "
                + " AND r.status like ?";
        try {
            Connection connection = getConnection();

            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, userId);
            st.setString(2, "%" + status + "%");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int reservation_id = rs.getInt("reservation_id");
                int user_id = rs.getInt("user_id");
                Date date = rs.getDate("date");

                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublication_year(rs.getDate("publication_date"));
                book.setQuantity(rs.getInt("quantity"));
                book.setImage(rs.getString("image"));
                book.setRate(rs.getInt("rate"));
                book.setStatus(rs.getBoolean("status"));
                book.setIntroduction(rs.getString("Introduction"));
                book.setDescription(rs.getString("Description"));
                
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                
                book.setCategory_id(category);

                list.add(new Reservation(reservation_id, user_id, date, status, book));
            }
            closeConnection(connection, st, rs);
        } catch (SQLException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public Reservation get(int reservationId) {
        Reservation reservation = new Reservation();

        String sql = "SELECT [book_id], "
                            + "[user_id], "
                            + "[date], "
                            + "[status] "
                            + "FROM [Reservation] "
                            + "WHERE reservation_id = ?";
        
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, reservationId);
            
            rs = st.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt("user_id");
                Date date = rs.getDate("date");
                String status = rs.getString("status");

                Book book = new Book();
                book.setId(rs.getInt("book_id"));

                reservation = new Reservation(reservationId, user_id, date, status, book);
            }
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> listReservation = new ArrayList<>();
        String sql = "SELECT r.reservation_id, "
                + " r.date, "
                + " r.status, "
                + " r.user_id, "
                + " u.fullname, "
                + " b.*, "
                + " c.category_name "
                + " FROM [dbo].[Reservation] AS r JOIN [Book] AS b ON r.book_id=b.book_id "
                + " JOIN [Category] AS c ON c.category_id = b.category_id "
                + " JOIN [User] AS u ON r.user_id=u.user_id ";
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                int reservation_id = rs.getInt("reservation_id");
                int user_id = rs.getInt("user_id");
                String user_fullname = rs.getString("fullname");
                Date date = rs.getDate("date");
                String status = rs.getString("status");

                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublication_year(rs.getDate("publication_date"));
                book.setQuantity(rs.getInt("quantity"));
                book.setImage(rs.getString("image"));
                book.setRate(rs.getInt("rate"));
                book.setStatus(rs.getBoolean("status"));
                book.setIntroduction(rs.getString("Introduction"));
                book.setDescription(rs.getString("Description"));
                
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                
                book.setCategory_id(category);

                listReservation.add(new Reservation(reservation_id, user_id, user_fullname, date, status, book));
            }
            closeConnection(connection, st, rs);
        } catch (SQLException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return listReservation;
    }

    @Override
    public int insert(Reservation reservation) {
        String sql = " INSERT INTO [Reservation] "
                + "([book_id], "
                + "[user_id], "
                + "[date], "
                + "[status])  "
                + "VALUES "
                + "(?, "
                + "?, "
                + "?, "
                + "?) ";
        
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, reservation.getBook().getId());
            st.setInt(2, reservation.getUser_id());
            st.setDate(3, reservation.getDate());
            st.setString(4, reservation.getStatus());
            
            if (st.executeUpdate() == 0) {
                throw new SQLException("Did not add new Reservation to database");
            }
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0; // add reservation failed case
        } finally {
            closeConnection(connection, st, rs);
        }
        return 1; //add reservation success case
    }

    @Override
    public int update(Reservation reservation) {
        String sql = " UPDATE [Reservation] "
                        + "SET [book_id] = ?, "
                        + "[user_id] = ?, "
                        + "[date] = ?, "
                        + "[status] = ? "
                    + "WHERE reservation_id = ?";
        
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, reservation.getBook().getId());
            st.setInt(2, reservation.getUser_id());
            st.setDate(3, reservation.getDate());
            st.setString(4, reservation.getStatus());
            st.setInt(5, reservation.getReservation_id());
            
            if (st.executeUpdate() == 0) {
                throw new SQLException("Did not add new Reservation to database");
            }
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0; //edit Reservation fail case
        } finally {
            closeConnection(connection, st, rs);
        }
        return 1; //edit Reservation success case
    }

    @Override
    public Reservation getReservaionDetailByReservationIdAndUserIdAndStatus(int reservationId, int userId, String status) {
        Reservation reservation = new Reservation();

        String sql = "SELECT r.reservation_id, "
                + " r.date, "
                + " r.status, "
                + " r.user_id, "
                + " b.*, "
                + " c.category_name "
                + " FROM [Reservation] AS r JOIN [Book] AS b ON r.book_id=b.book_id "
                + " JOIN [Category] AS c ON c.category_id = b.category_id "
                + " WHERE r.user_id = ? "
                + " AND r.status like ? "
                + " AND r.reservation_id = ? ";
        try {
            Connection connection = getConnection();

            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, userId);
            st.setString(2, "%" + status + "%");
            st.setInt(3, reservationId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int reservation_id = rs.getInt("reservation_id");
                int user_id = rs.getInt("user_id");
                Date date = rs.getDate("date");

                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublication_year(rs.getDate("publication_date"));
                book.setQuantity(rs.getInt("quantity"));
                book.setImage(rs.getString("image"));
                book.setRate(rs.getInt("rate"));
                book.setStatus(rs.getBoolean("status"));
                book.setIntroduction(rs.getString("Introduction"));
                book.setDescription(rs.getString("Description"));
                
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                
                book.setCategory_id(category);

                reservation = new Reservation(reservation_id, user_id, date, status, book);
            }
            closeConnection(connection, st, rs);
        } catch (SQLException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return reservation;
    }
}
