package Servlet;

import Controller.RouteController;
import Entity.Route;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import db.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ModifyRouteServlet")
public class ModifyRouteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        SmartUpload smart =new SmartUpload();
        smart.initialize(this.getServletConfig(),request,response);//初始化上传

        try {
            HttpSession session = request.getSession();
            smart.upload(); //准备上传
            String name = smart.getRequest().getParameter("rname");
            System.out.println(name+"rname");
            String loc = smart.getRequest().getParameter("loc");
            String des = smart.getRequest().getParameter("des");
            String id = smart.getRequest().getParameter("routeId");
            System.out.println(smart.getFiles().getSize());
            String filename=smart.getFiles().getFile(0).getFileName();
            //filename = filename.substring(0,filename.lastIndexOf("."));
            System.out.println(smart.getRequest().getParameter("des"));
            RouteController routeController = new RouteController();
            if (new Login().getUserID()==null){
                response.getWriter().print("<script language=javascript>alert('Please log into your account first');window.location='./Login.jsp'</script>");

            }
            else if (smart.getFiles().getSize()==0){
                System.out.println(new Route(id,name,loc,new Login().getUserID(),des).toString());
                routeController.alterRoute(new Route(id,name,loc,new Login().getUserID(),des));
                response.sendRedirect("/TourSharing_war_exploded/content");

            }
            else if (!filename.substring(0,filename.lastIndexOf(".")).equals(id)){
                response.getWriter().print("<script language=javascript>alert('The file name is incorrect!');window.location='./ownRoute.jsp'</script>");
            }
            else{
                smart.save("GPXFile");//保存文件夹
                System.out.println(session.getServletContext().getRealPath("/"));
                routeController.alterRoute(new Route(id,name,loc,new Login().getUserID(),des));
                response.sendRedirect("/TourSharing_war_exploded/content");
            }

        } catch (SmartUploadException | SQLException e) {
            e.printStackTrace();
        }
    }
}
