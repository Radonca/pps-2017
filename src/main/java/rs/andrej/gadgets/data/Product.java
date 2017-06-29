
package rs.andrej.gadgets.data;


public class Product {
    
    private int idProduct=-1;
    private String name;
    private double price;
    private int count;
    private Category category;

    public Product(int idProduct,String name, double price, int count, Category category) {
        this.idProduct=idProduct;
        this.name = name;
        this.price = price;
        this.count = count;
        this.category = category;
    }

   
   
    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public Category getCategory() {
        return category;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "Product SKU =" + idProduct + ", Name of the product=" + name + ", The product costs :=" + price + ",, category=" + category + '}';
    }

  
}
