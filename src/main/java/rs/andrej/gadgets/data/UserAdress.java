
package rs.andrej.gadgets.data;


public class UserAdress {
    private int idUserAdress=-1;
    private String city;
    private String street;
    private String country;

    public UserAdress(int idUserAdress, String city, String street, String country) {
        this.idUserAdress = idUserAdress;
        this.city = city;
        this.street = street;
        this.country = country;
    }
    public UserAdress( String city, String street, String country) {
        this.city = city;
        this.street = street;
        this.country = country;
    }

    public int getIdUserAdress() {
        return idUserAdress;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "UserAdress{" + "idUserAdress=" + idUserAdress + ", city=" + city + ", street=" + street + ", country=" + country + '}';
    }

    
    
}
