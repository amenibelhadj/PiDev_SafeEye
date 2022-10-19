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
 * @author Koussay
 */
public class DataSource {
     String url;
    String login="root";
    String pwd="";
    Connection cnx;
    public static DataSource instance;
    
    private DataSource() {
        this.url = "jdbc:mysql://localhost:3306/safeeye";
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
    
    
    public static DataSource getInstance(){
        if(instance == null){
            instance = new DataSource();
        }
        return instance;
    }
    
}
    

