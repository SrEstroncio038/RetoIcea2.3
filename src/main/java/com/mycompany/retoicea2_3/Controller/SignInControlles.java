/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private TextField birthday;
    @FXML
    private PasswordField passwrd;
    @FXML
    private PasswordField rpasswrd;
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
    public void home() throws IOException {
        App.setRoot("MainView");
    }
}
