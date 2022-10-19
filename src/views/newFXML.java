/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Koussay
 */
public class newFXML extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       
    Parent parent = FXMLLoader.load(getClass().getResource("recInterface.fxml"));
         Scene scene = new Scene(parent);
          primaryStage.setScene(scene);
          primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
