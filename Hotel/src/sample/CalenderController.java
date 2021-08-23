package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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


import javax.swing.*;


public class CalenderController implements Initializable{
    @FXML public ComboBox StateBox;
    @FXML public TextField FnameField;
    @FXML public TextField LnameField;
    @FXML public TextField PhoneField;
    @FXML public TextField EmailField;
    @FXML public TextField AD1Field;
    @FXML public TextField CityField;
    @FXML public TextField ZipField;
    @FXML public Button Save;
    public String checkIn,checkOut;
    public ResultSet rs;
    public String customerID;
    public String roomNumber;

    @FXML public void callBack(ActionEvent event){
        try {

            Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));

            //StateBox.setItems(list);
            Scene person = new Scene(root);
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(person);

            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML public void insertInformation(ActionEvent event){
        String Fname = FnameField.getText();
        String Lname = LnameField.getText();
        String phone = PhoneField.getText();
        String email = EmailField.getText();
        String address1 = AD1Field.getText();
        String city = CityField.getText();
        String zip = ZipField.getText();
        String state =(String) StateBox.getValue();
        BookingController date = new BookingController();
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // connect to database
            Connection connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/mrnic/OneDrive/Documents/School/Data Structures/Hotel/HotelDatabase.accdb");

            // NAME INSERT
            PreparedStatement name = connection.prepareStatement(
                    "INSERT INTO Customer (First,Last,Address,City,State,ZipCode,Email,PhoneNumber) " +
                            " values (?,?,?,?,?,?,?,?)");
            name.setString(1, Fname);
            name.setString(2, Lname);
            name.setString(3, address1);
            name.setString(4, city);
            name.setString(5, state);
            name.setString(6, zip);
            name.setString(7, email);
            name.setString(8, phone);

            name.execute();

            PreparedStatement ID = connection.prepareStatement("SELECT CustomerID FROM Customer WHERE Last =?");
            ID.setString(1,Lname);
            rs=ID.executeQuery();
            while(rs.next()){
                customerID=rs.getString("CustomerID");
            }

            PreparedStatement reserve = connection.prepareStatement("INSERT INTO Reservation(RoomNumber,CustomerID,First,Last,Checkin,Checkout) VALUES(?,?,?,?,?,?) ");
            reserve.setString(1,roomNumber);
            reserve.setString(2,customerID);
            reserve.setString(3,Fname);
            reserve.setString(4,Lname);
            reserve.setString(5,checkIn);
            reserve.setString(6,checkOut);
            reserve.executeUpdate();


            PreparedStatement updateRoom=connection.prepareStatement("UPDATE Rooms SET Available=?,Occupant=? WHERE RoomNumber=?");
            updateRoom.setBoolean(1,false);
            updateRoom.setString(2,Lname);
            updateRoom.setString(3,roomNumber);
            updateRoom.executeUpdate();
            connection.close();

            System.out.println("Booking Complete");
            closeButtonAction();

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
        }
    }

    @FXML private void closeButtonAction(){
        Stage stage = (Stage) Save.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StateBox.setValue("Select State");
        StateBox.getItems().addAll("Alabama","Alaska","Arizona","Arkansas",
                "California","Colorado","Connecticut","Delaware","Florida","Georgia",
                "Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana",
                "Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri",
                "Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York",
                "North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island",
                "South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington",
                "West Virginia","Wisconsin","Wyoming");
        BookingController getDate= new BookingController();
        DataBaseClass getRoomNum = new DataBaseClass();
        roomNumber=getRoomNum.reservationRoomNum;
        checkIn=getDate.date1;
        checkOut=getDate.date2;

    }
}