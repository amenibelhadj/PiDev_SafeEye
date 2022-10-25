package fiche.safeeye.gui;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fiche.safeeye.entities.Fiche;
import fiche.safeeye.service.FicheService;
import fiche.safeeye.utils.MyConnexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javax.management.Query.value;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 21654
 */
public class IFicheController implements Initializable {
    @FXML
    private TableView<Fiche> table;
    @FXML
    private TableColumn<Fiche ,String> nomtab;
    @FXML
    private TableColumn<Fiche ,String> prenomtab;
    @FXML
    private TableColumn<Fiche ,String> medtab;
    @FXML
    private TableColumn<Fiche ,String> maltab;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_afficher;
    @FXML
    private Button btn_imprimer;
    @FXML
    private TextField chercher;
    ObservableList<Fiche>fichelist;
    Connection mc;
   
    PreparedStatement ste;
    PreparedStatement ps ;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_med;
    @FXML
    private TextField txt_mal;
    @FXML
    private Button btn_valider;
    private boolean modifier=false , ajouter=false ;
    @FXML
    private TextField idtxt;
    @FXML
    private TableColumn<Fiche, Integer> idtab;
    @FXML
    private Button ref;
   ObservableList<Fiche>datalist = FXCollections.observableArrayList();
    @FXML
    private AnchorPane fiche_pane;
    
