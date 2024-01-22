package supplier;

public class SupplierPojo {

    private int supplierId;
    private String supplierName;
    private String supplierNumber;
    private String supplierEmail;
    private String supplierAddress;
    private int currentLoginUserId;

    // Constructors
    public SupplierPojo() {
        // Default constructor
    }

    public SupplierPojo(String supplierName, String supplierNumber, String supplierEmail, String supplierAddress, int currentLoginUserId) {
        this.supplierName = supplierName;
        this.supplierNumber = supplierNumber;
        this.supplierEmail = supplierEmail;
        this.supplierAddress = supplierAddress;
        this.currentLoginUserId = currentLoginUserId;
    }

    // Getters and Setters
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public int getCurrentLoginUserId() {
        return currentLoginUserId;
    }

    public void setCurrentLoginUserId(int currentLoginUserId) {
        this.currentLoginUserId = currentLoginUserId;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierNumber=" + supplierNumber
                + ", supplierEmail=" + supplierEmail + ", supplierAddress=" + supplierAddress + ", currentLoginUserId=" + currentLoginUserId + "]";
    }
}

