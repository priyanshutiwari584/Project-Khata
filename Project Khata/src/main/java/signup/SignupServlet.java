package signup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Accounts";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullname");
        String companyName = request.getParameter("companyname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Insert user data into the database
        try {
            // Establish a connection to the database
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL statement
            String sql = "INSERT INTO users (userid,  full_name,password, company_name, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, fullName);
            statement.setString(3, password);
            statement.setString(4, companyName);
            statement.setString(5, email);

            // Execute the statement
            statement.executeUpdate();

            // Close the database connection
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle database errors
        }

        // Redirect to a success page or perform any additional actions
        response.sendRedirect("signup_success.jsp");
    }
}