    /**
     * Initializes the controller class.
  
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
       MyConnexion connectNow = MyConnexion.getInstance();
       Connection connectDB = connectNow.getConnection();
       String ficheQuery = "SELECT ID_fiche, Nom_patient, Prenom_patient, Medecin, Maladie FROM fiche" ;
         try { 
         Statement statement = connectDB.createStatement();
         ResultSet queryOutput = statement.executeQuery(ficheQuery);
         while (queryOutput.next()){
         Integer queryID = queryOutput.getInt("ID_fiche");
         String queryNom = queryOutput.getNString("Nom_patient");
         String queryPrenom = queryOutput.getNString("Prenom_patient");
         String queryMedecin = queryOutput.getNString("Medecin");
         String queryMaladie = queryOutput.getNString("Maladie");
         datalist.add(new Fiche(queryID,queryNom,queryPrenom,queryMedecin,queryMaladie)); 
         }
        idtab.setCellValueFactory(new PropertyValueFactory<>("ID_fiche"));
        nomtab.setCellValueFactory(new PropertyValueFactory<>("Nom_patient"));       
        prenomtab.setCellValueFactory(new PropertyValueFactory<>("Prenom_patient"));        
        medtab.setCellValueFactory(new PropertyValueFactory<>("Medecin"));        
        maltab.setCellValueFactory(new PropertyValueFactory<>("Maladie"));
        
        table.setItems(datalist);
        FilteredList<Fiche> filteredData = new FilteredList<>(datalist , b -> true) ;
        chercher.textProperty().addListener((observable , oldValue , newValue) -> {
        filteredData.setPredicate(Fiche -> {
              if (newValue.isEmpty() || newValue == null){
                  return true;   
              }
        String searchKeyword = newValue.toLowerCase();
              if(Fiche.getNom_patient().toLowerCase().indexOf(searchKeyword)  > -1 ){
                  return true;
              } else if (Fiche.getPrenom_patient().toLowerCase().indexOf(searchKeyword)  > -1) {
                  return true;
              }
                else if (Fiche.getMedecin().toLowerCase().indexOf(searchKeyword)  > -1) {
                  return true;
              }
                else if (Fiche.getMaladie().toLowerCase().indexOf(searchKeyword)  > -1) 
                { return true; }
                else 
                    return false ;      
        });
        });
        SortedList<Fiche> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        
        } catch (SQLException e) {
            Logger.getLogger(IFicheController.class.getName()).log(Level.SEVERE, null ,e);
            e.printStackTrace();
        }
        
        
        
        nomtab.setCellValueFactory(new PropertyValueFactory<>("Nom_patient"));       
        prenomtab.setCellValueFactory(new PropertyValueFactory<>("Prenom_patient"));        
        medtab.setCellValueFactory(new PropertyValueFactory<>("Medecin"));        
        maltab.setCellValueFactory(new PropertyValueFactory<>("Maladie"));
    }    

    @FXML
    private void ajouterFiche(MouseEvent event) {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouterFiche.fxml"));
    Parent root = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initStyle(StageStyle.UNDECORATED);
    stage.setTitle("Ajouter une Fiche");
    stage.setScene(new Scene(root));  
    stage.show();
           }
        
        catch (IOException ex) {
            Logger.getLogger(IFicheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierFiche(MouseEvent event) {
        ajouter = false ;
        
       Fiche selected = table.getSelectionModel().getSelectedItem();
       txt_nom.setText(selected.getNom_patient());
       txt_prenom.setText(selected.getPrenom_patient());
       txt_med.setText(selected.getMedecin());
       txt_mal.setText(selected.getMaladie());
    }

    @FXML
    private void supprimerFiche(MouseEvent event) {
      
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setContentText("Fiche Suprimee avec succes!");
                alert.showAndWait(); 
                
              int i ;  

        String nom , prenom , med,mal;
        nom=txt_nom.getText();
        prenom=txt_prenom.getText();
        med=txt_med.getText();
        mal=txt_mal.getText();
        i=Integer.valueOf(idtxt.getText());
        
        Fiche f7 = new Fiche(i,nom , prenom ,med ,mal);
        FicheService fss = new FicheService ();
        fss.suppF(i); 
    }
   
    @FXML
    private void afficherFiche(MouseEvent event) {
      FicheService sp= new FicheService();
        List Fiche = (List) sp.afficherFiche();
        ObservableList et = FXCollections.observableArrayList(Fiche);
        idtab.setCellValueFactory(new PropertyValueFactory<Fiche, Integer>("ID_fiche"));
          nomtab.setCellValueFactory(new PropertyValueFactory<Fiche, String>("Nom_patient"));
        prenomtab.setCellValueFactory(new PropertyValueFactory<Fiche, String>("Prenom_patient"));
        medtab.setCellValueFactory(new PropertyValueFactory<Fiche, String>("Medecin"));
         maltab.setCellValueFactory(new PropertyValueFactory<Fiche, String>("Maladie"));
        table.setItems(et);
    }

    @FXML
    private void imprimerFiche(MouseEvent event)  throws SQLException, FileNotFoundException, IOException, DocumentException  { 
     ste = null ;
        mc = null ;
   
        String sql = "SELECT * FROM `fiche`";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();
    
    Document doc = new Document ();
    
    PdfWriter.getInstance(doc, new FileOutputStream("./fiche.pdf"));
    doc.open();
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ************************************* LISTE DES FICHES ************************************* "));
    doc.add(new Paragraph("   "));
    PdfPTable tablee = new PdfPTable(4);
    tablee.setWidthPercentage(50);
    PdfPCell cell;
    cell = new PdfPCell(new Phrase("Nom_patient", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell); 
    cell = new PdfPCell(new Phrase("Prenom_patient", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell); 
    cell = new PdfPCell(new Phrase("Medecin", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell);   
    cell = new PdfPCell(new Phrase("Maladie", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    tablee.addCell(cell);
    while (rs.next()) {
        Fiche e = new Fiche();
      
        e.setNom_patient(rs.getString("Nom_patient"));
        e.setPrenom_patient(rs.getString("Prenom_patient"));
        e.setMedecin(rs.getString("Medecin"));
        e.setMaladie(rs.getString("Maladie"));
       
      
        cell = new PdfPCell(new Phrase(e.getNom_patient(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getPrenom_patient(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getMedecin(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase(e.getMaladie(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
    }

    doc.add(tablee);
    doc.close();
    Desktop.getDesktop().open(new File("./fiche.pdf"));

        
    }
    
    @FXML
    private void validerF(MouseEvent event) {
        String  id,nom1,prenom1,med1,mal1;
        id=idtxt.getText();
         nom1=txt_nom.getText();
        prenom1=txt_prenom.getText();
        med1=txt_med.getText();
        mal1=txt_mal.getText();
         Fiche f66 = new Fiche(Integer.parseInt(id),nom1 , prenom1 ,med1 ,mal1);
          FicheService fsss = new FicheService ();
        fsss.modifierFiche(f66);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
             alert.setContentText("Fiche Modifiee avec succes!");
                alert.showAndWait();
    }

    @FXML
    private void selectF(MouseEvent event) {
        Fiche clicked = table.getSelectionModel().getSelectedItem();
        idtxt.setText(String.valueOf(clicked.getId_fiche()));
    }

    @FXML
    private void refF(MouseEvent event) {
         FicheService sp= new FicheService();
        List Fiche = (List) sp.afficherFiche();
        ObservableList et = FXCollections.observableArrayList(Fiche);
        idtab.setCellValueFactory(new PropertyValueFactory<Fiche, Integer>("ID_fiche"));
          nomtab.setCellValueFactory(new PropertyValueFactory<Fiche, String>("Nom_patient"));
        prenomtab.setCellValueFactory(new PropertyValueFactory<Fiche, String>("Prenom_patient"));
        medtab.setCellValueFactory(new PropertyValueFactory<Fiche, String>("Medecin"));
         maltab.setCellValueFactory(new PropertyValueFactory<Fiche, String>("Maladie"));
        table.setItems(et);
    }

    @FXML
    private void imprimerF(ActionEvent event) /*  throws SQLException, FileNotFoundException, IOException, DocumentException */ { 
       /*
        ps = null ;
        cc = null ;
        String sql = "SELECT * FROM `fiche`";
    ps=cc.prepareStatement(sql);
    ResultSet rs=ps.executeQuery();
    
    Document doc = new Document ();
    
    PdfWriter.getInstance(doc, new FileOutputStream("./fiche.pdf"));
    doc.open();
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ************************************* LISTE DES FICHES ************************************* "));
    doc.add(new Paragraph("   "));
    PdfPTable tablee = new PdfPTable(4);
    tablee.setWidthPercentage(50);
    PdfPCell cell;
    cell = new PdfPCell(new Phrase("Nom_patient", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell); 
    cell = new PdfPCell(new Phrase("Prenom_patient", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell); 
    cell = new PdfPCell(new Phrase("Medecin", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell);   
    cell = new PdfPCell(new Phrase("Maladie", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    tablee.addCell(cell);
    while (rs.next()) {
        Fiche e = new Fiche();
      
        e.setNom_patient(rs.getString("Nom_patient"));
        e.setPrenom_patient(rs.getString("Prenom_patient"));
        e.setMedecin(rs.getString("Medecin"));
        e.setMaladie(rs.getString("Maladie"));
       
      
        cell = new PdfPCell(new Phrase(e.getNom_patient(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getPrenom_patient(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getMedecin(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase(e.getMaladie(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
    }

    doc.add(tablee);
    doc.close();
    Desktop.getDesktop().open(new File("./fiche.pdf"));
*/
          
    }
   
