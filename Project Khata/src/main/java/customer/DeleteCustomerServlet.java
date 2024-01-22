package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the customer Name to be deleted from the request
        String customerName = request.getParameter("customerName");

        // Database connection parameters (modify these accordingly)
        String jdbcUrl = "jdbc:mysql://localhost:3306/accounts";
        String dbUser = "root";
        String dbPassword = "root";

        try {
            // Create a database connection
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Prepare a SQL statement to delete the customer by ID
            String sql = "DELETE FROM Customers WHERE CustomerName = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, customerName);

            // Execute the DELETE statement
            preparedStatement.executeUpdate();

            // Close the database resources
            preparedStatement.close();
            conn.close();

            // Redirect back to DeleteCustomer.jsp
            response.sendRedirect(request.getContextPath() + "/DeleteCustomer.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }
}
