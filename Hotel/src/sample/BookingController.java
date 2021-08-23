package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.*;
import java.lang.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.lang.*;
import java.util.*;
import javafx.collections.*;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.time.*;

public class BookingController implements Initializable {
    @FXML
    public TextField DATE;
    @FXML
    public TextField DATE2;
    @FXML
    public DatePicker datePicker;
    @FXML
    public DatePicker datePicker2;
    @FXML TextField Hello;
    @FXML
    public static  String date1;
    @FXML
    public static String date2;
    private static LocalDate date1Time;
    private static LocalDate date2Time;
    LocalDate today = LocalDate.now();

    @FXML
    public void selectData(ActionEvent event){
        date1=datePicker.getValue().toString();
        date1Time = datePicker.getValue();
        DATE.setText(date1);
    }

    public void checkOutDate(ActionEvent event){
        date2=datePicker2.getValue().toString();
        date2Time = datePicker2.getValue();
        DATE2.setText(date2);
    }


    public void initialize(URL url, ResourceBundle rb) {

    }
    public void changeScene(ActionEvent event){

        if(date1Time.isBefore(today) || date2Time.isBefore(today)){

            System.out.println("Please select check-in/check-out dates after today's date");
            try{
                Parent root = FXMLLoader.load(getClass().getResource("Calender.fxml"));
                Scene person = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(person);

                stage.show();
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if(date2Time.isBefore(date1Time)){

            System.out.println("Please select a check-out date that is AFTER the check-in date.");
            try{
                Parent root = FXMLLoader.load(getClass().getResource("Calender.fxml"));
                Scene person = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(person);

                stage.show();
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /*else if(date1 == null || date2 == null){

        }*/
        else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("RoomStatus.fxml"));
                Scene person = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(person);

                stage.show();

            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}