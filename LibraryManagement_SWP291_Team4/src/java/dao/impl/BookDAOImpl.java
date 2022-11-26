// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao.impl;

import dao.BookDAO;
import dao.CategoryDAO;
import dao.DBContext;

import java.util.ArrayList;
import entity.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.Author;
import entity.Book_Author;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s
 */
public class BookDAOImpl extends DBContext implements BookDAO {

    @Override
    public List<Book> getAllBook() {
        List<Book> list = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from Book";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Book(rs.getInt(1), rs.getString(2), categoryDAO.get(rs.getInt(3)), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10), rs.getString(11)));
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return list;

    }

    @Override
    public Book getBookbyId(int id) {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        String sql = "select * from Book where book_id=?";
        try {
            connection = getConnection();

            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Book b = new Book(rs.getInt(1), rs.getString(2), categoryDAO.get(rs.getInt(3)), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10), rs.getString(11));
                return b;
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return null;
    }

    @Override
    public List<Book_Author> getBookListWithAuthor(int index, int perPage) {
        List<Book_Author> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        AuthorDAOImpl ad = new AuthorDAOImpl();
        String sql = "select * from Book_Author order by book_id\n"
                + "offset ? row fetch next ? rows only";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * perPage);
            st.setInt(2, perPage);
            rs = st.executeQuery();
            while (rs.next()) {
                Book b = getBookbyId(rs.getInt(1));
                if(b.isStatus()==false) continue;
                Author a = ad.getAuthorById(rs.getInt(2));
                list.add(new Book_Author(b, a));
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return list;
    }

    @Override
    public int countBook() {

        int count = 0;
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select COUNT(*) from Book_Author";
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
    public Book get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Book> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Book book) {
        int res = 0;
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "INSERT INTO [dbo].[Book]\n"
                + "           ([title]\n"
                + "           ,[category_id]\n"
                + "           ,[publisher]\n"
                + "           ,[publication_date]\n"
                + "           ,[quantity]\n"
                + "           ,[image]\n"
                + "           ,[rate]\n"
                + "           ,[status]\n"
                + "           ,[Introduction]\n"
                + "           ,[Description])\n"
                + "        VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, book.getTitle());
            st.setInt(2, book.getCategory_id().getId());
            st.setString(3, book.getPublisher());
            st.setDate(4, book.getPublication_year());
            st.setInt(5, book.getQuantity());
            st.setString(6, book.getImage());
            st.setInt(7, book.getRate());
            st.setBoolean(8, book.isStatus());
            st.setString(9, book.getIntroduction());
            st.setString(10, book.getDescription());
            st.executeQuery();
            res = 1;
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            closeConnection(connection, st, rs);
        }
        return res;
    }

    @Override
    public int update(Book t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int countBookByCategory(int cid) {

        int count = 0;
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select COUNT(*) from Book_Author ba, Book b, Category c where ba.book_id = b.book_id and c.category_id = b.category_id and c.category_id = ?";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, cid);
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
    public int countBooksSearched(String search) {
        int count = 0;
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select count(*) from Book_Author ba, Book b"
                + " where ba.book_id = b.book_id"
                + " and (upper(b.title) like upper('%" + search + "%')"
                + " or upper(b.publisher) like upper('%" + search + "%')"
                + " or upper(b.Introduction) like upper('%" + search + "%')"
                + " or upper(b.Description) like upper('%" + search + "%'))";
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
    public ArrayList<Book_Author> getBookWithAuthorByCategory(int cid, int index, int perPage) {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Book_Author> list = new ArrayList<>();
        AuthorDAOImpl ad = new AuthorDAOImpl();
        String sql = "  select * from Book_Author ba, Book b where ba.book_id = b.book_id and b.category_id=?"
                + " order by b.book_id offset ? row fetch next ? rows only";
        try {
            st = getConnection().prepareStatement(sql);
            st.setInt(1, cid);
            st.setInt(2, (index - 1) * perPage);
            st.setInt(3, perPage);
            rs = st.executeQuery();
            while (rs.next()) {
                Book b = getBookbyId(rs.getInt("book_id"));
                Author a = ad.getAuthorById(rs.getInt("author_id"));
                list.add(new Book_Author(b, a));
            }
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return list;
    }

    @Override
    public ArrayList<Book_Author> getSearchedBookListWithAuthor(String search, int index, int perPage) {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Book_Author> list = new ArrayList<>();
        AuthorDAOImpl ad = new AuthorDAOImpl();
        String sql = "select * from Book_Author ba, Book b"
                + " where ("
                + " ba.book_id = b.book_id"
                + " and (upper(b.title) like upper('%" + search + "%')"
                + " or upper(b.publisher) like upper('%" + search + "%')"
                + " or upper(b.Introduction) like upper('%" + search + "%')"
                + " or upper(b.Description) like upper('%" + search + "%')"
                + "))"
                + "order by b.book_id offset ? row fetch next ? rows only";
        try {
            st = getConnection().prepareStatement(sql);
            st.setInt(1, (index - 1) * perPage);
            st.setInt(2, perPage);
            rs = st.executeQuery();

            while (rs.next()) {
                Book b = getBookbyId(rs.getInt("book_id"));
                Author a = ad.getAuthorById(rs.getInt("author_id"));
                list.add(new Book_Author(b, a));
            }
        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return list;

    }

    @Override
    public List<Book_Author> getBookListWithAuthor() {
        List<Book_Author> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        AuthorDAOImpl ad = new AuthorDAOImpl();
        String sql = "select * from Book_Author order by book_id";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Book b = getBookbyId(rs.getInt(1));
                Author a = ad.getAuthorById(rs.getInt(2));
                list.add(new Book_Author(b, a));
            }

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
        return list;
    }

    @Override
    public void updateBook(Book book) {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "UPDATE [dbo].[Book]\n"
                + "   SET [title] = ?\n"
                + "      ,[category_id] = ?\n"
                + "      ,[publisher] = ?\n"
                + "      ,[publication_date] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[rate] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[Introduction] = ?\n"
                + "      ,[Description] = ?\n"
                + " WHERE [book_id] = ?";
        try {
            connection = getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, book.getTitle());
            st.setInt(2, book.getCategory_id().getId());
            st.setString(3, book.getPublisher());
            st.setDate(4, book.getPublication_year());
            st.setInt(5, book.getQuantity());
            st.setString(6, book.getImage());
            st.setInt(7, book.getRate());
            st.setBoolean(8, book.isStatus());
            st.setString(9, book.getIntroduction());
            st.setString(10, book.getDescription());
            st.setInt(11, book.getId());
            st.executeQuery();

        } catch (SQLException e) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(connection, st, rs);
        }
    }

}
