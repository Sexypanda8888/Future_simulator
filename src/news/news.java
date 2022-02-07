package news;

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

public class news  extends HttpServlet {
    public news(){ super();}

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
        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }
        if(action.equals("get_news")){
            try{
                getNews(request,response,user);
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
        }else if(action.equals("add_news")){
            try{
                add_news(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("错误的action");
        }
    }

    public void getNews(HttpServletRequest request,HttpServletResponse response,user user) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select * from news";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                JSONObject node2 =new JSONObject();
                node.put("title",rs.getString("title"));
                node.put("url",rs.getString("url"));
                node2.put("date",rs.getString("date"));
                node2.put("title",node);
                node2.put("id",rs.getString("id"));
                arr.put(node2);
//                List list = new ArrayList();
//                list.add(rs.getString("todo_things"));
//                list.add(rs.getString("creatime"));
//                jsonList.add(list);

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
        System.out.println("运行到news servlet：delete_things");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String delete_id = request.getParameter("id");
        db conn=new db();
        String sql="DELETE FROM news WHERE id='"+ delete_id + "'";
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
        String title=request.getParameter("title");
        String url=request.getParameter("url");
        String date=request.getParameter("date");
        db conn=new db();
        String sql="UPDATE news SET title='"+title+"',url='"+url+"',date='"+date+"'WHERE id='"+modify_id+"'";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");

    }

    public void add_news(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("运行到servle：add_news");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String title=request.getParameter("title");
        String url=request.getParameter("url");
        String date=request.getParameter("date");
        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }
        db conn=new db();
        String sql="INSERT news (title,url,date) values( '"+title+"','"+url+"','"+date+"')";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }
}
