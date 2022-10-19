/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */

    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Koussay
 */
public class DataSource {
    String url="jdbc:mysql://localhost:3306/safeeye";
    String login="root";
    String pwd="";
    Connection cnx;
    public static DataSource instance;
    
    Statement ste;
   
    
    private DataSource() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    public static DataSource getinstance(){
        if(instance == null){
            instance = new DataSource();
        }
        return instance;
    }
    
}
