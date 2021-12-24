package business;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.user;
import org.json.JSONException;
import org.json.JSONObject;

import db.db;

public class business extends HttpServlet {

    public business() {
        super();
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("运行到servlet");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        HttpSession	session = request.getSession();
        user user = (user)session.getAttribute("user");
        if (user==null){
            response.setHeader("REDIRECT","REDIRECT");//告诉ajax要重定向
            response.setHeader("PATH","pages-login.html");//ip为服务器ip地址，在此用ip代指
            System.out.println("going to return!!!!!!!!");
            return;
        }

        if (action.equals("business")) {
            try {
                business(request, response,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("错误的action");
        }

    }

    public void business(HttpServletRequest request, HttpServletResponse response,user user) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String user_name = user.getUsername();
        System.out.println("username!!!_____----------------------------------------"+user_name);
        String future_name = request.getParameter("future_name");
        String s_number = request.getParameter("number");
        int number = Integer.parseInt(s_number);
        String do_what = request.getParameter("do_what");
        String s_price = request.getParameter("price");
//        int price = Integer.parseInt(s_price);
        float price=Float.parseFloat(s_price);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(new Date());

        if (do_what.equals("1")){
            number= -number; }


        db conn2 = new db();
        String sql3 = "select * from have_future where user_name='" + user_name + "' and future_name='" + future_name+ "'";
        System.out.println(sql3);
        ResultSet rs1 = conn2.executeQuery(sql3);
        if (rs1.next()) {
            String sql5 = "UPDATE have_future SET number=number+"+number+" where user_name='"+user_name+"' and future_name='"+future_name+"'";
            System.out.println(sql5);
            conn2.executeUpdate(sql5);
        }else{
            String sql4 = "insert into have_future(user_name, future_name, number) values('" + user_name + "', '" + future_name + "',0)";
            System.out.println(sql4);
            conn2.executeUpdate(sql4);
            String sql5 = "UPDATE have_future SET number=number+"+number+" where user_name='"+user_name+"' and future_name='"+future_name+"'";
            System.out.println(sql5);
            conn2.executeUpdate(sql5);
        }
        conn2.close();

        db conn1 = new db();
        String sql1 = "insert into business(user_name, future_name, number, price, time) values('" + user_name + "', '" + future_name + "', '" + number + "', '" + price + "', '" + time + "')";
        String sql2 = "UPDATE user SET money=money-"+number*price+" where user_name='"+user_name+"'";
        System.out.println("SQL语句是：" + sql1 + "<br>");
        conn1.executeUpdate(sql1);
        System.out.println("SQL语句是：" + sql2 + "<br>");
        conn1.executeUpdate(sql2);
        conn1.close();
        System.out.println("end");
    }
}
