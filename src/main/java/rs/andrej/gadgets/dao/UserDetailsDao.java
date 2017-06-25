
package rs.andrej.gadgets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.andrej.gadgets.data.UserDetails;


public class UserDetailsDao {
    private static final UserDetailsDao instance = new UserDetailsDao();

    private UserDetailsDao() {
    }

    public static UserDetailsDao getInstance() {
        return instance;
    }

    public UserDetails find(int idUserDetails, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDetails userDetails = null;
        try {
            ps = con.prepareStatement("SELECT * FROM user_details where user_details_id=?");
            ps.setInt(1, idUserDetails);
            rs = ps.executeQuery();
            if (rs.next()) {
                userDetails = new UserDetails(idUserDetails, rs.getString("email"), rs.getString("phone"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return userDetails;
    }

    public int insert(UserDetails userDetails, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO user_details(email, phone) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userDetails.getEmail());
            ps.setString(2, userDetails.getPhone());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public void update(UserDetails userDetails, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE user_details SET email=?, phone=? WHERE user_details_id=?");
            ps.setString(1, userDetails.getEmail());
            ps.setString(2, userDetails.getPhone());
            ps.setInt(3, userDetails.getIdUserDetails());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int idUserDetails, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM user_details WHERE user_details_id=?");
            ps.setInt(1, idUserDetails);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}

    

