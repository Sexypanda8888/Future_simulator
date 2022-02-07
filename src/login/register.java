package login;

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

import org.json.JSONException;
import org.json.JSONObject;

import db.db;

public class register extends HttpServlet {

    public register() {
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


        if (action.equals("register")) {
            try {
                register(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (action.equals("login")) {
            try {
                login(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("错误的action");
        }

    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 登陆成功
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");


        String sql = "select * from user where user_name='" + user_name + "' and password='" + password + "'";
        System.out.println(sql);
        db conn = new db();

        ResultSet rs = conn.executeQuery(sql);
        if (user_name.equals("administrator")&&password.equals("administrator")){
            response.sendRedirect("src/user_manage.html");
            user user = new user(user_name, password);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = dateFormat.format(new Date());
            //new 一个session来保存user、dateTime对象
            HttpSession	session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("loginTime", dateTime);
            session.setAttribute("sessionId", session.getId());
        }else if (rs.next()) {
            System.out.println("登录成功"+user_name);
            user user = new user(user_name, password);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = dateFormat.format(new Date());
            //new 一个session来保存user、dateTime对象
            HttpSession	session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("loginTime", dateTime);
            session.setAttribute("sessionId", session.getId());

            response.sendRedirect("src/homepage.html");
        }else
        {
            System.out.println("登录失败");
            response.sendRedirect("src/pages-login.html");

        }

        rs.close();

        conn.close();
        System.out.println("����������ϣ��ر������ݿ⣡");


    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 向服务器发送请求获取到参数
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        System.out.println(user_name + "--" + password);

        db conn = new db();

        String sql = "insert into user(user_name, password, email) values('" + user_name + "', '" + password + "', '" + email + "')";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
        System.out.println(user_name + "--" + password);

        db conn2 = new db();

        String sql2 = "insert into user_money(user_name, money) values('" + user_name + "', '0')";
        System.out.println(sql2);
        conn2.executeUpdate(sql2);
        conn2.close();
        System.out.println("操作数据完毕，关闭了数据库！");

        response.sendRedirect("src/pages-login.html");
    }

    public void check(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");

        String sql = "select * from user where user_name='" + user_name + "' and password='" + password + "'";
        System.out.println(sql);
        db conn = new db();

        ResultSet rs = conn.executeQuery(sql);
        if (rs.next()) {
            System.out.println("登录成功"+user_name);
            response.sendRedirect("src/homepage.html");
        }else
        {
            System.out.println("登录失败");
            response.sendRedirect("src/pages-login.html");

        }

        rs.close();

        conn.close();
        System.out.println("����������ϣ��ر������ݿ⣡");

    }

}
