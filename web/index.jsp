<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 31.08.2019
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test SQL</title>

</head>
<body>
<h2>Client table management</h2>
<form action="clservlet" method="get">
    <input type="checkbox" name="auto" value="1" <c:if test="${auto ne null}"> checked </c:if>/> Show changes
    automatically
    <table border="0">
        <tr>
            <td>
                <b>Full name</b>
            </td>
            <td>
                <input type="text" name="fullName"/>

            </td>
        </tr>
        <tr>
            <td>
                <b>Phone number</b>
            </td>
            <td>
                <input type="text" name="phone"/>

            </td>
        </tr>
    </table>
    <input type="submit" name="command" value="Add"/>
    <input type="submit" name="command" value="Delete"/>
    <input type="submit" name="command" value="List"/>
</form>

<c:if test="${clients ne null}">
    <table border="0" frame="border" rules="all">
        <caption>Table of clients in data base</caption>
        <tr>
            <th>Client</th>
            <th>Phone number</th>
        </tr>
        <c:forEach items="${clients}" var="cl">
            <tr>
                <td><c:out value="${cl.fullName}"/></td>
                <td><c:out value="${cl.phone}"/></td>
            </tr>
        </c:forEach>
    </table>

</c:if>
</body>
</html>
