/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class AddProductController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtMaterial;
    @FXML
    private TextField txtColour;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtHigh;
    @FXML
    private TextField txtWidth;
    @FXML
    private CheckBox chkPreOwned;
    @FXML
    private Button btnSave;

    ProductController productController = new ProductController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chkPreOwned.setVisible(false);
        if (MainViewController.logedUser instanceof Client) {
            chkPreOwned.setSelected(true);
        } else {
            chkPreOwned.setSelected(false);
        }
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void addProduct() {
        Characteristics c = new Characteristics(txtMaterial.getText(), txtColour.getText(), Float.valueOf(txtWeight.getText()), Float.valueOf(txtHigh.getText()), Float.valueOf(txtWidth.getText()));
        Product p = new Product(txtName.getText(), Float.parseFloat(txtPrice.getText()), Integer.parseInt(txtStock.getText()), chkPreOwned.isSelected(), c);
        productController.addProduct(p);
    }

}
