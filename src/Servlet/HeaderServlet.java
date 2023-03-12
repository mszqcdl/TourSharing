package Servlet;

import Controller.RouteController;
import Entity.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/HeaderServlet")
public class HeaderServlet extends HttpServlet { public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
    doPost(req, res);
}

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String slocation = req.getParameter("location");
        HttpSession session = req.getSession();
        try {
            RouteController routeController = new RouteController();
            ArrayList<Route> routes = routeController.searchLoc(slocation);
            String locations = "";
            String ids = "";
            String names = "";
            String descriptions = "";
            if (routes==null){
                session.setAttribute("names","");
                session.setAttribute("ids","");
                session.setAttribute("descriptions","");
                session.setAttribute("locations","");
            }
            else {
                for (int i=0;i<routes.size();i++){
                    locations = locations+routes.get(i).getLocation()+",";
                    ids = ids+routes.get(i).getIdRoute()+",";
                    names = names+routes.get(i).getRouteName()+",";
                    descriptions = descriptions+routes.get(i).getDescription()+",,";
                    //routes[i] = new Route(routes.get(i).getIdRoute(),routesList.get(i).getRouteName(),routesList.get(i).getLocation(),routesList.get(i).getIdUser(),routesList.get(i).getDescription());
                }
                session.setAttribute("names",names.substring(0,names.length()));
                session.setAttribute("ids",ids.substring(0,ids.length()));
                session.setAttribute("descriptions",descriptions.substring(0,descriptions.length()));
                session.setAttribute("locations",locations.substring(0,locations.length()));
            }
            res.sendRedirect("./searchLoc.jsp?"+slocation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
