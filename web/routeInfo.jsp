<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<html>
<head>
    <title> DetailedInfo </title>
    <!--    <link rel="stylesheet" href="CSS/Index.css"/>-->
    <!--    <link rel="stylesheet" href="CSS/Button_model.css"/>-->
    <!--    <link rel="stylesheet"-->
    <!--          href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">-->
    <link rel="stylesheet" href="CSS/MapDetail.css">
    <link rel="stylesheet" href="CSS/Button_model.css"/>
    <script src="https://kit.fontawesome.com/4a3b1f73a2.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Lato&display=swap" rel="stylesheet">
    <style>
        html{
            background-image: url(BG.jpg);
        }
    </style>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyD6cmC1svf3Q61uIb72YODjLF5bM2Ms6ag&sensor=false"></script>
    <script type="text/javascript" >

        //所有坐标和中心点
        var locations = ${list};
        var avgLat = ${avgLat};
        var avgLon = ${avgLon};

        //地图
        var map;
        var marker;
        var poly;

        /* 加载地图 */
        function initialize() {
            var latlng = new google.maps.LatLng(avgLat, avgLon);
            var myOptions = {
                zoom: 10,    //缩放级别
                center: latlng,        //坐标
                mapTypeId: google.maps.MapTypeId.ROADMAP    //类型:默认的普通二维图块
            };
            map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

            // 线条设置
            var polyOptions = {
                strokeColor: 'blue',    // 颜色
                strokeOpacity: 0.5,    // 透明度
                strokeWeight: 2    // 宽度
            }
            poly = new google.maps.Polyline(polyOptions);
            poly.setMap(map);    // 装载
            //window.alert(locations)


            /* 循环标出所有坐标 */
            for(var i=0; i<locations.length; i+=2){
                //var loc = locations[i].split(',');

                var path = poly.getPath();    //获取线条的坐标
                path.push(new google.maps.LatLng(locations[i], locations[i+1]));    //为线条添加标记坐标
                //生成标记图标
                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(locations[i], locations[i+1]),
                    map: map,
                    icon: "http://labs.google.com/ridefinder/images/mm_20_green.png"
                });
            }

        }

    </script>
</head>

<body onload="initialize()">
<!-- HEADER -->
<div id="1"></div>
<script>
    load("header.html");
    function load(url)
    {
        req = new XMLHttpRequest();
        req.open("GET", url, false);
        req.send(null);
        document.getElementById(1).innerHTML = req.responseText;
    }
</script>
<div id="mapMainContainer">
    <!-- JS rendered code -->
    <form action="MapServlet" method="post">
        <div>
            <div id="description" style="height:600px;width:247.34px;float:left;">
                <b><%=session.getAttribute("message")%></b>
                <br>
                Discover this 10.8-km out-and-back trail near Huairou, Beijing. Generally considered a challenging route, it takes an average of 4 h 37 min to complete.
                <br>
                <input type = "button" value = "Add to Favourite List" class = "button2" onclick="favourite()">
            </div>
            <div id="map_canvas" style="width:900px; height:500px;float: right;"></div>
        </div>
    </form>
</div>

</body>
</html>
