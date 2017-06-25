
package rs.andrej.gadgets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.andrej.gadgets.data.Category;
import rs.andrej.gadgets.data.Product;
import rs.andrej.gadgets.exceptions.ShopException;



public class ProductDao {
     private static final ProductDao instance = new ProductDao();

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        return instance;
    }

    public Product find(String name, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM product WHERE name=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                Category category = CategoryDao.getInstance().find(rs.getInt("category_id"), con);
                product=new Product(rs.getInt("product_id"),rs.getString("name"), rs.getDouble("price"), rs.getInt("count"), category);
            }
            
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }

    public Product find(int idProduct, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM product WHERE product_id=?");
            ps.setInt(1, idProduct);
            rs = ps.executeQuery();
            if (rs.next()) {
                Category category = CategoryDao.getInstance().find(rs.getInt("categoty_id"), con);
                product=new Product(rs.getInt("product_id"),rs.getString("name"), rs.getDouble("price"), rs.getInt("count"), category);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }

    public List<Product> find(Category category, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productsList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM product WHERE categoty_id=?");
            ps.setInt(1, category.getIdCategory());
            rs = ps.executeQuery();
            while (rs.next()) {
               Product product=new Product(rs.getInt("product_id"),rs.getString("name"), rs.getDouble("price"), rs.getInt("count"), category);
                productsList.add(product);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return productsList;
    }

    public int insert(Product product, Connection con) throws SQLException, ShopException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO product(name, price,count, category_id) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getCount());
            Category category = CategoryDao.getInstance().find(product.getCategory().getName(), con);
            if (category == null) {
                throw new ShopException("Category " + product.getCategory() + " doesn't exist in database.");
            }
            ps.setInt(4, category.getIdCategory());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public void update(Product product, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE product SET name=?, price=?,count=?, caregory_id=? WHERE product_id=?");
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3,product.getCount());
           
            ps.setInt(4, product.getCategory().getIdCategory());
            ps.setInt(5, product.getIdProduct());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Product product, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            
            PurchaseDao.getInstance().delete(product, con);

            
            ps = con.prepareStatement("DELETE FROM product WHERE product_id=?");
            ps.setInt(1, product.getIdProduct());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
}
