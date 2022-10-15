package com.example.zaliczenieklient2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class teacherEditWindowController implements Initializable {

    @FXML
    private TextField addStudentNumberText;

    @FXML
    private TextField addStudentClassNameText;

    @FXML
    private TextField removeMarkTeacherLoginText1;

    @FXML
    private TextField addStudentParentLoginText;

    @FXML
    private TextField addStudentUserLoginText;

    @FXML
    private TextField addUserLoginText;

    @FXML
    private TextField addUserPasswordText;

    @FXML
    private TextField addUserFirstNameText;

    @FXML
    private TextField addUserSurnameText;

    @FXML
    private TextField addUserAccessText;

    @FXML
    private TextField addTeacherUsLoginText;

    @FXML
    private TextField addSubjectNameText;

    @FXML
    private TextField addClassNameText;

    @FXML
    private TextField addClassDateText;

    @FXML
    private TextField addClassTeacherText;


    @FXML
    private TextField removeClassLoginText;

    @FXML
    private TextField addMarkNameText;

    @FXML
    private TextField addMarkValueText;

    @FXML
    private TextField removeMarkNameText;

    @FXML
    private TextField removeMarkValueText;

    @FXML
    private TextField removeMarkUloginText;

    @FXML
    private TextField removeMarkSubNameText;

    @FXML
    private TextField removeStudentLoginText;

    @FXML
    private TextField removeSubjectNameText;

    @FXML
    private TextField removeTeacherLoginText;

    @FXML
    private TextField addLessonThemeText;

    @FXML
    private TextField addLessonDateText;

    @FXML
    private TextField addLessonHourText;

    @FXML
    private TextField addLessonClassText;

    @FXML
    private TextField addLessonTeacherText;

    @FXML
    private TextField addLessonSubjectText;

    @FXML
    private TextField removeLessonDateText;

    @FXML
    private TextField removeLessonHourText;

    @FXML
    private TextField removeLessonClassText;

    @FXML
    private TextField removeLessonTeacherLoginText;

    @FXML
    private TextField removeLessonSubjectText;

    @FXML
    private TextField removeUserLoginText;

    @FXML
    private TextField removeFrequencyDateText;

    @FXML
    private TextField removeFrequencySubNameText;

    @FXML
    private TextField removeFrequencyULoginText;

    @FXML
    private TextField removeFrequencyHourText;

    @FXML
    private TextField removeFrequencyClassText;

    @FXML
    private TextField addFrequencyDateText;

    @FXML
    private TextField addFrequencySubNameText;

    @FXML
    private TextField addFrequencyStudentLoginText;

    @FXML
    private TextField addFrequencyHourText;

    @FXML
    private TextField addFrequencyTypeText;

    @FXML
    private TextField addFrequencyClassText;

    @FXML
    private TextField transferNewClassName;

    @FXML
    private TextField editTeachersOfSubjectsTLoginText;

    @FXML
    private TextField editTeachersOfSubjectsSubjectNameText;

    @FXML
    private TextField editSubjectsOfClassesSubjectNameText;

    @FXML
    private TextField editSubjectsOfClassesClassNameText;

    @FXML
    private TextField addMarkDiaryNumberText;

    @FXML
    private TextField addMarkClassNameText;

    @FXML
    private TextField addMarkSubjectNameText;

    @FXML
    private TextField transferStudentLogin;

    private Client client;
    private JSONObject serwer;

    int idClass;
    String loginTeacher;
    SendDataToContoller data = SendDataToContoller.getInstance();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = data.getClient();
        try {
            client.sendCase(13);
            serwer = client.getData();
            loginTeacher = serwer.optString("loginTeacher");

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //serwer = client.getData();
        //odbior danych
        //idClass //czy nauczyciel opiekuje się tylko jedną klasą?
        //loginTeacher="";
        //jeszcze jakas lista/vektor czego uczy dany nauczyciel
        //druga lista/vektor jakie klasy uczy nauczyciel
    }

    @FXML
    void transferStudentButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(transferStudentLogin.getText().length() > 0 && transferNewClassName.getText().length() > 0){
            Data.add(transferStudentLogin.getText());
            Data.add(transferNewClassName.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 0, 2);
        }
    }

    @FXML
    void removeUserByLoginButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(removeUserLoginText.getText().length() > 0){
            Data.add(removeUserLoginText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 1, 1);
        }
    }

    @FXML
    void addUserButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        String uLogin = addUserLoginText.getText();
        String uPassword = addUserPasswordText.getText();
        String uName = addUserFirstNameText.getText();
        String uSurname = addUserSurnameText.getText();
        String uAccess = addUserAccessText.getText();
        if(uLogin.length() > 0 && uPassword.length() > 0 && uName.length() > 0 && uSurname.length() > 0 &&
                uAccess.length() > 0){
            Data.add(uLogin);
            Data.add(uPassword);
            Data.add(uName);
            Data.add(uSurname);
            Data.add(uAccess);
            client.sendCase(6);
            client.SendEditSignal(Data,2,5);
        }
    }

    @FXML
    void removeStudentByLoginButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(removeStudentLoginText.getText().length()>0){
            Data.add(removeStudentLoginText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data,3,1);
        }
    }

    @FXML
    public void addStudentButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(addStudentNumberText.getText().length() > 0 && addStudentClassNameText.getText().length() > 0 &&
                addStudentUserLoginText.getText().length() > 0 && addStudentParentLoginText.getText().length() > 0){
            Data.add(addStudentNumberText.getText());
            Data.add(addStudentClassNameText.getText());
            Data.add(addStudentUserLoginText.getText());
            Data.add(addStudentParentLoginText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data,4,4);
        }
    }

    @FXML
    void removeTeacherByLoginButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(removeTeacherLoginText.getText().length()>0){
            Data.add(removeTeacherLoginText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data,5,1);
        }
    }

    @FXML
    void AddTeacherButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(addTeacherUsLoginText.getText().length()>0){
            Data.add(addTeacherUsLoginText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data,6,1);
        }
    }

    @FXML
    void removeSubjectByNameButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(removeSubjectNameText.getText().length()>0){
            Data.add(removeSubjectNameText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data,7,1);
        }
    }

    @FXML
    void addSubjectButton(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(addSubjectNameText.getText().length()>0){
            Data.add(addSubjectNameText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data,8,1);
        }
    }

    @FXML
    void removeMarkByID(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(removeMarkNameText.getText().length() > 0 && removeMarkValueText.getText().length() > 0 &&
                removeMarkUloginText.getText().length() > 0 && removeMarkSubNameText.getText().length() > 0 &&
                removeMarkTeacherLoginText1.getText().length() > 0) {
            if(loginTeacher.equals(removeMarkTeacherLoginText1.getText())) {//sprawdzic czy nauczyciel jest tym od tego przedmiotu
                Data.add(removeMarkNameText.getText());
                Data.add(removeMarkValueText.getText());
                Data.add(removeMarkUloginText.getText());
                Data.add(removeMarkSubNameText.getText());
                client.sendCase(6);
                client.SendEditSignal(Data, 9,4);
            }
        }
    }

    @FXML
    void addMark(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(addMarkNameText.getText().length() > 0 && addMarkValueText.getText().length() > 0 &&
                addMarkDiaryNumberText.getText().length() > 0 && addMarkClassNameText.getText().length() > 0 &&
                addMarkSubjectNameText.getText().length() > 0) {
            Data.add(addMarkNameText.getText());
            Data.add(addMarkValueText.getText());
            Data.add(addMarkDiaryNumberText.getText());
            Data.add(addMarkClassNameText.getText());
            Data.add(addMarkSubjectNameText.getText());
            Data.add(loginTeacher);
            Data.add(LocalDate.now().toString());
            client.sendCase(6);
            client.SendEditSignal(Data, 10,7);
        }
    }

    @FXML
    void removeLessonByID(ActionEvent event) throws JSONException, IOException{
        List<String> Data = new ArrayList<String>();
        if(removeLessonDateText.getText().length() > 0 && removeLessonHourText.getText().length() > 0 &&
                removeLessonClassText.getText().length() > 0 && removeLessonTeacherLoginText.getText().length() > 0 &&
                removeLessonSubjectText.getText().length() > 0){
            Data.add(removeLessonHourText.getText());
            Data.add(removeLessonClassText.getText());
            Data.add(removeLessonTeacherLoginText.getText());
            Data.add(removeLessonSubjectText.getText());
            Data.add(removeLessonDateText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 11, 5);
        }
    }

    @FXML
    void addLesson(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(addLessonHourText.getText().length() > 0 && addLessonClassText.getText().length() > 0 &&
                addLessonTeacherText.getText().length() > 0 && addLessonSubjectText.getText().length() > 0 &&
                addLessonDateText.getText().length() > 0 && addLessonThemeText.getText().length() > 0){
            Data.add(addLessonHourText.getText());
            Data.add(addLessonClassText.getText());
            Data.add(addLessonTeacherText.getText());
            Data.add(addLessonSubjectText.getText());
            Data.add(addLessonDateText.getText());
            Data.add(addLessonThemeText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 12, 6);
        }
    }

    @FXML
    void removeClassByName(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
            if(removeClassLoginText.getText().length()>0){
                Data.add(removeClassLoginText.getText());
                client.sendCase(6);
                client.SendEditSignal(Data, 13,1);
            }
    }

    @FXML
    void addClass(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(addClassNameText.getText().length() > 0 && addClassDateText.getText().length() > 0 &&
                addClassTeacherText.getText().length() > 0){
            Data.add(addClassNameText.getText());
            Data.add(addClassDateText.getText());
            Data.add(addClassTeacherText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 14, 3);
        }
    }

    @FXML
    void removeFrequency(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(removeFrequencySubNameText.getText().length() > 0 && removeFrequencyULoginText.getText().length() > 0 &&
                removeFrequencyDateText.getText().length() > 0 && removeFrequencyHourText.getText().length() > 0 &&
                removeFrequencyClassText.getText().length() > 0){
            Data.add(removeFrequencySubNameText.getText());
            Data.add(removeFrequencyULoginText.getText());
            Data.add(removeFrequencyDateText.getText());
            Data.add(removeFrequencyHourText.getText());
            Data.add(removeFrequencyClassText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 15, 5);
        }
    }

    @FXML
    void addFrequency(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(addFrequencySubNameText.getText().length() > 0 && addFrequencyStudentLoginText.getText().length() > 0 &&
                addFrequencyDateText.getText().length() > 0 && addFrequencyHourText.getText().length() > 0 &&
                addFrequencyTypeText.getText().length() > 0 && addFrequencyClassText.getText().length() > 0){
            Data.add(addFrequencySubNameText.getText());
            Data.add(addFrequencyStudentLoginText.getText());
            Data.add(addFrequencyDateText.getText());
            Data.add(addFrequencyHourText.getText());
            Data.add(addFrequencyTypeText.getText());
            Data.add(addFrequencyClassText.getText());
            Data.add(loginTeacher);
            client.sendCase(6);
            client.SendEditSignal(Data, 16, 7);
        }
    }

    @FXML
    void removeTeacherOfSubject(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(editTeachersOfSubjectsTLoginText.getText().length() > 0 &&
                editTeachersOfSubjectsSubjectNameText.getText().length() > 0){
            Data.add(editTeachersOfSubjectsTLoginText.getText());
            Data.add(editTeachersOfSubjectsSubjectNameText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 17, 2);
        }
    }

    @FXML
    void addTeacherOfSubject(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(editTeachersOfSubjectsTLoginText.getText().length() > 0 &&
                editTeachersOfSubjectsSubjectNameText.getText().length() > 0){
            Data.add(editTeachersOfSubjectsTLoginText.getText());
            Data.add(editTeachersOfSubjectsSubjectNameText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 18, 2);
        }
    }

    @FXML
    void removeSubjectOfClass(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(editSubjectsOfClassesSubjectNameText.getText().length() > 0 &&
                editSubjectsOfClassesClassNameText.getText().length() > 0){
            Data.add(editSubjectsOfClassesSubjectNameText.getText());
            Data.add(editSubjectsOfClassesClassNameText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 19, 2);
        }
    }

    @FXML
    void addSubjectOfClass(ActionEvent event) throws JSONException, IOException {
        List<String> Data = new ArrayList<String>();
        if(editSubjectsOfClassesSubjectNameText.getText().length() > 0 &&
                editSubjectsOfClassesClassNameText.getText().length() > 0){
            Data.add(editSubjectsOfClassesSubjectNameText.getText());
            Data.add(editSubjectsOfClassesClassNameText.getText());
            client.sendCase(6);
            client.SendEditSignal(Data, 20, 2);
        }
    }
}
