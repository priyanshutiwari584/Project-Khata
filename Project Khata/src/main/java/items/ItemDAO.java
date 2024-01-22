package items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Accounts";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    //Method to get all items
    public List<ItemPojo> getAllItems() {
        List<ItemPojo> items = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM items";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ItemPojo item = new ItemPojo();
                item.setProdId(resultSet.getInt("prodId"));
                item.setProdName(resultSet.getString("prodName"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setSellPrice(resultSet.getInt("sellPrice"));
                item.setCostPrice(resultSet.getInt("costPrice"));

                items.add(item);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return items;
    }
    
   //Method to GetItemsByUserId
    
    public List<ItemPojo> getItemsByUserId(int userId) {
    	List<ItemPojo> items = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM items WHERE CurrentLoginUserId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ItemPojo item = new ItemPojo();
                item.setProdId(resultSet.getInt("prodId"));
                item.setProdName(resultSet.getString("prodName"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setCostPrice(resultSet.getInt("costPrice"));
                item.setSellPrice(resultSet.getInt("sellPrice"));
                
                // ... Set other fields ...
                items.add(item);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return items;
    }
    
    //Method to GetItemsById
    public ItemPojo getItemsById(int prodId) {
        ItemPojo item = null;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM items WHERE prodId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, prodId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new ItemPojo();
                item.setProdId(resultSet.getInt("prodId"));
                item.setProdName(resultSet.getString("prodName"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setCostPrice(resultSet.getInt("costPrice"));
                item.setSellPrice(resultSet.getInt("sellPrice"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return item;
    }
    
    //Update the Items Quantity in the Items table
    public void updateItem(ItemPojo item) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "UPDATE items SET quantity = ? WHERE prodId = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, item.getQuantity());
            statement.setInt(2, item.getProdId());

            statement.executeUpdate();
        } finally {
            // Close resources (statement, connection) in a finally block
        }
    }

    // Method to retrieve an item by prodName
    public ItemPojo getItemByProdName(String prodName) {
        ItemPojo item = null;
        String sql = "SELECT * FROM items WHERE prodName = ?";
        try{
        	Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        	PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, prodName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = new ItemPojo();
                    item.setProdId(resultSet.getInt("prodId"));
                    item.setProdName(resultSet.getString("prodName"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setCostPrice(resultSet.getInt("costPrice"));
                    item.setSellPrice(resultSet.getInt("sellPrice"));
                
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return item;
    }

    // Method to update an item
    public boolean updateItem1(ItemPojo item) {
        String sql = "UPDATE items SET quantity = ?, costPrice = ?, sellPrice = ? WHERE prodId = ?";
        try {
        	Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        	PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getQuantity());
            statement.setInt(2, item.getCostPrice());
            statement.setInt(3, item.getSellPrice());
            statement.setInt(4, item.getProdId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return false;
        }
    }

 // Method to add a new item to the database
    public boolean addItem(ItemPojo item) {
        String sql = "INSERT INTO items (prodName, quantity, costPrice, sellPrice, currentLoginUserId) VALUES (?, ?, ?, ?, ?)";
        try{
        	Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        	PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, item.getProdName());
            statement.setInt(2, item.getQuantity());
            statement.setInt(3, item.getCostPrice());
            statement.setInt(4, item.getSellPrice());
            statement.setInt(5, item.getCurrentLoginUserId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return false;
        }
    }

    
    // Method to fetch items by CurrentLoginUserId
    public List<ItemPojo> getItemsBysUserId(int userId) {
        List<ItemPojo> items = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT prodName, quantity FROM Items WHERE CurrentLoginUserId = ?")) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String prodName = resultSet.getString("prodName");
                    int quantity = resultSet.getInt("quantity");

                    ItemPojo item = new ItemPojo();
                    item.setProdName(prodName);
                    item.setQuantity(quantity);

                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return items;
    }
}
