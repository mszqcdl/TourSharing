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

@WebServlet("/AddRouteServlet")
public class AddRouteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        SmartUpload smart =new SmartUpload();
        smart.initialize(this.getServletConfig(),request,response);//初始化上传

        try {
            HttpSession session = request.getSession();
            smart.upload(); //准备上传
            String name = smart.getRequest().getParameter("rname");
            String loc = smart.getRequest().getParameter("loc");
            String des = smart.getRequest().getParameter("des");
            System.out.println(smart.getFiles().getSize());
            String filename=smart.getFiles().getFile(0).getFileName();
            filename = filename.substring(0,filename.lastIndexOf("."));
            System.out.println(smart.getRequest().getParameter("des"));
            RouteController routeController = new RouteController();
            if (new Login().getUserID()==null){
                response.getWriter().print("<script language=javascript>alert('Please log into your account first');window.location='./Login.jsp'</script>");

            }
            else if (!filename.equals(routeController.searchMax())){
                response.getWriter().print("<script language=javascript>alert('The file name must be the same as the route ID!');window.location='./addRoute.jsp'</script>");
            }
            else{
                smart.save("GPXFile");//保存文件夹
                System.out.println(session.getServletContext().getRealPath("/"));
                routeController.addRoute(new Route(routeController.searchMax(),name,loc,new Login().getUserID(),des));
                response.sendRedirect("/TourSharing_war_exploded/content");
            }

        } catch (SmartUploadException | SQLException e) {
            e.printStackTrace();
        }
    }
}

