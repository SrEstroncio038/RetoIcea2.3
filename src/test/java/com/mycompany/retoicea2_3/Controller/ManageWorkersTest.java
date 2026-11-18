package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class ManageWorkersTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        new App().start(stage);
        App.setRoot("ManageWorkers");
    }

    @Test
    public void cargarTrabajadores() {
        ObservableList<String> trabajadores = lookup("#workers").queryAs(ListView.class).getItems();
        String[] esperados = {
            "Ana Sola --> 507f1f77bcf86cd799439012",
            "David Gómez --> 507f1f77bcf86cd799439015",
            "Sofía Blanco --> 507f1f77bcf86cd799439016"
        };
        for (String esperado : esperados) {
            assertTrue(trabajadores.contains(esperado));
        }
    }

    @Test
    public void popUpModificarSinSeleccion() {
        clickOn("#btnModify");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("First select a worker.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpBorrarSinSeleccion() {
        clickOn("#btnToRemove");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("First select a worker.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void modificarCambioVentana() {
        clickOn("Ana Sola --> 507f1f77bcf86cd799439012");
        clickOn("#btnModify");
        assertNotNull(ManageWorkersController.selectedWorker);
        verifyThat("#btnSave", isVisible());
    }

    @Test
    public void borrarTrabajador() {
        clickOn("Jorge Navarro --> 507f1f77bcf86cd799439017");
        clickOn("#btnToRemove");
        assertNull(ManageWorkersController.selectedWorker);

        ObservableList<String> trabajadores = lookup("#workers").queryAs(ListView.class).getItems();
        assertFalse(trabajadores.contains("Jorge Navarro --> 507f1f77bcf86cd799439017"));
    }

    @Test
    public void goSignUp() {
        clickOn("#btnToSignUp");
        verifyThat("#btnSave", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#btnHome");
        verifyThat("#products", isVisible());
    }
}
