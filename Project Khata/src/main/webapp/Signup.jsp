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
@charset "UTF-8";
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
    height:105vh;
    
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
</style>
</head>

<body>
    <div class="container-fluid">
        <form class="mx-auto" action="signup" method="post">
            <h3 class="text-center">Signup</h3>
            <div class="mb-3 mt-5">
                <label for="username" class="form-label">User Id</label>
                <input type="text" class="form-control" name="username" id="username" required>
            </div>
            <div class="mb-3">
                <label for="fullname" class="form-label">Full Name</label>
                <input type="text" class="form-control" name="fullname" id="fullname" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" name="email" id="email" required>
            </div>
            <div class="mb-3">
                <label for="companyname" class="form-label">Company Name</label>
                <input type="text" class="form-control" name="companyname" id="companyname" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" name="password" id="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Signup</button>
            <div id="" class="form-text">Already user?<a href="login.jsp">Login</a></div>
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