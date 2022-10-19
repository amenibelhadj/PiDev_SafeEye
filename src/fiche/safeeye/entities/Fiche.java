/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiche.safeeye.entities;

/**
 *
 * @author 21654
 */
public class Fiche {
        private int id_fiche;
    private String nom_p;
    private String prenom_p;
    private String nom_med;
    
    public Fiche (int id_fiche, String nom_p, String prenom_p, String nom_med){
        this.id_fiche=id_fiche;
        this.nom_p=nom_p;
        this.prenom_p=prenom_p;
        this.nom_med=nom_med;
    }
    public Fiche ( String nom_p, String prenom_p, String nom_med){
        this.nom_p=nom_p;
        this.prenom_p=prenom_p;
        this.nom_med=nom_med;
    }

    public int getId_fiche() {
        return id_fiche;
    }

    public String getNom_p() {
        return nom_p;
    }

    public String getPrenom_p() {
        return prenom_p;
    }

    public String getNom_med() {
        return nom_med;
    }
    public void setId_fiche(int id_fiche) {
        this.id_fiche = id_fiche;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public void setPrenom_p(String prenom_p) {
        this.prenom_p = prenom_p;
    }
    public void setNom_med(String nom_med) {
        this.nom_med = nom_med;
    }

    @Override
    public String toString() {
        return "Fiche{" + "id fiche=" + id_fiche + ", nom patient=" + nom_p + ", prenom patient=" + prenom_p + "nom medecin=" + nom_med + '}';
    }

    public Fiche() {
    }


    
    
    
}
