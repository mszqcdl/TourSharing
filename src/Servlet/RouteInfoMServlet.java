package Servlet;

import Controller.FaRouteController;
import Controller.RouteController;
import Entity.FaRoute;
import db.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RouteInfoMServlet")
public class RouteInfoMServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Login login = new Login();
        RouteController routeController = null;
        FaRouteController faRouteController = null;
        System.out.println(req.getParameter("routeId")+"rid");
        try {
            routeController = new RouteController();
            faRouteController = new FaRouteController();
            faRouteController.deleteRouterid(req.getParameter("routeId"));
            routeController.deleteRoute(req.getParameter("routeId"));
            res.getWriter().print("<script language=javascript>alert('Successfully delete!');window.location='./routeManage.jsp'</script>");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
