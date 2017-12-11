/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.mum.context.ApplicationContext;
import org.mum.model.LayoutTemplate;
import org.mum.model.Movie;
import org.mum.model.Schedule;
import org.mum.model.SectionTemplate;
import org.mum.service.LayoutTemplateService;
import org.mum.service.MovieService;
import org.mum.service.ScheduleService;
import org.mum.service.SectionTemplateService;
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
    private ChoiceBox<Movie> chbTitle;
    @FXML
    private ChoiceBox<LayoutTemplate> chbTemplate;
    @FXML
    private DatePicker dateTime;
    @FXML
    private VBox vboxSectionPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initControllers();
        loadDate();
    }
   
    private void initControllers(){
        ChangeListener<Number> listener = new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(chbTitle.getSelectionModel().getSelectedIndex() != -1 && 
                    chbTemplate.getSelectionModel().getSelectedIndex() != -1){
                    //initSectionPriceByTitleChange();
                }
            }
        };
        ChangeListener<Number> listener1 = new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(chbTitle.getSelectionModel().getSelectedIndex() != -1 && 
                    chbTemplate.getSelectionModel().getSelectedIndex() != -1){
                     //String movieId = this.chbTitle.getValue().getId();
                    String templateId = String.valueOf(newValue);
                    List<SectionTemplate> sections = SectionTemplateService.getSectionsByTemplateId(templateId);
                    vboxSectionPrice.getChildren().clear();
                    sections.stream().map((st) -> {
                        HBox hb = new HBox();
                        
                        HBox l = new HBox();
                        l.setMinWidth(82);
                        l.setMaxWidth(82);
                        HBox r = new HBox();
                        hb.setMinHeight(31);
                        Label lb = new Label(st.getSectionLabel());
                        l.getChildren().add(lb);
                        TextField tf = new TextField();
                        r.getChildren().add(tf);
                        hb.getChildren().add(l);
                        hb.getChildren().add(r);
                        return hb;
                    }).forEachOrdered((hb) -> {
                        vboxSectionPrice.getChildren().add(hb);
                    });
                }
            }
        };
        this.chbTitle.getSelectionModel().selectedIndexProperty().addListener(listener);
        this.chbTemplate.getSelectionModel().selectedIndexProperty().addListener(listener1);
    }
    
    private void loadDate(){
        List<Movie> movies = MovieService.getMovieList();
        chbTitle.setItems(FXCollections.observableArrayList(movies));
        List<LayoutTemplate> templates = LayoutTemplateService.getLayoutTemplateList();
        chbTemplate.setItems(FXCollections.observableArrayList(templates));
        if(!Constant.PAGETYPE_ADD.equals(ApplicationContext.stage.getUserData())){
            Schedule s = (Schedule) ApplicationContext.stage.getUserData();
            this.hidId.setText(s.getId());
            this.chbTitle.getSelectionModel().select(new Movie(s.getMovieId(), ""));
            this.chbTitle.setDisable(true);
            this.chbTemplate.getSelectionModel().select(new LayoutTemplate(s.getTemplateId(), ""));
            this.chbTemplate.setDisable(true);
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
