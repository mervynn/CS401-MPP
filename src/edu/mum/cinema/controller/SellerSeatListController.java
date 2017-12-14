/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.controller;

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
import edu.mum.cinema.context.ApplicationContext;
import edu.mum.cinema.model.Seat;
import edu.mum.cinema.service.SeatService;
import edu.mum.cinema.utilities.AlertMaker;
import edu.mum.cinema.utilities.Constant;
import edu.mum.cinema.utilities.Utilities;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class SellerSeatListController implements Initializable {

    private ObservableList<Seat> value = FXCollections.observableArrayList();
    @FXML
    private Hyperlink linkChangePin;
    @FXML
    private Hyperlink linkLogout;
    @FXML
    private Button btnMainpage;
    @FXML
    private Button btnSchedule;
    @FXML
    private Button btnSeats;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private MenuItem btnRefresh;
    @FXML
    private MenuItem btnEdit;
    @FXML
    private MenuItem btnDelete;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colMovie;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> colTime;
    @FXML
    private TableColumn<?, ?> colRowNum;
    @FXML
    private TableColumn<?, ?> colColumnNum;
    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private TableView<Seat> tableVSeat;

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
        colMovie.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colColumnNum.setCellValueFactory(new PropertyValueFactory<>("columnNum"));
        colRowNum.setCellValueFactory(new PropertyValueFactory<>("rowNum"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    
    public void loadDate(){
        value.clear();
        List<Seat> users = SeatService.getSeatList();
        users.forEach(m->{value.add(m);});
        this.tableVSeat.setItems(value);
    } 

    @FXML
    private void handleChangePINWindowAction(ActionEvent event) {
        Utilities.openWindow("/edu/mum/cinema/view/ChangePassword.fxml");
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) {
        ApplicationContext.currentUser = null;
        Utilities.replaceSceneContentWithNewStage("/edu/mum/cinema/view/Login.fxml");
    }

    @FXML
    private void handleMainpageAction(ActionEvent event) {
        Utilities.replaceSceneContent("/edu/mum/cinema/view/seller/Dashboard.fxml");
    }

    @FXML
    private void handleScheduleAction(ActionEvent event) {
        Utilities.replaceSceneContent("/edu/mum/cinema/view/seller/schedule/List.fxml");
    }

    @FXML
    private void handleReleaseAction(ActionEvent event) {
        Utilities.replaceSceneContent("/edu/mum/cinema/view/seller/seat/List.fxml");
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        List<Seat> fuzzyRes = SeatService.fuzzyQuery(this.txtSearch.getText());
        value.clear();
        value.addAll(fuzzyRes);
    }

    @FXML
    private void handleRefreshAction(ActionEvent event) {
        loadDate();
    }

    @FXML
    private void handleEditAction(ActionEvent event) {
        Seat m = this.tableVSeat.getSelectionModel().getSelectedItem();
        if(m == null){
            AlertMaker.showMessage("Please select a seat for edit");
            return;
        }
        ApplicationContext.stage.setUserData(m);
        ApplicationContext.stage.getScene().setUserData(this);
        Utilities.openWindow("/edu/mum/cinema/view/seller/seat/Modify.fxml");
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        Seat m = this.tableVSeat.getSelectionModel().getSelectedItem();
        if(m == null){
            AlertMaker.showMessage("Please select a seat for deletion");
            return;
        }
        AlertMaker.showMessage(SeatService.deleteSeat(m));
        loadDate();
    }
    
}
