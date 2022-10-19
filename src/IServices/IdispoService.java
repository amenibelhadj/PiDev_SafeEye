/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;
import Entities.RDV;
import Entities.dispo;
import java.util.List;
/**
 *
 * @author user
 */
public interface IdispoService {
    public void ajouterD(dispo v);
    public void supprimerD(dispo v);
    public void modifierD(dispo v);
    public List<dispo> afficherD();
}
