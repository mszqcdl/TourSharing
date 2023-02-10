package db;
import db.DBConnect;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public int login(String inputUrn,String inputPwd) throws Exception {
        // TODO Auto-generated method stub
        int flag = 1;
        String sql = "select * from User where idUser = '"+inputUrn+"';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;

        // 下面是针对数据库的具体操作
        try{
            // 连接数据库
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            //pstmt.setString(1,admin.getEmail()) ;//比如说这个地方的接口
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            if(rs.next()){
                String dbPwd = rs.getString("password");
                res = checkPwd(dbPwd,inputPwd);
                if(res)
                    flag = 1;//admin
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;//0 either factory or admin
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
}
