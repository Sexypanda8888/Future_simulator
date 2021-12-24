
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="login.user" %>
<%@ page import="java.sql.*" %>
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
    用户名：<%=user.getUsername()%><p>
    密码：<%=user.getPassword()%><p>
    登陆时间：<%=dateTime %><p>
    SessionId：<%=sessionId %><p>
    <form class="form-horizontal form-material" action="/qihuo/money?action=add_money&user_name=<%=user.getUsername() %>" method="post" id="forms">
        <br>
        <div class="form-group">
            <div class="col-xs-12">
                <input class="form-control" type="text" id="add_money" name="add_money" required="" placeholder="充值金额"> </div>
        </div>

        <div class="form-group text-center m-t-20">
            <div class="col-xs-12">
                <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light">充值</button>
            </div>
        </div>
    </form>
    <form class="form-horizontal form-material" action="/qihuo/money?action=reduce_money&user_name=<%=user.getUsername() %>" method="post" id="forms">
        <br>
        <div class="form-group">
            <div class="col-xs-12">
                <input class="form-control" type="text" id="reduce_money" name="reduce_money" required="" placeholder="提现金额"> </div>
        </div>

        <div class="form-group text-center m-t-20">
            <div class="col-xs-12">
                <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light">提现</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal form-material" action="/qihuo/business?action=business&user_name=<%=user.getUsername() %>" method="post" id="forms">
        <br>
        <div class="form-group">
            <div class="col-xs-12">
                <input class="form-control" type="text" id="future_name" name="future_name" required="" placeholder="期货名"> </div>
        </div>

        <div class="form-group ">
            <div class="col-xs-12">
                <input class="form-control" type="text" list="choose" id="do_what" name="do_what" required="" placeholder="操作">
                <datalist id="choose">
                    <option>买入</option>
                    <option>卖出</option>
                </datalist>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <input class="form-control" type="text" id="number" name="number" required="" placeholder="买入或卖出数量"> </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <input class="form-control" type="text" id="price" name="price" required="" placeholder="价格"> </div>
        </div>
        <div class="form-group text-center m-t-20">
            <div class="col-xs-12">
                <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light">交易</button>
            </div>
        </div>
    </form>

    <%
        try{
            Class.forName("com.mysql.jdbc.Driver"); }
        catch(ClassNotFoundException classnotfoundexception){
            classnotfoundexception.printStackTrace(); }

        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8");
            Statement statement=conn.createStatement();

            String sql = "select * from user_money where user_name='"+user.getUsername()+"'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
        %>
         账户余额：<%=rs.getString(2)%><p>
        <%
            }
        statement.close();
        conn.close();
        }
        catch (SQLException sqlexception){}
    %>

</body>
</html>