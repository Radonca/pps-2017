
package rs.andrej.gadgets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.andrej.gadgets.data.UserAdress;


public class UserAdressDao {
    
    private static final UserAdressDao instance = new UserAdressDao();

    private UserAdressDao() {
    }

    public static UserAdressDao getInstance() {
        return instance;
    }
    
    protected UserAdress find (int idUserAdress,Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserAdress userAdress = null;
        try {
            ps = con.prepareStatement("SELECT * FROM user_adress where user_adress_id=?");
            ps.setInt(1, idUserAdress);
            rs = ps.executeQuery();
            if (rs.next()) {
                userAdress = new UserAdress(idUserAdress, rs.getString("city"), rs.getString("street"), rs.getString("country"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return userAdress;
    }
     protected int insert(UserAdress userAdress, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO user_adress(city, street,country) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userAdress.getCity());
            ps.setString(2, userAdress.getStreet());
            ps.setString(3, userAdress.getCountry());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
public void update(UserAdress userAdress, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE user_adress SET city=?, street=?, country=? WHERE user_adress_id=?");
            ps.setString(1, userAdress.getCity());
            ps.setString(2, userAdress.getStreet());
            ps.setString(3, userAdress.getCountry());
            ps.setInt(4, userAdress.getIdUserAdress());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
public void delete(int idUserAdress, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM user_adress WHERE user_adress_id=?");
            ps.setInt(1, idUserAdress);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
