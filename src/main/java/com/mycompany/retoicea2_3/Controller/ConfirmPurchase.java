/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class ConfirmPurchase implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private ListView<?> lvCart;
    @FXML
    private TextField txtNameSurname;
    @FXML
    private TextField txtCardNumber;
    @FXML
    private TextField txtExpireDate;
    @FXML
    private TextField txtCvv;
    @FXML
    private Button btnConfirmPurchase;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
