/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.Reclamation;
import ReclamationService.ReclamationService;
import source.DataSource;

/**
 *
 * @author Koussay
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSource ds = DataSource.getInstance();
        System.out.println(ds.hashCode());
        
        Reclamation r = new Reclamation(1,"aa","x","x","zzz");
        Reclamation r1 = new Reclamation(1,"zz","ee","zz","aa");
        
        ReclamationService rs = new ReclamationService();
        
         //rs.ajouterReclamation(r1);
         //rs.supprimerReclamation(r);
         //rs.afficherReclamations();
         //rs.modifierReclamation(r1);
    
    }
    
}
