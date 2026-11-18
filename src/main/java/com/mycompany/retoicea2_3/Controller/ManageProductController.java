package com.mycompany.retoicea2_3.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Controlador de la vista de gestión de productos.
 * Permite acceder a las distintas funcionalidades relacionadas
 * con la administración de productos y ofertas.
 *
 * @author G.2
 */
public class ManageProductController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnManageOffers;
    @FXML
    private Button btnToAdd;
    @FXML
    private Button btnToRemove;
    @FXML
    private Button btnModify;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
