package signup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session object
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Invalidate the session (destroy it)
            session.invalidate();
        }

        // Set a message to indicate successful logout
        request.setAttribute("logoutMessage", "Logout Successfully");

        // Forward to the login page (login.jsp)
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
