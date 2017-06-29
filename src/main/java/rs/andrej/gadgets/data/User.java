
package rs.andrej.gadgets.data;


public class User {
    private int idUser;
    private String name;
    private String surrname;
    private double dateOfBirth;
    private String username;
    private String password;
    private double credit;
    private UserDetails userDetails;
    private UserAdress userAdress;
    private OrderHistory orderHistory;

    public User(String name, String surrname, double dateOfBirth, String username, String password, double credit, UserDetails userDetails, UserAdress userAdress, OrderHistory orderHistory) {
        this.name = name;
        this.surrname = surrname;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.credit = credit;
        this.userDetails = userDetails;
        this.userAdress = userAdress;
        this.orderHistory = orderHistory;
    }

    public User(int idUser, String name, String surrname, String username) {
        this.idUser = idUser;
        this.name = name;
        this.surrname = surrname;
        this.username = username;
    }

    public User(int idUser) {
        this.idUser = idUser;
    }
    

    public User(String name, String surrname,  String username, String password) {
        this.name = name;
        this.surrname = surrname;
        this.username = username;
        this.password = password;
    }
    

    public String getName() {
        return name;
    }

    public String getSurrname() {
        return surrname;
    }

    public double getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getCredit() {
        return credit;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public UserAdress getUserAdress() {
        return userAdress;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurrname(String surrname) {
        this.surrname = surrname;
    }

    public void setDateOfBirth(double dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setUserAdress(UserAdress userAdress) {
        this.userAdress = userAdress;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public String toString() {
        return "New User created :{" + "name=" + name + ", surrname=" + surrname + ", Date of Birth :=" + dateOfBirth + ", Username=" + username + ", Password=" + password + ", Credit=" + credit +  '}';
    }

    
}