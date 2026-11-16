/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Purchase;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class ConfirmPurchaseController implements Initializable {

    private PurchaseController purchaseController = new PurchaseController();

    @FXML
    private Button btnHome;
    @FXML
    private ListView<Product> lvCart;
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

    public float total = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Product> o = FXCollections.observableArrayList();
        for (Product p : ((Client) MainViewController.logedUser).getCart()) {
            total += p.getPrecio();
            o.add(p);
        }
        lvCart.setItems(o);
    }

    @FXML
    public void confirmPurcahse() throws IOException {
        boolean correct = true;
        if (!txtNameSurname.getText().matches("[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+(?:[ '-][A-Za-zÁÉÍÓÚáéíóúÑñÜü]+)+") && correct) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not a valid name");
            alert.showAndWait();
            correct = false;
        }
        if (correct && !txtCardNumber.getText().matches("\\d{16}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not a valid card number");
            alert.showAndWait();
            correct = false;
        }
        if (correct && !txtExpireDate.getText().matches("(0[1-9]|1[0-2])/\\d{2}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not a valid expaiering date");
            alert.showAndWait();
            correct = false;
        }
        if (correct && !txtCvv.getText().matches("\\d{3}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not a valid Cvv");
            alert.showAndWait();
            correct = false;
        }
        if (correct) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Purchase confirmed.");
            alert.showAndWait();
            Purchase p = new Purchase(new ObjectId(), LocalDate.now(), LocalDate.now().plusDays(5), total, ((Client) MainViewController.logedUser).getAddress(), ((Client) MainViewController.logedUser).getCart());
            ((Client) MainViewController.logedUser).setPurchases(p);
            purchaseController.createPurchase(p);
            ((Client) MainViewController.logedUser).deleteCart();
            ((Client) MainViewController.logedUser).setPurchases(p);
            App.setRoot("MainView");
        }
    }

    @FXML
    public void goHome() throws IOException {
        App.setRoot("MainView"); 
    }

}
