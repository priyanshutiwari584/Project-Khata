<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Successful Signup</title>
    <style>
        /* CSS for the card-style container */
        .card {
            border: 1px solid black;
            border-radius:10px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            max-width: 400px;
            background-color:aqua;
            margin: 0 auto;
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="card">
        <h1>Signup Successful!</h1>
        <p>You have successfully signed up.</p>
        <p>Redirecting to another page in <span id="countdown">5</span> seconds...</p>
    </div>

    <!-- JavaScript to redirect after a delay -->
    <script type="text/javascript">
        var countdown = 5; // Set the countdown time in seconds

        function updateCountdown() {
            document.getElementById("countdown").textContent = countdown;
            countdown--;
            if (countdown < 0) {
                // Redirect to another page after countdown
                window.location.href = "login.jsp"; // Replace with the actual page URL
            }
        }

        // Update the countdown every second
        setInterval(updateCountdown, 1000);
    </script>
</body>
</html>
