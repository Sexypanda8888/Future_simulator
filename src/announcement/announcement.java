package announcement;

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

public class announcement extends HttpServlet{
    public announcement(){ super();}

    public void destroy(){
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("运行到servlet:announcement_get_record");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("运行到announcement servlet");
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
        if(action.equals("get_announcement_list")){
            try{
                get_announcement_list(request,response);
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
        }else if(action.equals("add_announcement")){
            try{
                add_announcement(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("错误的action");
        }
    }

    public void get_announcement_list(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
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
        String sql="select * from announcement";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("id",rs.getString("id"));
                node.put("title",rs.getString("announcement_title"));

                node.put("comment",rs.getString("announcement_comment"));
                node.put("time",rs.getString("create_time"));

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
        System.out.println("运行到announcement servle：delete_things");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String delete_id = request.getParameter("id");
        db conn=new db();
        String sql="DELETE FROM announcement WHERE id='"+ delete_id + "'";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }

    public void modify_things(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
        System.out.println("运行到announcement servlet：modify_things");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String title=request.getParameter("title");
        String id=request.getParameter("id");
        String comment=request.getParameter("comment");
        String time=request.getParameter("time");
        db conn=new db();
        String sql="UPDATE announcement SET announcement_title='"+title+"',announcement_comment='"+comment+"',create_time='"+time+"'WHERE id='"+id+"'";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");

    }

    public void add_announcement(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("运行到servle：add_user");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String title=request.getParameter("title");
        String comment=request.getParameter("comment");
        String time=request.getParameter("time");


        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }
        db conn=new db();
        String sql="INSERT announcement (announcement_title,announcement_comment,create_time) values( '"+title+"','"+comment+"','"+time+"')";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }
}
