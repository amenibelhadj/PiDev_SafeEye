/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.RDV;
import Entities.dispo;
import Service.RdvService;
import Service.dispoService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import source.DataSource;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DispoController implements Initializable {

    @FXML
    private TextArea idtxt;
    @FXML
    private DatePicker dateid;
    @FXML
    private ComboBox<String> comboheure;
    @FXML
    private Button valider;
    @FXML
    private Button afficher;
    @FXML
    private Button modifier;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TableView<dispo> tab;
    @FXML
    private TableColumn<dispo, Integer> clid;
    @FXML
    private TableColumn<dispo, String> clnom;
    @FXML
    private TableColumn<dispo,String > cldate;
    @FXML
    private TableColumn<dispo, String> clheure;
    
      ObservableList<String> list = FXCollections.observableArrayList("Koussay","Mohamed","Ameni","Maryem","Khairy");
      ObservableList<String> liste = FXCollections.observableArrayList("09:00","10:00","11:00","13:00","14:00","15:00");
      
      Connection mc;
    PreparedStatement ste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combobox.setItems(list);
        comboheure.setItems(liste);
        
      
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        String nom="", datee="", heure="";
        nom+= combobox.getValue();
        datee+= dateid.getValue();  
        heure += comboheure.getValue();

        dispo r1;
        r1 = new dispo(nom,datee,heure);
        dispoService r= new dispoService();
        r.ajouterD(r1);
        
                JOptionPane.showMessageDialog(null, "ajouté" );
       
        combobox.setValue("select name");
        comboheure.setValue("");
        dateid.setValue(null);
        afficher();
    }

    @FXML
    private void afficher() {
        
                 dispoService sp= new dispoService();
        List dispo = (List) sp.afficherD();
        
        
        ObservableList et = FXCollections.observableArrayList(dispo);
        
        clid.setCellValueFactory(new PropertyValueFactory<dispo, Integer>("id_dispo"));
        
        clnom.setCellValueFactory(new PropertyValueFactory<dispo, String>("nom_medd"));
        cldate.setCellValueFactory(new PropertyValueFactory<dispo, String>("date"));
        clheure.setCellValueFactory(new PropertyValueFactory<dispo, String>("heure"));
        
        tab.setItems(et);
    }

    @FXML
    private void getselected(MouseEvent event) {
        dispo clicked = tab.getSelectionModel().getSelectedItem(); 
    
        idtxt.setText(String.valueOf(clicked.getId_dispo()));
    }

    @FXML
    private void modifier(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        try{
             mc=DataSource.getinstance().getCnx();
             
             String value1 = idtxt.getText();                   
             String value3 = combobox.getValue();
             LocalDate value4 = dateid.getValue();
             String value5 = comboheure.getValue();
             
             String sql = "update disponibilite set id_dispo = '"+value1+"',nom_medd = '"+value3+"', date = '"+value4+"', heure = '"+value5+"'  where id_dispo ='"+value1+"' ";
             ste=mc.prepareStatement(sql);
             ste.execute(); 
             
             
            JOptionPane.showMessageDialog(null, " modifié");
                   
       
        combobox.setValue("select name");
        comboheure.setValue("");
        dateid.setValue(null);
        afficher();
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);
        } 
     
               }

    }

    @FXML
    private void supprimer(MouseEvent event) throws SQLException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
            
        String nom="", datee="", heure="";
        int i;
        
        i=Integer.valueOf(idtxt.getText());

        dispo r1;
        r1 = new dispo(i,nom,datee,heure);
        dispoService r= new dispoService();
        r.supp(i);
            
            JOptionPane.showMessageDialog(null, " supprimer" );
            afficher();
             }
    }
    
}
