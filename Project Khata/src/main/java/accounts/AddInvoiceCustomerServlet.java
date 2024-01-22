package accounts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import items.ItemPojo;
import items.ItemDAO;
import java.io.FileInputStream;

@WebServlet("/AddInvoiceCustomerServlet")
public class AddInvoiceCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int currentLoginUserId = (int)request.getSession().getAttribute("CurrentLoginUserId");
        String customerName = request.getParameter("CustomerName");
        int prodId = Integer.parseInt(request.getParameter("prodId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String invoiceNo1 = generateInvoiceNumber();

        CustomerPurchaseDAO purchaseDAO = new CustomerPurchaseDAO();

        try {
            CustomerPurchasePojo existingPurchase = purchaseDAO.getPurchaseByCustomerAndProduct(currentLoginUserId, customerName, prodId);

            int sellPrice = Integer.parseInt(request.getParameter("SellPrice"));
            double newTotal = sellPrice * quantity;
            double newAmountPaid = Double.parseDouble(request.getParameter("amountPaid"));

            if (existingPurchase != null) {
                // Update the quantity for the existing entry
                int newQuantity = existingPurchase.getQuantity() + quantity;
                newTotal = sellPrice * newQuantity;
                newAmountPaid += existingPurchase.getAmountPaid();
                double newBalance = newTotal - newAmountPaid;

                existingPurchase.setQuantity(newQuantity);
                existingPurchase.setTotal(newTotal);
                existingPurchase.setAmountPaid(newAmountPaid);
                existingPurchase.setBalance(newBalance);
                existingPurchase.setInvoiceNo(invoiceNo1);
                purchaseDAO.updatePurchase(existingPurchase);

                // Update the quantity for the prodId in the items table
                ItemDAO  itemDAO = new ItemDAO();
                ItemPojo existingItem = itemDAO.getItemsById(prodId);
                if (existingItem != null) {
                    existingItem.setQuantity(existingItem.getQuantity() - quantity);
                    itemDAO.updateItem(existingItem);
                }
                
             // Call the InvoiceTemplate to generate the PDF
                InvoiceTemplate1 invoiceTemplate = new InvoiceTemplate1();
                String filePath = "C:\\Users\\HP\\eclipse-workspace\\Project Khata\\target\\invoice.pdf"; 
                invoiceTemplate.generateInvoicePDF1(invoiceNo1, filePath);

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
                
                //for the New Purchase
            } else {
    	   //Getting the current SystemDate.
    	   long millis=System.currentTimeMillis();  
        int currentLoginUserId1 = (int)request.getSession().getAttribute("CurrentLoginUserId");
        String customerName1 = request.getParameter("CustomerName");
        String customerNumber = request.getParameter("CustomerNumber");
        String customerEmail = request.getParameter("CustomerEmail");
        String customerAddress = request.getParameter("CustomerAddress");
        int prodId1 = Integer.parseInt(request.getParameter("prodId"));
        String prodName = request.getParameter("ProdName");
        int sellPrice1 = Integer.parseInt(request.getParameter("SellPrice"));
        int quantity1 = Integer.parseInt(request.getParameter("quantity"));
        double total =Double.parseDouble(request.getParameter("total"));
        double amountPaid = Double.parseDouble(request.getParameter("amountPaid"));
        double balance = Double.parseDouble(request.getParameter("balance")); 
        java.sql.Date date = new java.sql.Date(millis); 
        String invoiceNo = generateInvoiceNumber();
        String vendorName = (String)request.getSession().getAttribute("VendorName");
        String paymentMode = request.getParameter("PaymentMode");
        
        
        // Create an instance of CustomerPurchase and populate data
        CustomerPurchasePojo purchase = new CustomerPurchasePojo();
        purchase.setCurrentLoginUserId(currentLoginUserId1);
        purchase.setCustomerName(customerName1);
        purchase.setCustomerNumber(customerNumber);
        purchase.setCustomerEmail(customerEmail);
        purchase.setCustomerAddress(customerAddress);
        purchase.setProdId(prodId1);
        purchase.setProdName(prodName);
        purchase.setSellPrice(sellPrice1);
        purchase.setQuantity(quantity1);
        purchase.setTotal(total);
        purchase.setAmountPaid(amountPaid);
        purchase.setBalance(balance);
        purchase.setDate(date);
        purchase.setInvoiceNo(invoiceNo);
        purchase.setVendorName(vendorName);
        purchase.setPaymentMode(paymentMode);
        

        // Call method to add purchase data to database
        CustomerPurchaseDAO purchaseDAO1 = new CustomerPurchaseDAO();
        purchaseDAO1.addCustomerPurchase(purchase);
        
     // Update the quantity for the prodId in the items table
        ItemDAO itemDAO = new ItemDAO();
        ItemPojo existingItem = itemDAO.getItemsById(prodId);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() - quantity);
            itemDAO.updateItem(existingItem);
        }
        
     // Call the InvoiceTemplate to generate the PDF
        InvoiceTemplate1 invoiceTemplate = new InvoiceTemplate1();
        String filePath = "C:\\Users\\HP\\eclipse-workspace\\Project Khata\\target\\invoice.pdf"; // Specify the file path to save the PDF
        invoiceTemplate.generateInvoicePDF(invoiceNo, filePath);

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
        
    } catch (NumberFormatException e) {
           // Handle the parsing error
           e.printStackTrace(); 
       } catch (SQLException e) {
		
		e.printStackTrace();
	}
}
    //Generate the Invoice Number
    private String generateInvoiceNumber() {
        // Generate a random invoice number
        Random random = new Random();
        int randomNumber = random.nextInt(10000); 
        return  "RAC" + randomNumber;
    }
   
}
