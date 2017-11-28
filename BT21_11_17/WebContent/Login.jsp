<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty param.login}">
  <c:if test="${param.username =='hung' and param['password'] == '1'}">
     <c:set var="user" value="${param.username}" scope="session" />
     <c:redirect url="GuestBook.jsp"/>
  </c:if>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Form Đăng Nhập</title>
</head>
<body>
<form action="Login.jsp" method="post">
Username: <input type="text" name="username" /><br />
Password:  <input type="text" name="password"/><br />
<input type="submit" name="login" value="Login" />
</form>
</body>
</html>