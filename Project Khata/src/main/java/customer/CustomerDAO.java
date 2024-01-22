package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CustomerDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Accounts";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    //Method to add the Customer Details
    public void addCustomer(CustomerPojo customer) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "INSERT INTO customers (CurrentLoginUserId, CustomerName, CustomerNumber, CustomerEmail, CustomerAddress) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customer.getCurrentLoginUserId());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getCustomerNumber());
            statement.setString(4, customer.getCustomerEmail());
            statement.setString(5, customer.getCustomerAddress());

            statement.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }

    //Method for getting the Customer Details by CurrentLoginUserId
    public List<CustomerPojo> getCustomersByUserId(int currentLoginUserId) {
        List<CustomerPojo> customers = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            
            String sql = "SELECT * FROM customers where CurrentLoginUserId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,currentLoginUserId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CustomerPojo customer = new CustomerPojo();
                customer.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                customer.setCustomerName(resultSet.getString("CustomerName"));
                customer.setCustomerNumber(resultSet.getString("CustomerNumber"));
                customer.setCustomerEmail(resultSet.getString("CustomerEmail"));
                customer.setCustomerAddress(resultSet.getString("CustomerAddress"));

                customers.add(customer);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return customers;
    }

    
    //Method to get the Customer details by customer name
    public CustomerPojo getCustomerByName(String customerName) {
        CustomerPojo customer = null;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM customers WHERE CustomerName = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customerName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                customer = new CustomerPojo();
               // customer.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                customer.setCustomerName(resultSet.getString("CustomerName"));
                customer.setCustomerNumber(resultSet.getString("CustomerNumber"));
                customer.setCustomerEmail(resultSet.getString("CustomerEmail"));
                customer.setCustomerAddress(resultSet.getString("CustomerAddress"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return customer;
    }
    
    //Method for the getting the Customer details
    public List<CustomerPojo> getAllCustomers() {
        List<CustomerPojo> customerList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM customers";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CustomerPojo customer = new CustomerPojo();
                customer.setCurrentLoginUserId(resultSet.getInt("CurrentLoginUserId"));
                customer.setCustomerName(resultSet.getString("CustomerName"));
                customer.setCustomerNumber(resultSet.getString("CustomerNumber"));
                customer.setCustomerEmail(resultSet.getString("CustomerEmail"));
                customer.setCustomerAddress(resultSet.getString("CustomerAddress"));
                // Set other fields

                customerList.add(customer);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return customerList;
    }
}
