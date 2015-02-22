<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 15.02.15
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
       <title>Welcome to hotel</title>
    </head>
    <body>
       <h1>Welcome!</h1>

        <form action="/hotel" method="post">
            <input type="hidden" name="command" value="login" />
            Login
            <input id="login" type="text" name="login"> </br>
            Password
            <input id="password" type="password" name="password">  </br>
            <input type="submit" value="Login">
        </form>

    </body>
</html>
