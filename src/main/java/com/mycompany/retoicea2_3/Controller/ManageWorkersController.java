package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.*;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Controlador de la vista de gestión de trabajadores.
 * Permite visualizar, modificar, eliminar y registrar
 * trabajadores dentro del sistema.
 *
 * @author G.2
 */
public class ManageWorkersController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnToSignUp;
    @FXML
    private Button btnToRemove;
    @FXML
    private Button btnModify;
    @FXML
    private ListView<String> workers;
    
    public static Worker selectedWorker;
    public static String selectedUserId;
    
    WorkerController workerController = new WorkerController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedWorker=null;
        ObservableList<String> trabajadores = workerController.showAllWorkers();
        workers.setItems(trabajadores);
    }  
    
    @FXML
    public void modify() throws IOException {
        String selectedText = workers.getSelectionModel().getSelectedItem();
        if (selectedText != null) {
            String[] parts = selectedText.split(" ");
            String userId = parts[parts.length - 1];
            selectedUserId=userId;
            selectedWorker = workerController.showOneWorker(userId);
            if (selectedWorker != null) {
                App.setRoot("AddWorker");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Error");
            alert.setContentText("First select a worker.");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void delete() throws IOException{
        String selectedText = workers.getSelectionModel().getSelectedItem();
        if (selectedText != null) {
            String[] parts = selectedText.split(" ");
            String userId = parts[parts.length - 1]; 
            workerController.deleteWorker(userId);
            ObservableList<String> trabajadoresActualizados = workerController.showAllWorkers();
            workers.setItems(trabajadoresActualizados);  
            selectedWorker = null;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Error");
            alert.setContentText("First select a worker.");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void home() throws IOException {
        App.setRoot("MainView");
    }
    
    @FXML
    public void singUp() throws IOException {
        App.setRoot("AddWorker");
    }
    
    @FXML
    public void remove() throws IOException {
        delete();
    }
    
}
