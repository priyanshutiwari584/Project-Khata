package signup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Accounts";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username and password match
        boolean isMatch = checkPassword(username, password);

        if (isMatch) {
            // Successful login
            // Set the user's full name in the session attribute
            String fullName = getFullName(username);
            //Set the CurrentLoginUserId in the session attribute
            int CurrentLoginUserId =Integer.parseInt (request.getParameter("username"));
            //Set the Vendor name in the session attribute.
            String VendorName = getVendorName(username);
            
            //HttpSession...
            HttpSession session = request.getSession();
            //Full Name in session object.
            session.setAttribute("fullName", fullName);
            //CurrentLoginUserId in Session object.
            session.setAttribute("CurrentLoginUserId", CurrentLoginUserId);
            //Vendor Name in Session object.
            session.setAttribute("VendorName", VendorName);
            
            // Redirect to the dashboard page
            response.sendRedirect("Dashboard.jsp");
        } else {
            // Incorrect password
            // Display an error message or redirect to a failure page
            response.sendRedirect("login.jsp?error=true");
        }
    }

    private boolean checkPassword(String username, String password) {
        try {
            // Establish a connection to the database
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL statement
            String sql = "SELECT password FROM users WHERE userid = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            // Execute the statement
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Compare the stored password with the provided password
                String storedPassword = resultSet.getString("password");
                return password.equals(storedPassword);
            }

            // Close the database connection
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return false;
    }

    private String getFullName(String username) {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL statement
            String sql = "SELECT full_name FROM users WHERE userid = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            // Execute the statement
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the full name
                return resultSet.getString("full_name");
            }

            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return null;
    }
    
    private String getVendorName(String username) {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL statement
            String sql = "SELECT company_name FROM users WHERE userid = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            // Execute the statement
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the full name
                return resultSet.getString("company_name");
            }

            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return null;
    }
}
