/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.mum.context.ApplicationContext;
import org.mum.model.LayoutTemplate;
import org.mum.model.Movie;
import org.mum.model.Schedule;
import org.mum.model.SectionPrice;
import org.mum.model.SectionTemplate;
import org.mum.service.LayoutTemplateService;
import org.mum.service.MovieService;
import org.mum.service.ScheduleService;
import org.mum.service.SectionTemplateService;
import org.mum.utilities.AlertMaker;
import org.mum.utilities.Constant;
import org.mum.utilities.UtilitiesFactory;
import org.springframework.util.StringUtils;

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
    private VBox vboxSectionPrice;
    @FXML
    private VBox vform;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtTime;

    private List<SectionTemplate> sections;
    private Map<Integer, TextField> sectionPriceMap; //Maps <SectionTemplateID, TextField>

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
                     //String movieId = this.chbTitle.getValue().getId();
                     
                     chbTemplate.getSelectionModel().select(newValue.intValue());
                     System.out.println(chbTemplate.getValue());
                     
                    String templateId = String.valueOf(chbTemplate.getSelectionModel().getSelectedItem().getId());
                    sections = SectionTemplateService.getSectionsByTemplateId(templateId);
                    vboxSectionPrice.getChildren().clear();
                    sectionPriceMap = new HashMap<Integer, TextField>();
                    double height = 0;
                    for(SectionTemplate st : sections){
                        HBox hb = new HBox();
                        HBox l = new HBox();
                        l.setMinWidth(74);
                        l.setMaxWidth(74);
                        HBox r = new HBox();
                        hb.setMinHeight(35);
                        height += 35;
                        Label lb = new Label(st.getSectionLabel());
                        l.getChildren().add(lb);
                        TextField tf = new TextField();
                        sectionPriceMap.put(Integer.parseInt(st.getId()), tf);
                        tf.setUserData(st.getId());
                        r.getChildren().add(new Label("$"));
                        r.getChildren().add(tf);
                        hb.getChildren().add(l);
                        hb.getChildren().add(r);
                        vboxSectionPrice.getChildren().add(hb);
                    }
                    vform.setMinHeight(height + 260);
                }
            }
        };
        this.chbTemplate.getSelectionModel().selectedIndexProperty().addListener(listener);
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
            vboxSectionPrice.getChildren().clear();
            double height = 0;
            for(SectionPrice sp : s.getSectionPrices()){
                HBox hb = new HBox();
                HBox l = new HBox();
                l.setMinWidth(74);
                l.setMaxWidth(74);
                HBox r = new HBox();
                hb.setMinHeight(35);
                height += 35;
                Label lb = new Label(sp.getSectionName());
                l.getChildren().add(lb);
                TextField tf = new TextField(sp.getPrice().toString());
                tf.setUserData(sp.getId());
                r.getChildren().add(new Label("$"));
                r.getChildren().add(tf);
                hb.getChildren().add(l);
                hb.getChildren().add(r);
                vboxSectionPrice.getChildren().add(hb);
            }
            vform.setMinHeight(height + 260);
        }else{
            chbTitle.getSelectionModel().selectFirst();
        }
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        if(!isValidForm()) return;
        SimpleDateFormat sdf = UtilitiesFactory.getSimpleDateFormatInstance();
        Schedule schedule = new Schedule();
        schedule.setId(this.hidId.getText());
        LocalDate localDate = this.txtDate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        schedule.setDate(sdf.format(date));
        schedule.setTime(this.txtTime.getText());
        schedule.setMovieId(this.chbTitle.getValue().getId());
        schedule.setTemplateId(this.chbTemplate.getValue().getId());
        List<Node> nodes = vboxSectionPrice.getChildren();
        List<SectionPrice> sectionPrices = new ArrayList<>();
        if(Constant.PAGETYPE_ADD.equals(ApplicationContext.stage.getUserData())){
            for(Node node : nodes){
                TextField tf = (TextField)((HBox)((HBox) node).getChildren().get(1)).getChildren().get(1);
                SectionPrice sp = new SectionPrice();
                sp.setPrice(Double.valueOf(tf.getText()));
                // session_template talbe primary key
                sp.setSectionId(tf.getUserData().toString());
                sectionPrices.add(sp);
            }
            schedule.setSectionPrices(sectionPrices);
            AlertMaker.showMessage(ScheduleService.addSchedule(schedule));
        }else{
            for(Node node : nodes){
                TextField tf = (TextField)((HBox)((HBox) node).getChildren().get(1)).getChildren().get(1);
                SectionPrice sp = new SectionPrice();
                sp.setPrice(Double.valueOf(tf.getText()));
                // session_price talbe primary key
                sp.setId(tf.getUserData().toString());
                sectionPrices.add(sp);
            }
            schedule.setSectionPrices(sectionPrices);
            AlertMaker.showMessage(ScheduleService.updateSchedule(schedule));
        }
        ((Stage) chbTitle.getScene().getWindow()).close();
        ((AdminMovieListController) ApplicationContext.stage.getScene().getUserData()).loadDate();
    }
    
    private boolean isValidForm(){
        List<Node> nodes = vboxSectionPrice.getChildren();
        boolean checkRes = true;
        for(Node node : nodes){
            TextField tf = (TextField)((HBox)((HBox) node).getChildren().get(1)).getChildren().get(1);
            if("".equals(tf.getText())){
                checkRes = false;
                break;
            }
        }
        if(!checkRes || this.txtDate.getValue() == null || StringUtils.isEmpty(this.txtTime.getText())){
            AlertMaker.showMessage("please make sure all input intems aren't empty");
            return false;
        }
        
        return true;
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        ((Stage) chbTitle.getScene().getWindow()).close();
    }
    
}
