package sample;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.collections.*;
import java.lang.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.lang.*;
import java.util.*;
import javafx.collections.*;


public class Controller implements Initializable{
    @FXML public ComboBox StateBox;


    @FXML
    public void Booking(ActionEvent event)
    {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("Calender.fxml"));
            Scene Booking = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(Booking);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML public void Bill(ActionEvent event){
        try {
            Parent root= FXMLLoader.load(getClass().getResource("Bill.fxml"));
            Scene Bill = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(Bill);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    public void personalInformation(ActionEvent event){
        try {

            Parent root= FXMLLoader.load(getClass().getResource("PersonalInfo.fxml"));


            Scene person = new Scene(root);
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(person);

            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void Room(ActionEvent event){
        try {

            Parent root= FXMLLoader.load(getClass().getResource("RoomStatus.fxml"));

            //StateBox.setItems(list);
            Scene Room = new Scene(root);
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(Room);

            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
    public void buttonClick(){

    }
}
