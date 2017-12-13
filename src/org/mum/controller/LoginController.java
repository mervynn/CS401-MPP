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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.mum.context.ApplicationContext;
import org.mum.model.User;
import org.mum.service.UserService;
import org.mum.utilities.Utilities;

/**
 *
 * @author Mingwei
 */
public class LoginController extends Parent implements Initializable {
    
    @FXML
    private Button btnReset;
    @FXML
    private Button btnLogin;
    @FXML
    private Label labMsg;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUsername;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void handleResetAction(ActionEvent event) {
        this.txtUsername.setText("");
        this.txtPassword.setText("");
        this.labMsg.setText("");
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if("".equals(username) || "".equals(password)){
            this.labMsg.setText("username or password can not be empty");
            return;
        }
        User user = UserService.getUserByUserNameAndPassword(username, password);
        if(user != null){
            ApplicationContext.currentUser = user;
            if("0".equals(user.getRoleType()))
                Utilities.replaceSceneContentWithNewStage("/org/mum/view/admin/Dashboard.fxml");
            else
                Utilities.replaceSceneContentWithNewStage("/org/mum/view/seller/Dashboard.fxml");
        }else{
            this.labMsg.setText("username or password incorrect");
        }
    }

    @FXML
    private void handleEnterAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            handleLoginAction(null);
        }
    }
    
}
