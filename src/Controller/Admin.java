package Controller;

import Entity.Route;
import Entity.User;
import db.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin {
    DBConnect dbc = null;
    public Admin() throws SQLException {
        dbc = new DBConnect() ;
    }
    public boolean addUser(User user){
        //INSERT INTO `journey`.`favourite` (`User_idUser`, `Route_idRoute`) VALUES ('User', 'CN1001');
        String sql = "INSERT INTO `journey`.`User` (`idUser`, `password`) VALUES ('"+user.getIdUser()+"','"+user.getPwd()+"');";
        PreparedStatement pstmt = null ;
        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean delUser(String idUser){
        //DELETE FROM `journey`.`favourite` WHERE (`favourite` = '1');
        String sql = "DELETE FROM `journey`.`User` WHERE (`idUser` = '"+idUser+"');";
        PreparedStatement pstmt = null ;
        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean alterUserPwd(User user){
        //UPDATE `journey`.`Route` SET `idRoute` = '1002CN', `routeName` = 'Disneyland', `location` = 'Shanghai', `description` = 'Incredible trip', `User_idUser` = 'Ceci' WHERE (`idRoute` = '1001CN');
        String sql = "UPDATE `journey`.`User` SET `password` = '"+user.getPwd()+"' WHERE (`idUser` = '"+user.getIdUser()+"');";
        PreparedStatement pstmt = null ;
        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public User searchUser(String idUser){
        User user;
        // TODO Auto-generated method stub
        String sql = "select * from User where idUser = '"+idUser+"';";
        PreparedStatement pstmt = null ;

        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // search
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            if(rs.next()){
                user  = new User(rs.getString("idUser"),rs.getString("password"));
                rs.close() ;
                pstmt.close() ;
                return user;
            }
            else {
                rs.close() ;
                pstmt.close() ;
                return null;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
