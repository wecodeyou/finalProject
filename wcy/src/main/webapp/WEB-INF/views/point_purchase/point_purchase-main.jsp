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

<%

/* MemberVO login = (MemberVO)(session.getAttribute("login"));
 */

%>

<h1>�������� �׽�Ʈ ������</h1>

${login.userName}�� �ȳ��ϼ���

<form action = "<c:url value = "/pay/"/>" method = "post">

<input type = "hidden">
<input type = "submit" value = "login">
</form>

<form action = "<c:url value = "/pay/gopay"/>" method = "post">

������ ����Ʈ<input type = "text" value = "100" name = "point"> 
<input type = "submit" value = "�����ϱ�">
</form>




</body>
</html>