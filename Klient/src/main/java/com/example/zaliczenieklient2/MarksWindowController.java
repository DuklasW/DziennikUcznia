package com.example.zaliczenieklient2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONObject;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MarksWindowController implements Initializable {

    @FXML
    private TableView<MarkTable> table;

    @FXML
    private TableColumn<MarkTable, String> subject;

    @FXML
    private TableColumn<MarkTable, String> marks;

    @FXML
    private TableColumn<MarkTable, Double> avgMarks;



    private Client client;
    private JSONObject serwer;

    SendDataToContoller data = SendDataToContoller.getInstance();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //semestr ma wpisane 4 semestry od 1.09.2021 do 31.08.2023 -> wakacje zimowe i letnie są wliczone do semestru
        //String[] semestr = new String[]{"2021-09-01", "2022-01-31", "2022-02-01", "2022-08-31", "2022-09-01", "2023-01-31", "2023-02-01", "2023-08-31"};
        String[] semestr = new String[]{"2021-08-31", "2022-02-01", "2022-01-31", "2022-09-01", "2022-08-31", "2023-02-01", "2023-01-31", "2023-09-01"};
        LocalDate localDate = LocalDate.now();
        client = data.getClient();
        serwer = client.getData();
        ObservableList<MarkTable> list = FXCollections.observableArrayList();

        String subName;//Name of subject
        Integer n;//size for
        Integer countSubject = serwer.optInt("count");
        LocalDate markDate;
        boolean checkFirst = true;
        int countMarks=0;
        for(int z = 0; z < countSubject; z++) {
            serwer = client.getData();
            n = serwer.optInt("size");
            subName = serwer.optString("subject");
            double suma = 0;
            String marksString = "";
            for (int i = 0; i < n; i++){
                serwer = client.getData();
                for(int j = 0; j< 8; j+=2){
                    if(localDate.isAfter(LocalDate.parse(semestr[j])) && localDate.isBefore(LocalDate.parse(semestr[j+1]))){
                        markDate = LocalDate.parse(serwer.optString("date"));
                        if(markDate.isAfter(LocalDate.parse(semestr[j])) && markDate.isBefore(LocalDate.parse(semestr[j+1]))){
                            if (checkFirst){
                                marksString = serwer.optString("id");
                                checkFirst = false;
                            }
                            else marksString = marksString + ", " + serwer.optString("id");
                            suma = suma + serwer.optInt("id");
                            countMarks++;
                        }
                        j = 8;
                    }
                }
            }
            checkFirst = true;
            suma = suma * 1.0 / countMarks;
            if(marksString.equals("")) {marksString = "brak ocen!"; suma = 0.0;};
            list.addAll(new MarkTable(subName, marksString, suma));
            countMarks = 0;
        }

        subject.setCellValueFactory(new PropertyValueFactory<MarkTable, String>("subject"));
        marks.setCellValueFactory(new PropertyValueFactory<MarkTable, String>("marks"));
        avgMarks.setCellValueFactory(new PropertyValueFactory<MarkTable, Double>("avgMarks"));

        table.setItems(list);
    }

}
