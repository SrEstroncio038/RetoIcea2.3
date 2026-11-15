package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationUserBD;
import com.mycompany.retoicea2_3.Dao.UserDAO;
import com.mycompany.retoicea2_3.Model.Usuario;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

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
            System.out.println("Usuario o contraseña incorrectos");
        } else {
            MainViewController.logedUser = usuario;
            App.setRoot("MainView");
        }
    }

}
