/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.dispo;

import IServices.IdispoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import source.DataSource;

/**
 *
 * @author user
 */
public class dispoService {
     Connection cnx = DataSource.getinstance().getCnx();
    public void ajouterD(dispo v)
    {

         try {

            Statement pst;
            pst =cnx.createStatement();
            String req = "INSERT INTO `disponibilite`(`nom_medd` ,`date`,`heure` ) "
                    + "VALUES ('"+v.getNom_medd()+"', '"+v.getDate()+"', '"+v.getHeure()+"')";
        
            
            pst.executeUpdate(req);
           
            System.out.println("ajouté");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    public void supprimerD(dispo r) {
               
        try {
            String requete = "DELETE FROM disponibilite WHERE id_rdv='" ;

            PreparedStatement pst = DataSource.getinstance().getCnx()
                    .prepareStatement(requete);
            
            pst.setInt(1,r.getId_dispo());
            
            pst.executeUpdate(requete);
            System.out.println("deleted successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
      public void supp(int id) {
       String req = "DELETE FROM `disponibilite` WHERE `id_dispo`='"+id+"'" ;
        Statement st;
        try {
            st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" deleted successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    public void modifierD(dispo v) {
        
        
        try {
            
            String req="UPDATE disponibilite SET nom_medd=?,date=?,heure=? WHERE id_dispo=? ";
            PreparedStatement pst = DataSource.getinstance().getCnx()
                    .prepareStatement(req);
            
            
            pst.setString(1, v.getNom_medd());
            pst.setString(2, v.getDate());
            pst.setString(3, v.getHeure());
           
            pst.executeUpdate(req);
            System.out.println("updated successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    public List<dispo> afficherD() {
        List <dispo> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM disponibilite v  ";
            
            PreparedStatement pst;
            pst = DataSource.getinstance().getCnx()
                    .prepareStatement(req);
            
            ResultSet rs = pst.executeQuery(req);
            while(rs.next()){
                
                dispo v = new dispo();
            v.setId_dispo(rs.getInt("id_dispo")); 
           
            v.setNom_medd(rs.getString("nom_medd"));
            v.setDate(rs.getString("date"));
            v.setHeure(rs.getString("heure"));
          
                    
             list.add(v);
            }
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
       
    }

  
    
}
