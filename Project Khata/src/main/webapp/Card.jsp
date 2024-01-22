<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Card</title>
<style>
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;;
}
.card-images{
	height:450px;
	width:400px;
 	border:1px solid black;
 	border-radius:10px;
 	margin:100px auto;
 	background-color:lightcyan;
 }
 .card-images .card-image{
	height:80px;
	width:100px;
	margin-left:20px;
 	
 } 	
.a1 .btn{
	color:black;
	background-color:cyan;
	border:2px solid black;
	font-size:30px;
	font-weight:600;
	border-radius:10px;
}
.form-control{
    color: black;
    border-bottom-color: black;
    border: 1px solid black;
    box-shadow: none;
    border-bottom: 1px solid;
    border-radius: 4px;
    text-align: left;
    margin:10px 50px;
    height:30px;
    width:300px;
}
.form-label{
	margin:2px 50px;
	font-size:30px;
	font-weight:100;
	text-align:center;
}

</style>
</head>
<body>
<div class="card-images">
    <img class="card-image" src="https://pngimg.com/d/visa_PNG24.png" alt="Visa Card" >
    <img class="card-image" src="https://lwcdn.freebitco.in/wp-content/uploads/2023/07/Mastercard-img.png" alt="MasterCard" >
    <img class="card-image" src="https://freepngimg.com/save/127514-logo-american-express-download-free-image/1000x500" alt="American Express">
   <div class="mb-3">
	<label for="Card number" class="form-label">Card Number:</label><br>
	<input type="text" class="form-control" name="companyname" id="cardnumber" required>
	</div>
	<div class="mb-3">
	<label for="CVV" class="form-label">CVV:</label><br>
	<input type="text" class="form-control" name="companyname" id="CVV" required>
	</div>
	<div class="mb-3">
	<label for="Expiry" class="form-label">Expiry :</label><br>
	<input type="date" class="form-control" name="companyname" id="expiry" required>
	</div>
  </div>
</body>
</html>