package signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Accounts"; // Replace with your database URL
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASSWORD = "root"; // Replace with your database password

    // JDBC connection object
    private Connection connection;

    public UserDAO() {
        try {
            // Initialize the JDBC driver and establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new user to the database
    public boolean addUser(UserPojo user) {
        String sql = "INSERT INTO users (userid, full_name, password, company_name, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getUserid());
            statement.setString(2, user.getFull_name());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getCompany_name());
            statement.setString(5, user.getEmail());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all users from the database
    public List<UserPojo> getAllUsers() {
        List<UserPojo> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UserPojo user = new UserPojo(
                    resultSet.getInt("userid"),
                    resultSet.getString("full_name"),
                    resultSet.getString("password"),
                    resultSet.getString("company_name"),
                    resultSet.getString("email")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return users;
    }

    // Method to get a user by userid
    public UserPojo getUserByUserId(int userid) {
        UserPojo user = null;
        String sql = "SELECT * FROM users WHERE userid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userid);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserPojo(
                        resultSet.getInt("userid"),
                        resultSet.getString("full_name"),
                        resultSet.getString("password"),
                        resultSet.getString("company_name"),
                        resultSet.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return user;
    }

    // Close the database connection when done
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}

