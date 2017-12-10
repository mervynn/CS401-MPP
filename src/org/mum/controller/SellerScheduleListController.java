/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.mum.context.ApplicationContext;
import org.mum.model.Movie;
import org.mum.model.Schedule;
import org.mum.service.ScheduleService;
import org.mum.utilities.Utilities;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class SellerScheduleListController implements Initializable {

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
    private Pane vboxMovieSchedule;
    @FXML
    private HBox hboxTimeSchedule;
    @FXML
    private AnchorPane anchorPaneMovie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTimeHBox();
        String today = new SimpleDateFormat("MMM-dd-yyyy").format(new Date());
        hboxTimeSchedule.setUserData(today);
        createMovieWithScheduleVBox(today);
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
    
    private void createTimeHBox(){
        hboxTimeSchedule.getChildren().clear();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
        Calendar cal = Calendar.getInstance();
        sdf.format(new Date());
        for(int i = 0; i < 5;i++){
            Hyperlink hl = new Hyperlink();
            cal.add(Calendar.DATE, 1);
            hl.setText(sdf.format(cal.getTime()));
            hl.setUnderline(true);
            hl.setOnMouseClicked(new EventHandler(){
                @Override
                public void handle(Event event) {
                    String selectedDate = ((Hyperlink) event.getSource()).getText();
                    hboxTimeSchedule.setUserData(selectedDate);
                    createMovieWithScheduleVBox(selectedDate);
                }
            });
            hboxTimeSchedule.getChildren().add(hl);
        }
    }
    
    private void createMovieWithScheduleVBox(String date){
        vboxMovieSchedule.getChildren().clear();
        List<Movie> list = ScheduleService.getMovieWithSchedule(date);
        double anchorHeight = 0;
        for(Movie m : list){
            anchorHeight += 162;
            HBox schedule = new HBox();
            schedule.setPrefHeight(162);
            
            VBox imgV = new VBox();
            ImageView iv = new ImageView(m.getImageUrl());
            iv.setPreserveRatio(true);
            iv.setFitHeight(150);
            imgV.getChildren().add(iv);
            schedule.getChildren().add(imgV);
            
            VBox detailV = new VBox();
            
            HBox titleH = new HBox();
            Label titleLab = new Label();
            titleLab.setText(m.getTitle());
            titleLab.setFont(Font.font("Arial Black", FontWeight.BOLD, 20));
            titleH.setPrefHeight(15);
            titleH.getChildren().add(titleLab);
            detailV.getChildren().add(titleH);
            
            HBox durH = new HBox();
            durH.setPrefHeight(20);
            Label durLab = new Label();
            durLab.setText(m.getDuration() + " Minutes");
            durH.getChildren().add(durLab);
            detailV.getChildren().add(durH);
            
            HBox desH = new HBox();
            desH.setPrefHeight(70);
            Label desLab = new Label();
            desLab.setText(m.getDescription());
            desLab.setWrapText(true);
            desH.getChildren().add(desLab);
            detailV.getChildren().add(desH);
            
            HBox timeH = new HBox();
            timeH.setBorder(new Border(new BorderStroke(Color.GREY, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    
            HBox timeSel = new HBox();
            timeSel.setAlignment(Pos.CENTER_LEFT);
            Label timeLab = new Label();
            timeLab.setText("Select time");
            timeSel.getChildren().add(timeLab);
            timeH.getChildren().add(timeSel);
            
            HBox timeLinkH = new HBox();
            for(Schedule s : m.getSchedules()){
                Hyperlink timeLink = new Hyperlink();
                timeLink.setUserData(s.getId());
                timeLink.setText(s.getTime());
                timeLinkH.getChildren().add(timeLink);
                timeLink.setOnMouseClicked(new EventHandler(){
                    @Override
                    public void handle(Event event) {
                        Hyperlink curLink = (Hyperlink) event.getSource();
                        ApplicationContext.stage.setUserData(new String[]{
                            curLink.getParent().getUserData().toString(),
                            hboxTimeSchedule.getUserData().toString(),
                            curLink.getText(),
                            m.getDuration(),
                            curLink.getId()
                        });
                        Utilities.replaceSceneContent("/org/mum/view/seller/seat/Template.fxml");
                    }
                });
            }
            timeLinkH.setUserData(m.getId());
            timeH.getChildren().add(timeLinkH);
            detailV.getChildren().add(timeH);
            schedule.getChildren().add(detailV);
            vboxMovieSchedule.getChildren().add(schedule);
        }
        this.anchorPaneMovie.setPrefHeight(anchorHeight);
    }

}
