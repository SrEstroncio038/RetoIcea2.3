package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador para la vista de añadir de trabajadores.
 * Gestiona la interfaz y acciones asociadas al registro de nuevos empleados en la aplicación.
 * 
 * @author G.2
 */
public class AddWorkerController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    @FXML
    public void save() throws IOException{
        App.setRoot("ManageWorkers");
    }  
}
