
package fiche.safeeye.service;

import fiche.safeeye.entities.Fiche;
import java.util.List;

/**
 *
 * @author 21654
 * @param <Fiche>
 */
public interface IFicheService<Fiche> {
   
    public void ajouterFiche(Fiche f);
    public void supprimerFiche(Fiche f);
    public void modifierFiche(Fiche f);
    public List<Fiche> afficherFiche();
}
