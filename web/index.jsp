<%--
  Created by IntelliJ IDEA.
  User: 王艺璇
  Date: 2023/1/30
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>message</h1>
  <input type="email" id="1email" readonly="true" name="id" value="<%=session.getAttribute("message")%>">
</html>
