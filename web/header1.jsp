<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> HEADER1|WEBSITE </title>
    <!-- favicon -->
    <link rel="icon" href="https://yt3.ggpht.com/a/AGF-l78km1YyNXmF0r3-0CycCA0HLA_i6zYn_8NZEg=s900-c-k-c0xffffffff-no-rj-mo" type="image/gif" sizes="16x16">
    <!-- EXTERNAL LINKS -->
    <link rel="stylesheet" href="CSS/header.css">
    <script src="https://kit.fontawesome.com/4a3b1f73a2.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Lato&display=swap" rel="stylesheet">
</head>
<body>

<header>
    <section>
        <form action="HeaderServlet" method="post">
            <!-- MAIN CONTAINER -->
            <div id="container">
                <!-- WEB NAME -->
                <div id="webName"><a href="TourShare.jsp"> <b>Tour</b>Share </a></div>
                <!-- COLLCETIONS ON WEBSITE -->
                <div id="collection">
                    <%--                <a href="addRoute.html"> <i class="fas fa-plus-circle userIcon" title="create new route"></i> </a>--%>
                </div>
                <!-- SEARCH SECTION -->
                <div id="search">
                    <input type="text" id="input" name="location" placeholder="Input location searching routes">
                    <input type = "submit" value = "Search" class = "button1">
                </div>
                <!-- USER SECTION (CART AND USER ICON) -->
                <div id="user">
                    <a href="Login.jsp"> <i class="fas fa-arrow-circle-right userIcon" title="log out"></i> </a>
                </div>
            </div>
        </form>
    </section>
</header>

</body>
</html>