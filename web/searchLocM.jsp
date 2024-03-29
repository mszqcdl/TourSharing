<%--
  Created by IntelliJ IDEA.
  User: 王艺璇
  Date: 2023/3/13
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Entity.Route"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controller.RouteController" %>
<%@page import= "com.alibaba.fastjson.JSONObject"%>
<%@ page import="db.Login" %>
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> TourShare </title>
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
    <%
        String locations = (String) session.getAttribute("locations");
        JSONObject json1 = new JSONObject();
        json1.put("locations", locations);
        String ids = (String) session.getAttribute("ids");
        JSONObject json2 = new JSONObject();
        json2.put("ids", ids);
        String names = (String) session.getAttribute("names");
        JSONObject json3 = new JSONObject();
        json2.put("names", names);
        String descriptions = (String) session.getAttribute("descriptions");
        JSONObject json4 = new JSONObject();
        json2.put("descriptions", descriptions);
    %>
    <script type="text/javascript" >

        function initialize() {


            var tmp =  "<%=locations%>";
            var loc = tmp.split(',');
            tmp = "<%=ids%>";
            var id = tmp.split(',');
            tmp =  "<%=names%>";
            var name = tmp.split(',');
            tmp = "<%=descriptions%>";
            var des = tmp.split(',,');
            let containerResult = document.getElementById("containerResult");
            for (let i = 0; i < loc.length-1; i++) {
                route = new Object();
                route.rid = id[i];
                route.name = name[i];
                route.description = des[i];
                route.location = loc[i];
                console.log(route);
                containerResult.appendChild(
                    dynamicClothingSection(route)
                );
            }
        }
        function dynamicClothingSection(route) {

            let boxDiv = document.createElement("div");
            boxDiv.id = "box";

            let boxLink = document.createElement("a");
            // boxLink.href = '#'
            boxLink.href = "/TourSharing_war_exploded/routeInfo.jsp?" + route.rid;



            let detailsDiv = document.createElement("div");
            detailsDiv.id = "details";

            let h3 = document.createElement("h3");
            let description = document.createTextNode(route.description);
            h3.appendChild(description);

            let h4 = document.createElement("h4");
            let location = document.createTextNode(route.location);
            h4.appendChild(location);

            let h2 = document.createElement("h2");
            let rName = document.createTextNode(route.name);
            h2.appendChild(rName);



            boxDiv.appendChild(boxLink);
            boxLink.appendChild(detailsDiv);
            detailsDiv.appendChild(h3);
            detailsDiv.appendChild(h4);
            detailsDiv.appendChild(h2);


            return boxDiv;
        }
        function sendID(rid){
            // let routeid = document.getElementById("rid");
            // routeid.value = rid;
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
        <h1> Adventures </h1>
        <div id="containerResult">
            <input id = "rid" type="hidden" name="rid" placeholder="rid">
            <!-- JS rendered code -->
        </div>
    </form>

</div>
</body>
</html>

</body>
</html>
