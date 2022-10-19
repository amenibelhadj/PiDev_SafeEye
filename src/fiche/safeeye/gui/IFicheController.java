/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiche.safeeye.gui;

import fiche.safeeye.entities.Fiche;
import fiche.safeeye.service.FicheService;
import fiche.safeeye.utils.MyConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 21654
 */
public class IFicheController implements Initializable {

     @FXML
    private TextField nompatient;
    @FXML
    private TextField prenompatient;
    @FXML
    private TextField nommed;
    @FXML
    private Button btn;
    @FXML
    private TableView<Fiche> table;
    @FXML
    private TableColumn<Fiche, String> nomtab;
    @FXML
    private TableColumn<Fiche, String> prenomtab;
    @FXML
    private TableColumn<Fiche, String> medtab;
    @FXML
    private Button btnaff;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
     ObservableList<Fiche>fichelist;
    
     Connection mc;
    PreparedStatement ste;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   

    @FXML
    private void ajouterF(ActionEvent event) {
         String nom , prenom , nomm;
        nom=nompatient.getText();
        prenom=prenompatient.getText();
        nomm=nommed.getText();
        
        Fiche f7 = new Fiche(nom , prenom ,nomm);
        FicheService fss = new FicheService ();
        fss.ajouterFiche(f7);
        
    }

    @FXML
    private void afficherF(MouseEvent event) {
             mc =MyConnexion.getInstance().getConnection();
        fichelist = FXCollections.observableArrayList();
       
        String sql="select * from fiche";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Fiche fx = new Fiche();
                fx.setId_fiche(rs.getInt("ID_fiche"));
                fx.setNom_p(rs.getString("Nom_p"));
                fx.setPrenom_p(rs.getString("Prenom_p"));
                fx.setNom_med(rs.getString("Nom_med"));
                
                
                fichelist.add(fx); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        nomtab.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom_p()));
        prenomtab.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom_p()));
        medtab.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom_med()));
        
       


        table.setItems(fichelist);
        
    }

    @FXML
    private void modifierF(MouseEvent event) {
    }

    @FXML
    private void supprimerF(MouseEvent event) {
    }

   
}
