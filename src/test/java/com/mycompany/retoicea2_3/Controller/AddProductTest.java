package com.mycompany.retoicea2_3.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.main.App;
import java.time.LocalDate;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.util.WaitForAsyncUtils;

/**
 *
 * @author asola
 */
public class AddProductTest extends ApplicationTest {

    private Stage stage;
    private String productoCreado = null;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        MainViewController.logedUser = null;
        ManageProductController.selectedProductToModify = null;
        new App().start(stage);
        stage.setWidth(900);
        stage.setHeight(700);
        stage.centerOnScreen();
        App.setRoot("AddProduct");
        WaitForAsyncUtils.waitForFxEvents();
    }

    @AfterEach
    public void limpiarEstadoYBaseDeDatos() {
        if (productoCreado != null) {
            MongoDatabase db = DBConection.getInstancia().getBaseDatos();
            MongoCollection<Document> products = db.getCollection("products");
            products.deleteOne(new Document("productName", productoCreado));
            productoCreado = null;
        }
        MainViewController.logedUser = null;
        ManageProductController.selectedProductToModify = null;
    }

    private void recargarVista() {
        interact(() -> {
            try {
                App.setRoot("AddProduct");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        WaitForAsyncUtils.waitForFxEvents();
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

        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Product added successfully.", dialogPane.getContentText());
        clickOn("Aceptar");

        verifyThat("#btnModify", isVisible());
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
    public void cargarYModificarProducto() {
        ManageProductController.selectedProductToModify = new Product(
                new ObjectId(), "Estanteria", 89.99f, 10, true,
                new Characteristics("Pino", "Blanco", 12.0f, 180.0f, 80.0f)
        );
        recargarVista();

        verifyThat("#txtName", isDisabled());
        assertEquals("Estanteria", lookup("#txtName").queryAs(TextField.class).getText());
        assertEquals("89.99", lookup("#txtPrice").queryAs(TextField.class).getText());
        assertEquals("10", lookup("#txtStock").queryAs(TextField.class).getText());
        assertEquals("Pino", lookup("#txtMaterial").queryAs(TextField.class).getText());
        assertEquals("Blanco", lookup("#txtColour").queryAs(TextField.class).getText());
        assertEquals("12.0", lookup("#txtWeight").queryAs(TextField.class).getText());
        assertEquals("180.0", lookup("#txtHigh").queryAs(TextField.class).getText());
        assertEquals("80.0", lookup("#txtWidth").queryAs(TextField.class).getText());
        assertTrue(lookup("#chkPreOwned").queryAs(CheckBox.class).isSelected());
        doubleClickOn("#txtStock").write("15");
        clickOn("#btnSave");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Product updated successfully.", dialogPane.getContentText());
        clickOn("Aceptar");
        assertNull(ManageProductController.selectedProductToModify);
        verifyThat("#btnModify", isVisible());
    }

    @Test
    public void clienteOcultaBackYMarcaPreOwned() {
        MainViewController.logedUser = new Client("Juan", "Perez", "Bilbao", "Pass1234!", "juanp",
                LocalDate.of(2000, 1, 1), new Addres(12, "Calle Mayor", 3, 'A'));
        recargarVista();
        verifyThat("#btnBack", isInvisible());
        assertTrue(lookup("#chkPreOwned").queryAs(CheckBox.class).isSelected());
    }

    @Test
    public void goBack() {
        clickOn("#btnBack");
        assertNull(ManageProductController.selectedProductToModify);
        verifyThat("#btnModify", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#btnHome");
        assertNull(ManageProductController.selectedProductToModify);
        verifyThat("#products", isVisible());
    }
}
