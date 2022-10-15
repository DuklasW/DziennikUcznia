package com.example.zaliczenieklient2;

import javafx.scene.control.CheckBox;

public class FrequencyTable {

    private String login;
    private String id;
    private String name;
    private String surname;

    private CheckBox presents;
    private CheckBox absent;
    private CheckBox exempt;

    FrequencyTable(String fId, String fName, String fSurname, String fLogin, String vPresents, String vAbsent, String vExempt){
        this.id = fId;
        this.name = fName;
        this.surname = fSurname;
        this.login = fLogin;
        this.presents = new CheckBox();
        this.absent = new CheckBox();
        this.exempt = new CheckBox();
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getLogin(){return login;}
    public void setLogin(String login){this.login = login;}

    public CheckBox getPresents(){return presents;}
    public void setPresents(CheckBox presents){this.presents = presents;}

    public CheckBox getAbsent(){return absent;}
    public void setAbsent(CheckBox absent){this.absent = absent;}

    public CheckBox getExempt(){return exempt;}
    public void setExempt(CheckBox exempt){this.exempt = exempt;}
}