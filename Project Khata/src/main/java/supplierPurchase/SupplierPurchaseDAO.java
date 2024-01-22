package supplierPurchase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierPurchaseDAO {

    // Database connection parameters
    private String jdbcUrl = "jdbc:mysql://localhost:3306/Accounts";
    private String jdbcUser = "root";
    private String jdbcPassword = "root";

    // SQL queries
    private static final String INSERT_SUPPLIER_PURCHASE_SQL = "INSERT INTO SupplierPurchase (CurrentLoginUserId, SupplierName, SupplierNumber, SupplierEmail, SupplierAddress, ProdName, CostPrice, SellPrice, Quantity, Total, AmountPaid, Balance, Date, InvoiceNo, PaymentMode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_SUPPLIER_PURCHASE_SQL = "SELECT * FROM SupplierPurchase";
 // SQL query to retrieve supplier purchase records by CurrentLoginUserId
    private static final String SELECT_SUPPLIER_PURCHASE_BY_USER_ID_SQL = "SELECT * FROM SupplierPurchase WHERE CurrentLoginUserId = ?";

    // Establish database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    // Add a new supplier purchase record to the database
    public void addSupplierPurchase(SupplierPurchase supplierPurchase) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUPPLIER_PURCHASE_SQL)) {

            preparedStatement.setInt(1, supplierPurchase.getCurrentLoginUserId());
            preparedStatement.setString(2, supplierPurchase.getSupplierName());
            preparedStatement.setString(3, supplierPurchase.getSupplierNumber());
            preparedStatement.setString(4, supplierPurchase.getSupplierEmail());
            preparedStatement.setString(5, supplierPurchase.getSupplierAddress());
            preparedStatement.setString(6, supplierPurchase.getProdName());
            preparedStatement.setInt(7, supplierPurchase.getCostPrice());
            preparedStatement.setInt(8, supplierPurchase.getSellPrice());
            preparedStatement.setInt(9, supplierPurchase.getQuantity());
            preparedStatement.setDouble(10, supplierPurchase.getTotal());
            preparedStatement.setDouble(11, supplierPurchase.getAmountPaid());
            preparedStatement.setDouble(12, supplierPurchase.getBalance());
            preparedStatement.setDate(13, new java.sql.Date(supplierPurchase.getDate().getTime()));
            preparedStatement.setString(14, supplierPurchase.getInvoiceNo());
            preparedStatement.setString(15, supplierPurchase.getPaymentMode());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    // Get all supplier purchase records from the database
    public List<SupplierPurchase> getAllSupplierPurchases() {
        List<SupplierPurchase> supplierPurchases = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUPPLIER_PURCHASE_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                SupplierPurchase supplierPurchase = new SupplierPurchase();
                supplierPurchase.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                supplierPurchase.setSupplierName(resultSet.getString("SupplierName"));
                supplierPurchase.setSupplierNumber(resultSet.getString("SupplierNumber"));
                supplierPurchase.setSupplierEmail(resultSet.getString("SupplierEmail"));
                supplierPurchase.setSupplierAddress(resultSet.getString("SupplierAddress"));
                supplierPurchase.setProdName(resultSet.getString("ProdName"));
                supplierPurchase.setCostPrice(resultSet.getInt("CostPrice"));
                supplierPurchase.setSellPrice(resultSet.getInt("SellPrice"));
                supplierPurchase.setQuantity(resultSet.getInt("Quantity"));
                supplierPurchase.setTotal(resultSet.getDouble("Total"));
                supplierPurchase.setAmountPaid(resultSet.getDouble("AmountPaid"));
                supplierPurchase.setBalance(resultSet.getDouble("Balance"));
                supplierPurchase.setDate(resultSet.getDate("Date"));
                supplierPurchase.setInvoiceNo(resultSet.getString("InvoiceNo"));
                supplierPurchase.setPaymentMode(resultSet.getString("PaymentMode"));

                supplierPurchases.add(supplierPurchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return supplierPurchases;
    }


    // Get all supplier purchase records for a specific CurrentLoginUserId
    public List<SupplierPurchase> getSupplierPurchaseByUserId(int currentLoginUserId) {
        List<SupplierPurchase> supplierPurchases = new ArrayList<>();

        try (	Connection connection = getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUPPLIER_PURCHASE_BY_USER_ID_SQL)) {
            preparedStatement.setInt(1, currentLoginUserId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SupplierPurchase supplierPurchase = new SupplierPurchase();
                supplierPurchase.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                supplierPurchase.setSupplierName(resultSet.getString("SupplierName"));
                supplierPurchase.setSupplierNumber(resultSet.getString("SupplierNumber"));
                supplierPurchase.setSupplierEmail(resultSet.getString("SupplierEmail"));
                supplierPurchase.setSupplierAddress(resultSet.getString("SupplierAddress"));
                supplierPurchase.setProdName(resultSet.getString("ProdName"));
                supplierPurchase.setCostPrice(resultSet.getInt("CostPrice"));
                supplierPurchase.setSellPrice(resultSet.getInt("SellPrice"));
                supplierPurchase.setQuantity(resultSet.getInt("Quantity"));
                supplierPurchase.setTotal(resultSet.getDouble("Total"));
                supplierPurchase.setAmountPaid(resultSet.getDouble("AmountPaid"));
                supplierPurchase.setBalance(resultSet.getDouble("Balance"));
                supplierPurchase.setDate(resultSet.getDate("Date"));
                supplierPurchase.setInvoiceNo(resultSet.getString("InvoiceNo"));
                supplierPurchase.setPaymentMode(resultSet.getString("PaymentMode"));

                supplierPurchases.add(supplierPurchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception 
        }

        return supplierPurchases;
    }
    
    //Get the Supplier by customer and product
    public SupplierPurchase getSupplierByCustomerAndProduct(int currentLoginUserId, String supplierName, String prodName) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SupplierPurchase purchase = null;

        try {
            connection = getConnection();

            String query = "SELECT * FROM SupplierPurchase WHERE CurrentLoginUserId = ? AND SupplierName = ? AND prodName = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, currentLoginUserId);
            statement.setString(2, supplierName);
            statement.setString(3, prodName);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                purchase = new SupplierPurchase();
                purchase.setSupplierName(resultSet.getString("SupplierName"));
                purchase.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                purchase.setProdName(resultSet.getString("prodName"));
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
    //Method to Update the table when the supplier purchase is already existing
    public void updatePurchase(SupplierPurchase purchase) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();

            String query = "UPDATE SupplierPurchase SET Quantity = ?, Total = ?, Balance = ?, AmountPaid = ?, InvoiceNo = ?, Date =? WHERE CurrentLoginUserId = ? AND SupplierName = ? AND prodName = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, purchase.getQuantity());
            statement.setDouble(2, purchase.getTotal());
            statement.setDouble(3, purchase.getBalance());
            statement.setDouble(4, purchase.getAmountPaid());
            statement.setString(5, purchase.getInvoiceNo());
            statement.setDate(6, new java.sql.Date(purchase.getDate().getTime()));
            statement.setInt(7, purchase.getCurrentLoginUserId());
            statement.setString(8, purchase.getSupplierName());
            statement.setString(9, purchase.getProdName());

            statement.executeUpdate();
        } finally {
            // Close resources (statement, connection) in a finally block
        }
    }
    

    // Get SupplierPurchase by invoiceNo
    public List<SupplierPurchase> fetchSupplierPurchasesFromDatabase(String invoiceNo) throws SQLException {
        List<SupplierPurchase> purchases = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM SupplierPurchase WHERE InvoiceNo = ?"; // Adjust the query based on your schema
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, invoiceNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SupplierPurchase purchase = new SupplierPurchase();
                purchase.setCurrentLoginUserId(resultSet.getInt("currentLoginUserId"));
                purchase.setInvoiceNo(resultSet.getString("invoiceNo"));
                purchase.setSupplierName(resultSet.getString("supplierName"));
                purchase.setDate(resultSet.getDate("date"));
                purchase.setSupplierNumber(resultSet.getString("supplierNumber"));
                purchase.setSupplierEmail(resultSet.getString("supplierEmail"));
                purchase.setSupplierAddress(resultSet.getString("supplierAddress"));
                purchase.setProdName(resultSet.getString("prodName"));
                purchase.setQuantity(resultSet.getInt("quantity"));
                purchase.setSellPrice(resultSet.getInt("sellPrice"));
                purchase.setCostPrice(resultSet.getInt("costPrice"));
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
    public double getSupplierTotalPurchase(int userId) {
        double totalPurchase = 0;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT SUM(Total) FROM SupplierPurchase WHERE CurrentLoginUserId = ?")) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalPurchase = resultSet.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions 
        }

        return totalPurchase;
    }

}

