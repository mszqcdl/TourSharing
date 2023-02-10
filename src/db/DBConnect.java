package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private final String DBDRIVER = "com.mysql.cj.jdbc.Driver" ;
    private final String DBURL = "jdbc:mysql://localhost:3306/journey?useSSL=false&serverTimezone=UTC" ;
    private final String DBUSER = "root" ;
    //private final String DBURL = "jdbc:mysql://127.0.0.1:3306/User?useSSL=false&serverTimezone=UTC" ;
    private final String DBPASSWORD = "0202271wyx" ;
    private Connection conn = null ;

    public DBConnect()   {
        try{
            Class.forName(DBDRIVER) ;
            this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD) ;
            System.out.println("succeed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // 取得数据库连接
    public Connection getConnection(){
        return this.conn ;
    }

    // 关闭数据库连接
    public void close(){
        try{
            this.conn.close() ;
        }catch (Exception e){ }
    }

    public static void main(String[] args){
        DBConnect bd = new DBConnect();
    }
}
