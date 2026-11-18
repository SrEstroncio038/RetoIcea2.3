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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class ViewProductController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnAddToCart;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (MainViewController.selectedProduct != null) {
            txtName.setText(MainViewController.selectedProduct.getNombre());
            txtPrice.setText(String.valueOf(MainViewController.selectedProduct.getPrecio()));
            txtStock.setText(String.valueOf(MainViewController.selectedProduct.getStock()));
            txtMaterial.setText(MainViewController.selectedProduct.getCharacteristics().getMaterial());
            txtColour.setText(MainViewController.selectedProduct.getCharacteristics().getColor());
            txtWeight.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getWeight()));
            txtHigh.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getHeight()));
            txtWidth.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getWidth()));
        }
        txtName.setEditable(false);
        txtPrice.setEditable(false);
        txtStock.setEditable(false);
        txtMaterial.setEditable(false);
        txtColour.setEditable(false);
        txtWeight.setEditable(false);
        txtHigh.setEditable(false);
        txtWidth.setEditable(false);
    }

    public void goHome() throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void addToCart(ActionEvent event) throws IOException {
        Characteristics c = new Characteristics(txtMaterial.getText().trim(), txtColour.getText().trim(),
                Float.parseFloat(txtWeight.getText().trim().replace(',', '.')),
                Float.parseFloat(txtHigh.getText().trim().replace(',', '.')),
                Float.parseFloat(txtWidth.getText().trim().replace(',', '.')));
        Product p = new Product(txtName.getText().trim(), Float.parseFloat(txtPrice.getText().trim().replace(',', '.')),
                Integer.parseInt(txtStock.getText().trim()), false, c);
        ((Client) MainViewController.logedUser).setCart(p);
        MainViewController.selectedProduct = null;
        App.setRoot("MainView");

    }
}
