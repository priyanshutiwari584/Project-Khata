<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "supplierPurchase.SupplierPurchaseDAO" %>
<%@ page import = "accounts.CustomerPurchaseDAO" %>
<!DOCTYPE html>
<html lang="en" class="light">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!----======== CSS ======== -->
    <link rel="stylesheet" href="MainStyle.css">

    <!----===== Boxicons CSS ===== -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>

    <title>Dashboard Sidebar Menu</title>
    
    <style>
        /* Define CSS styles for the card */
        .card {
            border: 2px solid Red;
            border-radius:10px;
            color:blue;
            padding: 10px;
            margin: 10px;
            width: 300px;
            height: 100px;
            text-align:center;
            align-items:center;
            display: inline-block;
            background-color: #f2f2f2;
        }
        .total-label {
            font-weight: bold;
        }
    </style>
    
     <!-- Include jQuery library -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Include Chart.js library -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
    <nav class="sidebar close">
        <header>
            <div class="image-text">
                <span class="image">
                    <img src="logo.png" alt="">
                </span>

                <div class="text logo-text">
                    <span class="name">KhataApp</span>
                    <span class="profession">Accounts</span>
                </div>
            </div>

            <i class='bx bx-chevron-right toggle'></i>
        </header>

        <div class="menu-bar">
            <div class="menu">
                <li class="search-box">
                    <i class='bx bx-layout icon'></i>
                     <span class="text"> <% String VendorName = (String) request.getSession().getAttribute("VendorName");
    					%><%= VendorName %></span>
                </li>

                <ul class="menu-links">
                    <li class="nav-link">
                        <a href="#">
                            <i class='bx bx-home-alt icon'></i>
                            <span class="text nav-text">Dashboard</span>
                        </a>
                    </li>
                    <hr>
                    <li class="nav-link">
                        <a href="Customer.jsp">
                            <i class='bx bx-user icon'></i>
                            <span class="text nav-text">Customers</span>
                        </a>
                    </li>

                    <li class="nav-link">
                        <a href="Supplier.jsp">
                            <i class='bx bx-user-circle icon'></i>
                            <span class="text nav-text">Suppliers</span>
                        </a>
                    </li>
                    <hr>
                    <li class="nav-link">
                        <a href="Sales.jsp">
                            <i class='bx bx-bar-chart-alt-2 icon'></i>
                            <span class="text nav-text">Sales</span>
                        </a>
                    </li>

                    <li class="nav-link">
                        <a href="Purchase.jsp">
                            <i class='bx bxs-purchase-tag icon'></i>
                            <span class="text nav-text">Purchase</span>
                        </a>
                    </li>

                    <li class="nav-link">
                        <a href="Items.jsp">
                            <i class='bx bx-menu icon'></i>
                            <span class="text nav-text">Items</span>
                        </a>
                    </li>
                    
                    <li class="nav-link">
                        <a href="CustomerInvoice.jsp">
                            <i class='bx bx-check-shield icon'></i>
                            <span class="text nav-text">Invoice-Customer</span>
                        </a>
                    </li>
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
                        <i class='bx bx-moon icon moon'></i>
                        <i class='bx bx-sun icon sun'></i>
                    </div>
                    <span class="mode-text text">Dark mode</span>

                    <div class="toggle-switch">
                        <span class="switch"></span>
                    </div>
                </li>

            </div>
        </div>
    </nav>

    <nav class="nav">
        <%-- Retrieve user's full name,CurrentLoginUserId and VendorName from session --%>
    <% 
    String FullName = (String) request.getSession().getAttribute("fullName");
    %>
    
    <%-- Get the current hour of the day --%>
    <% java.util.Date now = new java.util.Date(); %>
    <% java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH"); %>
    <% int hour = Integer.parseInt(sdf.format(now)); %>
    
    <%-- Generate greeting message based on the time of day --%>
    <% String greeting = ""; %>
    <% if (hour >= 0 && hour < 12) { %>
        <% greeting = "Good morning"; %>
    <% } else if (hour >= 12 && hour < 18) { %>
        <% greeting = "Good afternoon"; %>
    <% } else { %>
        <% greeting = "Good evening"; %>
    <% } %>
   <%= greeting %>, <%= FullName %>!
	
        <br>
        <hr>
    </nav>
    <main class="home">
        <div class="text">
           <!--  Getting the Supplier Total Purchase --%>
    <% int currentLoginUserId = (int)request.getSession().getAttribute("CurrentLoginUserId");
    SupplierPurchaseDAO supplierPurchaseDAO = new SupplierPurchaseDAO();
    double totalPurchase = supplierPurchaseDAO.getSupplierTotalPurchase(currentLoginUserId); %>
	<!-- Getting the Customer Total Purchase -->
	<%
    CustomerPurchaseDAO customerPurchaseDAO = new CustomerPurchaseDAO();
    double totalSale = customerPurchaseDAO.getCustomerTotalPurchase(currentLoginUserId);
    %>
    <div class="card">
        <span class="total-label">Total Purchase:</span> <%= totalPurchase %>
    </div>
    
    <div class="card">
        <span class="total-label">Total Sale:</span> <%= totalSale %>
    </div>
    
     <h2>Product Quantity Chart</h2>

    <canvas id="productQuantityChart" width="400" height="200"></canvas>

    
        </div>
    </main>
    
    
<script>
        // Fetch data from the server using Ajax
        $.ajax({
            type: "GET",
            url: "FetchProductDataServlet",
            data: { currentLoginUserId: <%= currentLoginUserId %> },
            dataType: "json", // Specify JSON as the expected response type
            success: function(data) {
                var labels = data.labels;
                var quantities = data.quantities;

                var ctx = document.getElementById('productQuantityChart').getContext('2d');
                var chart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Quantity',
                            data: quantities,
                            backgroundColor: 'rgba(80, 170, 185, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            },
            error: function() {
                console.log("Error fetching data");
            }
        });
    </script>

    <script src="script.js"></script>

</body>

</html>