package accounts;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.text.SimpleDateFormat;
public class InvoiceTemplate1 {

    private static final float TABLE_X_START = 50;
    private static final float TABLE_WIDTH = 450;
    private static final float TABLE_COL_WIDTH = TABLE_WIDTH / 4;
    private static final float TABLE_ROW_HEIGHT = 20;

    //Method for generating the Pdf file
    public void generateInvoicePDF(String invoiceNo,String filePath) throws SQLException {
        try (PDDocument pdfDocument = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            pdfDocument.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);

         // Fetch data from the database based on invoiceNo
            CustomerPurchaseDAO customerPurchaseDAO = new CustomerPurchaseDAO();
            List<CustomerPurchasePojo> customerPurchases = customerPurchaseDAO.fetchCustomerPurchasesFromDatabase(invoiceNo); // Replace with your database retrieval logic
            // Draw the header with data from the first purchase in the list
            drawHeader(contentStream, customerPurchases.get(0));

            // Draw the table for purchase details
            drawPurchaseTable(contentStream, customerPurchases);

            // Draw the footer with total, amountPaid, and balance
            drawFooter(contentStream,  customerPurchases);
            
            // Draw a border around the entire PDF
            drawBorder(contentStream, page);

            contentStream.close();
            pdfDocument.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Draw header for the pdf
    private void drawHeader(PDPageContentStream contentStream, CustomerPurchasePojo purchase) throws IOException {
    	//Heading section
    	contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
        contentStream.newLineAtOffset(260,780 );
        contentStream.showText("Invoice");
        contentStream.endText();
     // Draw header cells
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(400, 730); // Adjust X and Y coordinates
        contentStream.showText("Invoice No: " + purchase.getInvoiceNo());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Vendor Name: " + purchase.getVendorName());
     // Format the date using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(purchase.getDate());
        contentStream.newLineAtOffset(0, -40);
        contentStream.showText("Date: " + formattedDate);
        contentStream.endText();
        
        //Customer Details
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 730);
        contentStream.showText("Customer Name: " + purchase.getCustomerName());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Number: " + purchase.getCustomerNumber());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Email: " + purchase.getCustomerEmail());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Address: " + purchase.getCustomerAddress());
        contentStream.endText();

