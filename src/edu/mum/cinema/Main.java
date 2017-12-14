/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema;

import javafx.application.Application;
import javafx.stage.Stage;
import edu.mum.cinema.context.ApplicationContext;
import edu.mum.cinema.utilities.Utilities;

/**
 *
 * @author Mingwei
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        ApplicationContext.stage = stage;
        Utilities.replaceSceneContent("/edu/mum/cinema/view/Login.fxml");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
