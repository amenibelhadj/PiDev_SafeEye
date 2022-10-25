
package fiche.safeeye.utils;
import java.util.*;
import java.sql.*;
/**
 *
 * @author 21654
 */
public class MyConnexion {
       String url = "jdbc:mysql://localhost:3306/safeeye";
    String user = "root";
    String pwd = "";
    Statement ste;
    private static MyConnexion instance;
    private Connection conn;
    public Connection getCnx;
    
    private MyConnexion(){
        try { 
            conn = DriverManager.getConnection(url,user,pwd);
            System.out.println("Connexion etablie !! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static MyConnexion getInstance(){
        if (instance==null)
            instance = new MyConnexion ();
        return instance;
    }
    
    public Connection getConnection(){
        return conn;
    
    }
    
}