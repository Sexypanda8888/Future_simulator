package datas.stock;


import db.db;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import datas.HttpRequest;


/*
参考：隔壁那个JSP->upload_file_jsp.jsp
由JSP改过来的Servlet
web.xml里配置四个条目：
    <servlet>
        <servlet-name>file_upload</servlet-name>
        <servlet-class>maintain.device.FileUpload</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>file_upload</servlet-name>
        <url-pattern>/file_upload</url-pattern>
    </servlet-mapping>
需要用到的lib：（拷贝到WEB-INF/lib下）
commons-fileupload-1.2.jar
commons-io-2.4.jar
文件上传后，会写到当前目录的/file目录下
 */
public class financeData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("运行到servlet");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        try {
            getStockList(request,response,type);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.getWriter().close();
        /*----------------------------------------返回给前端 结束----------------------------------------*/
    }

    public void getStockList(HttpServletRequest request,HttpServletResponse response,String type) throws IOException, JSONException {
        db conn=new db();
        List jsonList=new ArrayList();
        JSONArray arr =new JSONArray();
        JSONObject jsonObj = new JSONObject();
        String sql="select * from future_menu where type="+type;
        System.out.println("SQL语句是："+ sql + "<br>");
        ResultSet rs = conn.executeQuery(sql);
        try{
            while(rs.next()){
                JSONObject node =new JSONObject();
                node.put("name",rs.getString("name"));
                node.put("urlCode",rs.getString("urlCode"));
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
}