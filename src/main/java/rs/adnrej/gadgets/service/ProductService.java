
package rs.adnrej.gadgets.service;

import java.sql.Connection;
import java.sql.SQLException;
import rs.andrej.gadgets.dao.CategoryDao;
import rs.andrej.gadgets.dao.ProductDao;
import rs.andrej.gadgets.dao.ResourcesManager;
import rs.andrej.gadgets.data.Category;
import rs.andrej.gadgets.data.Product;
import rs.andrej.gadgets.exceptions.ShopException;


public class ProductService {
    
    private static final ProductService instance = new ProductService();

    private ProductService() {
    }

    public static ProductService getInstance() {
        return instance;
    }

    public int addNewProduct(Product product) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ProductDao.getInstance().insert(product, con);
        } catch (SQLException ex) {
            throw new ShopException("Failed to add new product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void addNewProductCategory(Category category) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            CategoryDao.getInstance().insert(category, con);
        } catch (SQLException ex) {
            throw new ShopException("Failed to add new product category " + category, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Product findProduct(String productName) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ProductDao.getInstance().find(productName, con);

        } catch (SQLException ex) {
            throw new ShopException("Failed to find product with name " + productName, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Category findProductCategory(String name) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return CategoryDao.getInstance().find(name, con);

        } catch (SQLException ex) {
            throw new ShopException("Failed to find product category with name " + name, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteProduct(String productName) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Product product = ProductDao.getInstance().find(productName, con);
            if (product != null) {
                ProductDao.getInstance().delete(product, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ShopException("Failed to delete product with name " + productName, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateProduct(Product product) throws ShopException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ProductDao.getInstance().update(product, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ShopException("Failed to update product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
}
