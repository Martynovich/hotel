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
       <title>Hotel</title>
    </head>
    <body>
       Hello my friend! Your login is ${clintLogin}! Your password is: ${pass}
       <form action="/hotel" method="post">
           <input type="hidden" name="command" value="free_rooms" />
           Get free rooms:</br>
           <input type="submit" value="Get free rooms">
       </form>
    </body>
</html>
