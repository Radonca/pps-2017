
package rs.andrej.gadgets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rs.andrej.gadgets.data.OrderHistory;
import rs.andrej.gadgets.data.User;
import rs.andrej.gadgets.data.UserAdress;
import rs.andrej.gadgets.data.UserDetails;


public class UserDao {
    
    private static final UserDao instance=new UserDao();
    
    private UserDao() {
    
    }
    
    public static UserDao getInstance(){
     return instance;
    }
    
     public User find(String username, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            ps = con.prepareStatement("SELECT * FROM user where username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                UserAdress userAdress = UserAdressDao.getInstance().find(rs.getInt("user_adress_id"), con);
                UserDetails userDetails = UserDetailsDao.getInstance().find(rs.getInt("user_details_id"), con);
                OrderHistory orderHistory=OrderHistoryDao.getInstance().find(rs.getInt("order_history_id"),con);
                
                user=new User(rs.getString("name"),  rs.getString("surrname"), rs.getDouble("date_of_birth"), rs.getString("username"), rs.getString("password"), rs.getDouble("credit"), userDetails, userAdress, orderHistory);
            }
             
            
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return user;
    }
      public void insert(User user, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            Integer fkAddress = null;
            if (user.getUserAdress() != null) {
                //insert address and receive the value of id
                fkAddress = UserAdressDao.getInstance().insert(user.getUserAdress(), con);
            }
            Integer fkContactDetails = null;
            if (user.getUserDetails() != null) {
                //insert contact details and receive the value of id
                fkContactDetails = UserDetailsDao.getInstance().insert(user.getUserDetails(), con);
            }

            ps = con.prepareStatement("INSERT INTO user(username, name, surrname,credit, date_of_birth, password, user_details_id,user_adress_id) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurrname());
            ps.setDouble(4,user.getCredit());
            ps.setDouble(5, user.getDateOfBirth());
            ps.setString(6, user.getPassword());
            ps.setInt(7, fkContactDetails);
            ps.setInt(8,fkAddress);
            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(User user, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE customer SET name=?, surrname=?,credit=? WHERE username=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurrname());
            ps.setDouble(3, user.getCredit());
            ps.setString(4,user.getUsername());
            ps.executeUpdate();

            if (user.getUserAdress() != null) {
                UserAdressDao.getInstance().update(user.getUserAdress(), con);
            }
            if (user.getUserDetails()!= null) {
                UserDetailsDao.getInstance().update(user.getUserDetails(), con);
            }

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(User user, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

           
            PurchaseDao.getInstance().delete(user, con);

            
            ps = con.prepareStatement("DELETE FROM user WHERE username=?");
            ps.setString(1, user.getUsername());
            ps.executeUpdate();

            
            if (user.getUserAdress() != null) {
                UserAdressDao.getInstance().delete(user.getUserAdress().getIdUserAdress(), con);
            }

            
            if (user.getUserDetails()!= null) {
                UserDetailsDao.getInstance().delete(user.getUserDetails().getIdUserDetails(), con);
            }

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}

