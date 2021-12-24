package money;

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

public class
money extends HttpServlet {

    public money() {
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
            response.setHeader("PATH","src/pages-login.html");//ip为服务器ip地址，在此用ip代指
//                System.out.println("going to return!!!!!!!!");
            return;
        }
//            System.out.println("From Session,user is"+user.getUsername()+"pwd is" + user.getPassword());

        if (action.equals("add_money")) {
            try {
                add_money(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (action.equals("reduce_money")) {
            try {
                reduce_money(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (action.equals("get_money")) {
            try {
                get_money(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("错误的action");
        }

    }

    public void add_money(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String add_money = request.getParameter("add_money");

        int  money = 0;
        if(add_money != null && !add_money.equals("")){
            money = Integer.parseInt(add_money);
        }

        String user_name = request.getParameter("user_name");

        db conn = new db();

        String sql = "UPDATE user SET money=money+"+money+" where user_name='"+user_name+"'";

        System.out.println("SQL语句是：" + sql + "<br>");

        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("����������ϣ��ر������ݿ⣡");

        response.sendRedirect("src/test_session.jsp");
    }

    public void reduce_money(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String reduce_money = request.getParameter("reduce_money");

        int  money = 0;
        if(reduce_money != null && !reduce_money.equals("")){
            money = Integer.parseInt(reduce_money);
        }

        String user_name = request.getParameter("user_name");

        db conn = new db();

        String sql = "UPDATE user SET money=money-"+money+" where user_name='"+user_name+"'";

        System.out.println("SQL语句是：" + sql + "<br>");

        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("����������ϣ��ر������ݿ⣡");

        response.sendRedirect("src/test_session.jsp");
    }
    public void get_money(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String user_name = request.getParameter("user_name");

        db conn = new db();

        String sql = "select * from user where user_name='"+user_name+"'";

        System.out.println("SQL语句是：" + sql + "<br>");
        System.out.println(sql);

        ResultSet rs = conn.executeQuery(sql);

        while(rs.next()){
            int money = rs.getInt("money");
            HttpSession	session = request.getSession();
            session.setAttribute("money", money);
            //请求转发
            request.getRequestDispatcher("src/test_session.jsp").forward(request, response);
        }

        conn.close();

        response.sendRedirect("src/test_session.jsp");
    }

}

