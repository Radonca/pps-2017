
package rs.andrej.gadgets.data;

import java.util.logging.Logger;


public class Category {
    
    private int idCategory=-1;
    private String name;

    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }
     public Category(String name) {
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getName() {
        return name;
    }

  

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "idCategory=" + idCategory + ", name=" + name + '}';
    }
      
    
}
