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
 * Controlador para la vista de añadir de productos. Gestiona la interacción del
 * usuario para registrar nuevos productos en el sistema, recopilando sus
 * características y validando su existencia previa.
 *
 * @author G.2
 */
public class AddProductController implements Initializable {

    @FXML
    private Button btnHome;
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

        txtName.setText(MainViewController.selectedProduct.getNombre());
        txtPrice.setText(String.valueOf(MainViewController.selectedProduct.getPrecio()));
        txtStock.setText(String.valueOf(MainViewController.selectedProduct.getStock()));
        txtMaterial.setText(MainViewController.selectedProduct.getCharacteristics().getMaterial());
        txtColour.setText(MainViewController.selectedProduct.getCharacteristics().getColor());
        txtWeight.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getWeight()) + "kg");
        txtHigh.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getHeight()) + "cm");
        txtWidth.setText(String.valueOf(MainViewController.selectedProduct.getCharacteristics().getWidth()) + "cm");
        txtName.setEditable(false);
        txtPrice.setEditable(false);
        txtStock.setEditable(false);
        txtMaterial.setEditable(false);
        txtColour.setEditable(false);
        txtWeight.setEditable(false);
        txtHigh.setEditable(false);
        txtWidth.setEditable(false);
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    private void back(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void addProduct() {
        boolean correct = true;

        if (correct && !txtName.getText().trim().matches("^[\\p{L}0-9][\\p{L}0-9 .,'\\-]{1,49}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setContentText("Invalid name.");
            alert.showAndWait();
            correct = false;
        }
        if (correct && (!txtPrice.getText().trim().matches("^\\d{1,7}([.,]\\d{1,2})?$")
                || Float.parseFloat(txtPrice.getText().trim().replace(',', '.')) <= 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setContentText("Invalid price.");
            alert.showAndWait();
            correct = false;
        }
        if (correct && !txtStock.getText().trim().matches("^\\d{1,6}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setContentText("Invalid stock.");
            alert.showAndWait();
            correct = false;
        }
        if (correct && !txtMaterial.getText().trim().matches("^\\p{L}[\\p{L} \\-]{1,29}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setContentText("Invalid material.");
            alert.showAndWait();
            correct = false;
        }
        if (correct && !txtColour.getText().trim().matches("^\\p{L}[\\p{L} \\-]{1,19}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setContentText("Invalid colour.");
            alert.showAndWait();
            correct = false;
        }
        if (correct && (!txtWeight.getText().trim().matches("^\\d{1,5}([.,]\\d{1,2})?$")
                || Float.parseFloat(txtWeight.getText().trim().replace(',', '.')) <= 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setContentText("Invalid weight.");
            alert.showAndWait();
            correct = false;
        }
        if (correct && (!txtHigh.getText().trim().matches("^\\d{1,5}([.,]\\d{1,2})?$")
                || Float.parseFloat(txtHigh.getText().trim().replace(',', '.')) <= 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setContentText("Invalid height.");
            alert.showAndWait();
            correct = false;
        }
        if (correct && (!txtWidth.getText().trim().matches("^\\d{1,5}([.,]\\d{1,2})?$")
                || Float.parseFloat(txtWidth.getText().trim().replace(',', '.')) <= 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setContentText("Invalid width.");
            alert.showAndWait();
            correct = false;
        }

        if (correct) {
            Characteristics c = new Characteristics(
                    txtMaterial.getText().trim(),
                    txtColour.getText().trim(),
                    Float.parseFloat(txtWeight.getText().trim().replace(',', '.')),
                    Float.parseFloat(txtHigh.getText().trim().replace(',', '.')),
                    Float.parseFloat(txtWidth.getText().trim().replace(',', '.')));
            Product p = new Product(
                    txtName.getText().trim(),
                    Float.parseFloat(txtPrice.getText().trim().replace(',', '.')),
                    Integer.parseInt(txtStock.getText().trim()),
                    chkPreOwned.isSelected(), c);
            try {
                productController.addProduct(p);
            } catch (ProductExistsException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Product Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

}
