


package Main;
import fiche.safeeye.entities.Fiche;
import fiche.safeeye.service.FicheService;
import fiche.safeeye.utils.MyConnexion;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           MyConnexion c = MyConnexion.getInstance();
        Fiche f1 = new Fiche (10,"Dania","Ben Ali","Koussay","tata");
        FicheService fs = new FicheService();
        fs.ajouterFiche(f1);
       //fs.supprimerFiche(f1);
       //fs.modifierFiche(f1);
       //fs.afficherFiche();
    }
    
}