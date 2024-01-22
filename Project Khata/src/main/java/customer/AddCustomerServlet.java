package customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentLoginUserId = Integer.parseInt(request.getSession().getAttribute("CurrentLoginUserId").toString());
        String customerName = request.getParameter("customerName");
        String customerNumber = request.getParameter("customerNumber");
        String customerEmail = request.getParameter("customerEmail");
        String customerAddress = request.getParameter("customerAddress");
        //Creating the Instance of the CustomerDAO
        CustomerDAO customerDAO = new CustomerDAO();
        CustomerPojo customer = new CustomerPojo();
        customer.setCurrentLoginUserId(currentLoginUserId);
        customer.setCustomerName(customerName);
        customer.setCustomerNumber(customerNumber);
        customer.setCustomerEmail(customerEmail);
        customer.setCustomerAddress(customerAddress);

        customerDAO.addCustomer(customer);

        response.sendRedirect("Customer.jsp"); // Redirect to the addCustomer.jsp page after adding customer
    }
}

