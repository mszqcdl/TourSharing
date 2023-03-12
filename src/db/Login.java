package db;
import db.DBConnect;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static String userID = null;

    public String getUserID() {
        System.out.println(userID);
        return userID;
    }

    //    public static void main() throws Exception {
//        Login login = new Login();
//        System.out.println(login.login("Cecilia","1234567"));
//    }
    public int login(String inputUrn,String inputPwd) throws Exception {
        // TODO Auto-generated method stub
        int flag = 0;
        String sql = "select * from User where idUser = '"+inputUrn+"';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        System.out.println("登录用户为"+inputUrn);

        // 下面是针对数据库的具体操作
        try{
            // 连接数据库
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            if(rs.next()){
                String dbPwd = rs.getString("password");
                res = checkPwd(dbPwd,inputPwd);
                if(res) {
                    flag = 1;//Ture
                    userID = inputUrn;
                }
            }
            System.out.println("成功登录用户id"+new Login().getUserID());
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean checkPwd(String pw,String dataStr){
        String result="";
        try {
            //dataStr = this.password;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));

            byte s[] = m.digest();
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            System.out.println("hhjk"+result);
            // return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pw.equals(result))
            return true;
        return false;
    }
    public void logout(){
        userID=null;
    }
}
