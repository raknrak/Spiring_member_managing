<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>login</title>
</head>
<body>
    <form action="/member/login" method="post">
        <input type="text" name="memberEmail" placeholder="이메일">
        <input type="text" name="memberPassword" placeholder="비밀번호">
        <input type="submit" value="로그인">
    </form>
</body>
</html>
