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
import java.net.URL;
import java.util.ResourceBundle;

public class BillController implements Initializable {

    public TextArea text;
    @FXML
    public void onClick(ActionEvent event){
        try {

            Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));

            //StateBox.setItems(list);
            Scene call = new Scene(root);
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(call);

            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void initialize (URL url, ResourceBundle rb){

    }


}