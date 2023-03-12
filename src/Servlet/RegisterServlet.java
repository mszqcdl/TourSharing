package Servlet;
import db.DBConnect;
import Controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")

public class RegisterServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        doPost(req, res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String urn = req.getParameter("Username");
        String pwd = req.getParameter("Password");
        DBConnect dbc = null;
        String sql = "select idUser from User where idUser = '"+urn+"';";
        PreparedStatement pstmt = null ;
        // 下面是针对数据库的具体操作
        try{
            // 连接数据库
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                res.getWriter().print("<script language=javascript>alert('The username already exists! Please re-enter another one');window.location='./Register.html'</script>");
            }
            else{
                //写入数据库
                String pwdDB = UserController.toMD5(pwd);
                //INSERT INTO `journey`.`user` (`idUser`, `password`) VALUES ('user', 'fcea920f7412b5da7be0cf42b8c93759');
                String sqlAdd = "INSERT INTO `journey`.`user` (`idUser`, `password`) VALUES ( '"+urn+ "','" + pwdDB + "');";
                pstmt = dbc.getConnection().prepareStatement(sqlAdd) ;
                pstmt.executeUpdate();
                res.getWriter().print("<script language=javascript>alert('Successfully register');window.location='./Login.jsp'</script>");
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
