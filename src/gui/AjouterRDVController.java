/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.RDV;
import Service.RdvService;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import source.DataSource;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterRDVController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private RadioButton rboui;
    @FXML
    private ToggleGroup r;
    @FXML
    private RadioButton rbnon;
    @FXML
    private ComboBox<String> combobox;
    
  
      ObservableList<String> list = FXCollections.observableArrayList("Koussay","Mohamed","Ameni","Maryem","Khairy");
      ObservableList<String> liste = FXCollections.observableArrayList("09:00","10:00","11:00","13:00","14:00","15:00");
    @FXML
    private DatePicker dateid;
    @FXML
    private TableView<RDV> tab;
    ObservableList<RDV>rdvList;
    @FXML
    private TableColumn<RDV, Integer> clid;
    @FXML
    private TableColumn<RDV, String> cliprv;
    @FXML
    private TableColumn<RDV, String> clnom;
    @FXML
    private TableColumn<RDV, String> cldate;
    @FXML
    private Button afficher;
    @FXML
    private TableColumn<RDV, String> clheure;
    @FXML
    private ComboBox<String> comboheure;
    @FXML
    private Button supprimer;
    
    Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea idtxt;
    @FXML
    private Button modifier;
    
    //ObservableList<RDV> liste = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        combobox.setItems(list);
        comboheure.setItems(liste);
       
    }    

    @FXML
    
    
    private void ajouterR(ActionEvent event) {
        String prv="" , nom="", datee="", heure="";
       
       
       if (rboui.isSelected()) {
            prv += rboui.getText();
        }
        if (rbnon.isSelected()) {
            prv += rbnon.getText();
        }
        
        nom+= combobox.getValue();
        datee+= dateid.getValue();  
        heure += comboheure.getValue();

        RDV r1;
        r1 = new RDV(prv,nom,datee,heure);
        RdvService r= new RdvService();
        r.ajouterRdv(r1);   
        
        JOptionPane.showMessageDialog(null, "Rdv ajouté" );
       
        combobox.setValue("select name");
        comboheure.setValue("");
        dateid.setValue(null);
        afficher();
        
           
}

    @FXML
    private void afficher() {
          
         RdvService sp= new RdvService();
        List RDV = (List) sp.afficherRdvs();
        
        
        ObservableList et = FXCollections.observableArrayList(RDV);
        
        clid.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("id_rdv"));
        cliprv.setCellValueFactory(new PropertyValueFactory<RDV, String>("prv"));
        clnom.setCellValueFactory(new PropertyValueFactory<RDV, String>("nom_med"));
        cldate.setCellValueFactory(new PropertyValueFactory<RDV, String>("date"));
        clheure.setCellValueFactory(new PropertyValueFactory<RDV, String>("heure"));
        
        tab.setItems(et);
    }

  

    @FXML
    private void supprimerRDV(MouseEvent event) throws SQLException {
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
            
         String prv="" , nom="", datee="", heure="";
                 int i;
        
         i=Integer.valueOf(idtxt.getText());
        
         
        RDV r1;
        r1 = new RDV(i,prv,nom,datee,heure);
        RdvService r= new RdvService();
        r.supp(i);
            JOptionPane.showMessageDialog(null, "Rdv supprimé" );
            afficher();
           
             }

    }

    @FXML
    private void modifierRDV(MouseEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        try{
             String value1=idtxt.getText();
             String value2="";
             if (rboui.isSelected()) {value2+= rboui.getText();}
             if (rbnon.isSelected()) { value2+= rbnon.getText();}
             String value3 = combobox.getValue();
             LocalDate value4 = dateid.getValue();
             String value5 = comboheure.getValue();
    
             mc=DataSource.getinstance().getCnx();
             String sql = "update rdv set id_rdv = '"+value1+"', prv = '"+value2+"', nom_med = '"+value3+"', date = '"+value4+"', heure = '"+value5+"'  where id_rdv ='"+value1+"' ";
             ste=mc.prepareStatement(sql);
             ste.execute(); 
             
             
             JOptionPane.showMessageDialog(null, "rdv modifié");
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
    private void getselected(MouseEvent event) {
         RDV clicked = tab.getSelectionModel().getSelectedItem();
        idtxt.setText(String.valueOf(clicked.getId_rdv()));
    }
    
    
    
    
    
    
    
}
