
package rs.adnrej.gadgets.service;

import java.sql.Connection;
import java.sql.SQLException;
import rs.andrej.gadgets.dao.ResourcesManager;
import rs.andrej.gadgets.dao.UserDao;
import rs.andrej.gadgets.data.User;
import rs.andrej.gadgets.exceptions.ShopException;


public class UserService {
    
     private static final UserService instance = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }

    public void addnewUser(User user) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            UserDao.getInstance().insert(user, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ShopException("Failed to add new user " + user, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
     public User findUser (String username) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return UserDao.getInstance().find(username, con);

        } catch (SQLException ex) {
            throw new ShopException("Failed to find user with username " + username, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    public void deleteUser(String username) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            User user = UserDao.getInstance().find(username, con);
            if (user != null) {
                UserDao.getInstance().delete(user, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ShopException("Failed to delete user with username " + username, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateUser(User user) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            UserDao.getInstance().update(user, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ShopException("Failed to update user " + user, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
