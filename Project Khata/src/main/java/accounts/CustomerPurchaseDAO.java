package accounts;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerPurchaseDAO {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Accounts";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    //Method for adding the CustomerPurchase data
    public void addCustomerPurchase(CustomerPurchasePojo purchase) throws SQLException {
    	
    	try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        String sql = "INSERT INTO CustomerPurchase (CurrentLoginUserId, CustomerName, CustomerNumber, CustomerEmail, CustomerAddress, " +
                     "prodId, ProdName, sellPrice, quantity, Total, AmountPaid, Balance, Date, invoiceNo, VendorName, PaymentMode) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, purchase.getCurrentLoginUserId());
            preparedStatement.setString(2, purchase.getCustomerName());
            preparedStatement.setString(3, purchase.getCustomerNumber());
            preparedStatement.setString(4, purchase.getCustomerEmail());
            preparedStatement.setString(5, purchase.getCustomerAddress());
            preparedStatement.setInt(6, purchase.getProdId());
            preparedStatement.setString(7, purchase.getProdName());
            preparedStatement.setInt(8, purchase.getSellPrice());
            preparedStatement.setInt(9, purchase.getQuantity());
            preparedStatement.setDouble(10, purchase.getTotal());
            preparedStatement.setDouble(11, purchase.getAmountPaid());
            preparedStatement.setDouble(12, purchase.getBalance());
            preparedStatement.setDate(13, (Date) purchase.getDate());
            preparedStatement.setString(14, purchase.getInvoiceNo());
            preparedStatement.setString(15, purchase.getVendorName());
            preparedStatement.setString(16, purchase.getPaymentMode());

            preparedStatement.executeUpdate();
        }
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    //Get the CustomerPurchase by using the CurrentLoginUserId
    public List<CustomerPurchasePojo> getCustomerPurchasesByUserId(int userId) throws SQLException {
        List<CustomerPurchasePojo> purchases = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        String sql = "SELECT * FROM CustomerPurchase WHERE CurrentLoginUserId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CustomerPurchasePojo purchase = new CustomerPurchasePojo();
                    purchase.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                    purchase.setCustomerName(resultSet.getString("CustomerName"));
                    purchase.setCustomerNumber(resultSet.getString("CustomerNumber"));
                    purchase.setCustomerEmail(resultSet.getString("CustomerEmail"));
                    purchase.setCustomerAddress(resultSet.getString("CustomerAddress"));
                    purchase.setProdId(resultSet.getInt("prodId"));
                    purchase.setProdName(resultSet.getString("ProdName"));
                    purchase.setSellPrice(resultSet.getInt("sellPrice"));
                    purchase.setQuantity(resultSet.getInt("quantity"));
                    purchase.setTotal(resultSet.getDouble("Total"));
                    purchase.setAmountPaid(resultSet.getDouble("AmountPaid"));
                    purchase.setBalance(resultSet.getDouble("Balance"));
                    purchase.setDate(resultSet.getDate("Date"));
                    purchase.setInvoiceNo(resultSet.getString("invoiceNo"));
                    purchase.setVendorName(resultSet.getString("VendorName"));
                    purchase.setPaymentMode(resultSet.getString("PaymentMode"));
                    purchases.add(purchase);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }
    
    //Method for getting the Purchase by Customer and Product 
    public CustomerPurchasePojo getPurchaseByCustomerAndProduct(int currentLoginUserId, String customerName, int prodId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CustomerPurchasePojo purchase = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "SELECT * FROM CustomerPurchase WHERE CurrentLoginUserId = ? AND CustomerName = ? AND prodId = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, currentLoginUserId);
            statement.setString(2, customerName);
            statement.setInt(3, prodId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                purchase = new CustomerPurchasePojo();
                purchase.setCustomerName(resultSet.getString("CustomerName"));
                purchase.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                purchase.setProdId(resultSet.getInt("prodId"));
                purchase.setQuantity(resultSet.getInt("quantity"));
                purchase.setTotal(resultSet.getDouble("total"));
                purchase.setSellPrice(resultSet.getInt("sellPrice"));
                purchase.setAmountPaid(resultSet.getDouble("amountPaid"));
               
            }
        } finally {
            // Close resources (resultSet, statement, connection) in a finally block
        }

        return purchase;
    }
    
    //Method to Update  the Customer Purchase if the Customer purchases the same Product.
    public void updatePurchase(CustomerPurchasePojo purchase) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "UPDATE CustomerPurchase SET Quantity = ?, Total = ?, Balance = ?, AmountPaid = ?, InvoiceNo = ? WHERE CurrentLoginUserId = ? AND CustomerName = ? AND prodId = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, purchase.getQuantity());
            statement.setDouble(2, purchase.getTotal());
            statement.setDouble(3, purchase.getBalance());
            statement.setDouble(4, purchase.getAmountPaid());
            statement.setString(5, purchase.getInvoiceNo());
            statement.setInt(6, purchase.getCurrentLoginUserId());
            statement.setString(7, purchase.getCustomerName());
            statement.setInt(8, purchase.getProdId());

            statement.executeUpdate();
        } finally {
            // Close resources (statement, connection) in a finally block
        }
    }
    //Method to Fetch the CustomerPurchase from Database using the InvoiceNo for generating the Pdf file
    public List<CustomerPurchasePojo> fetchCustomerPurchasesFromDatabase(String invoiceNo) throws SQLException {
        List<CustomerPurchasePojo> purchases = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM CustomerPurchase WHERE InvoiceNo = ?"; // Adjust the query based on your schema
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, invoiceNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerPurchasePojo purchase = new CustomerPurchasePojo();
                purchase.setInvoiceNo(resultSet.getString("invoiceNo"));
                purchase.setVendorName(resultSet.getString("vendorName"));
                purchase.setCustomerName(resultSet.getString("CustomerName"));
                purchase.setDate(resultSet.getDate("date"));
                purchase.setCustomerNumber(resultSet.getString("CustomerNumber"));
                purchase.setCustomerEmail(resultSet.getString("CustomerEmail"));
                purchase.setCustomerAddress(resultSet.getString("CustomerAddress"));
                purchase.setProdId(resultSet.getInt("prodId"));
                purchase.setProdName(resultSet.getString("prodName"));
                purchase.setQuantity(resultSet.getInt("quantity"));
                purchase.setSellPrice(resultSet.getInt("sellPrice"));
                purchase.setTotal(resultSet.getDouble("total"));
                purchase.setAmountPaid(resultSet.getDouble("amountPaid"));
                purchase.setBalance(resultSet.getDouble("balance"));
                purchase.setPaymentMode(resultSet.getString("PaymentMode"));
               
                purchases.add(purchase);
            }
        }

        return purchases;
    }
    
 // Method to calculate the total purchase for a specific user
    public double getCustomerTotalPurchase(int userId) {
        double totalSale = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT SUM(Total) FROM CustomerPurchase WHERE CurrentLoginUserId = ?")) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalSale = resultSet.getFloat(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return totalSale;
    }

}
