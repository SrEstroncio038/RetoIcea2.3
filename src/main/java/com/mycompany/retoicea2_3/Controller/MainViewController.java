/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.*;
import com.mycompany.retoicea2_3.Model.Usuario;
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
public class MainViewController implements Initializable {

    @FXML
    private Button profile;
    @FXML
    private Button mworker;
    @FXML
    private Button mProduct;
    @FXML
    private Button logout;

    public static Usuario u = null;
    @FXML
    private Button singin;
    @FXML
    private Button login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mProduct.setVisible(false);
        profile.setVisible(false);
        mworker.setVisible(false);
        logout.setVisible(false);
        if (u != null) {
            if (u instanceof Admin) {
                mProduct.setVisible(true);
            } else if (u instanceof Client) {
                profile.setText(u.getName());
                profile.setVisible(true);
            } else if (u instanceof Worker) {
                mProduct.setVisible(false);
            }
        }
    }

    @FXML
    public void logIn() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void SignIn() throws IOException {
        App.setRoot("SignIn");
    }

}
