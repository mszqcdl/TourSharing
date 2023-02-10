package Controller;

import Entity.Route;
import db.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RouteController {
    DBConnect dbc = null;
    public static void main(String[] args){

    }
    public RouteController() throws SQLException{
        dbc = new DBConnect() ;
    }
    //用户id查找路线
    public ArrayList<Route> searchUser(String idUser){
        ArrayList<Route> routes = new ArrayList<Route>();
        // TODO Auto-generated method stub
        String sql = "select * from Route where User_idUser = '"+idUser+"';";
        PreparedStatement pstmt = null ;


        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // search
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            while(rs.next()){
                Route route  = new Route(rs.getString("idRoute"),rs.getString("routeName"),rs.getString("location"),idUser,rs.getString("description"));
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

    //location查找路线
    public ArrayList<Route> searchLoc(String location){
        ArrayList<Route> routes = new ArrayList<Route>();
        // TODO Auto-generated method stub
        String sql = "select * from Route where location = '"+location+"';";
        PreparedStatement pstmt = null ;

        // database
        try{
            // connect
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // search
            ResultSet rs = pstmt.executeQuery();
            boolean res = false;
            while(rs.next()){
                Route route  = new Route(rs.getString("idRoute"),rs.getString("routeName"),location,rs.getString("User_idUser"),rs.getString("description"));
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

    //Add route
    public boolean addRoute(Route route){
        // TODO Auto-generated method stub
        //INSERT INTO `journey`.`Route` (`idRoute`, `routeName`, `location`, `description`, `User_idUser`) VALUES ('1001CN', 'The Palace Museum', 'Beijing', 'Wonderful trip', 'Cecilia');
        String sql = "INSERT INTO `journey`.`Route` (`idRoute`, `routeName`, `location`, `description`, `User_idUser`) VALUES ('"+route.getIdRoute()+"','"+route.getRouteName()+"','"+route.getLocation()+"','"+route.getDescription()+"','"+route.getIdUser()+"');";
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

    //Delete route
    public boolean deleteRoute(String idRoute){
        //DELETE FROM `journey`.`Route` WHERE (`idRoute` = '1');
        String sql = "DELETE FROM `journey`.`Route` WHERE (`idRoute` = '"+idRoute+"');";
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

    //Alter route
    public boolean alterRoute(Route route){
        //UPDATE `journey`.`Route` SET `idRoute` = '1002CN', `routeName` = 'Disneyland', `location` = 'Shanghai', `description` = 'Incredible trip', `User_idUser` = 'Ceci' WHERE (`idRoute` = '1001CN');
        String sql = "UPDATE `journey`.`Route` SET `routeName` = '"+route.getRouteName()+"', `location` = '"+route.getLocation()+"', `description` = '"+route.getDescription()+"', `User_idUser` = '"+route.getIdUser()+"' WHERE (`idRoute` = '"+route.getIdRoute()+"');";
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
