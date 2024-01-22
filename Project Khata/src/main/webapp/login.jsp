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
   <!-- Css StyleSheet -->
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="container-fluid">
    <form class="mx-auto" action="login" method="post">
    <%-- Check if an error occurred --%>
		<%
		if (request.getParameter("error") != null) {
		%>
		<p style="color: red;">Password or Username was incorrect.</p>
		<%
		}
		%>
        <h3 class="text-center">Login</h3>
        <div class="mb-3 mt-5">
          <label for="username" class="form-label">User Id</label>
          <input type="text" class="form-control" name="username" id="username" required >
        </div>
        <div class="mb-3 ">
          <label for="Password" class="form-label">Password</label>
          <input type="password" class="form-control" name="password" id="password" required>
          <div id="" class="form-text"><a href="Forgot_Password.jsp">Forgot Password?</a></div>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <div id="" class="form-text">Not a Member?<a href="Signup.jsp">Signup now</a></div>
      </form>
</div>
  <!-- Bootstrap JavaScript Libraries -->
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
  </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
    integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
  </script>
</body>

</html>