        // Draw a header line
        contentStream.setLineWidth(1);
        contentStream.moveTo(50, 650); // Adjust starting point
        contentStream.lineTo(550, 650); // Adjust ending point
        contentStream.stroke();
    }

    // Draw the Purchase Table
    private void drawPurchaseTable(PDPageContentStream contentStream, List<CustomerPurchasePojo> customerPurchases) throws IOException {
        float yPosition = 600; // Adjust Y coordinate for the start of the table
        int rowCount = 0;

        for (CustomerPurchasePojo purchase : customerPurchases) {
            if (rowCount == 0) {
                // Draw table headers
                drawTableHeaderRow(contentStream, yPosition);
                yPosition -= TABLE_ROW_HEIGHT;
            }

            // Draw table data for each purchase
            drawTableRow(contentStream, purchase, yPosition);
            yPosition -= TABLE_ROW_HEIGHT;
            rowCount++;
            
            
            
        }
       
    }
    //draw the table header row
    private void drawTableHeaderRow(PDPageContentStream contentStream, float yPosition) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
        contentStream.beginText();
        contentStream.newLineAtOffset(TABLE_X_START, yPosition);
        contentStream.showText("Product ID");
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText("Product Name");
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText("Quantity");
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText("Rate");
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText("Total");
       
        contentStream.endText();
    }
    //Draw the Table Row
    private void drawTableRow(PDPageContentStream contentStream, CustomerPurchasePojo purchase, float yPosition) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(TABLE_X_START, yPosition);
        contentStream.showText(String.valueOf(purchase.getProdId()));
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText(purchase.getProdName());
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText(String.valueOf(purchase.getQuantity()));
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText(String.valueOf(purchase.getSellPrice()));
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText(String.valueOf(purchase.getTotal()));
       
       
        contentStream.endText();
    }
    
    //Draw the footer section of the pdf file
    private void drawFooter(PDPageContentStream contentStream, List<CustomerPurchasePojo> customerPurchases) throws IOException {
        float yPosition = 120; // Adjust Y coordinate for the footer
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        
        // Display the data from the customerPurchases list in the footer
        for (CustomerPurchasePojo purchase : customerPurchases) {
            contentStream.beginText();
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("Total Amount: " + purchase.getTotal());
            yPosition -= TABLE_ROW_HEIGHT;
            contentStream.newLineAtOffset(0, -TABLE_ROW_HEIGHT);
            contentStream.showText("Amount Paid: " + purchase.getAmountPaid());
            yPosition -= TABLE_ROW_HEIGHT;
            contentStream.newLineAtOffset(0, -TABLE_ROW_HEIGHT);
            contentStream.showText("Balance Amount: " + purchase.getBalance());
            contentStream.newLineAtOffset(0, -TABLE_ROW_HEIGHT);
            contentStream.showText("Mode of Payment: " + purchase.getPaymentMode());
            contentStream.newLineAtOffset(370, 0);
            contentStream.showText("Signature: "+ "  ");
            contentStream.endText();
            //Thank you Message
            contentStream.beginText();
            contentStream.newLineAtOffset(400, 100);
            contentStream.showText("Thanks for comming ");
           
            contentStream.endText();
        }
    }

    //Method to draw the Border for the pdf
    private void drawBorder(PDPageContentStream contentStream, PDPage page) throws IOException {
        // Draw a border around the page
        contentStream.setLineWidth(1);
        contentStream.moveTo(40, 40); // Top-left corner
        contentStream.lineTo(40, page.getMediaBox().getHeight() - 40); // Bottom-left corner
        contentStream.lineTo(page.getMediaBox().getWidth() - 40, page.getMediaBox().getHeight() - 40); // Bottom-right corner
        contentStream.lineTo(page.getMediaBox().getWidth() - 40, 40); // Top-right corner
        contentStream.closePath();
        contentStream.stroke();
    }
    
    
    //======================================================================================================//
    
    //Generate Pdf after Updating the CustomerPurchase Value
    public void generateInvoicePDF1(String invoiceNo1,String filePath) throws SQLException {
        try (PDDocument pdfDocument = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            pdfDocument.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);

         // Fetch data from the database based on invoiceNo
            CustomerPurchaseDAO customerPurchaseDAO = new CustomerPurchaseDAO();
            List<CustomerPurchasePojo> customerPurchases = customerPurchaseDAO.fetchCustomerPurchasesFromDatabase(invoiceNo1); // Replace with your database retrieval logic
            // Draw the header with data from the first purchase in the list
            drawHeader1(contentStream, customerPurchases.get(0));

            // Draw the table for purchase details
            drawPurchaseTable1(contentStream, customerPurchases);
            
            // Draw the footer with total, amountPaid, and balance
            drawFooter1(contentStream,  customerPurchases);
            
            // Draw a border around the entire PDF
            drawBorder1(contentStream, page);

            contentStream.close();
            pdfDocument.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawHeader1(PDPageContentStream contentStream, CustomerPurchasePojo purchase) throws IOException {
    	//Heading section
    	contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
        contentStream.newLineAtOffset(260,780 );
        contentStream.showText("Invoice");
        contentStream.endText();
     // Draw header cells
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(400, 730); // Adjust X and Y coordinates
        contentStream.showText("Invoice No: " + purchase.getInvoiceNo());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Vendor Name: " + purchase.getVendorName());
     // Format the date using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(purchase.getDate());
        contentStream.newLineAtOffset(0, -40);
        contentStream.showText("Date: " + formattedDate);
        contentStream.endText();
        
        //Customer Details
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 730);
        contentStream.showText("Customer Name: " + purchase.getCustomerName());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Number: " + purchase.getCustomerNumber());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Email: " + purchase.getCustomerEmail());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Address: " + purchase.getCustomerAddress());
        // ... Show other header data ...
        contentStream.endText();

        // Draw a header line
        contentStream.setLineWidth(1);
        contentStream.moveTo(50, 650); // Adjust starting point
        contentStream.lineTo(550, 650); // Adjust ending point
        contentStream.stroke();
    }

    // ... Add methods for drawing the table and purchase details here ...
    private void drawPurchaseTable1(PDPageContentStream contentStream, List<CustomerPurchasePojo> customerPurchases) throws IOException {
        float yPosition = 600; // Adjust Y coordinate for the start of the table
        int rowCount = 0;

        for (CustomerPurchasePojo purchase : customerPurchases) {
            if (rowCount == 0) {
                // Draw table headers
                drawTableHeaderRow1(contentStream, yPosition);
                yPosition -= TABLE_ROW_HEIGHT;
            }

            // Draw table data for each purchase
            drawTableRow1(contentStream, purchase, yPosition);
            yPosition -= TABLE_ROW_HEIGHT;
            rowCount++;
            
            
            
        }
       
    }

    private void drawTableHeaderRow1(PDPageContentStream contentStream, float yPosition) throws IOException {
        // Customize this method to draw headers for columns
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
        contentStream.beginText();
        contentStream.newLineAtOffset(TABLE_X_START, yPosition);
        contentStream.showText("Product ID");
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText("Product Name");
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText("Quantity");
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText("Rate");
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText("Total");
       
        // Add more headers as needed
        contentStream.endText();
    }

    private void drawTableRow1(PDPageContentStream contentStream, CustomerPurchasePojo purchase, float yPosition) throws IOException {
        // Customize this method to draw data for each row
        contentStream.setFont(PDType1Font.HELVETICA, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(TABLE_X_START, yPosition);
        contentStream.showText(String.valueOf(purchase.getProdId()));
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText(purchase.getProdName());
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText(String.valueOf(purchase.getQuantity()));
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText(String.valueOf(purchase.getSellPrice()));
        contentStream.newLineAtOffset(TABLE_COL_WIDTH, 0);
        contentStream.showText(String.valueOf(purchase.getTotal()));
       
        // Add more data as needed
        contentStream.endText();
    }
    private void drawFooter1(PDPageContentStream contentStream, List<CustomerPurchasePojo> customerPurchases) throws IOException {
        float yPosition = 120; // Adjust Y coordinate for the footer
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        
        // Display the data from the customerPurchases list in the footer
        for (CustomerPurchasePojo purchase : customerPurchases) {
            contentStream.beginText();
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("Total Amount: " + purchase.getTotal());
            yPosition -= TABLE_ROW_HEIGHT;
            contentStream.newLineAtOffset(0, -TABLE_ROW_HEIGHT);
            contentStream.showText("Amount Paid: " + purchase.getAmountPaid());
            yPosition -= TABLE_ROW_HEIGHT;
            contentStream.newLineAtOffset(0, -TABLE_ROW_HEIGHT);
            contentStream.showText("Balance Amount: " + purchase.getBalance());
            contentStream.newLineAtOffset(0, -TABLE_ROW_HEIGHT);
            contentStream.showText("Mode of Payment: " + purchase.getPaymentMode());
            contentStream.newLineAtOffset(370, 0);
            contentStream.showText("Signature: "+ "  ");
            contentStream.endText();
            //Thank you Message
            contentStream.beginText();
            contentStream.newLineAtOffset(400, 100);
            contentStream.showText("Thanks for comming ");
           
            // Add more data fields as needed
            contentStream.endText();
        }
    }


    private void drawBorder1(PDPageContentStream contentStream, PDPage page) throws IOException {
        // Draw a border around the page
        contentStream.setLineWidth(1);
        contentStream.moveTo(40, 40); // Top-left corner
        contentStream.lineTo(40, page.getMediaBox().getHeight() - 40); // Bottom-left corner
        contentStream.lineTo(page.getMediaBox().getWidth() - 40, page.getMediaBox().getHeight() - 40); // Bottom-right corner
        contentStream.lineTo(page.getMediaBox().getWidth() - 40, 40); // Top-right corner
        contentStream.closePath();
        contentStream.stroke();
    }


   
}
