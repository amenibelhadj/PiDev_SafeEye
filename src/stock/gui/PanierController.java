/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import stock.entities.Produit;
import stock.services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PanierController implements Initializable {
    
    
    @FXML
    private Button add;
    @FXML
    private Button ButtonSomme;
    
    
    

    @FXML
    private TableView<?> tabPanier2;
    @FXML
    private TableColumn<Produit, String> med2;
    @FXML
    private TableColumn<Produit, Integer> QD2 ;
    @FXML
    private TableColumn<Produit, Integer> P2 ;
    
   
    
    
    
    @FXML
    private Button BAFF;
    @FXML
    private TableView<Produit> tabPanier1;
    @FXML
    private TableColumn<Produit, String> med;
    @FXML
    private TableColumn<Produit, Integer> QD ;
    @FXML
    private TableColumn<Produit, Integer> P ;
    @FXML
    private Button ButtonImprimer;
    @FXML
    private Button affPanier;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        med.setCellValueFactory(new PropertyValueFactory<>("nom"));
        QD.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        P.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
    }  
    @FXML
    void afficheDispo(ActionEvent event) {
        ProduitCRUD ps = new ProduitCRUD();
        ArrayList<Produit> Produit;

            Produit = (ArrayList<Produit>) ps.afficherProduit2();
            ObservableList obs = FXCollections.observableArrayList(Produit);
            tabPanier1.setItems(obs);
            
            med.setCellValueFactory(new PropertyValueFactory<>("nom"));
            QD.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            P.setCellValueFactory(new PropertyValueFactory<>("prix"));

     }
    
    /*
    @FXML
    private void affichePanier(ActionEvent event) {
        tabPanier2.getItems().removeAll(tabPanier2.getSelectionModel().getSelectedItems());
        Produit P  = (Produit) tabPanier2.getSelectionModel().getSelectedItem();
       
        ProduitCRUD cr = new ProduitCRUD();
        ArrayList<Produit> Produit;
        Produit = (ArrayList<Produit>) cr.afficherProduit2();
      //  cr.afficherProduit2();
    }*/

    /*
    @FXML
    private void Somme(ActionEvent event) {
    } */
    
    
}
