<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import ="supplier.SupplierDAO" %>
<%@ page import = "supplier.SupplierPojo" %>
<%@ page import ="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<title>Invoice Customer</title>
<!-- CSS StyleSheet -->
<style>
    body {
        padding: 30px;
        margin-right: 350px;
        margin-left: 350px;
        background: linear-gradient(to left, #bdc3c7, #2c3e50);

    }
    .container{
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color:whitesmoke;
            padding: 20px;
            text-align: left;
    }

    label {
        font-weight: bold;
        margin-top: 10px;
        font-size: medium;
    }

    input[type="text"],
    select {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type="radio"] {
        margin-right: 10px;
    }

    input[type="submit"] {
        padding: 10px 20px;
        background-color: #007BFF;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    a input[type="button"]{
        padding: 10px 20px;
        background-color: #007BFF;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    input[type="button"]:hover{
        background-color: #0056b3;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>

<!-- Jquery for populating the supplier name in the drop down and fetch the supplier details based on the selected supplier -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        $('#SupplierName').change(function () {
            var selectedSupplierName = $(this).val();

            $.ajax({
                url: 'GetSupplierDetailsServlet', // Your servlet URL
                method: 'GET',
                data: { supplierName: selectedSupplierName },
                success: function (data) {
                    // Assuming data is a JSON object with customer information
                    $('#SupplierNumber').val(data.supplierNumber);
                    $('#SupplierEmail').val(data.supplierEmail);
                    $('#SupplierAddress').val(data.supplierAddress);
                },
                error: function () {
                    console.error('Error fetching customer details');
                }
            });
        });
    });
</script>
<!-- Script for handling the radio button events and redirecting to another page -->
<script type="text/javascript">
function handleRadioClick(radioValue) {
    var redirectUrl;

    // Determine the redirect URL based on the selected radio button value
    if (radioValue === "Card") {
        redirectUrl = "Card.jsp"; // Replace with the URL for the first page
    } else if (radioValue === "Online") {
        redirectUrl = "Online.jsp"; // Replace with the URL for the second page
    }

    // Open the determined URL in a new tab/window
    if (redirectUrl) {
        window.open(redirectUrl, '_blank');
    }
}
</script>


</head>
<body>
<div class="container">
	<%int currentLoginUserId = (int)request.getSession().getAttribute("CurrentLoginUserId"); %>
	<form action="AddSupplierPurchaseServlet" method="post">
		<label for="SupplierName">Supplier Name:</label>
		 <select name="SupplierName" id="SupplierName">
			<option value="">Select Customer</option>
			<!-- Populate with customer names from the customers table -->
			<%
			SupplierDAO customerDAO = new SupplierDAO();
			List<SupplierPojo> customerList = customerDAO.getAllSuppliersByUserId(currentLoginUserId); // Replace with your method to retrieve all customers
			for (SupplierPojo customer : customerList) {
			%>
			<option value="<%=customer.getSupplierName()%>"><%=customer.getSupplierName()%></option>
			<%
			}
			%>
		</select> <br>
		<label for="SupplierNumber">Supplier Number:</label>
		 <input type="text" name="SupplierNumber" id="SupplierNumber" readonly><br>

		<label for="SupplierEmail">Supplier Email:</label>
		 <input type="text"  name="SupplierEmail" id="SupplierEmail" readonly><br>
		 
		 <label for="SupplierAddress">Supplier Address:</label> 
		 <input type="text" name="SupplierAddress" id="SupplierAddress" readonly><br>

		<label for="prodName">Product Name:</label>
        <input type="text" id="prodName" name="prodName" required><br>

        <label for="costPrice">Cost Price:</label>
        <input type="text" id="costPrice" name="costPrice" required><br>
        
        <label for="costPrice">Sell Price:</label>
        <input type="text" id="sellPrice" name="sellPrice" required><br>

        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" required><br>

        <label for="total">Total:</label>
        <input type="text" id="total" name="total"readonly><br>

        <label for="amountPaid">Amount Paid:</label>
        <input type="text" id="amountPaid" name="amountPaid" required><br>

        <label for="balance">Balance:</label>
        <input type="text" id="balance" name="balance" readonly><br>
		
		<input type="radio" id="PaymentMode" name="PaymentMode" value="Cash">
		<label for="Cash">Cash</label><br>
		<label for="radioOption1">
            <input type="radio" id="radioOption1" name="PaymentMode" value="Card" onclick="handleRadioClick('Card')"> Card
        </label><br>
        <label for="radioOption2">
            <input type="radio" id="radioOption2" name="PaymentMode" value="Online" onclick="handleRadioClick('Online')"> Online
        </label>
		
		<br>
		<input type="submit" value="Generate Invoice">
	</form>
</div>
	<br>
	<a class="a1" href="Purchase.jsp"><input class="btn btn-primary" type="button" value="Go back"></a>
<!-- JavaScript for calculations (Separate script tag) -->
<script>
        // Function to calculate Total and Balance
        function calculate() {
            var costPrice = parseFloat(document.getElementById("costPrice").value) || 0;
            var quantity = parseFloat(document.getElementById("quantity").value) || 0;
            var amountPaid = parseFloat(document.getElementById("amountPaid").value) || 0;

            var total = costPrice * quantity;
            var balance = total - amountPaid;

            document.getElementById("total").value = total.toFixed(2);
            document.getElementById("balance").value = balance.toFixed(2);
        }

        // Attach the calculate function to the input fields' input event
        document.getElementById("costPrice").addEventListener("input", calculate);
        document.getElementById("quantity").addEventListener("input", calculate);
        document.getElementById("amountPaid").addEventListener("input", calculate);
</script>
	
    
</body>
</html>
