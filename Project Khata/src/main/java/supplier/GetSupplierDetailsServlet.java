package supplier;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;


@WebServlet("/GetSupplierDetailsServlet")
public class GetSupplierDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierName = request.getParameter("supplierName");

        try{// Fetch customer details using the customerName
        SupplierDAO supplierDAO = new SupplierDAO();
        SupplierPojo supplier = supplierDAO.getSupplierByName(supplierName);
		

        // Convert customer object to JSON and send as response
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(supplier);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonResponse);
        out.close();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
