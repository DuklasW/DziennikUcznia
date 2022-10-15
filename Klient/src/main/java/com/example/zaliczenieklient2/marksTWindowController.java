package com.example.zaliczenieklient2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class marksTWindowController implements Initializable {

    @FXML
    private TextField Tdiarynr;

    @FXML
    private TextField Tname;

    @FXML
    private ChoiceBox<String> classChoiceBox;

    @FXML
    private ChoiceBox<String> markChoiceBox;

    @FXML
    private ChoiceBox<String> subjectChoiceBox;

    @FXML
    private Text errorText;

    @FXML
    private Text CLICK;

    private Client client;
    private JSONObject serwer;

    String loginTeacher;

    SendDataToContoller data = SendDataToContoller.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            client = data.getClient();
            client.sendCase(5);
            serwer = client.getData();
            int classcount = serwer.optInt("classcount");
            for(int i=0;i<classcount;i++){
                serwer = client.getData();
                classChoiceBox.getItems().add(serwer.optString("class"));
            }
            serwer = client.getData();
            int subjectcount = serwer.optInt("subjectcount");
            for(int i=0;i<subjectcount;i++){
                serwer = client.getData();
                subjectChoiceBox.getItems().add(serwer.optString("subject"));
            }
            markChoiceBox.getItems().addAll("0","1","1.5","2","2.5","3","3.5","4","4.5","5","5.5","6");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addmarkButton(ActionEvent event) throws JSONException, IOException {
        if(Tname.getText().length() > 0 && markChoiceBox.getValue().length() > 0 && Tdiarynr.getText().length() > 0 &&
                classChoiceBox.getValue().length() > 0 && subjectChoiceBox.getValue().length() > 0){
            client.sendCase(9);
            client.SendString(subjectChoiceBox.getValue());
            serwer = client.getData();
            String test = serwer.optString("boolean");
            loginTeacher = serwer.optString("loginN");
            if(test.equals("Yes")) {
                client.sendCase(11);
                client.SendString(classChoiceBox.getValue());
                serwer = client.getData();
                test = serwer.optString("boolean");
                if(test.equals("Yes")) {
                    List<String> Data = new ArrayList<>();
                    Data.add(Tname.getText());
                    Data.add(markChoiceBox.getValue());
                    Data.add(Tdiarynr.getText());
                    Data.add(classChoiceBox.getValue());
                    Data.add(subjectChoiceBox.getValue());
                    Data.add(loginTeacher);
                    Data.add(LocalDate.now().toString());
                    client.sendCase(6);
                    client.SendEditSignal(Data, 10,7);
                    errorText.setText("Dodano ocenę!");
                }
                else{
                    errorText.setText("Nie uczysz takiej klasy!");
                }
            }
            else{
                errorText.setText("Nie prowadzisz takiego przedmiotu!");
            }
        }
        else{
            errorText.setText("Uzupełnij pola!");
        }
    }
}
