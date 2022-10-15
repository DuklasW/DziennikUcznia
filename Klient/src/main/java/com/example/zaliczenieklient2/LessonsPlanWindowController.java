package com.example.zaliczenieklient2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LessonsPlanWindowController implements Initializable {
    @FXML
    private TableView<LessonsTable> table;

    @FXML
    private TableColumn<LessonsTable, String> hour;

    @FXML
    private TableColumn<LessonsTable, String> monday;

    @FXML
    private TableColumn<LessonsTable, String> thesday;

    @FXML
    private TableColumn<LessonsTable, String> wednesday;

    @FXML
    private TableColumn<LessonsTable, String> thursday;

    @FXML
    private TableColumn<LessonsTable, String> friday;

    private Client client;
    private JSONObject serwer;

    SendDataToContoller data = SendDataToContoller.getInstance();
    String[] days;
    LocalDate localDate;
    Integer dayInt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        days = new String[] {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        localDate = LocalDate.now();

        dayInt = localDate.getDayOfMonth(); // pobiera inta dzien miesiaca
        for(int i =0; i<7; i++) if(String.valueOf(localDate.getDayOfWeek()).equals(days[i])) dayInt -= i;
        displayPlan();
    }
    private void displayPlan(){
        client = data.getClient();

        String[] hours = new String[]{"8:00-8:45", "8:50-9:35", "9:40-10:25", "10:40-11:25", "11:35-12:20", "12:25-13:10", "13:15-14:00"};
        String[][] lessions = new String[5][7];
        String setMonday="";    //wskaze kiedy bedzie poniedzialek, a pozniej przetrzymuje date tygodnia
        String week="";
        ObservableList<LessonsTable> list = FXCollections.observableArrayList();
        list.removeAll(list);
        for(int i=0; i<5; i++){for(int j=0; j < 7; j++){lessions[i][j] ="";}}//ustawia cala tablice lessions na pusta

        for(int i=0; i<5; i++){
            setMonday = String.valueOf(localDate).substring(0,8) + (dayInt+i);
            if(i == 0 || i == 4) week += setMonday;
            else if(i == 2) week += " - ";
            client.SendString(setMonday);
            serwer = client.getData();
            int size = serwer.optInt("size");
            for(int j=0; j < size; j++){
                serwer = client.getData();
                int hour = serwer.optInt("hour");
                String lekcja = serwer.optString("lesson");
                lessions[i][hour] = lekcja;
            }
        }
        for(int i=0; i<7; i++){
            list.addAll(new LessonsTable(hours[i], lessions[0][i], lessions[1][i], lessions[2][i], lessions[3][i], lessions[4][i]));
        }
        hour.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("hour"));
        monday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("monday"));
        thesday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("thesday"));
        wednesday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("wednesday"));
        thursday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("thursday"));
        friday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("friday"));
        table.setItems(list);

    }
}

/*
 //System.out.println("LocalDate - System zone: " + localDate);
                //System.out.println("Day of week: " + localDate.getDayOfWeek());
                //String day = String.valueOf(localDate); //pobiera dzien tygodnia duzymi po angielsku
                //System.out.println(localDate +" Poniedzialek wypada: " + setMonday);
 */