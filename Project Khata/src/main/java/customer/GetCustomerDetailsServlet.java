package customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;


@WebServlet("/GetCustomerDetailsServlet")
public class GetCustomerDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerName = request.getParameter("customerName");

        // Fetch customer details using the customerName
        CustomerDAO customerDAO = new CustomerDAO();
        CustomerPojo customer = customerDAO.getCustomerByName(customerName);

        // Convert customer object to JSON and send as response
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(customer);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonResponse);
        out.close();
    }
}
