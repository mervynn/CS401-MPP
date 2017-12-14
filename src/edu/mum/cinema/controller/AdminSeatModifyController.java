/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.controller;

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
import edu.mum.cinema.context.ApplicationContext;
import edu.mum.cinema.model.Seat;
import edu.mum.cinema.service.SeatService;
import edu.mum.cinema.utilities.AlertMaker;
import org.springframework.util.StringUtils;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class AdminSeatModifyController implements Initializable {

    @FXML
    private TextField txtMovie;
    @FXML
    private PasswordField txtDate;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtRowNum;
    @FXML
    private TextField txtColumnNum;
    @FXML
    private TextField txtStatus;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Label hidId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(ApplicationContext.stage.getUserData() instanceof Seat){
            Seat s = (Seat) ApplicationContext.stage.getUserData();
            this.hidId.setText(s.getId());
            this.txtMovie.setText(s.getMovieTitle());
            this.txtDate.setText(s.getDate());
            this.txtTime.setText(s.getTime());
            this.txtColumnNum.setText(s.getColumnNum());
            this.txtRowNum.setText(s.getRowNum());
            this.txtStatus.setText(s.getStatus());
        }
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        if(!isValidForm()) return;
        Seat u = new Seat();
        u.setId(this.hidId.getText());
        u.setStatus(this.txtStatus.getText());
        AlertMaker.showMessage(SeatService.updateSeat(u));
        ((Stage) txtMovie.getScene().getWindow()).close();
        ((AdminSeatListController) ApplicationContext.stage.getScene().getUserData()).loadDate();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        ((Stage)txtMovie.getScene().getWindow()).close();
    }
    
    private boolean isValidForm(){
        if(StringUtils.isEmpty(txtStatus.getText())){
            AlertMaker.showMessage("please make sure all input intems aren't empty");
            return false;
        }
        return true;
    }
    
}
