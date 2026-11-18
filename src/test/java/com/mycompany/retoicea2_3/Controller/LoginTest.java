package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Usuario;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class LoginTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        MainViewController.logedUser = null;
        new App().start(stage);
        App.setRoot("Login");
    }

    @Test
    public void loginConUsuarioIncorrecto() {
        clickOn("#txtUser").write("usuarioInexistente");
        clickOn("#txtPassword").write("Clave123!");
        clickOn("#btnContinue");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("User or password are incorrect", dialogPane.getContentText());
        assertNull(MainViewController.logedUser);
    }

    @Test
    public void loginConCamposVacios() {
        clickOn("#btnContinue");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("User or password are incorrect", dialogPane.getContentText());
        assertNull(MainViewController.logedUser);
        
    }

    @Test
    public void loginCorrecto() {
        clickOn("#txtUser").write("anaperez");
        clickOn("#txtPassword").write("Ana12345!");
        clickOn("#btnContinue");
        assertEquals("anaperez", MainViewController.logedUser.getUser());
        verifyThat("#products", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#home");

        verifyThat("#products", isVisible());
    }
}