    private void imprimerFi() throws SQLException, FileNotFoundException, IOException, DocumentException { 
        ste = null ;
        mc = null ;
        String sql = "SELECT * FROM `fiche`";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ps.executeQuery();
    
    Document doc = new Document ();
    
    PdfWriter.getInstance(doc, new FileOutputStream("./fiche.pdf"));
    doc.open();
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ************************************* LISTE DES FICHES ************************************* "));
    doc.add(new Paragraph("   "));
    PdfPTable tablee = new PdfPTable(4);
    tablee.setWidthPercentage(50);
    PdfPCell cell;
    cell = new PdfPCell(new Phrase("Nom_patient", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell); 
    cell = new PdfPCell(new Phrase("Prenom_patient", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell); 
    cell = new PdfPCell(new Phrase("Medecin", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
    tablee.addCell(cell);   
    cell = new PdfPCell(new Phrase("Maladie", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    tablee.addCell(cell);
    while (rs.next()) {
        Fiche e = new Fiche();
      
        e.setNom_patient(rs.getString("Nom_patient"));
        e.setPrenom_patient(rs.getString("Prenom_patient"));
        e.setMedecin(rs.getString("Medecin"));
        e.setMaladie(rs.getString("Maladie"));
       
      
        cell = new PdfPCell(new Phrase(e.getNom_patient(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getPrenom_patient(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getMedecin(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase(e.getMaladie(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablee.addCell(cell);
    }

    doc.add(tablee);
    doc.close();
    Desktop.getDesktop().open(new File("./fiche.pdf"));

          
    }
}
