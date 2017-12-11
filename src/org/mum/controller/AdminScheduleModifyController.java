/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.mum.context.ApplicationContext;
import org.mum.model.Movie;
import org.mum.model.Schedule;
import org.mum.service.MovieService;
import org.mum.service.ScheduleService;
import org.mum.utilities.AlertMaker;
import org.mum.utilities.Constant;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class AdminScheduleModifyController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Label hidId;
    @FXML
    private ChoiceBox<String> chbTitle;
    @FXML
    private ChoiceBox<String> chbTemplate;
    @FXML
    private HBox hboxSectionPrice;
    @FXML
    private DatePicker dateTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Movie> movies = MovieService.getMovieList();
        List<String> tmp = new ArrayList<>();
        movies.forEach(m->{tmp.add(m.getTitle());});
        chbTitle.setItems(FXCollections.observableArrayList(tmp));
        if(!Constant.PAGETYPE_ADD.equals(ApplicationContext.stage.getUserData())){
            Schedule s = (Schedule) ApplicationContext.stage.getUserData();
            this.hidId.setText(s.getId());
            this.chbTitle.getSelectionModel().select(Integer.valueOf(s.getMovieId()));
            this.chbTitle.setDisable(true);
        }else{
            chbTitle.getSelectionModel().selectFirst();
        }
    }    


    @FXML
    private void handleSaveAction(ActionEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
        Schedule schedule = new Schedule();
        schedule.setId(this.hidId.getText());
        schedule.setDate(sdf.format(this.dateTime.getValue()));
        schedule.setTime(sdf.format(this.dateTime.getValue()));
        if(Constant.PAGETYPE_ADD.equals(ApplicationContext.stage.getUserData())){
            AlertMaker.showMessage(ScheduleService.addSchedule(schedule));
        }else{
            AlertMaker.showMessage(ScheduleService.updateSchedule(schedule));
        }
        ((Stage) dateTime.getScene().getWindow()).close();
        ((AdminMovieListController) ApplicationContext.stage.getScene().getUserData()).loadDate();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        ((Stage) chbTitle.getScene().getWindow()).close();
    }
    
}
