package com.example.zaliczenieklient2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable{
    @FXML
    private Label errorUsername;//pusty login
    @FXML
    private Label errorPassword;//puste haslo
    @FXML
    private TextField aLogin; //login z aplikacji
    @FXML
    protected TextField aPassword; //haslo z aplikacji
    @FXML
    private Label labelError;


    @FXML
    private Label connect;

    @FXML
    private ImageView redRefresh;

    @FXML
    private ImageView limeRefresh;

    private Client client = null;

    SendDataToContoller data = SendDataToContoller.getInstance();

    @FXML
    protected void logInButton() {

        try {
            if (aLogin.getLength() < 1) errorUsername.setText("Prosze uzupelnic login!");
            else {
                errorUsername.setText("");
                labelError.setText("");
            }
            if (aPassword.getLength() < 1) errorPassword.setText("Prosze uzupelnic haslo!");
            else {
                errorPassword.setText("");
                labelError.setText("");
            }
            if (client != null) {//sprawdza polaczenie z serwerem, jesli nie ma to nic nie robi

                System.out.println("Login: " + aLogin.getText() + ", Password: " + aPassword.getText());

                if (aPassword.getText().length() >= 1 && aLogin.getText().length() >= 1) {
                    if (client.sendLogPassToSerwer(aLogin.getText(), aPassword.getText())) {//wysyla login i haslo do serwera
                        System.out.println("wyslano");
                        disconnected();
                        client = null;
                    } else {
                        if (client.authorizationLogin()) {//sprawdza czy wpisalismy poprawny login i haslo
                            System.out.println("Zalogowal sie uzytkownik: " + aLogin.getText());
                            String acces = client.getData().optString("rola");
                            data.setClient(client);
                            if (client != null) {
                                if (acces.equals("UCZEN") || acces.equals("RODZIC")) {
                                    Application.setRoot("appWindow");
                                } else {
                                    Application.setRoot("appTWindow");
                                }
                            }
                        } else labelError.setText("Podany login i/lub haslo sa nieprawidlowe");
                    }
                }
            } else disconnected();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void refreshButton(){
        try {
            if (client == null) {
                connected();
            }
        }catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            connected();

        }catch (IOException e) {
            disconnected();
            e.printStackTrace();
        }
    }

    @FXML
    public void closeApplication() {
        if(client != null) client.closeEvrything(client.getSocket(), client.getBw(), client.getBr());
        Platform.exit();
    }

    public void connected() throws IOException{
        client = new Client(new Socket("localhost", 8081));
        System.out.println("connected to server.");
        connect.setTextFill(Paint.valueOf("lime"));
        labelError.setText("");
        connect.setText("Connected");
        redRefresh.setOpacity(0);
        limeRefresh.setOpacity(1);
    }
    public void disconnected(){
        connect.setTextFill(Paint.valueOf("red"));
        connect.setText("Disconnected");
        labelError.setText("Prosze sprawdzic polaczenie z serwerem!");
        limeRefresh.setOpacity(0);
        redRefresh.setOpacity(1);
    }
}