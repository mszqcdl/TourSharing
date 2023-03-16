package Servlet;
import Controller.FaRouteController;
import Controller.RouteController;
import Entity.FaRoute;
import db.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/FaRInfoServlet")

public class FaRouteInfoServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Login login = new Login();
        FaRouteController faRouteController = null;
        System.out.println("Delete Fa...");
        System.out.println(req.getParameter("routeId"));
        try {
            faRouteController = new FaRouteController();
            if (login.getUserID()==null){
                res.getWriter().print("<script language=javascript>alert('Guests cannot access this part. Please log in.');window.location='./Login.jsp'</script>");
            }
            else if(faRouteController.searchFaRoute(login.getUserID(),req.getParameter("routeId"))==false){
                System.out.println("else if ");
                res.getWriter().print("<script language=javascript>alert('You didin't favourite such route!');window.location='./faRoutes.jsp'</script>");

            } else{
                faRouteController.deleteRoute(new FaRoute(login.getUserID(),req.getParameter("routeId")));
                res.getWriter().print("<script language=javascript>alert('Successfully delete!');window.location='./faRoutes.jsp'</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
