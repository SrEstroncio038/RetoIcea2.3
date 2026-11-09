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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class ViewProductController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblStock;
    @FXML
    private Label lblMaterial;
    @FXML
    private Label lblColor;
    @FXML
    private Label lblPeso;
    @FXML
    private Label lblAlto;
    @FXML
    private Label lblAncho;
    @FXML
    private Button btnAddToCart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblName.setText(MainViewController.selectedProduct.getNombre());
        lblPrice.setText(String.valueOf(MainViewController.selectedProduct.getPrecio()));
        lblStock.setText(String.valueOf(MainViewController.selectedProduct.getStock()));
        lblMaterial.setText(MainViewController.selectedProduct.getCharacteristics().getMaterial());
        lblColor.setText(MainViewController.selectedProduct.getCharacteristics().getColor());
        lblPeso.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getWeight()) + "kg");
        lblAlto.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getHeight()) + "cm");
        lblAncho.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getWidth()) + "cm");
    }

    @FXML
    public void goHome() throws IOException {
        App.setRoot("MainView");
    }
}
