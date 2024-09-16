<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Confirmation</title>
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
        p {
            font-size: 16px;
            margin-bottom: 15px;
        }
        strong {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to Our Platform!</h1>
    <p>Your email has been successfully verified.</p>
    <p><strong>Email:</strong> ${email}</p>
    <p>You need to wait sometime to verify your account.</p>
    <p>If you have any questions or need assistance, please feel free to contact us.</p>
    <p>Best regards,<br> Your Platform Team</p>
</div>
</body>
</html>