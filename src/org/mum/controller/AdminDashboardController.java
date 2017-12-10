/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.mum.context.ApplicationContext;
import org.mum.utilities.Utilities;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class AdminDashboardController implements Initializable {

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
    private Label labName;
    @FXML
    private Label labDateTime;
    @FXML
    private Hyperlink linkChangePin;
    @FXML
    private Hyperlink linkLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.labName.setText(ApplicationContext.currentUser.getFirstName());
        final Timeline timeline = new Timeline(
            new KeyFrame(
                Duration.millis(200),
                event -> {
                    labDateTime.setText(Calendar.getInstance().getTime().toString());
                }
            )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
    private void handleMovieManageAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/movie/List.fxml");
    }

    @FXML
    private void handleScheduleAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/schedule/List.fxml");
    }

    @FXML
    private void handleReleaseAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/seat/List.fxml");
    }

    @FXML
    private void handleUserManageAction(ActionEvent event) {
        Utilities.replaceSceneContent("/org/mum/view/admin/user/List.fxml");
    }

}
