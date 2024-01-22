package items;

public class ItemPojo {
    private int prodId;
    private String prodName;
    private int quantity;
    private int costPrice;
    private int sellPrice;
    private int currentLoginUserId;

    // Default constructor
    public ItemPojo() {
    }

    // Constructor with parameters
    public ItemPojo(int prodId, String prodName, int quantity, int costPrice, int sellPrice, int currentLoginUserId) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
        this.currentLoginUserId = currentLoginUserId;
    }

    // Getters and setters
    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getCurrentLoginUserId() {
        return currentLoginUserId;
    }

    public void setCurrentLoginUserId(int currentLoginUserId) {
        this.currentLoginUserId = currentLoginUserId;
    }

    // Override toString for debugging or displaying information
    @Override
    public String toString() {
        return "Item [prodId=" + prodId + ", prodName=" + prodName + ", quantity=" + quantity + ", costPrice=" + costPrice
                + ", sellPrice=" + sellPrice + ", currentLoginUserId=" + currentLoginUserId + "]";
    }
}
