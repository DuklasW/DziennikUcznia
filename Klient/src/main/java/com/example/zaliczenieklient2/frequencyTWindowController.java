package com.example.zaliczenieklient2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.Subject;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

public class frequencyTWindowController implements Initializable {


    @FXML
    private Label classerrorLabel;
    @FXML
    private Label dayErrorLabel;

    @FXML
    private Label errorHourLabel;

    @FXML
    private DatePicker frequencyDatePicker;

    @FXML
    private TableColumn<FrequencyTable, Integer> id;
    @FXML
    private TableColumn<FrequencyTable, String> name;
    @FXML
    private TableColumn<FrequencyTable, String> surname;

    @FXML
    private TableColumn<FrequencyTable, String> presents;

    @FXML
    private TableColumn<FrequencyTable, String> absent;

    @FXML
    private TableColumn<FrequencyTable, String> exempt;

    @FXML
    private ChoiceBox<String> subjectCheckBox;
    @FXML
    private ChoiceBox<String> classCheckBox;
    @FXML
    private ChoiceBox<Integer> hoursCheckBox;

    @FXML
    private Label subjecterrorLabel;

    @FXML
    Label errorFrequency;

    @FXML
    private TableView<FrequencyTable> table;

    private Client client;
    private JSONObject serwer;


    SendDataToContoller data = SendDataToContoller.getInstance();
    ObservableList<FrequencyTable> list;
    ArrayList<String> loginList;
    ObservableList subjects = FXCollections.observableArrayList();
    ObservableList clasS = FXCollections.observableArrayList();
    ObservableList hours = FXCollections.observableArrayList();

    String subject="";
    LocalDate date= LocalDate.parse("1999-01-01");
    String classTeacher="";
    Integer hourLesson = -1;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = data.getClient();
        loadSubject();//zaladowuje przedmioty ktorych uczy nauczyciel do ChoiceBox

        //loadClass();//zaladowuje kladdo co choiceBox !!!!!!!!!!!
        //loadHours();//zaladowywuje wygor godziny lekcyjnej!!!!!!!!!!!

