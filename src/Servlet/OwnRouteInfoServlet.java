package Servlet;
import Controller.FaRouteController;
import Controller.RouteController;
import db.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OwnRInfoServlet")

public class OwnRouteInfoServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        System.out.println("Delete...");
        Login login = new Login();
        RouteController routeController = null;
        System.out.println(req.getParameter("routeId"));
        try {
            System.out.println("Enter try...");
            routeController = new RouteController();
            if (login.getUserID()==null){
                System.out.println("No user...");
                res.getWriter().print("<script language=javascript>alert('Guests cannot access this part. Please log in.');window.location='./Login.jsp'</script>");
            }
            else if(routeController.searchIds(login.getUserID(),req.getParameter("routeId"))==false){
                System.out.println(req.getQueryString());
                System.out.println("No route...");
                res.getWriter().print("<script language=javascript>alert('You didin't create such route!');window.location='./ownRoute.jsp'</script>");
            }
            else {
                System.out.println("start delete...");
                FaRouteController faRouteController=new FaRouteController();
                faRouteController.deleteRouterid(req.getParameter("routeId"));
                routeController.deleteRoute(req.getParameter("routeId"));
                res.getWriter().print("<script language=javascript>alert('Successfully delete!');window.location='./ownRoute.jsp'</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
