package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Usuario;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.LoggerClass;

public class LoginController implements Initializable {
    private static final Logger LOGGER = LoggerClass.getLogger();
    @FXML
    private Button home;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnContinue;

    UserController userController = new UserController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private Usuario logear(String user, String password) {

        for (Usuario u : userController.obtainUser()) {
            if (u.getUser().equals(user) && u.getPasword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    @FXML
    public void login() throws IOException {

        Usuario usuario = logear(
                txtUser.getText(),
                txtPassword.getText()
        );

        if (usuario == null) {
            LOGGER.log(Level.WARNING, "Intento de login fallido: {0}", txtUser.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("User or password are incorrect");
            alert.showAndWait();
        } else {
            LOGGER.log(Level.INFO, "Login correcto: {0}", usuario.getUser());
            MainViewController.logedUser = usuario;
            App.setRoot("MainView");
        }
    }

    @FXML
    public void goHome() throws IOException {
        App.setRoot("MainView");
    }

}
