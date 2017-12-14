/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.mum.cinema.context.ApplicationContext;
import edu.mum.cinema.model.User;
import edu.mum.cinema.service.UserService;
import edu.mum.cinema.utilities.AlertMaker;

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
    @FXML
    private PasswordField txtOldPassword;
    @FXML
    private PasswordField txtNewPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtUsername.setText(ApplicationContext.currentUser.getUsername());
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        if(!UserService.hash(this.txtOldPassword.getText()).equals(ApplicationContext.currentUser.getPassword())){
            AlertMaker.showMessage("Old password is incorrect");
            return;
        }
        if(UserService.hash(this.txtOldPassword.getText()).equals(this.txtNewPassword.getText())){
            AlertMaker.showMessage("New password can not be same with old password");
            return;
        }
        User u = new User();
        u.setId(ApplicationContext.currentUser.getId());
        u.setPassword(this.txtNewPassword.getText());
        AlertMaker.showMessage(UserService.changePassword(u));
        ApplicationContext.currentUser.setPassword(this.txtNewPassword.getText());
        ((Stage) txtUsername.getScene().getWindow()).close();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        ((Stage) txtUsername.getScene().getWindow()).close();
    }
    
}
