// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao.impl;

import dao.DBContext;
import dao.UserDAO;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s
 */
public class UserDAOImpl extends DBContext implements UserDAO {

    @Override
    public List<User> userList() {

        ArrayList<User> userlist = new ArrayList();
        String sql = "select * from User";
        try {
            Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
            closeConnection(connection, st, null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userlist;
    }

    //doc tat ca cac ban ghi tu table categories
    @Override
    public void register(String fullname, String username, String password, String email, String role, boolean status) {
        String sql = "INSERT INTO [User] ([fullname],[username],[password],[user_role],[mail],[status])\n"
                + "VALUES\n"
                + "  (?,?,?,?,?,?)";

        try {
            Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setObject(1, fullname);
            st.setObject(2, username);
            st.setObject(3, password);
            st.setObject(4, role);
            st.setObject(5, email);
            st.setObject(6, status);
            st.executeUpdate();
            closeConnection(connection, st, null);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public User check(String username, String password) {
        String sql = "SELECT * from [User] where username=? and password=?";

        try {
            Connection connection = getConnection();

            PreparedStatement st = connection.prepareStatement(sql);
            st.setObject(1, username);
            st.setObject(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7)
                );
            }
            closeConnection(connection, st, rs);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User getById(int id) {

        String sql = "SELECT * from [User] where user_id=?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7)
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getByEmail(String email) {

        String sql = "SELECT * from [User] where email=?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setObject(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7)
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getByUsername(String username) {

        String sql = "SELECT * from [User] where username=?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setObject(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7)
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int changePassword(String username, String password) {

        String sql = "update [User] set password = ? where username = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setObject(1, password);
            st.setObject(2, username);

            return st.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int countUser() {
        int count = 0;

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select count(*) from [User]";
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

    @Override
    public List<User> getPaggedUsers(int index, int userPerPage) {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from [User]"
                + "order by user_id desc offset ? row fetch next ? rows only";
        try {
            connection = getConnection();
            st = getConnection().prepareStatement(sql);
            st.setInt(1, (index - 1) * userPerPage);
            st.setInt(2, userPerPage);
            rs = st.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7)
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return users;
    }

    @Override
    public User getByID(int id) {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT * from [User] where user_id = ? ";
        try {
            connection = getConnection();
            st = getConnection().prepareStatement(sql);
            st.setObject(1, id);

            rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7)
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return null;
    }

    @Override
    public int updateUserStatus(int uid, boolean status) {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "update [User] set status = ? where user_id = ?";
            connection = getConnection();
            st = getConnection().prepareStatement(sql);
            st.setObject(1, status);
            st.setObject(2, uid);
            
            return st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return 0;

    }
}
