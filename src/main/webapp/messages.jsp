<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Messages</title>
</head>
<body>
<div>
    <form action="addMessage" method="post">
        <div>
            <textarea placeholder="Message body" name="tweetMessage"></textarea>
        </div>
        <br>
        <div>
            <button type="submit">Send</button>
        </div>

    </form>
</div>

<div style="margin: 200px">
    <h2>Tweets :</h2>
    <c:forEach items="${tweets}" var="tweet">
        <div>
                ${tweet.message}
        </div>
        <br>
        <div>
            <fmt:formatDate value="${tweet.publishedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </div>
        <br>
        <div>
                ${tweet.autgor.login}
        </div>
        <br>
    </c:forEach>

</div>


</body>
</html>
