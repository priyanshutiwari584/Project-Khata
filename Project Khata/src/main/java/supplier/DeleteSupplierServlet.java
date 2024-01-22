package supplier;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteSupplierServlet")
public class DeleteSupplierServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the supplier ID to delete
        String supplierName = request.getParameter("supplierName");

        // Delete the supplier from the database
        SupplierDAO supplierDAO = new SupplierDAO();
        try {
			supplierDAO.deleteSupplier(supplierName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Redirect back to the DeleteSupplier.jsp page
        response.sendRedirect("DeleteSupplier.jsp");
    }
}
