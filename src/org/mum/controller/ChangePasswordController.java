/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mum.context.ApplicationContext;
import org.mum.utilities.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtUsername.setText(ApplicationContext.currentUser.getUsername());
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        AlertMaker.showMessage("TODO");
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        ((Stage) txtUsername.getScene().getWindow()).close();
    }
    
}
