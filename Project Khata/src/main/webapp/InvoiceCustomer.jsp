<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="customer.CustomerPojo"%>
<%@page import="customer.CustomerDAO"%>
<%@page import="items.ItemDAO"%>
<%@page import="items.ItemPojo"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>Invoice Customer</title>
<!-- CSS StyleSheet -->
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


</head>
<body>
<div class="container">
	<%int currentLoginUserId = (int)request.getSession().getAttribute("CurrentLoginUserId"); %>
	<form action="AddInvoiceCustomerServlet" method="post">
		<label for="CustomerName">Supplier Name:</label>
		 <select name="CustomerName" id="CustomerName">
			<option value="">Select Customer</option>
			<!-- Populate with customer names from the customers table -->
			<%
			CustomerDAO customerDAO = new CustomerDAO();
			List<CustomerPojo> customerList = customerDAO.getCustomersByUserId(currentLoginUserId); // Replace with your method to retrieve all customers
			for (CustomerPojo customer : customerList) {
			%>
			<option value="<%=customer.getCustomerName()%>"><%=customer.getCustomerName()%></option>
			<%
			}
			%>
		</select> <br>
		<label for="CustomerNumber">Supplier Number:</label>
		 <input type="text" name="CustomerNumber" id="CustomerNumber" readonly><br>

		<label for="CustomerEmail">Supplier Email:</label>
		 <input type="text"  name="CustomerEmail" id="CustomerEmail" readonly><br>
		 
		 <label for="CustomerAddress">Supplier Address:</label> 
		 <input type="text" name="CustomerAddress" id="CustomerAddress" readonly><br>

		<label for="prodId">Product ID:</label>
		 <select name="prodId" id="prodId">
			<option value="">Select Product</option>
			<!-- Populate with product IDs from the items table -->
			<%
			ItemDAO itemDAO = new ItemDAO();
			List<ItemPojo> itemList = itemDAO.getItemsByUserId(currentLoginUserId); // Replace with your method to retrieve all items
			for (ItemPojo item : itemList) {
			%>
			<option value="<%=item.getProdId()%>"><%=item.getProdId()%></option>
			<%
			}
			%>
		</select> <br>
		<label for="ProdName">Product Name:</label>
		 <input type="text" name="ProdName" id="ProdName" readonly><br>
		 
		<label for="sellPrice">Selling Price:</label> 
		<input type="text" name="SellPrice" id="SellPrice" readonly><br>
		
		<label for="Quantity">Quantity:</label>
		<input type="text" name="quantity" id="quantity" placeholder="Quantity" oninput="calculateTotal();"><br>
		
		<label for="Quantity">Total:</label>
		<input type="text" name="total" id="total" placeholder="Total" readonly><br>
		
		<label for="Quantity">Amount Paid:</label>
		<input type="text" name="amountPaid" id="amountPaid" placeholder="AmountPaid" oninput="calculateBalance();"><br>
		
		<label for="Quantity">Balance:</label>
		<input type="text" name="balance" id="balance" placeholder="Balance" readonly><br>
		
		<input type="radio" id="PaymentMode" name="PaymentMode" value="Cash">
		<label for="Cash">Cash</label><br>
		
        <label for="radioOption1">
            <input type="radio" id="radioOption1" name="PaymentMode" value="Card" onclick="handleRadioClick('Card')"> Card
        </label>
        <label for="radioOption2">
            <input type="radio" id="radioOption2" name="PaymentMode" value="Online" onclick="handleRadioClick('Online')"> Online
        </label>
    	
		
		<br>
		<input type="submit" value="Generate Invoice">
	</form>
</div>
<br>
	<a class="a1" href="Sales.jsp"><input class="btn btn-primary" type="button" value="Go back"></a>
    


    
<!-- Jquery cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- For Calculating Total and Balance Amount -->
<script type="text/javascript">
function calculateTotal() {
    var sellPrice = parseFloat(document.getElementById("SellPrice").value);
    var quantity = parseFloat(document.getElementById("quantity").value);
    var total = sellPrice * quantity;
    document.getElementById("total").value = total.toFixed(2);
}

function calculateBalance() {
    var total = parseFloat(document.getElementById("total").value);
    var amountPaid = parseFloat(document.getElementById("amountPaid").value);
    var balance = total - amountPaid;
    document.getElementById("balance").value = balance.toFixed(2);
}
</script>
<!-- ... Jquery for the populating the customer name in drop down and fetching the Customer Details of selected customer from the dropdown menu-->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        $('#CustomerName').change(function () {
            var selectedCustomerName = $(this).val();

            $.ajax({
                url: 'GetCustomerDetailsServlet', // Your servlet URL
                method: 'GET',
                data: { customerName: selectedCustomerName },
                success: function (data) {
                    // Assuming data is a JSON object with customer information
                    $('#CustomerNumber').val(data.customerNumber);
                    $('#CustomerEmail').val(data.customerEmail);
                    $('#CustomerAddress').val(data.customerAddress);
                },
                error: function () {
                    console.error('Error fetching customer details');
                }
            });
        });
    });
</script>
<!-- Jquery for the Populating the Product Id in drop down and fetch the product details based on the selected drop down -->
<script>
    $(document).ready(function () {
        $('#prodId').change(function () {
            var selectedProdId = $(this).val();

            $.ajax({
                url: 'GetProductDetailsServlet',
                method: 'GET',
                data: { prodId: selectedProdId },
                success: function (data) {
                    $('#ProdName').val(data.prodName);
                    $('#SellPrice').val(data.sellPrice);
                    // Rest of your code
                },
                error: function () {
                    console.error('Error fetching product details');
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
        redirectUrl = "Card1.jsp"; // Replace with the URL for the first page
    } else if (radioValue === "Online") {
        redirectUrl = "Online1.jsp"; // Replace with the URL for the second page
    }

    // Open the determined URL in a new tab/window
    if (redirectUrl) {
        window.open(redirectUrl, '_blank');
    }
}
</script>

</body>
</html>