package dao.impl;

import dao.DBContext;
import dao.LoanDAO;
import entity.Book;
import entity.Loan;
import entity.OverdueLoan;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s
 */
public class LoanDAOImpl extends DBContext implements LoanDAO {

    @Override
    public Loan getByID(int id) {

        String sql = "select * from Loan where loan_id = ?";

        BookDAOImpl bookDAO = new BookDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Loan loan;
            Book book;
            User user;
            if (rs.next()) {
                book = bookDAO.getBookbyId(rs.getInt("book_id"));
                user = userDAO.getById(rs.getInt("user_id"));
                loan = new Loan(
                        rs.getInt("loan_id"),
                        book,
                        user,
                        rs.getDate("loan_date_actual"),
                        rs.getDate("loan_date_from"),
                        rs.getDate("loan_date_tor"),
                        rs.getString("status")
                );
                return loan;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Loan> getByUserID(int id) {

        ArrayList<Loan> loans = new ArrayList<>();
        String sql = "select * from Loan where user_id = ?";

        BookDAOImpl bookDAO = new BookDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Loan loan;
            Book book;
            User user;
            while (rs.next()) {
                book = bookDAO.getBookbyId(rs.getInt("book_id"));
                user = userDAO.getById(rs.getInt("user_id"));
                loan = new Loan(
                        rs.getInt("loan_id"),
                        book,
                        user,
                        rs.getDate("loan_date_actual"),
                        rs.getDate("loan_date_from"),
                        rs.getDate("loan_date_tor"),
                        rs.getString("status")
                );
                loans.add(loan);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return loans;
    }

    @Override
    public List<Loan> getBorrowing(int id) {

        ArrayList<Loan> loans = new ArrayList<>();
        String sql = "select * from Loan where user_id = ? and loan_date_actual is null";

        BookDAOImpl bookDAO = new BookDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Loan loan;
            Book book;
            User user;
            while (rs.next()) {
                book = bookDAO.getBookbyId(rs.getInt("book_id"));
                user = userDAO.getById(rs.getInt("user_id"));
                loan = new Loan(
                        rs.getInt("loan_id"),
                        book,
                        user,
                        rs.getDate("loan_date_actual"),
                        rs.getDate("loan_date_from"),
                        rs.getDate("loan_date_tor"),
                        rs.getString("status")
                );
                loans.add(loan);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return loans;
    }

    @Override
    public int[] countBorrowPerMonth() {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int[] count = new int[12];
        String sql = "select COUNT(*) from Loan group by MONTH(loan_date_from) order by MONTH(loan_date_from) asc";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                count[i] = rs.getInt(1);
                i++;
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return count;
    }

    @Override
    public int countBorrowedToday() {
        int count = 0;

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select count(*) from [Appointment] where [date] = cast(getdate() as date) and [status] = 'completed'";
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
    public int countReturnedToday() {
        int count = 0;

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select count(*) from Loan	where [loan_date_actual] = cast(getdate() as date) and [status] = 'completed'";
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
    public int countReturnLoan() {
        int count = 0;

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select count(*) from Loan where loan_date_actual is not null";
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
    public int countOverdueLoan() {
        int count = 0;

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select count(*) from Loan where DATEDIFF(day,loan_date_tor,loan_date_actual) > 0 or (loan_date_actual is null and DATEDIFF(day,getdate(),loan_date_tor) > 0)";
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
    public int countBorrowingLoan() {
        int count = 0;

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select count(*) from Loan where loan_date_actual is not null";
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
    public List<Loan> getReturnedList(int index, int perPage) {
        List<Loan> loans = new ArrayList<>();

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String getListQuery = "select * from Loan where loan_date_actual is not null";
        String paggingQuery = " order by loan_id desc offset ? row fetch next ? rows only";

        try {
            connection = getConnection();
            st = connection.prepareStatement(getListQuery + paggingQuery);
            st.setInt(1, (index - 1) * perPage);
            st.setInt(2, perPage);
            rs = st.executeQuery();

            Loan loan;
            Book book;
            User user;
            while (rs.next()) {
                book = new BookDAOImpl().getBookbyId(rs.getInt("book_id"));
                user = new UserDAOImpl().getById(rs.getInt("user_id"));
                loan = new Loan(
                        rs.getInt("loan_id"),
                        book,
                        user,
                        rs.getDate("loan_date_actual"),
                        rs.getDate("loan_date_from"),
                        rs.getDate("loan_date_tor"),
                        rs.getString("status")
                );
                loans.add(loan);
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return loans;
    }

    @Override
    public List<Loan> getBorrowingList(int index, int perPage) {
        List<Loan> loans = new ArrayList<>();

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String getListQuery = "select * from Loan where loan_date_actual is null ";
        String paggingQuery = "order by loan_id desc offset ? row fetch next ? rows only";

        try {
            connection = getConnection();
            st = connection.prepareStatement(getListQuery + paggingQuery);
            st.setInt(1, (index - 1) * perPage);
            st.setInt(2, perPage);
            rs = st.executeQuery();

            Loan loan;
            Book book;
            User user;
            while (rs.next()) {
                book = new BookDAOImpl().getBookbyId(rs.getInt("book_id"));
                user = new UserDAOImpl().getById(rs.getInt("user_id"));
                loan = new Loan(
                        rs.getInt("loan_id"),
                        book,
                        user,
                        rs.getDate("loan_date_actual"),
                        rs.getDate("loan_date_from"),
                        rs.getDate("loan_date_tor"),
                        rs.getString("status")
                );
                loans.add(loan);
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return loans;
    }

    @Override
    public List<OverdueLoan> getOverdueList(int index, int perPage, int finePerday) {
        List<OverdueLoan> loans = new ArrayList<>();

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String getListQuery = "select * from Loan where DATEDIFF(day,loan_date_tor,loan_date_actual) > 0 or (loan_date_actual is null and DATEDIFF(day,getdate(),loan_date_tor) > 0)";
        String paggingQuery = " order by loan_id desc offset ? row fetch next ? rows only";

        try {
            connection = getConnection();
            st = connection.prepareStatement(getListQuery + paggingQuery);
            st.setInt(1, (index - 1) * perPage);
            st.setInt(2, perPage);
            rs = st.executeQuery();

            Date toDay = new Date();
            long millisecondDiff;
            long dateDiff;
            OverdueLoan overdueLoan;
            Book book;
            User user;
            while (rs.next()) {
                book = new BookDAOImpl().getBookbyId(rs.getInt("book_id"));
                user = new UserDAOImpl().getById(rs.getInt("user_id"));
                overdueLoan = new OverdueLoan(
                        rs.getInt("loan_id"),
                        book,
                        user,
                        rs.getDate("loan_date_actual"),
                        rs.getDate("loan_date_from"),
                        rs.getDate("loan_date_tor"),
                        rs.getString("status")
                );
                if (overdueLoan.getDateActual() == null) {
                    millisecondDiff = Math.abs(overdueLoan.getDateTo().getTime() - toDay.getTime());
                    dateDiff = TimeUnit.DAYS.convert(millisecondDiff, TimeUnit.MILLISECONDS);
                    overdueLoan.setFine(dateDiff * finePerday);
                } else {
                    millisecondDiff = Math.abs(overdueLoan.getDateActual().getTime() - overdueLoan.getDateTo().getTime());
                    dateDiff = TimeUnit.DAYS.convert(millisecondDiff, TimeUnit.MILLISECONDS);
                    overdueLoan.setFine(dateDiff * finePerday);
                }
                loans.add(overdueLoan);
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return loans;
    }

    @Override
    public OverdueLoan getOverdueByID(int id, int finePerday) {
        
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        String sql = "select * from Loan where loan_id = ? and (DATEDIFF(day,loan_date_tor,loan_date_actual) > 0 or (loan_date_actual is null and DATEDIFF(day,getdate(),loan_date_tor) > 0))";

        BookDAOImpl bookDAO = new BookDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            connection = getConnection();
            st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            Date toDay = new Date();
            long millisecondDiff;
            long dateDiff;
            OverdueLoan overdueLoan;
            Book book;
            User user;
            if (rs.next()) {
                book = bookDAO.getBookbyId(rs.getInt("book_id"));
                user = userDAO.getById(rs.getInt("user_id"));
                overdueLoan = new OverdueLoan(
                        rs.getInt("loan_id"),
                        book,
                        user,
                        rs.getDate("loan_date_actual"),
                        rs.getDate("loan_date_from"),
                        rs.getDate("loan_date_tor"),
                        rs.getString("status")
                );
                if (overdueLoan.getDateActual() == null) {
                    millisecondDiff = Math.abs(overdueLoan.getDateTo().getTime() - toDay.getTime());
                    dateDiff = TimeUnit.DAYS.convert(millisecondDiff, TimeUnit.MILLISECONDS);
                    overdueLoan.setFine(dateDiff * finePerday);
                } else {
                    millisecondDiff = Math.abs(overdueLoan.getDateActual().getTime() - overdueLoan.getDateTo().getTime());
                    dateDiff = TimeUnit.DAYS.convert(millisecondDiff, TimeUnit.MILLISECONDS);
                    overdueLoan.setFine(dateDiff * finePerday);
                }
                return overdueLoan;
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return null;
    }

}
