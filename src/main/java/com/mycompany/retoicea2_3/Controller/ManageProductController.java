/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationProductBD;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class ManageProductController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnManageOffers;
    @FXML
    private Button btnToAdd;
    @FXML
    private Button btnToRemove;
    @FXML
    private Button btnModify;
    @FXML
    private ListView<Product> products;
    
    public static Product selectedProductToModify = null;
    
    ProductController productController = new ProductController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductController productController = new ProductController();
        products.setItems(productController.showAllProductos());
    }    
    
    @FXML
    public void home() throws IOException {
        App.setRoot("MainView");
    }
    
    @FXML
    public void offers() throws IOException {
        App.setRoot("CreateOffer");
    }
    
    @FXML
    public void singUp() throws IOException {
        App.setRoot("AddProduct");
    }
    
    @FXML
    public void modify() throws IOException {
        boolean isValid = true;
        selectedProductToModify = products.getSelectionModel().getSelectedItem();
        if (selectedProductToModify == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Error");
            alert.setHeaderText(null);
            alert.setContentText("First select a product.");
            alert.showAndWait();
            isValid = false;
        }

        if (isValid) {
            App.setRoot("AddProduct");
        }
    }
    
    @FXML
    public void remove() {
        boolean isValid = true;
        Product selectedProduct = products.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle("Selection Error");
            alert.setHeaderText(null);
            alert.setContentText("First select a product.");
            alert.showAndWait();
            isValid = false;
        }
        if (isValid) {
            ProductController productController = new ProductController();
            productController.removeProduct(selectedProduct.getNombre());
            products.setItems(productController.showAllProductos());
        }
    }
    
}
