package supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierDAO {

    // Database connection parameters
    private String jdbcUrl = "jdbc:mysql://localhost:3306/Accounts";
    private String jdbcUser = "root";
    private String jdbcPassword = "root";

    // SQL queries
    private static final String INSERT_SUPPLIER_SQL = "INSERT INTO Suppliers (SupplierName, SupplierNumber, SupplierEmail, SupplierAddress, CurrentLoginUserId) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_SUPPLIERS_SQL = "SELECT * FROM Suppliers";
 // SQL query to retrieve suppliers based on CurrentLoginUserId
    private static final String SELECT_SUPPLIERS_BY_USER_ID_SQL = "SELECT * FROM Suppliers WHERE CurrentLoginUserId = ?";
 // SQL query to delete a supplier by SupplierId
    private static final String DELETE_SUPPLIER_SQL = "DELETE FROM Suppliers WHERE SupplierName = ?";

    // Establish database connection
    private Connection getConnection() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    // Add a new supplier to the database
    public void addSupplier(SupplierPojo supplier) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUPPLIER_SQL)) {

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getSupplierNumber());
            preparedStatement.setString(3, supplier.getSupplierEmail());
            preparedStatement.setString(4, supplier.getSupplierAddress());
            preparedStatement.setInt(5, supplier.getCurrentLoginUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception 
        }
    }

    // Get all suppliers from the database
    public List<SupplierPojo> getAllSuppliers() {
        List<SupplierPojo> suppliers = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUPPLIERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                SupplierPojo supplier = new SupplierPojo();
                supplier.setSupplierId(resultSet.getInt("SupplierId"));
                supplier.setSupplierName(resultSet.getString("SupplierName"));
                supplier.setSupplierNumber(resultSet.getString("SupplierNumber"));
                supplier.setSupplierEmail(resultSet.getString("SupplierEmail"));
                supplier.setSupplierAddress(resultSet.getString("SupplierAddress"));
                supplier.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));

                suppliers.add(supplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception 
        }

        return suppliers;
    }

    // Get all suppliers for a specific CurrentLoginUserId
    public List<SupplierPojo> getAllSuppliersByUserId(int currentLoginUserId) {
        List<SupplierPojo> suppliers = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUPPLIERS_BY_USER_ID_SQL)) {

            preparedStatement.setInt(1, currentLoginUserId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SupplierPojo supplier = new SupplierPojo();
                supplier.setSupplierId(resultSet.getInt("SupplierId"));
                supplier.setSupplierName(resultSet.getString("SupplierName"));
                supplier.setSupplierNumber(resultSet.getString("SupplierNumber"));
                supplier.setSupplierEmail(resultSet.getString("SupplierEmail"));
                supplier.setSupplierAddress(resultSet.getString("SupplierAddress"));
                supplier.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));

                suppliers.add(supplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception 
        }

        return suppliers;
    }

    // Delete a supplier from the database by SupplierId
    public void deleteSupplier(String supplierName) throws ClassNotFoundException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUPPLIER_SQL)) {

            preparedStatement.setString(1, supplierName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    // Get the Supplier by using the SupplierName
    public SupplierPojo getSupplierByName(String supplierName) throws ClassNotFoundException {
        SupplierPojo supplier = null;

        try {
            Connection conn = getConnection();

            String sql = "SELECT * FROM Suppliers WHERE SupplierName = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, supplierName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                supplier = new SupplierPojo();
               // customer.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                supplier.setSupplierName(resultSet.getString("SupplierName"));
                supplier.setSupplierNumber(resultSet.getString("SupplierNumber"));
                supplier.setSupplierEmail(resultSet.getString("SupplierEmail"));
                supplier.setSupplierAddress(resultSet.getString("SupplierAddress"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return supplier;
    }
}
