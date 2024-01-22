package supplier;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddSupplierServlet")
public class AddSupplierServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String supplierName = request.getParameter("supplierName");
        String supplierNumber = request.getParameter("supplierNumber");
        String supplierEmail = request.getParameter("supplierEmail");
        String supplierAddress = request.getParameter("supplierAddress");
        int currentLoginUserId = (int)request.getSession().getAttribute("CurrentLoginUserId");

        // Create a Supplier object with the form data
        SupplierPojo supplier = new SupplierPojo(supplierName, supplierNumber, supplierEmail, supplierAddress, currentLoginUserId);

        // Add the supplier to the database
        SupplierDAO supplierDAO = new SupplierDAO();
        supplierDAO.addSupplier(supplier);

        // Redirect to a success page or show a success message
        response.sendRedirect("Supplier.jsp");
    }
}
