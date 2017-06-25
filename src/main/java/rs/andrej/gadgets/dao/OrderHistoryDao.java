
package rs.andrej.gadgets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.andrej.gadgets.data.OrderHistory;
import rs.andrej.gadgets.data.Purchase;
import rs.andrej.gadgets.data.UserDetails;


public class OrderHistoryDao {
    
     private static final OrderHistoryDao instance = new OrderHistoryDao();
    
    private OrderHistoryDao() {
    }

    public static OrderHistoryDao getInstance() {
        return instance;
    }
    public OrderHistory find(int idOrderHistory, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderHistory orderHistory = null;
        try {
            ps = con.prepareStatement("SELECT * FROM order_history where order_history_id=?");
            ps.setInt(1, idOrderHistory);
            rs = ps.executeQuery();
            if (rs.next()) {
                orderHistory = new OrderHistory(idOrderHistory, rs.getString("attribute"),rs.getInt("purchas_)id"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderHistory;
    }
     
}
