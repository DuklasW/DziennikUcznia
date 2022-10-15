package com.example.zaliczenieklient2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class teacherWindowController implements Initializable {
    @FXML
    private Text Tname;
    @FXML
    private Text Tsurname;

    @FXML
    private Text ClassView;

    @FXML
    private ListView<String> subjectsListView;

    private Client client;
    private JSONObject serwer;

    SendDataToContoller data = SendDataToContoller.getInstance();
    ObservableList subjectsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        client = data.getClient();
        serwer = client.getData();
        subjectsList = FXCollections.observableArrayList();
        Tname.setText(serwer.optString("imie"));
        Tsurname.setText(serwer.optString("nazwisko"));
        loadDataLessons();
        loadDataClasses();
    }

    private void loadDataClasses(){
        serwer = client.getData();
        String findClass = serwer.optString("class");
        if(findClass == null) ClassView.setText("Brak przypisanych klass");
        else ClassView.setText(findClass);
    }
/*
    private void loadDataClasses() {
        serwer = client.getData();
        int size = serwer.optInt("countClasses");
        classList.removeAll(classList);
        String findClass;
        for(int i=0; i<size; i++){
            serwer = client.getData();
            findClass = serwer.optString("class");
            classList.add(findClass);
        }
        if(size == 0) classList.add("Brak przypisanych klass");
        classListView.getItems().addAll(classList);
    }
    */

    private void loadDataLessons() {
        int size = serwer.optInt("countSubjects");
        subjectsList.removeAll(subjectsList);
        String subjects;
        for(int i=0; i<size; i++){
            serwer = client.getData();
            subjects = serwer.optString("subjects");
            if(subjects.startsWith("Jezyk")){
                subjectsList.add("Jezyk " + subjects.substring(5));
            }
            else{
                subjectsList.add(subjects);
            }
        }
        if(size == 0) subjectsList.add("Brak przypisanych przedmiotów");
        subjectsListView.getItems().addAll(subjectsList);
    }
}