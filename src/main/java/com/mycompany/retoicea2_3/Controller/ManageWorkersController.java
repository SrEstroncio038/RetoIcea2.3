package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.*;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author asola
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
    private ListView<String> workerList;
    
    public static Worker selectedWorker;
    
    WorkerController workerController = new WorkerController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedWorker=null;
        ObservableList<String> workers = workerController.showAllWorkers();
    
        workerList.setItems(workers);
    }  
    
    @FXML
    public void home() throws IOException {
        App.setRoot("MainView");
    }
    
    @FXML
    public void singUp() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    public void remove() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    public void modify() throws IOException {
        App.setRoot("AddWorker");
    }
    
}
