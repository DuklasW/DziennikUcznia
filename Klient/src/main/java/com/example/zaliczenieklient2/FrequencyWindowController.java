package com.example.zaliczenieklient2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FrequencyWindowController implements Initializable {

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

    @FXML
    private Text weekLabel;

    private Client client;
    private JSONObject serwer;

    SendDataToContoller data = SendDataToContoller.getInstance();
    String[] days;
    LocalDate localDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        client = data.getClient();
        days = new String[] {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        localDate = LocalDate.now();
        for(int i =0; i<7; i++) if(String.valueOf(localDate.getDayOfWeek()).equals(days[i])) localDate = localDate.minusDays(i);//dayInt -= i;
        displayFrequency();
    }
    private void displayFrequency() {
        String[] hours = new String[]{"8:00-8:45", "8:50-9:35", "9:40-10:25", "10:40-11:25", "11:35-12:20", "12:25-13:10", "13:15-14:00"};
        String[][] frequency = new String[5][7];
        String setDay = "";    //wskaze kiedy bedzie poniedzialek, a pozniej przetrzymuje date tygodnia
        String week = "";
        ObservableList<LessonsTable> list = FXCollections.observableArrayList();
        list.removeAll(list);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                frequency[i][j] = "";
            }
        }//ustawia cala tablice lessions na pusta

        for (int i = 0; i < 5; i++) {
            if(i > 0) localDate = localDate.plusDays(1);
            //if(i == 0) localDate = localDate.minusDays(7);
            setDay = String.valueOf(localDate);//.substring(0, 8) + (dayInt);
            if (i == 0 || i == 4) week += setDay;
            else if (i == 2) week += " - ";
            client.SendString(setDay);
            serwer = client.getData();
            int size = serwer.optInt("size");
            for (int j = 0; j < size; j++) {
                serwer = client.getData();
                Integer hour = serwer.optInt("hour");
                String status = serwer.optString("freqwency");
                frequency[i][hour] = status;
            }
        }
        localDate = localDate.minusDays(4);
        for (int i = 0; i < 7; i++) {
            list.addAll(new LessonsTable(hours[i], frequency[0][i], frequency[1][i], frequency[2][i], frequency[3][i], frequency[4][i]));
        }
        weekLabel.setText(week);
        hour.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("hour"));
        monday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("monday"));
        thesday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("thesday"));
        wednesday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("wednesday"));
        thursday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("thursday"));
        friday.setCellValueFactory(new PropertyValueFactory<LessonsTable, String>("friday"));
        table.setItems(list);

    }
    @FXML
    void previewButton() throws JSONException, IOException {
        localDate = localDate.minusDays(7);
        client.sendCase(1);
        displayFrequency();
    }
    @FXML
    void nextButton() throws JSONException, IOException {
        localDate = localDate.plusDays(7);
        client.sendCase(1);
        displayFrequency();
    }
}
