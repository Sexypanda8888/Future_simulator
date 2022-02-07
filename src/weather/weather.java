package weather;

import db.db;
import login.user;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class weather extends HttpServlet {
    public weather(){ super();}

    public void destroy(){
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("运行到servlet:weather_get_record");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("运行到weather servlet");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }
        if(action.equals("get_city_list")){
            try{
                get_city_list(request,response);
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
        }else if(action.equals("add_weather")){
            try{
                add_weather(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("错误的action");
        }
    }

    public void get_city_list(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }
        String sql="select * from weather";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                String tem_low=rs.getString("low");
                String tem_high=rs.getString("high");
//                String tem=tem_low+"摄氏度"+"-"+tem_high+"摄氏度";
                //这里出了点问题，最高和最低是反的
                String tem="最低:"+tem_high+"  最高:"+tem_low+"  (摄氏度)";
               // System.out.println(tem);
                node.put("city",rs.getString("city"));
                node.put("tem",tem);
                node.put("direction",rs.getString("wind_direction"));
                node.put("strength",rs.getString("wind_strength"));
                node.put("type",rs.getString("type"));
                node.put("date",rs.getString("date"));
                arr.put(node);
            }
        }catch(SQLException | JSONException e){
            e.printStackTrace();
        }
        System.out.println("=====================================================");
        try{
            rs.close();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn.close();
        jsonObj.put("data",arr);
        System.out.println(jsonObj.toString());
        response.setContentType("json/application; charset=UTF-8");
        response.getWriter().print(jsonObj);
        response.getWriter().flush();
        response.getWriter().close();
    }

    public void delete_things(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
        System.out.println("运行到servle：delete_things");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String delete_city = request.getParameter("delete_city");
        db conn=new db();
        String sql="DELETE FROM weather WHERE city='"+ delete_city + "'";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }

    public void modify_things(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
        System.out.println("运行到servle：modify_things");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String modify_city=request.getParameter("city");
        String type=request.getParameter("type");
        db conn=new db();
        String sql="UPDATE weather SET type='"+type+"'WHERE city='"+modify_city+"'";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");

    }

    public void add_weather(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("运行到servle：add_weather");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String city=request.getParameter("city");
        String low=request.getParameter("low");
        String high=request.getParameter("high");
        String wind_direction=request.getParameter("wind_direction");
        String wind_strength=request.getParameter("wind_strength");
        String type=request.getParameter("type");
        String date=request.getParameter("date");

        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }
        db conn=new db();
        String sql="INSERT weather (city,low,high,wind_direction,wind_strength,type,date) values( '"+city+"','"+low+"','"+high+"','"+wind_direction+"','"+wind_strength+"','"+type+"','"+date+"')";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }
}