        subjectCheckBox.setOnAction(this::setSubject); //zapisujemy przedmiot do subject
        frequencyDatePicker.setOnAction(this::setDate);//zapisujemy datę do date
        classCheckBox.setOnAction(this::setClass);
        hoursCheckBox.setOnAction(this::setHourLesson);
        //checkData();
        checkAll();
    }
    //public void check

    public void setHourLesson(ActionEvent event){
        hourLesson = hoursCheckBox.getValue();
    }

    public void checkAll(){
        if (!subject.equals("") && !classTeacher.equals("")) {
            try {
                list = FXCollections.observableArrayList();
                list.removeAll(list);
                table.setItems(list);
                setTbleView();
                setHour();
            } catch (JSONException e) {
                list = FXCollections.observableArrayList();
                list.removeAll(list);
                table.setItems(list);
                throw new RuntimeException(e);
            } catch (IOException e) {
                list = FXCollections.observableArrayList();
                list.removeAll(list);
                table.setItems(list);
                throw new RuntimeException(e);
            }
        } else {
            list = FXCollections.observableArrayList();
            list.removeAll(list);
            table.setItems(list);
        }
    }

    public void setClass(ActionEvent event){
        if (!subject.equals("") && date.isAfter(LocalDate.parse("1999-01-02"))) {
            classTeacher = classCheckBox.getValue();
            System.out.println(classTeacher);
            checkAll();
        }else System.out.println("Uzupelnij najpierw date i przedmiot");
    }

    public void setDate(ActionEvent event){
        if(frequencyDatePicker.getValue() != null) {
            date = frequencyDatePicker.getValue();
            System.out.println("Wybrano dzien: " + date);
            checkSubjectData();
            checkAll();
        }
    }
    public void setSubject(ActionEvent event){
        if(subjectCheckBox.getValue() != null) {
            subject = subjectCheckBox.getValue();
            System.out.println("Wybrano przedmiot: " + subject);
            checkSubjectData();
            checkAll();
        }
    }

    private void checkSubjectData() {
        if (!subject.equals("") && date.isAfter(LocalDate.parse("1999-01-02"))) {
                try {
                    client.sendCase(20);
                    client.SendString(subject);
                    client.SendString(String.valueOf(date));
                    serwer = client.getData();

                    int size;
                    clasS.removeAll(clasS);

                    size = serwer.getInt("size");
                    System.out.println(size);
                    for(int i=0; i<size; i++){
                        serwer = client.getData();
                        clasS.add(serwer.optString("class"));
                    }
                    classCheckBox.setItems(clasS);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }else {
            if(subject.equals("")) System.out.println("Wybierz przedmiot");
            if(date.isBefore(LocalDate.parse("1999-01-02"))) System.out.println("Ustaw poprawna date");
        }
    }
    /*
    boolean goodHour = false;
    @FXML
    void clickHourBox(){//przycisk potwierdza wybor godziny lekcyjnej
        goodHour = checkHour();
    }
    */
    private void setHour() throws  JSONException, IOException{
        client.sendCase(21);
        client.SendString(subject);
        client.SendString(String.valueOf(date));
        client.SendString(classTeacher);
        hours.removeAll(hours);
        serwer = client.getData();
        int size = serwer.optInt("size");
        for(int i = 0; i<size; i++){
            serwer = client.getData();
            hours.add(serwer.optInt("hour"));
        }
        hoursCheckBox.setItems(hours);
    }


    private void setTbleView() throws JSONException, IOException {//wyswietla tablice z uczniami
        client.sendCase(10);
        client.SendString(classCheckBox.getValue());
        list = FXCollections.observableArrayList();
        loginList = new ArrayList<>();
        serwer = client.getData();
        int size = serwer.optInt("size");
        String cId, cName, cSurname, cLoginU;
        for (int i = 0; i < size; i++) {
            serwer = client.getData();
            cId = serwer.optString("id");
            cName = serwer.optString("name");
            cSurname = serwer.optString("surname");
            cLoginU = serwer.optString("login");
            list.addAll(new FrequencyTable(cId, cName, cSurname, cLoginU, "", "", ""));
        }

        name.setCellValueFactory(new PropertyValueFactory<FrequencyTable, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<FrequencyTable, Integer>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<FrequencyTable, String>("surname"));
        presents.setCellValueFactory(new PropertyValueFactory<FrequencyTable, String>("presents"));
        absent.setCellValueFactory(new PropertyValueFactory<FrequencyTable, String>("absent"));
        exempt.setCellValueFactory(new PropertyValueFactory<FrequencyTable, String>("exempt"));
        table.setItems(list);
    }
/*
    private void checkData() {//sprawdza czy nauczyciel wybral dobry przedmiot i klase
        if (goodSubject && goodClass) {
            try {
                setTbleView();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            list = FXCollections.observableArrayList();
            list.removeAll(list);
            table.setItems(list);
        }
    }
    */
    /*
    private Boolean checkClass() {//sprawdza czy nauczyciel uczy dobra klase
        String chooseClass = classCheckBox.getValue();
        if (chooseClass == null) classerrorLabel.setText("Wybierz klase");
        else {
            client.SendString(chooseClass);
            serwer = client.getData();
            if (serwer.optString("boolean").equals("Yes")) {
                classerrorLabel.setText("Wybrano poprawnie");
                return true;
            } else classerrorLabel.setText("Nie uczysz tej klasy!");

        }
        return false;
    }
    */
    /*
    private Boolean checkTeacher() {//sprawdza czy nauczyciel uczy dobry przedmiot
        String chooseSubject = subjectCheckBox.getValue();
        if (chooseSubject == null) subjecterrorLabel.setText("Wybierz przedmiot");
        else {
            client.SendString(chooseSubject);
            serwer = client.getData();
            if (serwer.optString("boolean").equals("Yes")) {
                subjecterrorLabel.setText("Wybrano poprawnie");
                return true;
            } else subjecterrorLabel.setText("Nie uczysz tego przedmiotu!");
        }
        return false;
    }
     */

    private Boolean checkHour(){//sprawdza czy wybralismy godzine lekcyjna
        Integer chooseHour = hoursCheckBox.getValue();
        if(chooseHour == null) errorHourLabel.setText("Wybierz godzine lekcyjna");
        else{errorHourLabel.setText("Wybrano poprawnie");
            return true;
        }
        return false;
    }

    private void loadHours(){//laduje do checkBox godziny lekcyjne
        hours.removeAll(hours);
        for(int i = 0; i< 7; i++) hours.add(i+1);
        hoursCheckBox.setItems(hours);
    }
    private void loadSubject() {//laduje do checkBox przedmioty
        serwer = client.getData();
        subjects.removeAll(subjects);
        int sizeSubject = serwer.optInt("size");
        String subjectName;
        for (int i = 0; i < sizeSubject; i++) {
            serwer = client.getData();
            subjectName = serwer.optString("subject");
            subjects.add(subjectName);
        }
        subjectCheckBox.setItems(subjects);
    }
/*
    private void loadClass() {//laduje do checkBox klasy
        serwer = client.getData();
        clasS.removeAll(clasS);
        int sizeClass = serwer.optInt("size");
        String className;
        for (int i = 0; i < sizeClass; i++) {
            serwer = client.getData();
            className = serwer.optString("Class");
            clasS.add(className);
        }
        classCheckBox.setItems(clasS);
    }
*/
    boolean checkCheckBoxes() { //sprawdza czy zaznaczylismy jeden stan obecnosci i kazdego ucznia
        for (FrequencyTable bean : list) {
            int suma = 0;
            if (bean.getExempt().isSelected()) suma++;
            if (bean.getPresents().isSelected()) suma++;
            if (bean.getAbsent().isSelected()) suma++;
            if (suma != 1) {
                errorFrequency.setText("Źle zaznaczona frekwencja");
                return false;
            } else errorFrequency.setText("");
        }
        return true;
    }

    @FXML
    void printSelected(ActionEvent event) throws JSONException, IOException {//wysyla frekfencje do serwera
        LocalDate localDate = frequencyDatePicker.getValue();
        if(localDate != null) {
            errorFrequency.setText("");
            dayErrorLabel.setText("Wybrano Poprawnie");
            if (!subject.equals("") && !classTeacher.equals("")) {
                errorFrequency.setText("");
                if(hourLesson>0){
                    errorFrequency.setText("");
                    if (checkCheckBoxes()) {
                        for (FrequencyTable bean : list) {
                            client.sendCase(12);
                            if (bean.getPresents().isSelected())
                                client.sendFrequency(subjectCheckBox.getValue(), bean.getLogin(), localDate, hoursCheckBox.getValue(), "O", classCheckBox.getValue());
                            if (bean.getAbsent().isSelected())
                                client.sendFrequency(subjectCheckBox.getValue(), bean.getLogin(), localDate, hoursCheckBox.getValue(), "NB", classCheckBox.getValue());
                            if (bean.getExempt().isSelected())
                                client.sendFrequency(subjectCheckBox.getValue(), bean.getLogin(), localDate, hoursCheckBox.getValue(), "Z", classCheckBox.getValue());
                        }
                        errorFrequency.setText("Wyslano poprawnie!");
                    }
                }else errorFrequency.setText("Sprawdz dane!");
            }else errorFrequency.setText("Sprawdz dane!");
        }else {dayErrorLabel.setText("Nie wybrales Daty!");errorFrequency.setText("Sprawdz dane!");}
    }
}