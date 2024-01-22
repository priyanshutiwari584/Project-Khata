package customer;

public class CustomerPojo {
    private int currentLoginUserId;
    private String customerName;
    private String customerNumber;
    private String customerEmail;
    private String customerAddress;

    public int getCurrentLoginUserId() {
        return currentLoginUserId;
    }

    public void setCurrentLoginUserId(int currentLoginUserId) {
        this.currentLoginUserId = currentLoginUserId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
