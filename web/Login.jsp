<%@ page import="db.Login" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="CSS/Index.css"/>
    <link rel="stylesheet" href="CSS/Button_model.css"/>
    <link rel="stylesheet"
          href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <style>
        html{
            background-image: url(BG.jpg);
        }
    </style>
    <%new Login().logout();%>
</head>
<body>

    <div id="login-box">
        <h1>Welcome to TourShare!</h1>
        <form action="LoginServlet" method="post">
        <div class="form">
            <div class="item">
                <i class="fa fa-github-alt" style="font-size:24px"></i>
                <input id = "Username" type="text" name="Username" placeholder="Username">
            </div>
            <div class="item">
                <i class="fa fa-search" style="font-size:24px"></i>
                <input id = "Password" type="password" name="Password" placeholder="Password">
            </div>
        </div>
        <input type = "submit" value = "Login" class = "button1">
        </form>
        <input type = "button" value = "Register" class = "button1" onclick="register()">

        <div id = "hint" class = "hint"></div>
    </div>

    <script>
        /*注册*/
        function register(){
            window.location.href = "Register.html";
        }
    </script>
</body>
</html>