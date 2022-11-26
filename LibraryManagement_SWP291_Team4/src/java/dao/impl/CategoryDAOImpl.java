// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao.impl;

import dao.CategoryDAO;
import dao.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Category;
import entity.CategoryDisplayer;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author s
 */
public class CategoryDAOImpl extends DBContext implements CategoryDAO {

    @Override
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        
        try {
            Connection connection = getConnection();
            String sql = "select * from Category";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            closeConnection(connection, st, rs);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    @Override
    public Category get(int id) {
        Category c = null;
        try {
            Connection connection = getConnection();
            String sql = "select * from Category where category_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = (new Category(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            closeConnection(connection, st, rs);
        } catch (SQLException e) {
        }
        return c;
    }

    @Override
    public List<Category> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CategoryDisplayer> getAllCategoryDisplayer() {
        DBContext dBContext = new DBContext();

        ArrayList<CategoryDisplayer> list = new ArrayList<>();
        try {
            String sql = "  select c.category_id, c.category_name, c.image, count(b.book_id) as total from Category c, Book b, Book_Author ba where c.category_id = b.category_id and b.book_id = ba.book_id group by c.category_id,c.category_name, c.image";
            PreparedStatement st = dBContext.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new CategoryDisplayer(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("image"),
                        rs.getInt("total")
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
}
