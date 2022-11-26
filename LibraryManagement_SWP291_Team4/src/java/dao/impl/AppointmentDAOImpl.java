// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao.impl;

import dao.AppointmentDAO;
import dao.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Appointment;
import entity.Book;
import entity.Category;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AppointmentDAOImpl extends DBContext implements AppointmentDAO {

    @Override
    public List<Appointment> getAllAppointmentByUserIdAndStatus(int userId, String status) {
        List<Appointment> listAppointment = new ArrayList<>();

        String sql = "SELECT a.appointment_id,"
                + " a.date,"
                + " a.status,"
                + " a.user_id,"
                + " b.*, "
                + " c.category_name "
                + " FROM [Appointment] AS a JOIN [Book] AS b ON a.book_id = b.book_id "
                + " JOIN [Category] AS c ON c.category_id = b.category_id "
                + " WHERE a.user_id = ? "
                + " AND a.status like ? ";
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);

            st.setInt(1, userId);
            st.setString(2, "%" + status + "%");

            rs = st.executeQuery();
            while (rs.next()) {
                int appointment_id = rs.getInt("appointment_id");
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

                listAppointment.add(new Appointment(appointment_id, user_id, date, status, book));
            }
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return listAppointment;
    }

    @Override
    public Appointment getAppointmentDetailByAppointmentIdAndUserIdAndStatus(int appointmentId, int userId, String status) {
        Appointment appointment = new Appointment();

        String sql = "SELECT a.appointment_id, "
                + " a.date, "
                + " a.status, "
                + " a.user_id, "
                + " b.*, "
                + " c.category_name "
                + " FROM [Appointment] AS a JOIN [Book] AS b ON a.book_id=b.book_id "
                + " JOIN [Category] AS c ON c.category_id = b.category_id "
                + " WHERE a.user_id = ? "
                + " AND a.status like ? "
                + " AND a.appointment_id = ? ";

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setString(2, "%" + status + "%");
            st.setInt(3, appointmentId);

            rs = st.executeQuery();
            if (rs.next()) {
                int appointment_id = rs.getInt("appointment_id");
                int user_id = rs.getInt("user_id");//userId
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

                appointment = new Appointment(appointment_id, user_id, date, status, book);
            }
        } catch (SQLException e) {
            //cho exception ra ben ngoai sevlet
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return appointment;
    }

    @Override
    public Appointment get(int appointmentId) {
        Appointment appointment = new Appointment();

        String sql = "SELECT [book_id], "
                + "[user_id], "
                + "[date], "
                + "[status] "
                + "FROM [Appointment] "
                + "WHERE appointment_id = ?";

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, appointmentId);

            rs = st.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt("user_id");
                Date date = rs.getDate("date");
                String status = rs.getString("status");

                Book book = new Book();
                book.setId(rs.getInt("book_id"));

                appointment = new Appointment(appointmentId, user_id, date, status, book);
            }
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAll() {
        List<Appointment> listAppointment = new ArrayList<>();

        String sql = "SELECT a.appointment_id, "
                + "a.date, "
                + "a.status, "
                + "a.user_id, "
                + "u.fullname, "
                + "b.*, "
                + " c.category_name "
                + "FROM [Appointment] AS a JOIN [Book] AS b ON a.book_id=b.book_id "
                + " JOIN [Category] AS c ON c.category_id = b.category_id "
                + "JOIN [User] AS u ON a.user_id = u.user_id";
        Connection connection = getConnection();

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                int appointment_id = rs.getInt("appointment_id");
                Date date = rs.getDate("date");
                String status = rs.getString("status");
                int user_id = rs.getInt("user_id");
                String user_fullname = rs.getString("fullname");

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

                listAppointment.add(new Appointment(appointment_id, user_id, user_fullname, date, status, book));
            }
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return listAppointment;
    }

    @Override
    public int insert(Appointment appointment) {
        String sql = " INSERT INTO [Appointment] "
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
            st.setInt(1, appointment.getBook().getId());
            st.setInt(2, appointment.getUser_id());
            st.setDate(3, appointment.getDate());
            st.setString(4, appointment.getStatus());

            if (st.executeUpdate() == 0) {
                throw new SQLException("Did not add new appointment to database");
            }
        } catch (SQLException e) {
            //cho exception ra ben ngoai sevlet
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0; // add failed case
        } finally {
            closeConnection(connection, st, rs);
        }
        return 1; //add success case
    }

    @Override
    public int update(Appointment appointment) {
        String sql = " UPDATE [Appointment] "
                + "SET [book_id] = ?, "
                + "[user_id] = ?, "
                + "[date] = ?, "
                + "[status] = ? "
                + "WHERE appointment_id = ?";

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, appointment.getBook().getId());
            st.setInt(2, appointment.getUser_id());
            st.setDate(3, appointment.getDate());
            st.setString(4, appointment.getStatus());
            st.setInt(5, appointment.getAppointment_id());

            if (st.executeUpdate() == 0) {
                throw new SQLException("Did not add new appointment to database");
            }
        } catch (SQLException e) {
            //cho exception ra ben ngoai sevlet
            Logger.getLogger(AppointmentDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0; //edit fail case
        } finally {
            closeConnection(connection, st, rs);
        }
        return 1; //edit success case
    }
}
