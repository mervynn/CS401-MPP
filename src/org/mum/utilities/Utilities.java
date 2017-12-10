/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.utilities;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mum.Main;
import org.mum.context.ApplicationContext;

/**
 *
 * @author Mingwei
 */
public class Utilities {
    public static void replaceSceneContent(String fxml) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(fxml));
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        ApplicationContext.stage.setScene(scene);
    }
    
    public static void replaceSceneContentWithNewStage(String fxml) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(fxml));
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        Stage s = ApplicationContext.stage;
        ApplicationContext.stage = new Stage(StageStyle.DECORATED);
        ApplicationContext.stage.setScene(scene);
        ApplicationContext.stage.show();
        s.close();
    }
    
    public static void openWindow(String fxml){
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(fxml));
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void openWindow(Class c, String fxml){
        Parent root = null;
        try {
            root = FXMLLoader.load(c.getResource(fxml));
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
