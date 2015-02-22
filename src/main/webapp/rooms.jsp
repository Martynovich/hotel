<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 21.02.15
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rooms</title>
</head>
<body>
<table>
    <tr>
        <td>Apartment ID</td>
        <td>Apartment number</td>
        <td>Apartment rooms</td>
        <td>Apartment free</td>
        <td>Apartment price</td>
        <td>Apartment stars</td>
    </tr>
    <c:forEach var="room" items="${freeRooms}">
        <tr>
            <td>${room.id}</td>
            <td>${room.number}</td>
            <td>${room.rooms}</td>
            <td>${room.free}</td>
            <td>${room.price}</td>
            <td>${room.level}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
