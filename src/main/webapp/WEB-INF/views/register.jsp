<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
<nav class="navbar is-light" role="navigation" aria-label="main navigation">
    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="${pageContext.request.contextPath}/index">
                Home
            </a>
        </div>
    </div>
    <div class="navbar-end">
        <div class="navbar-item">
            <div class="buttons">
                <a class="button is-success" href="${pageContext.request.contextPath}/registration">
                    <strong>Sign up</strong>
                </a>
                <a class="button is-success is-outlined" href="${pageContext.request.contextPath}/login">
                    Log in
                </a>
            </div>
        </div>
    </div>
</nav>
<section class="section is-small">
    <div class="columns">

        <div class="column">
            <h1 class="tile is-size-4 ">Let's create a new User!</h1>
            <p class="content is-size-1">Registration</p>
            <p class="content is-3">Please fill in this form to create an account.</p>
        </div>
    </div>
</section>
<section class="section is-small">
    <div class="columns">

        <div class="column">
            <form action="${pageContext.request.contextPath}/registration" method="post">
                <div class="container">
                    <div class="field">
                        <label class="label">Login</label>
                        <p class="control has-icons-left">
                            <input class="input" type="text" placeholder="Enter Login" name="login" required>
                            <span class="icon is-small is-left">
                                        <i class="fas fa-user"></i>
                                    </span>
                        </p>
                    </div>
                    <div class="field">
                        <label class="label">Name</label>
                        <p class="control has-icons-left">
                            <input class="input" type="text" placeholder="Enter Your Name" name="user_name" required>
                            <span class="icon is-small is-left">
                                        <i class="fas fa-address-book"></i>
                                    </span>
                        </p>
                    </div>
                    <div class="field">
                        <label class="label">Surname</label>
                        <p class="control has-icons-left">
                            <input class="input" type="text" placeholder="Enter Your Surname" name="user_surname"
                                   required>
                            <span class="icon is-small is-left">
                                        <i class="fas fa-address-book"></i>
                                    </span>
                        </p>
                    </div>
                    <div class="field">
                        <label class="label">Password</label>
                        <p class="control has-icons-left">
                            <input class="input" type="password" placeholder="Enter Password"
                                   name="psw" required>
                            <span class="icon is-small is-left">
                                        <i class="fas fa-lock"></i>
                                    </span>
                        </p>
                    </div>
                    <div class="field">
                        <label class="label">Password</label>
                        <p class="control has-icons-left">
                            <input class="input" type="password" placeholder="Repeat Password"
                                   name="psw-repeat" required>
                            <span class="icon is-small is-left">
                                        <i class="fas fa-lock"></i>
                                    </span>
                        </p>
                    </div>
                    <div>
                        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
                    </div>
                    <div class="field">
                        <p class="control">
                            <button class="button is-success" type="submit">
                                Register
                            </button>
                        </p>
                    </div>
                    <div class="container signin">
                        <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Sign in</a>.</p>
                    </div>
            </form>
        </div>
        <div class="column"></div>
    </div>
</section>
</body>
</html>
