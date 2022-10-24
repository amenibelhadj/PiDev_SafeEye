/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import stock.entities.Produit;
import stock.utils.MyConnexion;

/**
 *
 * @author hp
 */
public class AchatCRUD implements InterfaceAchat<Produit> {
    Connection conn = MyConnexion.getInstance().getConnection();

    public void ajouterProduit2(Produit P)
    {
        
            String req="INSERT INTO `achat`(`id_produit`, `nom`, `type`, `quantite`, `prix`) VALUES (' "+ P.getId_produit()+ "',' "+ P.getNom() + "',' "+ P.getType() + "',' "+ P.getQuantite() + "',' " + P.getPrix() + "')";
            Statement st ; 
        try {
            st=conn.createStatement() ; 
            st.executeUpdate(req) ; 
            System.out.println("ajout terminé!!!");
        }catch(SQLException ex )
        { 
            System.err.println(ex.getMessage()) ;
        } 
    }
    }
    
