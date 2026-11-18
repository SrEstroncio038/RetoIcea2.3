package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.time.LocalDate;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class AddProductTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        new App().start(stage);
        App.setRoot("AddProduct");
    }

    @Test
    public void popUpNombre() {
        clickOn("#txtName").write("");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid name.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpNombrePrecioMal() {
        clickOn("#txtName").write("");
        clickOn("#txtPrice").write("a");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid name.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpPrice() {
        clickOn("#txtName").write("Silla de roble");
        clickOn("#txtPrice").write("a");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid price.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpStock() {
        clickOn("#txtName").write("Silla de roble");
        clickOn("#txtPrice").write("19,99");
        clickOn("#txtStock").write("-1");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid stock.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpMaterial() {
        clickOn("#txtName").write("Silla de roble");
        clickOn("#txtPrice").write("19,99");
        clickOn("#txtStock").write("1");
        clickOn("#txtMaterial").write("a");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid material.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpColor() {
        clickOn("#txtName").write("Silla de roble");
        clickOn("#txtPrice").write("19,99");
        clickOn("#txtStock").write("1");
        clickOn("#txtMaterial").write("Roble");
        clickOn("#txtColour").write("1");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid colour.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpPeso() {
        clickOn("#txtName").write("Silla de roble");
        clickOn("#txtPrice").write("19,99");
        clickOn("#txtStock").write("1");
        clickOn("#txtMaterial").write("Roble");
        clickOn("#txtColour").write("Rojo");
        clickOn("#txtWeight").write("0");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid weight.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpAltura() {
        clickOn("#txtName").write("Silla de roble");
        clickOn("#txtPrice").write("19,99");
        clickOn("#txtStock").write("1");
        clickOn("#txtMaterial").write("Roble");
        clickOn("#txtColour").write("Rojo");
        clickOn("#txtWeight").write("10");
        clickOn("#txtHigh").write("0");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid height.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpAncho() {
        clickOn("#txtName").write("Silla de roble");
        clickOn("#txtPrice").write("19,99");
        clickOn("#txtStock").write("1");
        clickOn("#txtMaterial").write("Roble");
        clickOn("#txtColour").write("Rojo");
        clickOn("#txtWeight").write("10");
        clickOn("#txtHigh").write("1.5");
        clickOn("#txtWidth").write("a");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid width.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void crearProducto() {
        clickOn("#txtName").write("Mesa");
        clickOn("#txtPrice").write("19,99");
        clickOn("#txtStock").write("1");
        clickOn("#txtMaterial").write("Roble");
        clickOn("#txtColour").write("Rojo");
        clickOn("#txtWeight").write("10");
        clickOn("#txtHigh").write("a");
        clickOn("#txtWidth").write("2,5");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Invalid height.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void productAlreadyExits() {
        clickOn("#txtName").write("Silla de roble");
        clickOn("#txtPrice").write("19.99");
        clickOn("#txtStock").write("1");
        clickOn("#txtMaterial").write("Roble");
        clickOn("#txtColour").write("Rojo");
        clickOn("#txtWeight").write("10");
        clickOn("#txtHigh").write("1.5");
        clickOn("#txtWidth").write("2.5");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Product already exists.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void goHome() {
        clickOn("#btnHome");
        verifyThat("#products", isVisible());
    }
}
