package Controller;

import Entity.Route;
import Entity.User;
import db.DBConnect;
import db.Login;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    DBConnect dbc = null;
    public UserController() throws SQLException {
        dbc = new DBConnect() ;
    }
    //查找所有用户
    public ArrayList<User> searchAll(){
        ArrayList<User> users = new ArrayList<User>();
        // TODO Auto-generated method stub
        String sql = "select * from User;";
        PreparedStatement pstmt = null ;
        //System.out.println(new Login().getUserID());
        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // search
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            while(rs.next()){
                User user  = new User(rs.getString("idUser"),rs.getString("password"));
                users.add(user);
                System.out.println(user);
//                System.out.println(route.getIdRoute());
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if (users.size()==0){
            return null;
        }
        return users;
    }
    public static String toMD5(String pwd){
        String newPwd = "";
        try{
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(pwd.getBytes("UTF8"));

            byte s[] = m.digest();
            for (int i = 0; i < s.length; i++) {
                newPwd += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return newPwd;
    }
//    public static void addNewUser(String urn,String pwd){
//
//    }
}
