package supplierPurchase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import items.ItemDAO;
import items.ItemPojo;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddSupplierPurchaseServlet")
public class AddSupplierPurchaseServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long millis=System.currentTimeMillis();
		int currentLoginUserId = (int)request.getSession().getAttribute("CurrentLoginUserId");
        String supplierName = request.getParameter("SupplierName");
        String prodName = request.getParameter("prodName");
        int costPrice = Integer.parseInt(request.getParameter("costPrice"));
        int sellPrice = Integer.parseInt(request.getParameter("sellPrice")); 
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String invoiceNo = generateInvoiceNumber();
        java.sql.Date date = new java.sql.Date(millis);

        SupplierPurchaseDAO purchaseDAO = new SupplierPurchaseDAO();

        try {
            SupplierPurchase existingPurchase = purchaseDAO.getSupplierByCustomerAndProduct(currentLoginUserId, supplierName, prodName);

            
            double newTotal = costPrice * quantity;
            double newAmountPaid =Float.parseFloat(request.getParameter("amountPaid"));

            if (existingPurchase != null) {
                // Update the quantity for the existing entry
                int newQuantity = existingPurchase.getQuantity() + quantity;
                newTotal = costPrice * newQuantity;
                newAmountPaid += existingPurchase.getAmountPaid();
                double newBalance = newTotal - newAmountPaid;

                existingPurchase.setQuantity(newQuantity);
                existingPurchase.setTotal(newTotal);
                existingPurchase.setAmountPaid(newAmountPaid);
                existingPurchase.setBalance(newBalance);
                existingPurchase.setInvoiceNo(invoiceNo);
                existingPurchase.setDate(date);
                purchaseDAO.updatePurchase(existingPurchase);
                
                //Updating the Items table
                ItemDAO itemDAO = new ItemDAO();

                // Check if the product name (prodName) already exists in the items table
                ItemPojo existingItem = itemDAO.getItemByProdName(prodName);

                if (existingItem != null) {
                    // Product already exists, update the quantity, costPrice, and sellPrice
                    int newQuantity1 = existingItem.getQuantity() + quantity;
                    existingItem.setQuantity(newQuantity1);
                    existingItem.setCostPrice(costPrice);
                    existingItem.setSellPrice(sellPrice);

                    // Update the existing item in the database
                    boolean updated = itemDAO.updateItem1(existingItem);

                    if (updated) {
                        // Successfully updated the existing item
                    	System.out.println("Updated");
                        
                    } else {
                        // Failed to update the existing item
                    	System.out.println("Error");
                    }
                } 
                
             // Call the InvoiceTemplate to generate the PDF
                InvoiceTemplate invoiceTemplate = new InvoiceTemplate();
                String filePath = "C:\\\\Users\\\\HP\\\\eclipse-workspace\\\\Project Khata\\\\target\\\\invoice.pdf"; 
                invoiceTemplate.generateInvoicePDF(invoiceNo, filePath);

                // Set the content type to indicate a PDF file
                response.setContentType("application/pdf");

                // Set the response header to indicate the file name
                response.setHeader("Content-Disposition", "inline; filename=invoice.pdf");

                // Read the PDF file and send it as the response
                FileInputStream inputStream = new FileInputStream(filePath);
                ServletOutputStream outputStream1 = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream1.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream1.flush();
                outputStream1.close();
            
                
                
            }else {
		//Getting the current SystemDate.
        long millis1=System.currentTimeMillis(); 
        int currentLoginUserId1 = (int)request.getSession().getAttribute("CurrentLoginUserId");
        String supplierName1 = request.getParameter("SupplierName");
        String supplierNumber = request.getParameter("SupplierNumber");
        String supplierEmail = request.getParameter("SupplierEmail");
        String supplierAddress = request.getParameter("SupplierAddress");
        String prodName1 = request.getParameter("prodName");
        int costPrice1 = Integer.parseInt(request.getParameter("costPrice"));
        int sellPrice1 = Integer.parseInt(request.getParameter("sellPrice"));
        int quantity1 = Integer.parseInt(request.getParameter("quantity"));
        double total = Double.parseDouble(request.getParameter("total"));
        double amountPaid = Double.parseDouble(request.getParameter("amountPaid"));
        double balance = Double.parseDouble(request.getParameter("balance"));
        java.sql.Date date1 = new java.sql.Date(millis1); 
        String invoiceNo1 = generateInvoiceNumber();
        String paymentMode = request.getParameter("PaymentMode");

        // Create a SupplierPurchase object with the form data
        SupplierPurchase supplierPurchase = new SupplierPurchase();
        supplierPurchase.setCurrentLoginUserId(currentLoginUserId1);
        supplierPurchase.setSupplierName(supplierName1);
        supplierPurchase.setSupplierNumber(supplierNumber);
        supplierPurchase.setSupplierEmail(supplierEmail);
        supplierPurchase.setSupplierAddress(supplierAddress);
        supplierPurchase.setProdName(prodName1);
        supplierPurchase.setCostPrice(costPrice1);
        supplierPurchase.setSellPrice(sellPrice1);
        supplierPurchase.setQuantity(quantity1);
        supplierPurchase.setTotal(total);
        supplierPurchase.setAmountPaid(amountPaid);
        supplierPurchase.setBalance(balance);
        supplierPurchase.setDate(date1); // Convert date string to a Date object
        supplierPurchase.setInvoiceNo(invoiceNo1);
        supplierPurchase.setPaymentMode(paymentMode);

        // Add the supplier purchase to the database
        SupplierPurchaseDAO supplierPurchaseDAO = new SupplierPurchaseDAO();
        supplierPurchaseDAO.addSupplierPurchase(supplierPurchase);

        ItemDAO itemDAO = new ItemDAO();

        // Check if the product name (prodName) already exists in the items table
        ItemPojo existingItem = itemDAO.getItemByProdName(prodName);

        if (existingItem != null) {
            // Product already exists, update the quantity, costPrice, and sellPrice
            int newQuantity = existingItem.getQuantity() + quantity;
            existingItem.setQuantity(newQuantity);
            existingItem.setCostPrice(costPrice);
            existingItem.setSellPrice(sellPrice);

            // Update the existing item in the database
            boolean updated = itemDAO.updateItem1(existingItem);

            if (updated) {
                // Successfully updated the existing item
            	System.out.println("Updated");
               
            } else {
                // Failed to update the existing item
            	System.out.println("Error");
                
            }
        } else {
            // Product does not exist, create a new item
           ItemPojo newItem = new ItemPojo(0, prodName, quantity, costPrice, sellPrice, currentLoginUserId);

            // Add the new item to the database
            boolean added = itemDAO.addItem(newItem);

            if (added) {
                // Successfully added the new item
                System.out.println("Added");
            } else {
                // Failed to add the new item
                System.out.println("Error");
            }
        }
        
     // Call the InvoiceTemplate to generate the PDF
        InvoiceTemplate invoiceTemplate = new InvoiceTemplate();
        String filePath = "C:\\\\Users\\\\HP\\\\eclipse-workspace\\\\Project Khata\\\\target\\\\invoice.pdf"; 
        invoiceTemplate.generateInvoicePDF1(invoiceNo1, filePath);

        // Set the content type to indicate a PDF file
        response.setContentType("application/pdf");

        // Set the response header to indicate the file name
        response.setHeader("Content-Disposition", "inline; filename=invoice.pdf");

        // Read the PDF file and send it as the response
        FileInputStream inputStream = new FileInputStream(filePath);
        ServletOutputStream outputStream2 = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream2.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream2.flush();
        outputStream2.close();
    
             
            }
        }
	   
        catch(SQLException e){
            	e.printStackTrace();
            }catch(Exception r) {
            	r.printStackTrace();
            }  
	}   
        
     //Generate the Invoice Number
    private String generateInvoiceNumber() {
        // Generate a random invoice number
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Adjust the range as needed
        return  "QSR" + randomNumber;
    }
}
