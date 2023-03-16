package Servlet;

import Controller.FaRouteController;
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

@WebServlet("/RouteInfoServlet")
public class RouteInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Login login = new Login();
        FaRouteController faRouteController = null;
        System.out.println(req.getParameter("routeId")+"rid");
        try {
            faRouteController = new FaRouteController();
            if (login.getUserID()==null){
                res.getWriter().print("<script language=javascript>alert('Guests cannot access this part. Please log in.');window.location='./Login.jsp'</script>");
            }
            else{
                faRouteController.addRoute(new FaRoute(login.getUserID(),req.getParameter("routeId")));
                res.getWriter().print("<script language=javascript>alert('Successfully add!');window.location='./content'</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
