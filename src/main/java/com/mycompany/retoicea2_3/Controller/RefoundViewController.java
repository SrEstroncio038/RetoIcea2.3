package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Purchase;
import com.mycompany.retoicea2_3.Model.Refounds;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.bson.types.ObjectId;

public class RefoundViewController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnRefund;
    @FXML
    private ListView<Product> listProduct;

    RefoundController rc = new RefoundController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Product> items = FXCollections.observableArrayList();
        for (Purchase p : ((Client) MainViewController.logedUser).getPurchases()) {
            for (Product pr : p.getBoughtProducts()) {
                items.add(pr);
            }
        }
        listProduct.setItems(items);
        listProduct.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void refound() {

        ObservableList<Product> selected = listProduct.getSelectionModel().getSelectedItems();
        if (selected.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Select at least one product to refund.");
            alerta.showAndWait();
        } else {
            ArrayList<Product> refundProducts = new ArrayList<>();
            double total = 0;
            for (Product pr : selected) {
                refundProducts.add(new Product(pr.getId(), pr.getNombre(), pr.getPrecio(), pr.getStock(), pr.isSeminuevo(), pr.getCharacteristics()));
                total += pr.getPrecio();
            }
            Refounds r = new Refounds(new ObjectId(), LocalDate.now(), total, refundProducts);
            rc.createRefound(r);
            listProduct.getItems().removeAll(new ArrayList<>(selected));
            ((Client) MainViewController.logedUser).setRefounds(r);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Product refounded: ");
            alerta.showAndWait();
        }
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        App.setRoot("UserProfile");
    }
}
