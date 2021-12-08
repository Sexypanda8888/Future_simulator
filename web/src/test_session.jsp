
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="login.user" %>
<%
    user user = (user)session.getAttribute("user");
    String dateTime = (String)session.getAttribute("loginTime");
    String sessionId = (String)session.getAttribute("sessionId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
Hello,this is an index page.<p>
    用户名：<%=user.getUsername()%><p>
    密码：<%=user.getPassword()%><p>
    登陆时间：<%=dateTime %><p>
    SessionId：<%=sessionId %><p>
</body>
</html>