package Controller;

import java.security.MessageDigest;

public class UserController {
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
