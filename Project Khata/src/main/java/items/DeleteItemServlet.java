package items;


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

@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the Product Name to be deleted from the request
        String prodName = request.getParameter("prodName");

        // Database connection parameters (modify these accordingly)
        String jdbcUrl = "jdbc:mysql://localhost:3306/accounts";
        String dbUser = "root";
        String dbPassword = "root";

        try {
            // Create a database connection
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Prepare a SQL statement to delete the Product Name
            String sql = "DELETE FROM Items WHERE prodName = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, prodName);

            // Execute the DELETE statement
            preparedStatement.executeUpdate();

            // Close the database resources
            preparedStatement.close();
            conn.close();

            // Redirect back to DeleteItems.jsp
            response.sendRedirect(request.getContextPath() + "/DeleteItems.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }
}
