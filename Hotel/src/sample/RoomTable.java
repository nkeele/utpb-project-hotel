package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoomTable {
    public SimpleStringProperty roomNum,roomType,Available,status,occupant;
    public SimpleIntegerProperty patioOne,patioTwo,forestOne,forestTwo;
    public RoomTable(String roomNum,String roomType,int patioOne,int patioTwo, int forestOne, int forestTwo, String status,String Available, String occupant){
        this.roomNum=new SimpleStringProperty(roomNum);
        this.roomType=new SimpleStringProperty(roomType);
        this.patioOne=new SimpleIntegerProperty(patioOne);
        this.patioTwo = new SimpleIntegerProperty(patioTwo);
        this.forestOne=new SimpleIntegerProperty(forestOne);
        this.forestTwo=new SimpleIntegerProperty(forestTwo);
        this.status=new SimpleStringProperty(status);
        this.Available=new SimpleStringProperty(Available);
        this.occupant=new SimpleStringProperty(occupant);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getOccupant() {
        return occupant.get();
    }

    public SimpleStringProperty occupantProperty() {
        return occupant;
    }

    public void setOccupant(String occupant) {
        this.occupant.set(occupant);
    }

    public int getPatioOne() {
        return patioOne.get();
    }

    public SimpleIntegerProperty patioOneProperty() {
        return patioOne;
    }

    public void setPatioOne(int patioOne) {
        this.patioOne.set(patioOne);
    }

    public int getPatioTwo() {
        return patioTwo.get();
    }

    public SimpleIntegerProperty patioTwoProperty() {
        return patioTwo;
    }

    public void setPatioTwo(int patioTwo) {
        this.patioTwo.set(patioTwo);
    }

    public int getForestOne() {
        return forestOne.get();
    }

    public SimpleIntegerProperty forestOneProperty() {
        return forestOne;
    }

    public void setForestOne(int forestOne) {
        this.forestOne.set(forestOne);
    }

    public int getForestTwo() {
        return forestTwo.get();
    }

    public SimpleIntegerProperty forestTwoProperty() {
        return forestTwo;
    }

    public void setForestTwo(int forestTwo) {
        this.forestTwo.set(forestTwo);
    }

    public String getAvailable() {
        return Available.get();
    }

    public SimpleStringProperty availableProperty() {
        return Available;
    }

    public void setAvailable(String available) {
        this.Available.set(available);
    }

    public String getRoomNum() {
        return roomNum.get();
    }

    public SimpleStringProperty roomNumProperty() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum.set(roomNum);
    }

    public String getRoomType() {
        return roomType.get();
    }

    public SimpleStringProperty roomTypeProperty() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType.set(roomType);
    }
}