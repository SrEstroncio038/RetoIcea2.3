/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Purchase;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class UserProfileController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Label lblName;
    @FXML
    private Label lbSname;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblBdate;
    @FXML
    private ListView<Purchase> purchaseList;
    @FXML
    private Button refundButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Purchase> items = FXCollections.observableArrayList();
        for (Purchase p : ((Client) MainViewController.logedUser).getPurchases()) {
            items.add(p);
        }
        purchaseList.setItems(items);
        lblName.setText(((Client) MainViewController.logedUser).getName());
        lbSname.setText(((Client) MainViewController.logedUser).getSurname());
        lblCity.setText(((Client) MainViewController.logedUser).getBirthPlace());
        lblBdate.setText(((Client) MainViewController.logedUser).getBirthDate().toString());
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void refound(ActionEvent event) throws IOException {
         App.setRoot("Refound");
    }
}
