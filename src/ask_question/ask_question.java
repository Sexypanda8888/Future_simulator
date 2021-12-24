package ask_question;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ask_question extends HttpServlet {
    public ask_question(){ super();}

    public void destroy(){
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("运行到servlet:ask_question");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("运行到servlet:ask_question");
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
        System.out.println("From Session,user is"+user.getUsername()+"pwd is" + user.getPassword());

        if(action.equals("get_question_list")){
            try{
                get_question_lisst(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(action.equals("add_question")){
            try{
                add_question(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(action.equals("do_answers")){
            try{
                do_answers(request,response);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println("错误的action");
        }
    }

    public void get_question_lisst(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select * from ask_question";
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("id",rs.getString("id"));
                node.put("question",rs.getString("question"));
                node.put("answer",rs.getString("answer"));
                node.put("answer_user",rs.getString("answer_user"));
                node.put("ask_user",rs.getString("ask_user"));
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

    public void add_question(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("运行到servle：add_todo_things");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String question=request.getParameter("question");
        String answer=request.getParameter("answer");
        String answer_user="No answer";

        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }

        db conn=new db();
        String sql="INSERT ask_question (question,answer,ask_user,answer_user) values( '"+question+"','"+answer+"','"+user.getUsername()+"','"+answer_user+"')";
        System.out.println(sql);
        conn.executeUpdate(sql);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }

    public void do_answers(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
        db conn=new db();
        System.out.println("运行到servle：do_answer");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String id=request.getParameter("id");
        String answer=request.getParameter("answer");

        String sql="select * from ask_question where id='"+id+"'";
        ResultSet rs = conn.executeQuery(sql);
        System.out.println(sql);
        String rs2="";
        String question="";

        HttpSession session = request.getSession();
        user user = (login.user)session.getAttribute("user");
        if (user==null){
            response.sendRedirect("src/pages-login.html");
            System.out.println("going to return!!!!!!!!");
            return;
        }

        while(rs.next()){
            rs2=rs.getString("answer");
            question=rs.getString("question");
        }

        String sql2="";
        if(rs2.equals("This question is not answered")){
            sql2="UPDATE ask_question SET answer='"+answer+"',answer_user='"+user.getUsername()+"' WHERE id='"+id+"'";
        }else{
            sql2="INSERT ask_question (question,answer,answer_user) values( '"+question+"','"+answer+"','"+user.getUsername()+"')";
        }
        conn.executeUpdate(sql2);
        System.out.println(sql2);
        conn.close();
        System.out.println("操作数据完毕，关闭了数据库！");
    }
}
