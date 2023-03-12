package Controller;

import Entity.FaRoute;
import Entity.Route;
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

    public ArrayList<Route> searchUser(String idUser){
        ArrayList<Route> routes = new ArrayList<Route>();
        // TODO Auto-generated method stub
        String sql = "select * from route where idRoute in (select Route_idRoute from favourite where User_idUser='"+idUser+"');";
        PreparedStatement pstmt = null ;
        System.out.println("查找用户id为...的收藏"+idUser);


        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // search
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            while(rs.next()){
                Route route  = new Route(rs.getString("idRoute"),rs.getString("routeName"),rs.getString("location"),rs.getString("User_idUser"),rs.getString("description"));
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

    public boolean searchFaRoute(String idUser,String idRoute){
        ArrayList<Route> routes = new ArrayList<Route>();
        // TODO Auto-generated method stub
        //select * from favourite where User_idUser='Cecilia' and Route_idRoute='1001CN';
        String sql = "select * from favourite where User_idUser='"+idUser+"' and Route_idRoute='"+idRoute+"';";
        PreparedStatement pstmt = null ;
        boolean exist=false;


        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // search
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            while(rs.next()){
                exist=true;
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return exist;
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

    //Delete favourite route by ids
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

    //Delete favourite route by rid
    public boolean deleteRouterid(String rid){
        //DELETE FROM `journey`.`favourite` WHERE (`Route_idRoute` = '1001CN') and (`User_idUser` = 'User');
        String sql = "DELETE FROM `journey`.`favourite` WHERE `Route_idRoute` = '"+rid+"';";
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
