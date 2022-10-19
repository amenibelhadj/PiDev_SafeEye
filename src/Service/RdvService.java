
package Service;

import Entities.RDV;
import Entities.dispo;
import IServices.IRdvService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.V;
import source.DataSource;
/**
 *
 * @author user
 * import javax.activation.DataSource;
 */


/**
 *
 * @author user
 */
public class RdvService implements IRdvService <RDV> {

    Connection cnx = DataSource.getinstance().getCnx();
    @Override
    public void ajouterRdv(RDV v)
    {

         try {
             String req = "INSERT INTO `rdv`(`prv`, `nom_med` ,`date`,`heure` ) "
                    + "VALUES ('"+v.getPrv()+"', '"+v.getNom_med()+"', '"+v.getDate()+"', '"+v.getHeure()+"')";
            Statement pst;
            pst =cnx.createStatement();
      
            pst.executeUpdate(req);
           
            System.out.println("Rendez vous ajouté");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    public void supprimerRdv(RDV r) {
               
        try {
            String requete = "DELETE FROM rdv WHERE id_rdv='" ;

            PreparedStatement pst = DataSource.getinstance().getCnx()
                    .prepareStatement(requete);
            
            pst.setInt(1,r.getId_rdv());
            
            pst.executeUpdate(requete);
            System.out.println("RDV was deleted successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
      public void supp(int id) {
       String req = "DELETE FROM `rdv` WHERE `id_rdv`='"+id+"'" ;
        Statement st;
        try {
            st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("rdv was deleted successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(RDV v) {
        
        
        try {

           String req="UPDATE rdv SET prv=?,nom_med=?,date=?,heure=? WHERE id_rdv=? ";
            PreparedStatement pst = DataSource.getinstance().getCnx()
                    .prepareStatement(req);
            
            pst.setString(1,v.getPrv());
            pst.setString(2, v.getNom_med());
            pst.setString(3, v.getDate());
            pst.setString(4, v.getHeure()); 
           
            pst.executeUpdate(req);
            System.out.println("RDV was updated successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<RDV> afficherRdvs() {
        List <RDV> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM rdv v  ";
            
            PreparedStatement pst;
            pst = DataSource.getinstance().getCnx()
                    .prepareStatement(req);
            
            ResultSet rs = pst.executeQuery(req);
            while(rs.next()){
                
                RDV v = new RDV();
            v.setId_rdv(rs.getInt("id_rdv")); 
           // v.setId_user(rs.getInt("id_user"));    
            v.setPrv(rs.getString("prv"));
            v.setNom_med(rs.getString("nom_med"));
            v.setDate(rs.getString("date"));
            v.setHeure(rs.getString("heure"));
          
                    
             list.add(v);
            }
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
       
    }

   
}
             
  