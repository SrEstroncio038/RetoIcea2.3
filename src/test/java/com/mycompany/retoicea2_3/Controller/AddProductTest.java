package com.mycompany.retoicea2_3.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class AddProductTest extends ApplicationTest {

    private String productoCreado = null;

    @Override
    public void start(Stage stage) throws IOException {
        MainViewController.logedUser = null;
        new App().start(stage);
        App.setRoot("AddProduct");
    }

    @AfterEach
    public void limpiarBaseDeDatos() {
        if (productoCreado != null) {
            MongoDatabase db = DBConection.getInstancia().getBaseDatos();
            MongoCollection<Document> products = db.getCollection("products");
            products.deleteOne(new Document("productName", productoCreado));
            productoCreado = null;
        }
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
    public void crearProductoAlturaMal() {
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
    public void crearProductoExitoso() {
        productoCreado = "Mesa Test " + System.currentTimeMillis();

        clickOn("#txtName").write(productoCreado);
        clickOn("#txtPrice").write("19,99");
        clickOn("#txtStock").write("5");
        clickOn("#txtMaterial").write("Roble");
        clickOn("#txtColour").write("Rojo");
        clickOn("#txtWeight").write("10");
        clickOn("#txtHigh").write("1,5");
        clickOn("#txtWidth").write("2,5");
        clickOn("#btnSave");
        verifyThat("#btnSave", isVisible());
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
