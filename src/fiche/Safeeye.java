/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiche;
import fiche.safeeye.entities.Fiche;
import fiche.safeeye.service.FicheService;
import fiche.safeeye.utils.MyConnexion;
/**
 *
 * @author 21654
 */
public class Safeeye {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         MyConnexion c = MyConnexion.getInstance();
        Fiche f1 = new Fiche (1,"koussay","zarrouk","khayri");
        Fiche f2 = new Fiche (2,"dania","benali","khayri");
        FicheService fs = new FicheService();
        //fs.ajouterFiche(f1);
       //fs.supprimerFiche(f1);
       
       //fs.afficherFiche();
    }
    
}
