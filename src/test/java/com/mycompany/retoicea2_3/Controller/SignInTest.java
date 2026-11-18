package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class SignInTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        new App().start(stage);
        App.setRoot("SignIn");
    }

    @Test
    public void popUpNombreInvalido() {
        clickOn("#name").write("123");
        clickOn("#addres").write("Calle Mayor, 12, 3, A");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid name.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpNombreVacio() {
        clickOn("#addres").write("Calle Mayor, 12, 3, A");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid name.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpApellidoInvalido() {
        clickOn("#name").write("Juan");
        clickOn("#user").write("nuevo_usuario");
        clickOn("#sname").write("123");
        clickOn("#addres").write("Calle Mayor, 12, 3, A");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid surname.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpCiudadInvalida() {
        clickOn("#name").write("Juan");
        clickOn("#user").write("nuevo_usuario");
        clickOn("#sname").write("Perez");
        clickOn("#city").write("123");
        clickOn("#addres").write("Calle Mayor, 12, 3, A");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid city.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpFechaVacia() {
        clickOn("#name").write("Juan");
        clickOn("#user").write("nuevo_usuario");
        clickOn("#sname").write("Perez");
        clickOn("#city").write("Bilbao");
        clickOn("#addres").write("Calle Mayor, 12, 3, A");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid date.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpPasswordInvalida() {
        clickOn("#name").write("Juan");
        clickOn("#user").write("nuevo_usuario");
        clickOn("#sname").write("Perez");
        clickOn("#city").write("Bilbao");
        clickOn("#birthday").write("01/01/2000").push(KeyCode.ENTER);
        clickOn("#passwrd").write("1234");
        clickOn("#rpasswrd").write("1234");
        clickOn("#addres").write("Calle Mayor, 12, 3, A");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid password.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpPasswordNoCoincide() {
        clickOn("#name").write("Juan");
        clickOn("#user").write("nuevo_usuario");
        clickOn("#sname").write("Perez");
        clickOn("#city").write("Bilbao");
        clickOn("#birthday").write("01/01/2000").push(KeyCode.ENTER);
        clickOn("#passwrd").write("Pass1234!");
        clickOn("#rpasswrd").write("OtraPass123!");
        clickOn("#addres").write("Calle Mayor, 12, 3, A");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Password doesn't match.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpDireccionInvalida() {
        clickOn("#name").write("Juan");
        clickOn("#user").write("nuevo_usuario");
        clickOn("#sname").write("Perez");
        clickOn("#city").write("Bilbao");
        clickOn("#birthday").write("01/01/2000").push(KeyCode.ENTER);
        clickOn("#passwrd").write("Pass1234!");
        clickOn("#rpasswrd").write("Pass1234!");
        clickOn("#addres").write("Direccion sin formato");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid addres.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpUsuarioYaExiste() {
        clickOn("#name").write("Lucia");
        clickOn("#user").write("client_lucia");
        clickOn("#sname").write("Martinez");
        clickOn("#city").write("Madrid");
        clickOn("#birthday").write("05/12/1998").push(KeyCode.ENTER);
        clickOn("#passwrd").write("Pass1234!");
        clickOn("#rpasswrd").write("Pass1234!");
        clickOn("#addres").write("Calle Sol,8,0,C");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Username already exists.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void registroExitosoCambioVentana() {
        clickOn("#name").write("Juan");
        clickOn("#user").write("client_test_" + System.currentTimeMillis());
        clickOn("#sname").write("Perez");
        clickOn("#city").write("Bilbao");
        clickOn("#birthday").write("01/01/2000").push(KeyCode.ENTER);
        clickOn("#passwrd").write("Pass1234!");
        clickOn("#rpasswrd").write("Pass1234!");
        clickOn("#addres").write("Calle Mayor,12,3,A");
        clickOn("#register");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("SignIn successfully complited.", dialogPane.getContentText());
        clickOn("Aceptar");
        verifyThat("#txtUser", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#home");
        verifyThat("#products", isVisible());
    }
}
