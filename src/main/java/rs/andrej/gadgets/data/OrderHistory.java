
package rs.andrej.gadgets.data;


public class OrderHistory {
    
    private int idOrderHistory;
    private String attribute;
    private int idPurchase;

    public OrderHistory(int idOrderHistory, String attribute, int idPurchase) {
        this.idOrderHistory = idOrderHistory;
        this.attribute = attribute;
        this.idPurchase = idPurchase;
    }

    public int getIdOrderHistory() {
        return idOrderHistory;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdOrderHistory(int idOrderHistory) {
        this.idOrderHistory = idOrderHistory;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    @Override
    public String toString() {
        return "Your previous orders :{" + "Product SKU number :" + idOrderHistory + ", name :=" + attribute + ", Your purchase reference :=" + idPurchase + '}';
    }
    
    
}
