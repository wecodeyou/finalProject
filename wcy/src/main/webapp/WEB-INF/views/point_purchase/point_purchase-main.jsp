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


<h1>�������� �׽�Ʈ ������</h1>

${login.userName}�� �ȳ��ϼ���

<form action = "<c:url value = "/pay/gopay"/>" method = "post">

������ ����Ʈ<input type = "text" value = "100" name = "point"> 
<input type = "submit" value = "�����ϱ�">
</form>




</body>
</html>