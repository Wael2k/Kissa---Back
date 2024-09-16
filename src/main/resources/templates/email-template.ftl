<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Email Confirmation</title>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td style="padding: 20px;">
            <table cellpadding="0" cellspacing="0" width="100%" style="background-color: #f0f0f0; border-radius: 5px;">
                <tr>
                    <td style="padding: 20px;">
                        <h1 style="font-size: 24px; font-weight: bold; margin-bottom: 10px;">Hello!</h1>
                        <p style="font-size: 16px; margin-bottom: 20px;">Thank you for signing up. Please click the link below to confirm your email address:</p>
                        <a href="http://localhost:8080/api/auth/verify?token=${tokenMail}" style="display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;">Confirm Email</a>
                        <p style="font-size: 16px; margin-top: 30px;">If you didn't request this, you can safely ignore this email.</p>
                        <p style="font-size: 16px;">Your OTP code is: <strong>${otpEmail}</strong></p>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>