package Servlet;
import Controller.RouteController;
import Entity.Route;
import db.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{

        String urn = req.getParameter("Username");
        String pwd = req.getParameter("Password");


        int result = 0;
        if(urn.equals("")){
            HttpSession session=req.getSession();
            res.getWriter().print("<script language=javascript>alert('The username is empty! ');window.location='./Login.jsp'</script>");
        }
        else if(pwd.equals("")){
            HttpSession session=req.getSession();
            res.getWriter().print("<script language=javascript>alert('The password is empty! ');window.location='./Login.jsp'</script>");
        }
        else{
            try{
                Login log = new Login();
                result = log.login(urn,pwd);
                if (result==0){
                    HttpSession session=req.getSession();
                    res.getWriter().print("<script language=javascript>alert('The password or user name is wrong! ');window.location='./Login.jsp'</script>");
                }
                else if (result==1){
                    HttpSession session=req.getSession();

                    /*传输到routeInfo的信息*/
//                    ExtractPoints extractPoints = new ExtractPoints("");
//                    session.setAttribute("message", message);
//                    session.setAttribute("list",extractPoints.getLocations());
//                    session.setAttribute("avgLat",extractPoints.getA());
//                    session.setAttribute("avgLon",extractPoints.getB());
                    RouteController routeController = new RouteController();
                    //session.setAttribute("routes",routeController.searchAll());
                    ArrayList<Route> routes = routeController.searchAll();
                    String locations = "";
                    String ids = "";
                    String names = "";
                    String descriptions = "";
                    for (int i=0;i<routes.size();i++){
                        locations = locations+routes.get(i).getLocation()+",";
                        ids = ids+routes.get(i).getIdRoute()+",";
                        names = names+routes.get(i).getRouteName()+",";
                        descriptions = descriptions+routes.get(i).getDescription()+",,";
                        //routes[i] = new Route(routes.get(i).getIdRoute(),routesList.get(i).getRouteName(),routesList.get(i).getLocation(),routesList.get(i).getIdUser(),routesList.get(i).getDescription());
                    }
                    session.setAttribute("names",names.substring(0,names.length()-1));
                    session.setAttribute("ids",ids.substring(0,ids.length()-1));
                    session.setAttribute("descriptions",descriptions.substring(0,descriptions.length()-1));
                    session.setAttribute("locations",locations.substring(0,locations.length()-1));
                    System.out.println(locations);
                    res.sendRedirect("./content");
                    //res.getWriter().print("<script language=javascript>window.location='./test.html'</script>");
                }
            }
            catch (Exception e){
                HttpSession session=req.getSession();
                res.sendRedirect("./page-error-400.jsp");
            }
        }


    }
}
