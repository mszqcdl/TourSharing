package Controller;

import Entity.FaRoute;
import db.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FaRouteController {
    //用户id查找路线
    DBConnect dbc = null;
    public FaRouteController() throws SQLException{
        dbc = new DBConnect() ;
    }

    public ArrayList<FaRoute> searchUser(String idUser){
        ArrayList<FaRoute> routes = new ArrayList<FaRoute>();
        // TODO Auto-generated method stub
        String sql = "select * from Favourite where User_idUser = '"+idUser+"';";
        PreparedStatement pstmt = null ;


        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // search
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            while(rs.next()){
                FaRoute route  = new FaRoute(rs.getString("User_idUser"),rs.getString("Route_idRoute"));
                routes.add(route);
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if (routes.size()==0){
            return null;
        }
        return routes;
    }


    //Add favourite route
    public boolean addRoute(FaRoute route){
        //INSERT INTO `journey`.`favourite` (`User_idUser`, `Route_idRoute`) VALUES ('User', 'CN1001');
        String sql = "INSERT INTO `journey`.`favourite` (`User_idUser`, `Route_idRoute`) VALUES ('"+route.getIdUser()+"','"+route.getIdRoute()+"');";
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

    //Delete favourite route
    public boolean deleteRoute(FaRoute faRoute){
        //DELETE FROM `journey`.`favourite` WHERE (`Route_idRoute` = '1001CN') and (`User_idUser` = 'User');
        String sql = "DELETE FROM `journey`.`favourite` WHERE (`Route_idRoute` = '"+faRoute.getIdRoute()+"') and (`User_idUser` = '"+faRoute.getIdUser()+"');";
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
}
