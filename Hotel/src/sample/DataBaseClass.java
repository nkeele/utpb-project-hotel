package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.lang.*;
import javax.swing.*;
import javafx.scene.control.TableColumn.CellEditEvent;


public class DataBaseClass implements Initializable {
    @FXML
    public TextField testField;
    @FXML
    static String name;
    @FXML
    public static String ID;
    @FXML
    static public ResultSet rs;
    @FXML
    public TableView<RoomTable> roomTable;
    @FXML
    public TableColumn<RoomTable, String> RoomNum;
    @FXML
    public  TableColumn<RoomTable, String> RoomType;
    @FXML
    public TableColumn <RoomTable,String>Available;
    @FXML
    public TableColumn <RoomTable,String>status;
    @FXML
    public TableColumn <RoomTable,String> occupant;
    @FXML
    public TableColumn<RoomTable,Integer> patioOne;
    @FXML
    public TableColumn<RoomTable,Integer> patioTwo;
    @FXML
    public TableColumn<RoomTable,Integer> forestOne;
    @FXML
    public TableColumn<RoomTable,Integer> forestTwo;
    public ObservableList<RoomTable> roomData;
    public String statusVal;
    public String row;
    public static String reservationRoomNum;
    public ResultSet RS;
    public boolean availability;


    /*
     * Used to set the status from clean to dirty,dirty to clean
     */

    public void changeStatusEvent(CellEditEvent statusEvent){
        RoomTable statusUpdate = roomTable.getSelectionModel().getSelectedItem();
        statusUpdate.setStatus((String) statusEvent.getNewValue());
        row=statusUpdate.getRoomNum().toString();

        statusVal = statusUpdate.getStatus().toString();
        System.out.println(statusVal);
        System.out.println(row);
    }
    /*
     * Returns to main menu
     */
    @FXML
    public void callBack(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

            //StateBox.setItems(list);
            Scene person = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(person);

            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetData();

    }
    /*
     * Gets data from database and puts the data into the room status table.
     */
    @FXML
    public void GetData(){
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // connect to database
            Connection connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/mrnic/OneDrive/Documents/School/Data Structures/Hotel/HotelDatabase.accdb");


            roomData = FXCollections.observableArrayList();
            ResultSet rs = connection.createStatement().executeQuery("SELECT RoomNumber,RoomType,Patio1Price,Patio2Price,Forest1Price,Forest2Price,Status,Available,Occupant FROM Rooms");
            while(rs.next()){

                roomData.add(new RoomTable(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            connection.close();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (ClassNotFoundException cnfex) {

            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        RoomNum.setCellValueFactory(new PropertyValueFactory<RoomTable,String>("roomNum"));
        RoomType.setCellValueFactory(new PropertyValueFactory<RoomTable,String>("roomType"));
        patioOne.setCellValueFactory(new PropertyValueFactory<RoomTable,Integer>("patioOne"));
        patioTwo.setCellValueFactory(new PropertyValueFactory<RoomTable,Integer>("patioTwo"));
        forestOne.setCellValueFactory(new PropertyValueFactory<RoomTable,Integer>("forestOne"));
        forestTwo.setCellValueFactory(new PropertyValueFactory<RoomTable,Integer>("forestTwo"));
        status.setCellValueFactory(new PropertyValueFactory<RoomTable,String>("status"));
        Available.setCellValueFactory(new PropertyValueFactory<RoomTable,String>("Available"));
        occupant.setCellValueFactory(new PropertyValueFactory<RoomTable,String>("occupant"));

        roomTable.setItems(roomData);
        roomTable.setEditable(true);
        status.setCellFactory(TextFieldTableCell.forTableColumn());

        roomTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                reservationRoomNum=roomTable.getSelectionModel().getSelectedItem().getRoomNum();

            }
        });
    }
    public void updateTable(ActionEvent event){
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // connect to database
            Connection connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/mrnic/OneDrive/Documents/School/Data Structures/Hotel/HotelDatabase.accdb");


            roomData = FXCollections.observableArrayList();
            String update = "UPDATE Rooms SET Status=? WHERE RoomNumber=? ";
            //ResultSet rs = connection.createStatement().executeQuery("UPDATE Rooms SET Status = ? WHERE RoomNumber=? ");
            PreparedStatement ps = connection.prepareStatement(update);

            ps.setString(1,statusVal);
            ps.setString(2,row);

            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (ClassNotFoundException cnfex) {

            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

    }
    public void personalInfo(ActionEvent event){
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // connect to database
            Connection connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/mrnic/OneDrive/Documents/School/Data Structures/Hotel/HotelDatabase.accdb");


            PreparedStatement ps = connection.prepareStatement("SELECT Available FROM Rooms WHERE RoomNumber=?");

            ps.setString(1,reservationRoomNum);
            RS=ps.executeQuery();
            while(RS.next()){
                availability=RS.getBoolean("Available");
            }
            if(availability==true) {

                Parent root = FXMLLoader.load(getClass().getResource("PersonalInfo.fxml"));


                Scene person = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(person);

                stage.show();
            }
            if(availability==false){
                System.out.println("Please Select another Room");
            }

        }
        catch ( SQLException sqlException ) {
            JOptionPane.showMessageDialog( null,
                    sqlException.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE );
            System.exit( 1 );
        }

        catch(ClassNotFoundException cnfex) {

            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}