/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.gui;

import edu.projet.entities.User;
import edu.projet.service.UserCRUD;
import edu.projet.utils.MyConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class GestionUserController implements Initializable {

    @FXML
    private TextField gnom;
    @FXML
    private TextField gprenom;
    @FXML
    private TextField gcin;
    @FXML
    private TextField gemail;
    @FXML
    private ComboBox boxrole2;
    @FXML
    private TextField gid;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User,String> IdColumn;
    @FXML
    private TableColumn <User,String> NomColumn;
    @FXML
    private TableColumn <User,String> PrenomColumn;
    @FXML
    private TableColumn<User,String> CinColumn;
    @FXML
    private TableColumn<User,String> EmailColumn;
    @FXML
    private TableColumn<User,String> RoleColumn;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //combobox
        ObservableList<String> listrole = FXCollections.observableArrayList("","patient","medecin","pharmacien","infirmier","agent_reclamation");
        boxrole2.setValue("");
        boxrole2.setItems(listrole);
        //Table
        table_affiche();
        
    }  
    public void table_affiche(){
        Connection cnx = MyConnexion.getInstance().getConnection();
        ObservableList<User> users = FXCollections.observableArrayList();
    
        try {
            String req = "SELECT * FROM `user`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            {
                while(rs.next()){
                User u = new User();
                u.setId_user(rs.getInt("id_user"));
                u.setNom_user(rs.getString("nom_user"));
                u.setPrenom_user(rs.getString("prenom_user"));
                u.setCin_user(rs.getString("cin_user"));
                u.setEmail_user(rs.getString("email_user"));
                u.setRole_user(rs.getString("role_user"));
                users.add(u);}
            }   
            
            IdColumn.setCellValueFactory(new PropertyValueFactory<User,String>("id_user"));
            NomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("nom_user"));
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("prenom_user"));
            CinColumn.setCellValueFactory(new PropertyValueFactory<User,String>("cin_user"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email_user"));
            RoleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("role_user"));
            table.setItems(users);
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public Integer rechercheUser(User u){
                    Integer exist = 0;
                    Connection cnx = MyConnexion.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE nom_user=?,prenom_user=?,cin_user=?,email_user=?,role_user=? ");
                        pst.setString(1, u.getNom_user());
                        pst.setString(2, u.getPrenom_user());
                        pst.setString(3, u.getCin_user());
                        pst.setString(4, u.getEmail_user());
                        pst.setString(5, u.getRole_user());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return exist;
    }
    public Integer rechercheID(User u){
                    Integer exist = 0;
                    Connection cnx = MyConnexion.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE id_user=? ");
                        pst.setInt(1, u.getId_user());
                      
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return exist;
    }
    
    
    
    
    @FXML
    private void AjouterU(ActionEvent event) {
        String nom,prenom,email,role;
        String cin;
        
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        
        //Contôle de saisie
        if (nom==null || prenom ==null || email==null || role==null || cin==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }else if (email.matches("(.*)@(.*)")==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        }else if (cin.length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();

        }else if (cin.matches("[0-9]*")==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();
        }else{
            //Methode Ajouter    
            User user1 = new User (nom,prenom,cin,email,role);
            
            //controle d'existance
            if (rechercheUser(user1)!=null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("USER ALREADY EXISTS");
            alert.show(); 
            }
            
            else {
            UserCRUD uc = new UserCRUD();
            uc.ajouterUser(user1);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully created your account. Please check your e-mail box to get your ID ");
            alert.show();
            table_affiche();
            }
        } 
    }


    @FXML
    private void ModifierU(ActionEvent event) {
        int ID=0;
        String nom,prenom,email,role,id;
        String cin;
        
        id = gid.getText();
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        ID =Integer.parseInt(id);
        //Contôle de saisie
        if (nom==null || prenom ==null || email==null || role==null || cin==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }else if (email.matches("(.*)@(.*)")==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        }else if (cin.length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();

        }else if (cin.matches("[0-9]*")==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();
        }else{
        
            User user1 = new User (ID,nom,prenom,cin,email,role);
            if (rechercheUser(user1)==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText("Attention !!");
                alert.setContentText("USER DOES NOT EXISTS");
                alert.show(); 
            }
            
            else {
                UserCRUD uc = new UserCRUD();
                uc.modifierUser(user1);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Update Done !!");
                alert.setContentText("You have successfully updated an account");
                alert.show();
                table_affiche();
            }
    
        } 
    }
    

    @FXML
    private void SupprimerU(ActionEvent event) {
        int ID=0;
        String nom,prenom,email,role,id;
        String cin;
        
        
        id = gid.getText();
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        ID = Integer.parseInt(id);
        //Contôle de saisie
        if (nom==null || prenom ==null || email==null || role==null || cin==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }else if (email.matches("(.*)@(.*)")==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        }else if (cin.length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();

        }else if (cin.matches("[0-9]*")==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();
        }else{
        
            User user2 = new User (ID,nom,prenom,cin,email,role);
            if (rechercheUser(user2)==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText("Attention !!");
                alert.setContentText("USER DOES NOT EXISTS");
                alert.show(); 
            }
            else {
                UserCRUD uc = new UserCRUD();
                uc.supprimerUser(user2);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Removal Done !!");
                alert.setContentText("You have successfully deleted an account ");
                alert.show();
                table_affiche();
            }
        }
    }
    
    
}

