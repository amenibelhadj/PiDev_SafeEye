/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Reclamation;
import Iservice.IReclamationService;
import ReclamationService.ReclamationService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import source.DataSource;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class RecInterfaceController implements Initializable {

    @FXML
    private TextArea prenomRec;
    @FXML
    private TextArea nomRec;
    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String> premon;
    @FXML
    private TableColumn<Reclamation, String> email;
    @FXML
    private TableColumn<Reclamation, String> desc;
    @FXML
    private TextArea emailRec;
    @FXML
    private TextArea descRec;
    @FXML
    private Button ajouterRec;
    @FXML
    private Button supprimerRec;
    @FXML
    private Button modifierRec;
    @FXML
    private TableView<Reclamation> tableRec;
    
      ObservableList<Reclamation>recList;
    
     Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea idtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         afficherReclamation();
    }  
    void afficherReclamation(){
            mc=DataSource.getInstance().getCnx();
        recList = FXCollections.observableArrayList();
       
        
        String sql="select * from reclamation";
        try {
            ste=mc.prepareStatement(sql); //preparer requeete
            ResultSet rs=ste.executeQuery();//exec lel req mte3ek 
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));//tekhou mel base w tseti fel instance mb3ed el instance bsh thotha fi lista w tajoputiha or taffich or update
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setDescription(rs.getString("description"));
                recList.add(r); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        nom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        premon.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        desc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
       
        tableRec.setItems(recList);
        
    
        
    }
//tjiblek les champs eli clikcet alihom fel tab w thothom fel labels
    @FXML
    private void getSelected(MouseEvent event) {
          Reclamation clicked = tableRec.getSelectionModel().getSelectedItem();
         nomRec.setText(String.valueOf(clicked.getNom()));
        prenomRec.setText(String.valueOf(clicked.getPrenom()));
        emailRec.setText(String.valueOf(clicked.getEmail()));
        descRec.setText(String.valueOf(clicked.getDescription()));
        idtxt.setText(String.valueOf(clicked.getId()));
    }

    @FXML
    private void addRec(MouseEvent event) throws SQLException {
        
        String nom = nomRec.getText();
        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String email = emailRec.getText();
        String description = descRec.getText();
        
     
         if (nom.isEmpty() || prenom.isEmpty()|| email.isEmpty()|| description.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{     
             Reclamation r=new Reclamation(1,description,nom,prenom,email);
             ReclamationService rc = new ReclamationService();
             
             ResultSet rs=ste.executeQuery();
             
             if(nom==null || prenom ==null || email==null || description==null){
                Alert alert = new Alert(Alert.AlertType.ERROR); 
             alert.setContentText("Veuillez remplir tous les champs!");// 
             alert.showAndWait();
              nomRec.setText(null);//ki tal9a esm w la9ab mawjoud ihotehom null
             prenomRec.setText(null);   
                         
             }else{
                
                   
             rc.ajouterReclamation(r);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Reclamation Ajoutée avec succes!");
                alert.showAndWait();
         }}
         refresh();
    }

    @FXML
    private void deleteRec(MouseEvent event) throws SQLException {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
            
            String value1 = idtxt.getText();
            
             String nom = nomRec.getText();
        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String email = emailRec.getText();
        String description = descRec.getText();

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
            
             Reclamation r= new Reclamation( Integer.parseInt(value1),description,nom,prenom,email);
             ReclamationService rc = new ReclamationService();
             rc.supprimerReclamation(r);
            JOptionPane.showMessageDialog(null, "Reclamation supprimer" );
        
            refresh();
             }
        else{

          nomRec.setText(null);
          prenomRec.setText(null);
          emailRec.setText(null);
          descRec.setText(null);

        }
    }

    @FXML
    private void updateRec(MouseEvent event)  {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
       
            String value1 = idtxt.getText();
             String value2 = nomRec.getText();
             String value3 = prenomRec.getText();
             String value4 = emailRec.getText();
             String value5 = descRec.getText();
            
             Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        try{
        
             ReclamationService rc = new ReclamationService( );
             Reclamation r= new Reclamation(Integer.parseInt(value1),value5,value2,value3,value4);
             rc.modifierReclamation(r);
            JOptionPane.showMessageDialog(null, "reclamation modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        refresh();
               }
        else{

              nomRec.setText(null);
              prenomRec.setText(null);
              emailRec.setText(null);
              descRec.setText(null);
              
            ;

        }
        refresh();
    }
    
    public void refresh(){
        
         recList.clear();
       
          
          mc=DataSource.getInstance().getCnx();

        recList = FXCollections.observableArrayList();
        
        String sql="select * from reclamation";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Reclamation e = new Reclamation();
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setEmail(rs.getString("email"));
                e.setDescription(rs.getString("description"));
                recList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableRec.setItems(recList);   
    }
}


