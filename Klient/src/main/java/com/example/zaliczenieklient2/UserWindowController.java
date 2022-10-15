package com.example.zaliczenieklient2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class UserWindowController implements Initializable {
    @FXML
    private Text name;
    @FXML
    private Text surname;
    @FXML
    private Text parentname;
    @FXML
    private Text parentsurname;
    @FXML
    private Text studentclass;
    @FXML
    private Text diarynumber;

    private Client client;
    private JSONObject serwer;

    SendDataToContoller data = SendDataToContoller.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        client = data.getClient();
        serwer = client.getData();
        
        String Sname = serwer.optString("imie");
        String Ssurname = serwer.optString("nazwisko");
        String Spname = serwer.optString("imie1");
        String Spsurname = serwer.optString("nazwisko1");
        String Sstudentclass = serwer.optString("klasa");
        String Sdiarynumber = serwer.optString("numer");
        
        name.setText(Sname);
        surname.setText(Ssurname);
        parentname.setText(Spname);
        parentsurname.setText(Spsurname);
        studentclass.setText(Sstudentclass);
        diarynumber.setText(Sdiarynumber);
    }
}
