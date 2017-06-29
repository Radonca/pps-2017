
package rs.andrej.gadgets.data;


public class UserDetails {
    
    private int idUserDetails;
    private String email;
    private String phone;

    public UserDetails(int idUserDetails, String email, String phone) {
        this.idUserDetails = idUserDetails;
        this.email = email;
        this.phone = phone;
    }

    public int getIdUserDetails() {
        return idUserDetails;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setIdUserDetails(int idUserDetails) {
        this.idUserDetails = idUserDetails;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Users personal details :{" + "Email : " + email +  ", Phone=" + phone + '}';
    }
    
    
}
