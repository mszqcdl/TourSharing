package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ContentServlet")
public class ContentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //String urn = request.getParameter("Username");
//        System.out.println("here");
//        System.out.println(request.getParameter("rid"));
        request.getRequestDispatcher("/content.jsp").forward(request, response);



    }
}
