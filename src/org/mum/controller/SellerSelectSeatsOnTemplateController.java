/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.mum.context.ApplicationContext;
import org.mum.model.Seat;
import org.mum.service.SeatService;
import org.mum.utilities.AlertMaker;
import org.mum.utilities.Constant;
import org.mum.utilities.Utilities;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class SellerSelectSeatsOnTemplateController implements Initializable {

    @FXML
    private Button btnMainpage;
    @FXML
    private Button btnSchedule;
    @FXML
    private Button btnSeats;
    @FXML
    private Hyperlink linkChangePin;
    @FXML
    private Hyperlink linkLogout;
    @FXML
    private VBox vboxSeat;
    @FXML
    private Label labTtile;
    @FXML
    private Label labDate;
    @FXML
    private Label labTime;
    @FXML
    private Label labDuration;
    @FXML
    private Label labQuanlity;
    @FXML
    private Label labAmount;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSubmit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createSeatsLayoutAndOccupied();
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
        Utilities.replaceSceneContent("/org/mum/view/seller/Dashboard.fxml");
    }

    @FXML
    private void handleScheduleAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/seller/schedule/List.fxml");
    }

    @FXML
    private void handleReleaseAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/seller/seat/List.fxml");
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/seller/schedule/List.fxml");
    }

    
    private void createSeatsLayoutAndOccupied(){
        String[] preData = (String[])ApplicationContext.stage.getUserData();
        String movieId = preData[0];
        String date = preData[1];
        String time = preData[2];
        String duration = preData[3];
        String scheduleId = preData[4];
        List<Seat> seats = SeatService.getSeatListByScheduleId(scheduleId);
        this.labDate.setText(date);
        this.labTime.setText(time);
        this.labDuration.setText(duration + " Minutes");
        String preLoopRowNum = "";
        HBox temp = new HBox();
        temp.setMinHeight(50);
        temp.setAlignment(Pos.CENTER);
        Hyperlink linkScreen = new Hyperlink("THE SCREEN");
        linkScreen.setUnderline(true);
        temp.getChildren().add(linkScreen);
        this.vboxSeat.getChildren().add(temp);
        for(Seat s : seats){
            String sealLabel = s.getRowNum() + s.getColumnNum();
            Button btnSeat = new Button();
            btnSeat.setText(sealLabel + ": $" + s.getPrice());
            btnSeat.setUserData(s);
            btnSeat.setStyle(styleByStatus(s.getStatus()));
            if(!preLoopRowNum.equals(s.getRowNum())){
                temp = new HBox();
                temp.setAlignment(Pos.CENTER);
                temp.setMinHeight(30);
                temp.setMinWidth(50);
                this.vboxSeat.getChildren().add(temp);
            }
            if(s.getStatus().equals(Constant.SEATSTATUS_FREE) || 
                    s.getStatus().equals(Constant.SEATSTATUS_LOCKED_BYME)){
                btnSeat.setOnMouseClicked(new EventHandler(){
                    @Override
                    public void handle(Event event) {
                        Button curBtn = ((Button) event.getSource());
                        Seat curSeat = (Seat) curBtn.getUserData();
                        if(curSeat.getStatus().equals(Constant.SEATSTATUS_FREE)){
                            curBtn.setStyle("-fx-background-color: #ee7600");
                            labAmount.setText(String.valueOf(Double.valueOf(labAmount.getText()) + 
                                    Double.valueOf(curSeat.getPrice())));
                            labQuanlity.setText(String.valueOf(Integer.valueOf(labQuanlity.getText()) + 1));
                            curSeat.setStatus(Constant.SEATSTATUS_LOCKED_BYME);
                        }else{
                            curBtn.setStyle("-fx-background-color: green");
                            labAmount.setText(String.valueOf(Double.valueOf(labAmount.getText()) -
                                    Double.valueOf(curSeat.getPrice())));
                            labQuanlity.setText(String.valueOf(Integer.valueOf(labQuanlity.getText()) - 1));
                            curSeat.setStatus(Constant.SEATSTATUS_FREE);
                        }
                        curBtn.setUserData(curSeat);
                    }
                    
                });
                
            }
            temp.getChildren().add(btnSeat);
            preLoopRowNum = s.getRowNum();
        }
    }
    
    private String styleByStatus(String status){
        if(status.equals(Constant.SEATSTATUS_FREE))
            return "-fx-background-color: green";
        else if(status.equals(Constant.SEATSTATUS_OCCUPIED))
            return "-fx-background-color: gray";
        else if(status.equals(Constant.SEATSTATUS_LOCKED_BYOTHERONE))
            return "-fx-background-color: red";
        else
            return "-fx-background-color: #ee7600";
    }

    @FXML
    private void handleSubmitAction(ActionEvent event) {
        if(Double.valueOf(this.labAmount.getText()) == 0 || Integer.valueOf(this.labQuanlity.getText()) == 0){
            AlertMaker.showMessage("Please select a seat");
            return;
        }
        if(Double.valueOf(this.labAmount.getText()) < 0 || Integer.valueOf(this.labQuanlity.getText()) < 0){
            AlertMaker.showMessage("Please make sure unlock your locked data before submit");
            return;
        }
        List<Seat> seatsSelected = new ArrayList<>();
        AlertMaker.showMessage(SeatService.submitSeats(seatsSelected));
        Utilities.replaceSceneContent("/org/mum/view/seller/schedule/List.fxml");
        
    }

}
