<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <title>Form</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS v5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    
<style>
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;;
}

body{
    background-color: aqua;
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

form{
    width: 35%;
    background-color: white;
    text-align: left;
    height:95vh;
    
    padding: 50px;
    border: 20px;
    border-radius: 20px;
    margin-top: 50px;
    margin-left: 35%;
}

.btn-primary{
    width: 100%;
    border-radius: 50px;
}

.form-control{
    color: black;
    border-bottom-color: black;
    border: none;
    box-shadow: none;
    border-bottom: 1px solid;
    border-radius: 4px 4px 0 0;
    text-align: left;
}

h3{
    font-size: 2 rem;
    font-weight: 700;
}

@media only screen and (max-width:600px) {
    form{
        width: 100% ! important;
    }
}
.a1 .btn{
	color:black;
	background-color:cyan;
	border:2px solid black;
	font-size:30px;
	font-style:italic;
	font-weight:600;
	border-radius:10px;
}
</style>
</head>

<body>
    <div class="container-fluid">
        <form class="mx-auto" action="AddCustomerServlet" method="post">
            <h3 class="text-center">Add Customer</h3>
            <div class="mb-3 mt-5">
                <label for="CustomerName" class="form-label">Customer Name</label>
                <input type="text" class="form-control" name="customerName" id="CustomerName" required>
            </div>
            <div class="mb-3">
                <label for="CustomerNumber" class="form-label">Customer Number</label>
                <input type="text" class="form-control" name="customerNumber" id="customerNumber" required>
            </div>
            <div class="mb-3">
                <label for="CustomerEmail" class="form-label">Customer Email</label>
                <input type="email" class="form-control" name="customerEmail" id="customerEmail" required>
            </div>
            <div class="mb-3">
                <label for="CustomerAddress" class="form-label">Customer Address</label>
                <input type="text" class="form-control" name="customerAddress" id="customerAddress" required>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Add Customer</button>
        </form>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
        </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
        </script>
</body>

</html>