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
        <p>${phone.createdAt} ${phone.updatedAt}</p>
        <form method="post" action="/${phone.id}/name">
            <input name="name" placeholder="바꿀 이름" value="${phone.name}">
            <button>수정</button>
        </form>
    </c:forEach>
</section>
<section>
    <form method="post">
        <input name="name" placeholder="폰 이름 입력">
        <button>생성</button>
    </form>
</section>
<section>
    <form method="get" action="/list">
        <input name="page" type="range" value="0" min="0" max="10" placeholder="페이지">
        <input name="size" type="range" value="5" min="1" max="5" placeholder="사이즈">
        <select name="sort">
            <option value="name,asc">이름 오름차순</option>
            <option value="createdAt,desc">생성 내림차순</option>
            <option value="updatedAt,desc">수정 내림차순</option>
        </select>
        <button>리스트 보기</button>
    </form>
</section>
</body>
</html>
