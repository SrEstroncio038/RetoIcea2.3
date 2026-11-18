package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.time.LocalDate;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author asola
 */
public class ViewProductTest extends ApplicationTest {

    private Client cliente;

    @Override
    public void start(Stage stage) throws IOException {
        cliente = new Client("Juan", "Perez", "Bilbao", "Pass1234!", "juanp",
                LocalDate.of(2000, 1, 1), new Addres(12, "Calle Mayor", 3, 'A'));
        MainViewController.logedUser = cliente;

        MainViewController.selectedProduct = new Product(
                new ObjectId(), "Estanteria", 89.99f, 10, false,
                new Characteristics("Pino", "Blanco", 12.0f, 180.0f, 80.0f));

        new App().start(stage);
        App.setRoot("ViewProduct");
    }

    @Test
    public void cargarDatos() {
        assertEquals("Estanteria", lookup("#txtName").queryAs(TextField.class).getText());
        assertEquals("89.99", lookup("#txtPrice").queryAs(TextField.class).getText());
        assertEquals("10", lookup("#txtStock").queryAs(TextField.class).getText());
        assertEquals("Pino", lookup("#txtMaterial").queryAs(TextField.class).getText());
        assertEquals("Blanco", lookup("#txtColour").queryAs(TextField.class).getText());
        assertEquals("12.0", lookup("#txtWeight").queryAs(TextField.class).getText());
        assertEquals("180.0", lookup("#txtHigh").queryAs(TextField.class).getText());
        assertEquals("80.0", lookup("#txtWidth").queryAs(TextField.class).getText());

        assertFalse(lookup("#txtName").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtPrice").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtStock").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtMaterial").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtColour").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtWeight").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtHigh").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtWidth").queryAs(TextField.class).isEditable());
    }

    @Test
    public void CamposBloqueados() {
        assertFalse(lookup("#txtName").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtPrice").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtStock").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtMaterial").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtColour").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtWeight").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtHigh").queryAs(TextField.class).isEditable());
        assertFalse(lookup("#txtWidth").queryAs(TextField.class).isEditable());
    }

    @Test
    public void addToCartCambioVentana() {
        clickOn("#btnAddToCart");

        assertFalse(cliente.getCart().isEmpty());
        assertEquals("Estanteria", cliente.getCart().get(0).getNombre());
        assertEquals(89.99f, cliente.getCart().get(0).getPrecio());
        assertNull(MainViewController.selectedProduct);
        verifyThat("#products", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#btnHome");
        verifyThat("#products", isVisible());
    }
}
