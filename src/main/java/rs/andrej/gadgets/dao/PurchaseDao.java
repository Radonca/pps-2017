
package rs.andrej.gadgets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.andrej.gadgets.data.Product;
import rs.andrej.gadgets.data.Purchase;
import rs.andrej.gadgets.data.User;


public class PurchaseDao {
     private static final PurchaseDao instance = new PurchaseDao();

    private PurchaseDao() {
    }

    public static PurchaseDao getInstance() {
        return instance;
    }

    public Purchase find(int idPurchase, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Purchase purchase = null;
        try {
            ps = con.prepareStatement("SELECT * FROM purchase where purchase_id=?");
            ps.setInt(1, idPurchase);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = UserDao.getInstance().find(rs.getString("user_id"), con);
                Product product = ProductDao.getInstance().find(rs.getInt("product_id"), con);
                purchase = new Purchase(idPurchase, user, product);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return purchase;
    }

    public int insert(Purchase purchase, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO purchase(user_id, product_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, purchase.getUser().getUsername());
            ps.setInt(2, purchase.getProduct().getIdProduct());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public void delete(User user, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM purchase WHERE user_id=?");
            ps.setString(1, user.getUsername());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Product product, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM purchase WHERE product_id=?");
            ps.setInt(1, product.getIdProduct());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
}
