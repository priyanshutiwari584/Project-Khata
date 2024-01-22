package items;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/FetchProductDataServlet")
public class FetchProductDataServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get the currentLoginUserId from the request parameter
            int currentLoginUserId = Integer.parseInt(request.getParameter("currentLoginUserId"));

            // Initialize  ItemDAO
            ItemDAO itemDAO = new ItemDAO();

            // Fetch product data based on currentLoginUserId
            List<ItemPojo> items = itemDAO.getItemsBysUserId(currentLoginUserId);

            // Create JSON objects and arrays to represent the data
            JsonObject data = new JsonObject();
            JsonArray labelsArray = new JsonArray();
            JsonArray quantitiesArray = new JsonArray();

            for (ItemPojo item : items) {
                labelsArray.add(item.getProdName());
                quantitiesArray.add(item.getQuantity());
            }

            data.add("labels", labelsArray);
            data.add("quantities", quantitiesArray);

            // Serialize data to JSON using Gson
            Gson gson = new Gson();
            String jsonData = gson.toJson(data);

            // Set response content type and write the JSON response
            response.setContentType("application/json");
            response.getWriter().write(jsonData);

            // Close resources
            //itemDAO.close();
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid input");
        }
    }
}
