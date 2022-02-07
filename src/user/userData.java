package user;

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
import  java.util.Date;

public class userData extends HttpServlet {
    public userData(){ super();}

    public void destroy(){
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("运行到servlet:user_get_record");
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        System.out.println("运行到servlet");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");
        if (user==null){
            response.setHeader("REDIRECT","REDIRECT");//告诉ajax要重定向
            response.setHeader("PATH","src/pages-login.html");//ip为服务器ip地址，在此用ip代指
            System.out.println("going to return!!!!!!!!");
            return;
        }


        if(action.equals("get_user_data")){
            try{
                getuserData(request,response,user);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(action.equals("delete_things")) {
            try {
                delete_things(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_user_city")){
            try{
                getuserCity(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(action.equals("modify_things")){
            try{
                modify_things(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(action.equals("get_user_money")) {
            try {
                getuserMoney(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_user_quantity")) {
            try {
                getuserQua(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_stock_data")) {
            try {
                getstockData(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_notice")) {
            try {
                getNotice(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_todo")) {
            try {
                getTodo(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_news")) {
            try {
                getNews(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_news_data")) {
            try {
                getnewsData(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_todo_data")) {
            try {
                gettodoData(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(action.equals("get_notice_data")) {
            try {
                getnoticeData(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if(action.equals("get_money")) {
            try {
                getMoney(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("错误的action");
        }
    }
    public void getMoney(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select count(*) count,max(money) from user where money<-5000 union select count(*) count,max(money) from user where money between -5000 and -1 union select count(*) count,max(money) from user where money between 0 and 5000 union select count(*) count,max(money) from user where money>5000;";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("count",rs.getString("count"));

                arr.put(node);
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
    public void getnoticeData(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select count(*) as count,date_format(now(),'%m-%d') as d from notice_table where date_format(notice_time,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 1 day),'%m-%d') as d from notice_table where date_format(notice_time,'%Y-%m-%d') = date_format(date_sub(now(), interval 1 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 2 day),'%m-%d') as d from notice_table where date_format(notice_time,'%Y-%m-%d') = date_format(date_sub(now(), interval 2 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 3 day),'%m-%d') as d from notice_table where date_format(notice_time,'%Y-%m-%d') = date_format(date_sub(now(), interval 3 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 4 day),'%m-%d') as d from notice_table where date_format(notice_time,'%Y-%m-%d') = date_format(date_sub(now(), interval 4 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 5 day),'%m-%d') as d from notice_table where date_format(notice_time,'%Y-%m-%d') = date_format(date_sub(now(), interval 5 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 6 day),'%m-%d') as d from notice_table where date_format(notice_time,'%Y-%m-%d') = date_format(date_sub(now(), interval 6 day),'%Y-%m-%d')  order by d;";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("count",rs.getString("count"));
                node.put("date",rs.getString("d"));
                arr.put(node);
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
    public void gettodoData(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select count(*) as count,date_format(now(),'%m-%d') as d from todo_list where date_format(creatime,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 1 day),'%m-%d') as d from todo_list where date_format(creatime,'%Y-%m-%d') = date_format(date_sub(now(), interval 1 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 2 day),'%m-%d') as d from todo_list where date_format(creatime,'%Y-%m-%d') = date_format(date_sub(now(), interval 2 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 3 day),'%m-%d') as d from todo_list where date_format(creatime,'%Y-%m-%d') = date_format(date_sub(now(), interval 3 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 4 day),'%m-%d') as d from todo_list where date_format(creatime,'%Y-%m-%d') = date_format(date_sub(now(), interval 4 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 5 day),'%m-%d') as d from todo_list where date_format(creatime,'%Y-%m-%d') = date_format(date_sub(now(), interval 5 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 6 day),'%m-%d') as d from todo_list where date_format(creatime,'%Y-%m-%d') = date_format(date_sub(now(), interval 6 day),'%Y-%m-%d')  order by d;";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("count",rs.getString("count"));
                node.put("date",rs.getString("d"));
                arr.put(node);
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
    public void getnewsData(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select count(*) as count,date_format(now(),'%m-%d') as d from news where date_format(date,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 1 day),'%m-%d') as d from news where date_format(date,'%Y-%m-%d') = date_format(date_sub(now(), interval 1 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 2 day),'%m-%d') as d from news where date_format(date,'%Y-%m-%d') = date_format(date_sub(now(), interval 2 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 3 day),'%m-%d') as d from news where date_format(date,'%Y-%m-%d') = date_format(date_sub(now(), interval 3 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 4 day),'%m-%d') as d from news where date_format(date,'%Y-%m-%d') = date_format(date_sub(now(), interval 4 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 5 day),'%m-%d') as d from news where date_format(date,'%Y-%m-%d') = date_format(date_sub(now(), interval 5 day),'%Y-%m-%d') union select count(*) as count,date_format(date_sub(now(), interval 6 day),'%m-%d') as d from news where date_format(date,'%Y-%m-%d') = date_format(date_sub(now(), interval 6 day),'%Y-%m-%d')  order by d;";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("count",rs.getString("count"));
                node.put("date",rs.getString("d"));
                arr.put(node);
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

    public void getNotice(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select count(*) as count from notice_table;";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("count",rs.getString("count"));
                arr.put(node);
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
    public void getTodo(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select count(*) as count from todo_list;";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("count",rs.getString("count"));
                arr.put(node);
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
    public void getNews(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select count(*) as count from news";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("count",rs.getString("count"));
                arr.put(node);
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
    public void getstockData(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select count(*) as count, holder from future_menu where urlcode like \"sz%\" or urlcode like \"sh%\" or urlcode like \"hk%\" group by holder;";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("value",rs.getString("count"));
                node.put("name",rs.getString("holder"));
                arr.put(node);
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
    public void getuserQua(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql= "select count(*) as count from user";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("count",rs.getString("count"));
                arr.put(node);
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
    public void getuserCity(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql= "select city,count(*) as count from user group by city";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("city",rs.getString("city"));
                node.put("count",rs.getString("count"));
                arr.put(node);
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

    public void getuserMoney(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql= "select money,user_name as name from user order by money desc limit 5";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("money",rs.getString("money"));
                node.put("name",rs.getString("name"));
                arr.put(node);
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

    public void getuserData(HttpServletRequest request,HttpServletResponse response,user user) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select * from user where user_name='"+user.getUsername()+"'";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("user_name",rs.getString("user_name"));
                node.put("email",rs.getString("email"));
                node.put("city",rs.getString("city"));
                node.put("money",rs.getString("money"));
                arr.put(node);
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
        String sql="UPDATE todo_list SET todo_things='"+todo_things+"',creatime='"+creatime+"'WHERE id='"+modify_id;
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");

    }


}
