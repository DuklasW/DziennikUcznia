package com.example.zaliczenieklient2;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class Client {

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;

    private JSONObject json;

    public Client(Socket socket) {//Constructor
        try {
            this.socket = socket;
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println(("Error creating client"));
            e.printStackTrace();
            closeEvrything(socket, bw, br);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    public JSONObject getData(){
        JSONObject serwer = null;
        try {
            serwer = new JSONObject(br.readLine());
        }catch (IOException | JSONException e){e.printStackTrace();}
        return serwer;
    }
    public void SendString(String data){
        try{
            json = new JSONObject();
            json.put("data", data);
            bw.write(json.toString());
            bw.newLine();
            bw.flush();
        }catch(IOException | JSONException e){e.printStackTrace();}
    }

    public void sendLessionPlan(Integer godzina, String klasa, String przedmiot, LocalDate date){
        try {
            json = new JSONObject();
            json.put("godzina", godzina);
            json.put("klasa", klasa);
            json.put("przedmiot", przedmiot);
            json.put("date", date);
            bw.write(json.toString());
            bw.newLine();
            bw.flush();
        } catch (IOException | JSONException e) {
        e.printStackTrace();
    }
    }
    public void sendFrequency(String przedmiot, String loginS, LocalDate data, int godzina, String rodzaj, String klasa) {
        try {
            json = new JSONObject();
            json.put("przedmiot", przedmiot);
            json.put("loginS", loginS);
            json.put("LocalDate", data);
            json.put("godzina", godzina);
            json.put("rodzaj", rodzaj);
            json.put("klasa", klasa);
            bw.write(json.toString());
            bw.newLine();
            bw.flush();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void SendEditSignal(List<String> Data, int i, int n) throws JSONException, IOException {
        json = new JSONObject();
        for(int j=0;j<n;j++){
            json.put("data" + j, Data.get(j));
        }
        json.put("whichEdit", i);
        bw.write(json.toString());
        bw.newLine();
        bw.flush();
    }

    public boolean sendLogPassToSerwer(String login, String password){//Wysyla login i haslo w JONSONie do serwera, po wcisnieciu przycisku zaloguj
        try{
            json = new JSONObject();
            json.put("login", login);
            json.put("haslo", password);
            bw.write(json.toString());
            bw.newLine();
            bw.flush();
            return false;
        }catch(IOException | JSONException e){
            e.printStackTrace();
            closeEvrything(socket, bw, br);
            System.out.println("Error to send login&pass(json) to the serwer");
            return true;
        }
    }

    public boolean authorizationLogin(){
        try {
            JSONObject serwer = new JSONObject(br.readLine());
            if (serwer.optString("status").equals("accept")) return true;
            else return false;
        }catch(IOException | JSONException e){e.printStackTrace();}
        return false;
    }

    public void closeEvrything(Socket socket, BufferedWriter bw, BufferedReader br){
        try{
            if(br != null) br.close();
            if(bw != null) bw.close();
            if(socket != null) socket.close();
        }catch(IOException e){e.printStackTrace();}
    }

    public void sendCase(int i) throws JSONException, IOException {
        json = new JSONObject();
        json.put("cases", i);
        bw.write(json.toString());
        bw.newLine();
        bw.flush();
    }

    public void sendMonday(String monday) throws JSONException, IOException {
        json = new JSONObject();
        json.put("monday", monday);
        bw.write(json.toString());
        bw.newLine();
        bw.flush();
    }
}