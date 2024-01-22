package items;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/GetProductDetailsServlet")
public class GetProductDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int prodId = Integer.parseInt(request.getParameter("prodId"));

        // Fetch product details using the prodId
        ItemDAO itemDAO = new ItemDAO();
        ItemPojo item = itemDAO.getItemsById(prodId);

        // Convert item object to JSON and send as response
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("prodName", item.getProdName());
        jsonResponse.addProperty("sellPrice", item.getSellPrice()); // No need to convert to string
        // Add other product-related properties

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(jsonResponse));
        out.close();
    }
    
}

