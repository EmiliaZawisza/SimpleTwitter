<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import=" java.util.* " %>
<%@ page import="model.User" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<h2>
    <br>
    <strong class="d-block text-gray-dark">${requestScope.user.name}</strong>
    <br>
    <strong class="d-block text-gray-dark">${user.email}</strong>
    <br>
    <strong class="d-block text-gray-dark">${user}</strong>
    <br>
    <fmt:formatDate value="${requestScope.now}" pattern="yyyy-MM-dd HH:mm:ss"/>

</h2>

<h3>
    <c:forEach items="${users}" var="user">
        <c:catch var = "exp">
        <c:if test='${!fn:startsWith(user.name,"k")}'>
            <div>
                Users : ${user}
            </div>
        </c:if>
        <br>
        </c:catch>
        <p>${exp}</p>
    </c:forEach>
</h3>

<p>
    Me from undefined scope = ${me}
    Me from request scope = ${requestScope.me} <br>
    Me from session scope = ${sessionScope.get("me")}
    Me from session scope without get method = ${sessionScope.get("me")} <br>
</p>

</body>
</html>