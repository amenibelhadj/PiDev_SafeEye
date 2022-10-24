/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.entities;

import java.util.Set;

/**
 *
 * @author hp
 */
public class Achat {
    private int id_achat;
    private int id_produit;
    private int id_user;
    private int prix;
    private Set<Produit> produits ;  

    public Achat() {
    }

    public Achat(int id_achat, int id_produit, int id_user, int prix, Set<Produit> produits) {
        this.id_achat = id_achat;
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.prix = prix;
        this.produits = produits;
    }

    public int getId_achat() {
        return id_achat;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public int getPrix() {
        return prix;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    
    
    
}
