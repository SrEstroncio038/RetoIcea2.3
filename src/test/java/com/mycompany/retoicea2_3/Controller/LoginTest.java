package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
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

    @AfterEach
    public void limpiarEstado() {
        MainViewController.logedUser = null;
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
        clickOn("Aceptar");
    }

    @Test
    public void loginConCamposVacios() {
        clickOn("#btnContinue");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("User or password are incorrect", dialogPane.getContentText());
        assertNull(MainViewController.logedUser);
        clickOn("Aceptar");
    }

    @Test
    public void loginCorrecto() {
        clickOn("#txtUser").write("admin_elena");
        clickOn("#txtPassword").write("AdminPassword987");
        clickOn("#btnContinue");
        assertNotNull(MainViewController.logedUser);
        assertEquals("admin_elena", MainViewController.logedUser.getUser());
        verifyThat("#products", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#home");
        verifyThat("#products", isVisible());
    }
}
