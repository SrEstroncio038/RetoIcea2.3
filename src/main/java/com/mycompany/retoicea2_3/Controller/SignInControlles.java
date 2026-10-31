/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class SignInControlles implements Initializable {

    @FXML
    private Button home;
    @FXML
    private TextField user;
    @FXML
    private TextField name;
    @FXML
    private TextField sname;
    @FXML
    private TextField addres;
    @FXML
    private TextField city;
    @FXML
    private PasswordField passwrd;
    @FXML
    private PasswordField confirmPasswrd;
    @FXML
    private DatePicker birthday;
    @FXML
    private Button register;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        boolean correct = true;
        if (!name.getText().matches("^[a-zA-Z]+$") || name.getText() == null) {
            correct = false;
            //popup
        } else if (user.getText() == null) {
            correct = false;
            //popup
        } else if (!sname.getText().matches("^[a-zA-Z]+$") || sname.getText() == null) {
            correct = false;
            //popup
        } else if (!city.getText().matches("^[a-zA-Z]+$") || city.getText() == null) {
            correct = false;
            //popup
        } else if (birthday.getValue() == null) {
            correct = false;
            //popup
        } else if (!passwrd.getText().matches("^[a-zA-Z ]+,\\s*(\\d+),\\s*(\\d+),\\s*([a-dA-D])$") || passwrd.getText() == null) {
            correct = false;
            //popup
        }
        if (!confirmPasswrd.getText().equals(passwrd.getText())) {
            correct = false;
            //popup
        }
        if (addres.getText().matches("^[a-zA-Z ]+,\\s*(\\d+),\\s*(\\d+),\\s*([A-D])$")) {

        }
        if (correct) {
            Addres a = new Addres(Integer.parseInt(addres.getText().split(",")[1]), addres.getText().split(",")[0], Integer.parseInt(addres.getText().split(",")[2]), addres.getText().split(",")[3].charAt(0));
            Client c = new Client(name.getText(), sname.getText(), city.getText(), passwrd.getText(), user.getText(), birthday.getValue(), a);
            //añadir cliente
            App.setRoot("Login");
        }
    }

}
