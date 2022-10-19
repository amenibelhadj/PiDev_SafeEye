/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiche.safeeye.service;

import fiche.safeeye.entities.Fiche;
import fiche.safeeye.utils.MyConnexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21654
 */
public class FicheService implements fiche.safeeye.service.IFicheService<Fiche> {
    
    
    
    @Override
    public void ajouterFiche(Fiche f) {
         try {
            String requete= "INSERT INTO `fiche` (Nom_p,Prenom_p,Nom_med)"
                    + "VALUES (?,?,?)";
            PreparedStatement pst = MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1,f.getNom_p());
            pst.setString(2, f.getPrenom_p());
            pst.setString(3, f.getNom_med());
           
            pst.executeUpdate();
            System.out.println("fiche ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    

 
    @Override
    public void supprimerFiche(Fiche f) {
        try {
            String requete = "DELETE FROM `fiche` where ID_fiche=?";
            PreparedStatement pst = MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, f.getId_fiche());
            pst.executeUpdate();
            System.out.println("Fiche supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    
    
    @Override
    public void modifierFiche(Fiche f) {
             
     try {
            String requete = "UPDATE fiche SET Nom_p=?,Prenom_p=?,Nom_med=?  WHERE id_fiche=?";
            PreparedStatement pst = MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1,f.getNom_p());
            pst.setString(2, f.getPrenom_p());
            pst.setString(3, f.getNom_med());
            
            pst.executeUpdate();
            System.out.println("Fiche modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    @Override
    public List<Fiche> afficherFiche() {
         List<Fiche> FichesList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM fiche f ";
            PreparedStatement pst = MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            ResultSet rs =  pst.executeQuery(requete);
            while(rs.next()){
                Fiche f = new Fiche ();
                f.setId_fiche(rs.getInt("Id_fiche"));
                f.setNom_p(rs.getString("Nom_p"));
                f.setPrenom_p(rs.getString("Prenom_p"));
                f.setNom_med(rs.getString("Nom_med"));
                
                FichesList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return FichesList;
    }

    
}
