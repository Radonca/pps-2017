
package rs.andrej.gadgets.data;


public class Purchase {
    
    private int idPurchase;
    private User user;
    private Product product;

    public Purchase(int idPurchase, User user, Product product) {
        this.idPurchase = idPurchase;
        this.user = user;
        this.product = product;
    }

    public Purchase(User user, Product product) {
        this.user = user;
        this.product = product;
    }
    

    public int getIdPurchase() {
        return idPurchase;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
    
    
    
}
