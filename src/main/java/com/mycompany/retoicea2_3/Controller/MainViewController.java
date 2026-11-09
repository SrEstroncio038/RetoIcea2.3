/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.*;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Usuario;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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

    public static Usuario logedUser;

    public static Product selectedProduct;

    @FXML
    private Button singin;
    @FXML
    private Button login;
    @FXML
    private ListView<String> products;

    ProductController productController = new ProductController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedProduct = null;
        ObservableList<String> productos = productController.showAllProductos();
        products.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectedProduct = productController.showOneProduct(products.getSelectionModel().getSelectedItem().split(", precio: ")[0].replace("Producto:", ""));
                if (selectedProduct != null) {
                    try {
                        App.setRoot("ViewProduct");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        products.setItems(productos);
        mProduct.setVisible(false);
        profile.setVisible(false);
        mworker.setVisible(false);
        logout.setVisible(false);
        if (logedUser != null) {
            if (logedUser instanceof Admin) {
                mProduct.setVisible(true);
            } else if (logedUser instanceof Client) {
                profile.setText(logedUser.getName());
                profile.setVisible(true);
            } else if (logedUser instanceof Worker) {
                mProduct.setVisible(false);
            }
        }
    }

    @FXML
    public void logIn() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void signIn() throws IOException {
        App.setRoot("SignIn");
    }

    @FXML
    public void logOut() throws IOException {
        logedUser = null;
        App.setRoot("MainView");
    }

}
