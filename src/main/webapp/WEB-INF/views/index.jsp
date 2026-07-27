<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>JPA 심화</title>
</head>
<body>
<h1>JPA 심화</h1>
<section>
    <c:forEach items="${phones}" var="phone">
        <p>${phone}</p>
    </c:forEach>
</section>
<section>
    <form method="post">
        <input name="name" placeholder="폰 이름 입력">
        <button>생성</button>
    </form>
</section>
</body>
</html>
