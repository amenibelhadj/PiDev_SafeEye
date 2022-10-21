/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import Entities.Reclamation;
import Entities.ReponseRec;
import ReclamationService.ReclamationService;
import ReclamationService.ReponseRecService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import source.DataSource;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class ReponseRecInterfaceController implements Initializable {

    @FXML
    private TextArea nomRep;
    @FXML
    private Button ajouterRep;
    @FXML
    private TableView<ReponseRec> tableRep;
    
    @FXML
    private TableColumn<ReponseRec,String> message;
    @FXML
    private TableColumn<ReponseRec, String> date;
    @FXML
    private Button supprimerRep;
    @FXML
    private Button modifierRep;
    @FXML
    private TextArea idtxt;
    @FXML
    private DatePicker dateRep;
    
    ObservableList<ReponseRec>recList;
    
     Connection mc;
    PreparedStatement ste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       }    

    @FXML
    private void addRec(MouseEvent event) throws SQLException {
         String message = nomRep.getText();
        LocalDate date = dateRep.getValue(); // bch te5ou text mawjoud f label w thotou f variable
       ;
        
     
         if (message.isEmpty() ){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{     
             ReponseRec r=new ReponseRec(1,message,date);
             ReponseRecService rc = new ReponseRecService();
             String sql = "select * from reponse where message='"+message+"'";
             ste=mc.prepareStatement(sql);
             ResultSet rs=ste.executeQuery();
             if(rs.next()==true){
              Alert alert = new Alert(Alert.AlertType.ERROR); 
             alert.setContentText("message existe deja!");// taamalek alerte louken existe
             alert.showAndWait();
              nomRep.setText(null);//ki tal9a esm w la9ab mawjoud ihotehom null
                        
             }else{
             rc.ajouterReponseRec(r);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
             //alert.setHeaderText("Succes");
             alert.setContentText("Reponse Ajoutée avec succes!");
                alert.showAndWait();             
         }}
         refresh();
    }
    


public void refresh(){
        
         recList.clear();
       
          
          mc=DataSource.getInstance().getCnx();

        recList = FXCollections.observableArrayList();
        
        String sql="select * from reponse";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                ReponseRec e = new ReponseRec();
                e.setMessage(rs.getString("message"));
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableRep.setItems(recList);   
    }


}

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void deleteRep(MouseEvent event) {
    }

    @FXML
    private void updateRep(MouseEvent event) {
    }

    private void afficherReponseRec() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
