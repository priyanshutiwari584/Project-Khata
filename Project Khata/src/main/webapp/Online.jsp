<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online</title>
<style>
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;;
}
.card-images{
	height:400px;
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
    <img class="card-image" src="https://upload.wikimedia.org/wikipedia/commons/f/f2/Google_Pay_Logo.svg" alt="Visa Card" >
    <img class="card-image" src="https://upload.wikimedia.org/wikipedia/commons/7/71/PhonePe_Logo.svg" alt="MasterCard" >
    <img class="card-image" src="https://upload.wikimedia.org/wikipedia/commons/2/24/Paytm_Logo_%28standalone%29.svg" alt="American Express">
   <div class="mb-3">
	<label for="VPA" class="form-label">VPA:</label><br>
	<input type="text" class="form-control" name="companyname" id="cardnumber" required>
	</div>
	<div class="mb-3">
	<label for="HolderName" class="form-label">Holder Name:</label><br>
	<input type="text" class="form-control" name="companyname" id="CVV" required>
	</div>
	<div class="mb-3">
	<label for="Expiry" class="form-label">Otp :</label><br>
	<input type="number" class="form-control" name="companyname" id="expiry" required>
	</div>
  </div>

</body>
</html>