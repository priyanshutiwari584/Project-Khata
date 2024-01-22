package signup;

public class UserPojo {
    private int userid;
    private String full_name;
    private String password;
    private String company_name;
    private String email;

    // Default constructor
    public UserPojo() {
    }

    // Constructor with parameters
    public UserPojo(int userid, String full_name, String password, String company_name, String email) {
        this.userid = userid;
        this.full_name = full_name;
        this.password = password;
        this.company_name = company_name;
        this.email = email;
    }

    // Getters and setters
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Override toString for debugging or displaying information
    @Override
    public String toString() {
        return "User [userid=" + userid + ", full_name=" + full_name + ", password=" + password +
               ", company_name=" + company_name + ", email=" + email + "]";
    }
}

