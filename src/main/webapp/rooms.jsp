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
    <script>
        var submitForm = function(id) {
            document.getElementById("userInput").innerHTML = '<input type="hidden" name="roomId" value="'+ id +'">';
            document.getElementById("roomForm").submit();
        }
    </script>
</head>
<body>
<form action="/hotel" id="roomForm">
    <table>
        <tr>
            <td>Apartment number</td>
            <td>Apartment rooms</td>
            <td>Apartment free</td>
            <td>Apartment price</td>
            <td>Apartment stars</td>
        </tr>
        <c:forEach var="room" items="${freeRooms}">
            <tr onclick="submitForm(${room.id})">
                <td>${room.number}</td>
                <td>${room.rooms}</td>
                <td>${room.free}</td>
                <td>${room.price}</td>
                <td>${room.level}</td>
            </tr>
            <%--<input type="hidden" name="roomId" value="${room.id}">--%>
        </c:forEach>
        <input type="hidden" name="command" value="apartment"/>
        <span id="userInput"></span>
    </table>
</form>
</body>
</html>
