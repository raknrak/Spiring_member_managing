<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>update</title>
</head>
<body>
<%--수정항목과 수정하지 말아야 할 항목을 readonly로 나누어서 구분함.--%>
<form action="/member/update" method="post" name="updateForm">
  id: <input type="text" name="id" value="${member.id}"readonly>
  email: <input type="text" name="memberEmail" value="${member.memberEmail}"readonly>
  password: <input type="text" name="memberPassword" id="memberPassword">
  name: <input type="text" name="memberName" value="${member.memberName}" readonly>
  age: <input type="text" name="memberAge" value="${member.memberAge}" autofocus>
  mobile: <input type="text" name="memberMobile" value="${member.memberMobile}">
  <input type="button" value="수정" onclick="update()">

</form>
</div>
</body>
<script>
  /*비밀번호 검증*/
  const update = () => {
    const passwordDB = '${member.memberPassword}';
    const password = document.getElementById("memberPassword").value;
    if (passwordDB == password) {
      document.updateForm.submit();
    } else {
      alert("비밀번호가 일치하지 않습니다!");
    }
  }
</script>
</html>