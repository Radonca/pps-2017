
package rs.adnrej.gadgets.service;


import java.sql.Connection;
import java.sql.SQLException;
import rs.andrej.gadgets.dao.ProductDao;
import rs.andrej.gadgets.dao.PurchaseDao;
import rs.andrej.gadgets.dao.ResourcesManager;
import rs.andrej.gadgets.dao.UserDao;
import rs.andrej.gadgets.data.Product;
import rs.andrej.gadgets.data.Purchase;
import rs.andrej.gadgets.data.User;
import rs.andrej.gadgets.exceptions.ShopException;


public class PurchaseService {
    
    private static final PurchaseService instance =new PurchaseService();
    
    private PurchaseService(){}
    
        public static PurchaseService getInstance(){
        
            return instance;
        }
    public void makePurchase(User u,Product p) throws ShopException, SQLException {
        Connection conn=null;
        
        try{
            conn=ResourcesManager.getConnection();
            conn.setAutoCommit(false);
            if(p.getCount()==0){
                throw new ShopException("There are no products left");
            }
            
            u.setCredit(u.getCredit()-p.getPrice());
            UserDao.getInstance().update(u, conn);
            
            p.setCount(p.getCount()-1);
            ProductDao.getInstance().update(p, conn);
            
            Purchase pp = new Purchase(u,p);
            PurchaseDao.getInstance().insert(pp, conn);
            
            conn.commit();
        }catch(Exception ex){
            ResourcesManager.rollbackTransactions(conn);
        }finally{
            ResourcesManager.closeConnection(conn);
            
        }
            
            
            
            
        }
    
    }

