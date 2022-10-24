/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hp
 */
public class MyConnexion {
    String url ="jdbc:mysql://localhost:3306/test_pi";
    String user ="root";
    String pwd="";
    Statement ste;
    ResultSet rst;
    private static MyConnexion instance;
    private Connection conn;
    Connection cnx;
    private MyConnexion()
    {
        try
        {
            conn = DriverManager.getConnection(url,user,pwd); 
            System.out.println("connexion etablie !!");
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public static MyConnexion getInstance()
    {
        if (instance == null)
        {
            instance =new MyConnexion();
        }
        return instance;
    }   
    public Connection getConnection ()
    {
        return conn;
    }
    
}
