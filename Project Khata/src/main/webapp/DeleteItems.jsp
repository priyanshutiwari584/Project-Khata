<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="items.ItemPojo" %>
<%@ page import="items.ItemDAO" %>

<!DOCTYPE html>
<html>
<head>
<title>Delete Items</title>
<!-- Include Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(to left, #bdc3c7, #2c3e50);
}

.table-container {
	max-width: 1400px;
	margin: 0 auto;
	padding: 30px;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	margin-top: 100px;
}

.table-responsive {
	overflow-x: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #000;
	color: #fff;
}

tr:nth-child(even) {
	background-color: #000000;
}

tr:hover {
	background-color: #d9d9d9;
}
</style>
</head>
<body>
	</div>

	</nav>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-10">
				<div class="table-container">
					<h2 class="text-center">Delete Items</h2>
							<div class="table-responsive">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Product Name</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<%
										int currentLoginUserId = (int) request.getSession().getAttribute("CurrentLoginUserId");

										// Use the CustomersDAO to retrieve customer data based on currentLoginUserId
										ItemDAO itemDAO = new ItemDAO();
										List<ItemPojo> customers = itemDAO.getItemsByUserId(currentLoginUserId);

										for (ItemPojo customer : customers) {
										%>
										<tr>
											<td><%=customer.getProdName()%></td>
											<td>
												<form action="DeleteItemServlet" method="post">
													<input type="hidden" name="prodName"
														value="<%=customer.getProdName()%>">
													<button class="btn btn-danger" type="submit">Delete</button>
												</form>
											</td>
										</tr>
										<%
										}
										%>
									</tbody>
								</table>

							</div>

							<a href="Items.jsp" class="btn btn-primary" role="button">Go Back</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Include Bootstrap JS and jQuery -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>