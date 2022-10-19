/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.test;

import edu.projet.entities.User;
import edu.projet.service.UserCRUD;
import edu.projet.utils.MyConnexion;

/**
 *
 * @author Ameni
 */
public class Projet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyConnexion c = MyConnexion.getInstance();
        User u1=new User (2,"Belhadj","Ameni","12345678","ameni.belhadj@esprit.tn","patient");
        UserCRUD uc = new UserCRUD();
        /*uc.ajouterUser(u1);*/
        /*uc.supprimerUser(u1);*/
        uc.modifierUser(u1);
    }
    
}
