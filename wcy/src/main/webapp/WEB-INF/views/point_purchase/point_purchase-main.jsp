<%@page import="com.it.wecodeyou.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>


<h1>����Ʈ ���� �׽�Ʈ ������</h1>

${login.userName}�� �ȳ��ϼ���

<form action = "<c:url value = "/pay/gopay"/>" method = "post">

������ ����Ʈ
<input type = "text" name = "point">
<br>
 ���� ��ư<br>
  <input type='radio' name='radiop' value='100' />100P(�׽�Ʈ��)
  <input type='radio' name='radiop' value='10000' />10000P
  <input type='radio' name='radiop' value='50000' />50000P
  <input type='radio' name='radiop' value='100000' />100000P

<input type = "submit" value = "�����ϱ�">
</form>



<c:if test="${login == null}">
	<script>   
	   alert("�α����� �ʿ��� �����Դϴ�.");
	   location.href="<c:url value='/' />";
	</script>
</c:if>	


</body>
</html>