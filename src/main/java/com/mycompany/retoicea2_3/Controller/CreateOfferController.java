package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Offer;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Controlador de la vista de gestión de ofertas.
 * Permite crear y eliminar ofertas asociadas a productos,
 * validando los datos introducidos por el usuario.
 *
 * @author G.2
 */
public class CreateOfferController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox<String> cmbProduct;
    @FXML
    private TextField txtDiscount;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private DatePicker date;

    ProductController productController = new ProductController();
    OfferController offerController = new OfferController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> nombresProductos = productController.showAllProductNames();
        cmbProduct.setItems(nombresProductos);
    }

    @FXML
    public void goHome() throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    public void goBack() throws IOException {
        App.setRoot("ManageProduct");
    }

    /**
 * Crea una nueva oferta para el producto seleccionado
 * tras validar los datos introducidos por el usuario.
 * La oferta queda asociada al producto indicado y se
 * almacena en el sistema.
 */
    @FXML
    public void createOffer() {
        boolean correct = true;
        if (cmbProduct.getSelectionModel().getSelectedItem() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("No product selected try again.");
            alerta.showAndWait();
            correct = false;
        }
        if (correct && (!txtDiscount.getText().matches("^\\d+$") || Integer.parseInt(txtDiscount.getText()) <= 0 || Integer.parseInt(txtDiscount.getText()) >= 100)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not a valid discound");
            alert.showAndWait();
            correct = false;
        }
        if (correct && (date.getValue() == null || date.getValue().isBefore(LocalDate.now()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not a valid date");
            alert.showAndWait();
            correct = false;
        }
        if (correct) {
            Product p = productController.showOneProduct(cmbProduct.getSelectionModel().getSelectedItem().split(", precio: ")[0].replace("Producto:", ""));
            Offer o = new Offer(p.getId(), Integer.parseInt(txtDiscount.getText()), date.getValue());
            offerController.addOffer(o);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Offer created");
            alert.setContentText("Offer created successfully");
            alert.showAndWait();
        }
    }

    /**
 * Elimina una oferta asociada al producto seleccionado
 * tras validar los datos introducidos por el usuario.
 */
    @FXML
    public void deleteOffer() {
        Offer o = null;
        boolean correct = true;
        if (cmbProduct.getSelectionModel().getSelectedItem() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("No product selected try again.");
            alerta.showAndWait();
            correct = false;
        }
        if (correct && (!txtDiscount.getText().matches("^\\d+$") || Integer.parseInt(txtDiscount.getText()) <= 0 || Integer.parseInt(txtDiscount.getText()) >= 100)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not a valid discound");
            alert.showAndWait();
            correct = false;
        }
        if (correct && (date.getValue() == null || date.getValue().isBefore(LocalDate.now()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not a valid date");
            alert.showAndWait();
            correct = false;
        }
        if (correct) {
            Product p = productController.showOneProduct(cmbProduct.getSelectionModel().getSelectedItem().split(", precio: ")[0].replace("Producto:", ""));
            o = new Offer(p.getId(), Integer.parseInt(txtDiscount.getText()), date.getValue());
            offerController.deleteOffer(o);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Offer deleted");
            alert.setContentText("Offer deleted successfully");
            alert.showAndWait();
        }
    }

}
