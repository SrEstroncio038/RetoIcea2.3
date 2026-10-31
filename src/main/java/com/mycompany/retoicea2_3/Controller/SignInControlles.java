/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Usuario;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class SignInControlles implements Initializable {

    @FXML
    private Button home;
    @FXML
    private TextField user;
    @FXML
    private TextField name;
    @FXML
    private TextField sname;
    @FXML
    private TextField addres;
    @FXML
    private TextField city;
    @FXML
    private PasswordField passwrd;
    @FXML
    private PasswordField rpasswrd;
    @FXML
    private DatePicker birthday;
    @FXML
    private Button register;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        App.setRoot("MainView");
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if (!name.getText().matches("^[a-zA-Z]+$") || name == null) {
                // 1. Crear el Popup y la etiqueta
        Popup popup = new Popup();
        Label label = new Label("¡Nombre no correcto!");
        
        // 2. Darle un estilo visual (Opcional, pero recomendado para que parezca una alerta)
        label.setStyle("-fx-background-color: #ffcccc; " +  // Fondo rojo claro
                       "-fx-text-fill: #cc0000; " +        // Texto rojo oscuro
                       "-fx-padding: 10px; " +             // Margen interno
                       "-fx-border-color: #cc0000; " +      // Borde rojo
                       "-fx-border-radius: 5px; " +
                       "-fx-background-radius: 5px;");

        // 3. Añadir la etiqueta al contenido del popup
        popup.getContent().add(label);
        popup.setAutoHide(true); 
        

        Node miNodo = (Node) event.getSource();
        Stage ventanaActual = (Stage) miNodo.getScene().getWindow();
        popup.show(ventanaActual);
        }
        Usuario cliente = new Client(name.getText(), sname.getText(), city.getText(), passwrd.getText(), user.getText(), birthday.getValue());
        // añadir en la bd
        App.setRoot("Login");
    }

}
