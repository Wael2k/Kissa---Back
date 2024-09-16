<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verification Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button[type="submit"]:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Verification Form</h1>
    <form action="/api/auth/v1/verify-mail" method="post" id="verificationForm">
        <input type="hidden" id="token" name="token" value="${token}">

        <div>
            <label for="otp">OTP Code:</label>
            <input type="text" id="otp" name="otp" required>
            <span class="error-message" id="otpError"></span>
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
<script>
    document.getElementById("verificationForm").addEventListener("submit", function(event) {
        var otp = document.getElementById("otp").value;
        var otpError = document.getElementById("otpError");
        otpError.textContent = "";
        if (otp.trim() === "") {
            otpError.textContent = "OTP Code is required.";
            event.preventDefault();
        }
    });
</script>
</body>
</html>