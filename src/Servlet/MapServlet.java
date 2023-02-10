package Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Hello World!";
        HttpSession session=request.getSession();
        session.setAttribute("message", message);
        response.sendRedirect("./index.jsp");
//        request.setAttribute("message", message);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("./index.jsp");
//        dispatcher.forward(request, response);
    }
}
