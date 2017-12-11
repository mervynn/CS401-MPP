/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mum.context.ApplicationContext;
import org.mum.model.User;
import org.mum.service.UserService;
import org.mum.utilities.AlertMaker;
import org.mum.utilities.Constant;
import org.mum.utilities.Utilities;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class AdminUserListController implements Initializable {

    private ObservableList<User> value = FXCollections.observableArrayList();
    
    @FXML
    private Button btnMainpage;
    @FXML
    private Button btnMovie;
    @FXML
    private Button btnSchedule;
    @FXML
    private Button btnSeats;
    @FXML
    private Button btnUser;
    @FXML
    private Hyperlink linkChangePin;
    @FXML
    private Hyperlink linkLogout;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnAddWindow;
    @FXML
    private TableView<User> tableVUser;
    @FXML
    private MenuItem btnRefresh;
    @FXML
    private MenuItem btnEdit;
    @FXML
    private MenuItem btnDelete;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colUsername;
    @FXML
    private TableColumn<?, ?> colRoleType;
    @FXML
    private TableColumn<?, ?> colFirstName;
    @FXML
    private TableColumn<?, ?> colLastName;
    @FXML
    private TableColumn<?, ?> colEmail;

 /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadDate();
    }
    
    private void initCol(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRoleType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
    }
    
    public void loadDate(){
        value.clear();
        List<User> users = UserService.getUserList();
        users.forEach(m->{value.add(m);});
        this.tableVUser.setItems(value);
    } 

    @FXML
    private void handleChangePINWindowAction(ActionEvent event) {
        Utilities.openWindow("/org/mum/view/ChangePassword.fxml");
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) {
        ApplicationContext.currentUser = null;
        Utilities.replaceSceneContentWithNewStage("/org/mum/view/Login.fxml");
    }

    @FXML
    private void handleMainpageAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/Dashboard.fxml");
    }

    @FXML
    private void handleScheduleAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/schedule/List.fxml");
    }
    
    @FXML
    private void handleMovieManageAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/movie/List.fxml");
    }

    @FXML
    private void handleReleaseAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/seat/List.fxml");
    }

    @FXML
    private void handleUserManageAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/user/List.fxml");
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        List<User> fuzzyRes = UserService.fuzzyQuery(this.txtSearch.getText());
        value.clear();
        value.addAll(fuzzyRes);
    }
    
    @FXML
    private void handleAddWindowAction(ActionEvent event) {
        ApplicationContext.stage.setUserData(Constant.PAGETYPE_ADD);
        ApplicationContext.stage.getScene().setUserData(this);
        Utilities.openWindow("/org/mum/view/admin/user/Modify.fxml");
    }

    @FXML
    private void handleRefreshAction(ActionEvent event) {
        loadDate();
    }

    @FXML
    private void handleEditAction(ActionEvent event) {
        User m = this.tableVUser.getSelectionModel().getSelectedItem();
        if(m == null){
            AlertMaker.showMessage("Please select a user for edit");
            return;
        }
        ApplicationContext.stage.setUserData(m);
        ApplicationContext.stage.getScene().setUserData(this);
        Utilities.openWindow("/org/mum/view/admin/user/Modify.fxml");
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        User m = this.tableVUser.getSelectionModel().getSelectedItem();
        if(m == null){
            AlertMaker.showMessage("Please select a user for deletion");
            return;
        }
        AlertMaker.showMessage(UserService.deleteUser(m));
        loadDate();
    }

}
