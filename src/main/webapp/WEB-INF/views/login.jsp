
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div><font color="#eb490e">${errorMsg}</font></div>
<form action="/onlineshop_war_exploded/login" method="post">
    <div class="container">
        <h1>Login</h1>
        <p>Please fill in this form to sign into account.</p>
        <hr>
        <p>${errorMessage}</p>
        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="login" required>
        <hr>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <hr>

        <button type="submit" class="registerbtn">Login</button>
    </div>

    <div class="container signin">
        <p>Don't have an account? <a href="/onlineshop_war_exploded/registration">Registration</a>.</p>
    </div>
</form>

</body>
</html>
