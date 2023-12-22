<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>list</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>email</th>
        <th>password</th>
        <th>name</th>
        <th>age</th>
        <th>mobile</th>
        <th>조회</th>
        <th>삭제</th>
    </tr>
    <c:forEach items="${memberList}" var="member">
        <tr>
            <td>${member.id}</td>
            <td>
<%--쿼리스트링 방식 사용--%>
                <a href="/member?id=${member.id}">${member.memberEmail}</a>
            </td>
            <td>${member.memberPassword}</td>
            <td>${member.memberName}</td>
            <td>${member.memberAge}</td>
            <td>${member.memberMobile}</td>
            <td>
                <a href="/member?id=${member.id}">조회</a>
            </td>
            <td>
<%--                버튼을 클릭하면 delete함수 호출. id값이 기준.--%>
                <%--JS를 사용--%>
                <button onclick="deleteMember('${member.id}')">삭제</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<%--경고창 넣어야 함.--%>
<script>
    /*delete JS*/
<%--    ${memner.id}가 넘어옴--%>
    const deleteMember = (id) => {
        console.log(id);
        location.href = "/member/delete?id="+id;
    }
</script>
</html>