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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mum.context.ApplicationContext;
import org.mum.model.User;
import org.mum.service.UserService;
import org.mum.utilities.AlertMaker;
import org.mum.utilities.Constant;
import org.springframework.util.StringUtils;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class AdminUserModifyController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Label hidId;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtRoleType;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!Constant.PAGETYPE_ADD.equals(ApplicationContext.stage.getUserData())){
            User u = (User) ApplicationContext.stage.getUserData();
            this.hidId.setText(u.getId());
            this.txtUserName.setText(u.getUsername());
            this.txtPassword.setText(u.getPassword());
            this.txtRoleType.setText(u.getRoleType());
            this.txtEmail.setText(u.getEmail());
            this.txtFirstName.setText(u.getFirstName());
            this.txtLastName.setText(u.getLastName());
        }
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        if(!isValidForm()) return;
        User u = new User();
        u.setId(this.hidId.getText());
        u.setUsername(this.txtUserName.getText());
        u.setPassword(this.txtPassword.getText());
        u.setEmail(this.txtEmail.getText());
        u.setRoleType(this.txtRoleType.getText());
        u.setFirstName(this.txtFirstName.getText());
        u.setLastName(this.txtLastName.getText());
        if(Constant.PAGETYPE_ADD.equals(ApplicationContext.stage.getUserData())){
            AlertMaker.showMessage(UserService.addUser(u));
        }else{
            AlertMaker.showMessage(UserService.updateUser(u));
        }
        ((Stage) txtUserName.getScene().getWindow()).close();
        ((AdminUserListController) ApplicationContext.stage.getScene().getUserData()).loadDate();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        ((Stage)txtUserName.getScene().getWindow()).close();
    }

    private boolean isValidForm(){
        if(StringUtils.isEmpty(txtUserName.getText()) || StringUtils.isEmpty(txtPassword.getText())
                || StringUtils.isEmpty(txtRoleType.getText()) || StringUtils.isEmpty(txtFirstName.getText())
                || StringUtils.isEmpty(txtLastName.getText()) || StringUtils.isEmpty(txtEmail.getText())){
            AlertMaker.showMessage("please make sure all input intems aren't empty");
            return false;
        }
        return true;
    }
    
}
