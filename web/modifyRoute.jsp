<%--
  Created by IntelliJ IDEA.
  User: 王艺璇
  Date: 2023/3/14
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Controller.RouteController" %>
<%@ page import="Entity.Route" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> modifyRoute </title>
    <!-- favicon -->
    <style>
        html{
            background-image: url(BG.jpg);
        }
    </style>
    <!-- <link rel="stylesheet" href="/box1.css"> -->
    <link rel="stylesheet" href="CSS/addRoute.css">
    <link rel="stylesheet" href="CSS/Button_model.css">
    <link href="https://fonts.googleapis.com/css?family=Lato&display=swap" rel="stylesheet">
    <!-- header links -->
    <script src="https://kit.fontawesome.com/4a3b1f73a2.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Lato&display=swap" rel="stylesheet">
    <%RouteController routeController = new RouteController();
        String rid = routeController.searchMax();
        session.setAttribute("rid",rid);
        Route route = routeController.searchId(request.getQueryString());
    %>
    <script type="text/javascript" >
        function initialize(){
            let rId = location.search.split('?')[1];
            alert(rId)
            var contentDetails="Hello";
            console.log('connected!!');
            {
                console.log(contentDetails);
                dynamicContentDetails(rId)
            }
        }
        function dynamicContentDetails(rId)
        {

            var routeName = "<%=route.getRouteName()%>"
            var routeLoc = "<%=route.getLocation()%>"
            var routeDes = "<%=route.getDescription()%>"
            let mainContainer = document.createElement('div')
            mainContainer.id = 'containerD'
            document.getElementById('containerProduct').appendChild(mainContainer);

            let imageSectionDiv = document.createElement('div')
            imageSectionDiv.id = 'imageSection'

            let imgTag = document.createElement('input')
            imgTag.type="file"
            imgTag.accept=".gpx"
            imgTag.name = "GPXFile"
            imgTag.title="Pleas upload GPX file"
            imgTag.insertAdjacentHTML('beforeend', '<i class="fa-regular fa-file-circle-plus" style="font-size:200px;color:#5bc0de;background:#ffffff;border-style:none" title="delete"></i>');


            imageSectionDiv.appendChild(imgTag)

            let productDetailsDiv = document.createElement('div')
            productDetailsDiv.id = 'productDetails'

            // console.log(productDetailsDiv);

            let h1 = document.createElement('h1')
            let h1Text = document.createTextNode("Route name: ")
            var rname = document.createElement("input")
            rname.id='rname'
            rname.name='rname'
            rname.value=routeName
            rname.setAttribute("required","true")
            h1.appendChild(h1Text)
            h1.appendChild(rname)


            let detailsDiv = document.createElement('div')
            detailsDiv.id = 'details'

            let h3DetailsDiv = document.createElement('h3')
            let h3DetailsText = document.createTextNode('Location: ')
            var loc = document.createElement("input")
            loc.id='loc'
            loc.name='loc'
            loc.value=routeLoc;
            loc.setAttribute("required","true")
            h3DetailsDiv.appendChild(h3DetailsText)
            h3DetailsDiv.appendChild(loc)

            let h3 = document.createElement('h3')
            let h3Text = document.createTextNode('Description')
            h3.appendChild(h3Text)

            let para = document.createElement('p')
            let paraText = document.createTextNode("")
            var des = document.createElement("input")
            des.id='des'
            des.name='des'
            des.value=routeDes
            para.appendChild(paraText)
            para.appendChild(des)

            let productPreviewDiv = document.createElement('div')
            productPreviewDiv.id = 'productPreview'

            let h3ProductPreviewDiv = document.createElement('h3')
            let h3ProductPreviewText = document.createTextNode('Route id (The name of the GPX file): '+rId)
            h3ProductPreviewDiv.appendChild(h3ProductPreviewText)
            productPreviewDiv.appendChild(h3ProductPreviewDiv)



            let buttonDiv = document.createElement('div')
            buttonDiv.id = 'button'

            let buttonTag = document.createElement('input')
            buttonTag.type="submit"
            buttonTag.class="button1"
            buttonTag.style="margin-top: 5px;width: 120px;height: 30px;border-width: 0px;border-radius: 1px;background: #4cd374;cursor: pointer;outline: none; font-family: Microsoft YaHei;color: white;font-size: 17px;opacity: 0.6;"
            buttonTag.value='Confirm'
            buttonDiv.appendChild(buttonTag)



            console.log(mainContainer.appendChild(imageSectionDiv));
            mainContainer.appendChild(imageSectionDiv)
            mainContainer.appendChild(productDetailsDiv)
            productDetailsDiv.appendChild(h1)
            //productDetailsDiv.appendChild(h4)
            productDetailsDiv.appendChild(detailsDiv)
            detailsDiv.appendChild(h3DetailsDiv)
            detailsDiv.appendChild(h3)
            detailsDiv.appendChild(para)
            productDetailsDiv.appendChild(productPreviewDiv)
            let param = document.createElement('p')
            var routeId = document.createElement("input")
            routeId.name='routeId'
            routeId.type='text'
            routeId.value=rId;
            param.appendChild(routeId)
            param.hidden=true
            productDetailsDiv.appendChild(param)


            productDetailsDiv.appendChild(buttonDiv)


            return mainContainer
        }
    </script>

</head>

<body onload="initialize()">
<!-- HEADER -->
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
<form action="ModifyRouteServlet" method="post" enctype="multipart/form-data">
    <div id="containerProduct">
        <!-- JS rendered code -->

    </div>
</form>



</body>
</html>