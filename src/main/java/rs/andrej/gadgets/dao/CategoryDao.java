
package rs.andrej.gadgets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rs.andrej.gadgets.data.Category;


public class CategoryDao {
    
      private static final CategoryDao instance = new CategoryDao();

    private CategoryDao() {
    }

    public static CategoryDao getInstance() {
        return instance;
    }

    public Category find(int idCategory, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Category category = null;
        try {
            ps = con.prepareStatement("SELECT * FROM category where category_id=?");
            ps.setInt(1, idCategory);
            rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category(idCategory, rs.getString("name"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return category;
    }

    public Category find(String name, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Category category = null;
        try {
            ps = con.prepareStatement("SELECT * FROM category WHERE name=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getInt("category_id"), name);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return category;
    }

    public void insert(Category category, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO category (name) VALUES(?)");
            ps.setString(1, category.getName());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(Category category, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE category SET name=? WHERE category_id=?");
            ps.setString(1, category.getName());
            ps.setInt(2, category.getIdCategory());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int idCategory, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM category WHERE category_id=?");
            ps.setInt(1, idCategory);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
}
