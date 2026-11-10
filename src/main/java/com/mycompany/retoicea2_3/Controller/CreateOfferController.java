/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Offer;
import com.mycompany.retoicea2_3.Model.Product;
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
 * FXML Controller class
 *
 * @author asola
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> productos = productController.showAllProductos();
        cmbProduct.setItems(productos);

    }

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

    @FXML
    public void deleteOffer() {
        Product p = productController.showOneProduct(cmbProduct.getSelectionModel().getSelectedItem().split(", precio: ")[0].replace("Producto:", ""));
        Offer o = new Offer(p.getId(), Integer.parseInt(txtDiscount.getText()), date.getValue());
        if(offerController.showOneOffer(o) == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Offer not found");
           ;
        }else{
            offerController.deleteOffer(o);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Offer deleted");
            alert.setContentText("Offer deleted successfully");
            alert.showAndWait();
        }
    }
}
