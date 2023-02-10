package Servlet;
import db.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
            res.getWriter().print("<script language=javascript>alert('The username is empty! ');window.location='./Login.html'</script>");
        }
        else if(pwd.equals("")){
            HttpSession session=req.getSession();
            res.getWriter().print("<script language=javascript>alert('The password is empty! ');window.location='./Login.html'</script>");
        }
        else{
            try{
                Login log = new Login();
                result = log.login(urn,pwd);
                if (result==0){
                    HttpSession session=req.getSession();
                    res.getWriter().print("<script language=javascript>alert('The password or user name is wrong! ');window.location='./Login.html'</script>");
                }
                else if (result==1){
                    HttpSession session=req.getSession();
                    MapServlet mapServlet = new MapServlet();
                    String message = "Hello World!";
                    session.setAttribute("message", message);
                    res.sendRedirect("./test.jsp");
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
