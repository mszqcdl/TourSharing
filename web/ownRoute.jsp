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
    <title> Routes </title>
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
            <%RouteController routeController = new RouteController();
                    //session.setAttribute("routes",routeController.searchAll());
                    Login login = new Login();
                    ArrayList<Route> routes = routeController.searchUser(login.getUserID());
                    String locations = "";
                    String ids = "";
                    String names = "";
                    String descriptions = "";
                    if (routes==null){
                        session.setAttribute("names","");
                        session.setAttribute("ids","");
                        session.setAttribute("descriptions","");
                        session.setAttribute("locations","");
                    }
                    else{
                        for (int i=0;i<routes.size();i++){
                            locations = locations+routes.get(i).getLocation()+",";
                            ids = ids+routes.get(i).getIdRoute()+",";
                            names = names+routes.get(i).getRouteName()+",";
                            descriptions = descriptions+routes.get(i).getDescription()+",,";
                            //routes[i] = new Route(routes.get(i).getIdRoute(),routesList.get(i).getRouteName(),routesList.get(i).getLocation(),routesList.get(i).getIdUser(),routesList.get(i).getDescription());
                        }
                        session.setAttribute("names",names.substring(0,names.length()-1));
                        session.setAttribute("ids",ids.substring(0,ids.length()-1));
                        session.setAttribute("descriptions",descriptions.substring(0,descriptions.length()-1));
                        session.setAttribute("locations",locations.substring(0,locations.length()-2));
                    }

            %>

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
            boxLink.href = "/TourSharing_war_exploded/ownRouteInfo.jsp?" + route.rid;
            // console.log('link=>' + boxLink);


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

            let btholder = document.createElement("div");
            let bt1 = document.createElement("button2");
            bt1.insertAdjacentHTML('beforeend', '<i class="fa fa-trash" style="font-size:20px;color:#5bc0de;background:#ffffff;border-style:none;" title="delete"></i>');
            btholder.appendChild(bt1);
            let bt2 = document.createElement("button1");
            bt2.insertAdjacentHTML('beforeend', '<i class="fa fa-file" style="font-size:20px;color:#5bc0de;background:#ffffff;border-style:none;" title="modify"></i>');
            btholder.appendChild(bt2);
            btholder.insertAdjacentHTML('beforeend', '<i style="background:#ffffff;"></i>');


            boxDiv.appendChild(boxLink);
            boxLink.appendChild(detailsDiv);
            detailsDiv.appendChild(h3);
            detailsDiv.appendChild(h4);
            detailsDiv.appendChild(h2);
            detailsDiv.appendChild(btholder);

            return boxDiv;
        }

    </script>

</head>
<body onload="initialize()">
<div id="1"></div>
<script>
    load("header.jsp");
    function load(url)
    {
        req = new XMLHttpRequest();
        req.open("GET", url, false);
        req.send(null);
        document.getElementById(1).innerHTML = req.responseText;
    }
</script>
<div id="mainContainer">

    <h1> Adventures </h1>
    <div id="containerResult">
        <!-- JS rendered code -->
    </div>


</div>
</body>
</html>