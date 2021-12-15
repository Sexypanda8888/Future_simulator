package datas;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
public class futureData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("运行到servlet");
//        request.setCharacterEncoding("gb18030");
//        response.setCharacterEncoding("gbk");
        response.setHeader("Content-Type","application/javascript; charset=utf-8");
        String url = request.getParameter("url");
        url=java.net.URLDecoder.decode(url,"utf-8");
        System.out.println("将要发送的url为"+url);
//直接输入要访问的地址，它只是一个用来访问的

        String s=HttpRequest.sendGet(url,"");
        System.out.println(s);
//        EncodingUtil a =new EncodingUtil();

//        String b=a.convertEncoding_Str(s,"GB18030","utf-8");
//        String ss=new String(s.getBytes("utf-8"),"GBK");
//        System.out.println(ss);
        response.getWriter().print(s);
        response.getWriter().flush();
        response.getWriter().close();
        /*----------------------------------------返回给前端 结束----------------------------------------*/
    }


}
