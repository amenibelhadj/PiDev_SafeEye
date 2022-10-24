/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.activation.DataSource;
import stock.entities.Produit;
import stock.services.ProduitCRUD;
import stock.utils.MyConnexion;

/**
 *
 * @author hp
 */
public class main {
    public static void main(String[] args) {
        MyConnexion ds = MyConnexion.getInstance();
        System.out.println(ds.hashCode());
        
        ProduitCRUD ps= new ProduitCRUD();
        
        Produit P = new Produit(6,"drex",1,24,12);
       // System.out.println(ps.afficherProduit2());
       int x;
        x=ps.rechercherProduit("doliprane");  
        System.out.println(x);
        
        
       //  ps.ajouterProduit(P);
        // ps.supprimerProduit(5);
         //System.out.println(ps.afficherProduit());
         //ps.modiferProduit(P);
    
    }
    
}
