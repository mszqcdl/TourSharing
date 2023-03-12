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

//        // 设置响应内容类型
//        response.setContentType("application/json");
//
//        // 获取输出流
//        PrintWriter out = response.getWriter();
//
//        // 创建一个person对象
//        try {
//            Route route = new RouteController().searchAll().get(0);
//            JSONObject gson = (JSONObject) JSONObject.toJSON(route);
//            String json = gson.toJSONString();
//            System.out.println(json);
//            out.print(json);
//        } catch (SQLException e) {
//            out.print("new String()");
//            e.printStackTrace();
//        }
        request.getRequestDispatcher("/content.jsp").forward(request, response);

        // 创建一个Gson对象


        // 将person对象转换为JSON字符串


        // 将JSON字符串写入响应体


    }
}
