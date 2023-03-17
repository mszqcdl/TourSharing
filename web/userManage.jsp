<%@page import="java.util.ArrayList"%>
<%@page import="Controller.RouteController" %>
<%@page import= "com.alibaba.fastjson.JSONObject"%>
<%@ page import="db.Login" %>
<%@ page import="Controller.UserController" %>
<%@ page import="Entity.User" %>
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> TourShareManage </title>
    <link href="https://fonts.googleapis.com/css?family=Lato&display=swap" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <!-- favicon -->
    <style>
        html{
            background-image: url(BG.jpg);
        }
    </style>
    <link rel="stylesheet" href="CSS/content.css">
    <link rel="stylesheet" href="CSS/Button_model.css"/>
    <script src="https://kit.fontawesome.com/4a3b1f73a2.js"></script>

    <script type="text/javascript" >

        function initialize() {
            <%UserController userController = new UserController();
            //session.setAttribute("routes",routeController.searchAll());
            Login login = new Login();
            ArrayList<User> users = userController.searchAll();
            String ids = "";
            for (int i=0;i<users.size();i++){
                ids = ids+users.get(i).getIdUser()+",";
                //names = names+routes.get(i).getRouteName()+",";
                //descriptions = descriptions+routes.get(i).getDescription()+",,";
                //routes[i] = new Route(routes.get(i).getIdRoute(),routesList.get(i).getRouteName(),routesList.get(i).getLocation(),routesList.get(i).getIdUser(),routesList.get(i).getDescription());
            }
            //session.setAttribute("names",names.substring(0,names.length()-1));
            session.setAttribute("ids",ids.substring(0,ids.length()-1));
            //session.setAttribute("descriptions",descriptions.substring(0,descriptions.length()-1));
            //session.setAttribute("locations",locations.substring(0,locations.length()-2));
    %>

            tmp = "<%=ids%>";
            alert(tmp)
            var id = tmp.split(',');
            alert(id[0])
            let containerResult = document.getElementById("containerResult");


            for (let i = 0; i < id.length-1; i++) {
                user = new Object();
                user.uid = id[i];
                console.log(user);
                containerResult.appendChild(
                    dynamicClothingSection(user)
                );
            }
        }
        function dynamicClothingSection(route) {

            let boxDiv = document.createElement("div");
            boxDiv.id = "box";

            let boxLink = document.createElement("a");
            // boxLink.href = '#'
            boxLink.href = "/TourSharing_war_exploded/userInfoM.jsp?" + user.uid;



            let detailsDiv = document.createElement("div");
            detailsDiv.id = "details";

            let h3 = document.createElement("h3");
            let userid = document.createTextNode(user.uid);
            h3.appendChild(userid);




            boxDiv.appendChild(boxLink);
            boxLink.appendChild(detailsDiv);
            detailsDiv.appendChild(h3);


            return boxDiv;
        }

    </script>
</head>
<body onload="initialize()">
<div id="1"></div>
<script>
    load("headerM.jsp");
    function load(url)
    {
        req = new XMLHttpRequest();
        req.open("GET", url, false);
        req.send(null);
        document.getElementById(1).innerHTML = req.responseText;
    }
</script>
<div id="mainContainer">
    <form action="ContentServlet" method="post">
        <h1> Users </h1>
        <div id="containerResult">
            <input id = "rid" type="hidden" name="rid" placeholder="rid">
            <!-- JS rendered code -->
        </div>
    </form>

</div>
</body>
</html>