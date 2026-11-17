package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Controlador para la vista de añadir de productos.
 * Gestiona la interacción del usuario para registrar nuevos productos en el sistema,
 * recopilando sus características y validando su existencia previa.
 * 
 * @author G.2
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        try {
            productController.addProduct(p);
        } catch (ProductExistsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

}
