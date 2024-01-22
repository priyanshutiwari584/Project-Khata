package supplierPurchase;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/DownloadInvoiceServlet1")
public class DownloadInvoiceServlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the invoiceNo parameter from the request
        String invoiceNo = request.getParameter("invoiceNo");
        InvoiceTemplate invoiceTemplate = new InvoiceTemplate();
        String filePath = "C:\\\\Users\\\\HP\\\\eclipse-workspace\\\\Project Khata\\\\target\\\\invoice.pdf"; 
        // Generate the PDF file based on invoiceNo 
        try {
			invoiceTemplate.generateInvoicePDF1(invoiceNo, filePath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Set the content type to indicate a PDF file
        response.setContentType("application/pdf");

        // Set the response header to indicate the file name
        response.setHeader("Content-Disposition", "inline; filename=invoice.pdf");

        // Read the PDF file and send it as the response
        FileInputStream inputStream = new FileInputStream(filePath);
        ServletOutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.flush();
        outputStream.close();
	}

}
