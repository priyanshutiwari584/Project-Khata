<!DOCTYPE html>
<%@page import="customer.CustomerDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="customer.CustomerPojo"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="MainStyle.css">

<!----===== Boxicons CSS ===== -->
<link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css'
	rel='stylesheet'>


<title>Dashboard Sidebar Menu</title>

<style>
table {
	width: 100%;
	border-collapse: collapse;
}

tr:hover {
	background-color: cyan;
	color:black;
}

th, td {
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
	color:black;
}

@media screen and (max-width: 400px) {
	th, td {
		font-size: 14px;
	}
}
</style>

</head>

<body>
	<nav class="sidebar close">
		<header>
			<div class="image-text">
				<span class="image"> <img src="logo.png" alt="">
				</span>

				<div class="text logo-text">
					<span class="name">KhataApp</span> <span class="profession">Accounts</span>
				</div>
			</div>

			<i class='bx bx-chevron-right toggle'></i>
		</header>

		<div class="menu-bar">
			<div class="menu">
			
				<li class="search-box">
				<i class='bx bx-layout icon'></i>
                     <span class="text" > 
                     <% String VendorName = (String) request.getSession().getAttribute("VendorName");%>
                     <%= VendorName %>
                     </span>
                </li>
				<ul class="menu-links">
					<li class="nav-link"><a href="Dashboard.jsp"> <i
							class='bx bx-home-alt icon'></i> <span class="text nav-text">Dashboard</span>
					</a></li>
					<hr>
					<li class="nav-link"><a href="#"> <i
							class='bx bx-user icon'></i> <span class="text nav-text">Customers</span>
					</a></li>

					<li class="nav-link"><a href="Supplier.jsp"> <i
							class='bx bx-user-circle icon'></i> <span class="text nav-text">Suppliers</span>
					</a></li>
					<hr>
					<li class="nav-link"><a href="Sales.jsp"> <i
							class='bx bx-bar-chart-alt-2 icon'></i> <span
							class="text nav-text">Sales</span>
					</a></li>

					<li class="nav-link"><a href="Purchase.jsp"> <i
							class='bx bxs-purchase-tag icon'></i> <span class="text nav-text">Purchase</span>
					</a></li>

					<li class="nav-link"><a href="Items.jsp"> <i
							class='bx bx-menu icon'></i> <span class="text nav-text">Items</span>
					</a></li>

					<li class="nav-link"><a href="CustomerInvoice.jsp"> <i
							class='bx bx-check-shield icon'></i> <span class="text nav-text">Invoice-Customer</span>
					</a></li>
					
					<li class="nav-link"><a href="SupplierInvoice.jsp"> <i
							class='bx bx-check-shield icon'></i> <span class="text nav-text">Invoice-Supplier</span>
					</a></li>

				</ul>
			</div>
			<hr>
			<div class="bottom-content">
				 <li class="#">
    				<form id="logoutForm" action="LogoutServlet" method="post" style="display: none;">
        				<input type="submit" value="Logout">
    				</form>
    				<a href="#" onclick="document.getElementById('logoutForm').submit(); return false;">
        				<i class='bx bx-log-out icon'></i>
        					<span class="text nav-text">Logout</span>
    				</a>
				</li>

				<li class="mode">
					<div class="sun-moon">
						<i class='bx bx-moon icon moon'></i> <i class='bx bx-sun icon sun'></i>
					</div> <span class="mode-text text">Dark mode</span>

					<div class="toggle-switch">
						<span class="switch"></span>
					</div>
				</li>

			</div>
		</div>
	</nav>

	<nav class="nav">
		<%-- Retrieve user's full name from session --%>
		<%
		String FullName = (String) request.getSession().getAttribute("fullName");
		//String UserName = (String) request.getSession().getAttribute("CurrentLoginUserId");
		%>

		<%-- Get the current hour of the day --%>
		<%
		java.util.Date now = new java.util.Date();
		%>
		<%
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
		%>
		<%
		int hour = Integer.parseInt(sdf.format(now));
		%>

		<%-- Generate greeting message based on the time of day --%>
		<%
		String greeting = "";
		%>
		<%
		if (hour >= 0 && hour < 12) {
		%>
		<%
		greeting = "Good morning";
		%>
		<%
		} else if (hour >= 12 && hour < 18) {
		%>
		<%
		greeting = "Good afternoon";
		%>
		<%
		} else {
		%>
		<%
		greeting = "Good evening";
		%>
		<%
		}
		%>
		<%=greeting%>,
		<%=FullName%>! <br>
		<hr>
	</nav>
	<main class="home">
		<div class="text">
			  <h2>Customer Information</h2>

    <table>
        <tr>
            <th>Customer Name</th>
            <th>Customer Number</th>
            <th>Customer Email</th>
            <th>Customer Address</th>
        </tr>

        <%
            int currentLoginUserId = (int)(request.getSession().getAttribute("CurrentLoginUserId"));
            CustomerDAO customerDAO = new CustomerDAO();
            List<CustomerPojo> customers = customerDAO.getCustomersByUserId(currentLoginUserId);

            for (CustomerPojo customer : customers) {
        %>
        <tr>
            <td><%= customer.getCustomerName() %></td>
            <td><%= customer.getCustomerNumber() %></td>
            <td><%= customer.getCustomerEmail() %></td>
            <td><%= customer.getCustomerAddress() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a class="a1" href="AddCustomer.jsp"><input class="btn btn-primary" type="button" value="Add Customer"></a>
    <a class="a1" href="DeleteCustomer.jsp"><input class="btn btn-primary" type="button" value="Delete Customer"></a>
		</div>

	</main>

	<script src="script.js"></script>

</body>

</html>