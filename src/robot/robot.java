package robot;

import db.db;
import login.user;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class robot  extends HttpServlet {
    public robot(){ super();}

    public void destroy(){
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("运行到servlet:user_get_record");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("运行到servlet");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        if(action.equals("create_robot")){
            try{
                creatRobot(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(action.equals("delete_things")){
            try{
                delete_things(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(action.equals("modify_things")){
            try{
                modify_things(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(action.equals("add_todo_things")){
            try{
                add_todo_things(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("错误的action");
        }
    }

    public void creatRobot(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.setHeader("REDIRECT","REDIRECT");//告诉ajax要重定向
            response.setHeader("PATH","src/pages-login.html");//ip为服务器ip地址，在此用ip代指
//                System.out.println("going to return!!!!!!!!");
            return;
        }
        String JSESSIONID=request.getParameter("JSESSIONID");
        String type=request.getParameter("type");
        String code=request.getParameter("code");
        String interval=request.getParameter("interval");
        String volume=request.getParameter("volume");
        String lasting_sec=request.getParameter("lasting_sec");
        String robot_key = String.valueOf(System.currentTimeMillis());
        //现在默认type就是2了，定时买入最简单,本来是应该进行分流的
        String sql="INSERT into robot_strategy (robot_key,user_session,stock_code,strategy_code) values( '"+robot_key+"','"+JSESSIONID+"','"+code+"','interval_buy')";
        System.out.println("SQL语句是："+ sql + "<br>");
        conn.executeUpdate(sql);
        if (type.equals("2")) {
            String sql2 = "INSERT into interval_buy_strategy(robot_key,interval,transaction_amount) values( '" + robot_key + "'," + interval + "," + volume + ")";
            System.out.println("SQL语句是：" + sql + "<br>");
            conn.executeUpdate(sql2);
            conn.close();
        }
        if (type.equals("3")) {
            String sql2 = "INSERT into interval_sell_strategy(robot_key,interval,transaction_amount) values( '" + robot_key + "'," + interval + "," + volume + ")";
            System.out.println("SQL语句是：" + sql + "<br>");
            conn.executeUpdate(sql2);
            conn.close();
        }

        if (type.equals("2")){
            String command="python D:\\cmdtest.py -c "+code+" -it "+interval+" -st interval_buy -m "+volume+" -s "+JSESSIONID+" -t "+lasting_sec;
            Runtime.getRuntime().exec(command);
            System.out.println("-------------------------------------------"+command);
        }
        if (type.equals("3")){
            String command="python D:\\cmdtest.py -c "+code+" -it "+interval+" -st interval_sell -m "+volume+" -s "+JSESSIONID+" -t "+lasting_sec;
            Runtime.getRuntime().exec(command);
            System.out.println("-------------------------------------------"+command);
        }

        jsonObj.put("data",arr);
        System.out.println(jsonObj.toString());
        response.setContentType("json/application; charset=UTF-8");
        response.getWriter().print(jsonObj);
        response.getWriter().flush();
        response.getWriter().close();
    }



    public void delete_things(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
            System.out.println("运行到servle：delete_things");
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String delete_id = request.getParameter("delete_id");
            db conn=new db();
            String sql="DELETE FROM todo_list WHERE id='"+ delete_id + "'";
            System.out.println(sql);
            conn.executeUpdate(sql);
            conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }

    public void modify_things(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
        System.out.println("运行到servle：modify_things");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String modify_id=request.getParameter("id");
        String todo_things=request.getParameter("todo_things");
        String creatime=request.getParameter("creatime");
        db conn=new db();
        String sql="UPDATE todo_list SET todo_things='"+todo_things+"',creatime='"+creatime+"'WHERE id='"+modify_id+"'";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");

    }

    public void add_todo_things(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("运行到servle：add_todo_things");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String todo_things=request.getParameter("add_todo_things");
        String creatime=request.getParameter("add_creatime");
        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }
        db conn=new db();
        String sql="INSERT todo_list (todo_things,creatime,todo_user) values( '"+todo_things+"','"+creatime+"','"+user.getUsername()+"')";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }
}
