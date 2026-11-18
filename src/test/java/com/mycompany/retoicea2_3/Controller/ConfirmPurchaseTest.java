package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class ConfirmPurchaseTest extends ApplicationTest {

    private Client cliente;

    @Override
    public void start(Stage stage) throws IOException {
        cliente = new Client("Juan", "Perez", "Bilbao", "password123", "juanp",
                LocalDate.of(2000, 1, 1), new Addres(12, "Calle Mayor", 3, 'A'));

        cliente.setCart(new Product(new ObjectId(), "Silla de roble", 79.99f, 40, false,
                new Characteristics("Roble", "Marrón", 5f, 90f, 40f)));
        cliente.setCart(new Product(new ObjectId(), "Mesa de comedor", 249.5f, 12, false,
                new Characteristics("Madera", "Blanco", 25f, 75f, 120f)));
        MainViewController.logedUser = cliente;

        new App().start(stage);
        App.setRoot("ConfirmPurchase");
    }

    @Test
    public void cargarCarrito() {
        ObservableList<Product> products = lookup("#lvCart").queryAs(ListView.class).getItems();
        String[] productosEsperados = {
                "Silla de roble - 79,99 € (nuevo)",
                "Mesa de comedor - 249,50 € (nuevo)"
        };
        for (String esperado : productosEsperados) {
            boolean encontrado = false;
            for (Product producto : products) {
                if (producto.toString().equalsIgnoreCase(esperado)) {
                    encontrado = true;
                    break;
                }
            }
            assertTrue(encontrado);
        }
    }

    @Test
    public void popUpNombre() {
        clickOn("#txtNameSurname").write("hola");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid name", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpNombreVacio() {
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid name", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpNombreNumeroTarjetaMal() {
        clickOn("#txtNameSurname").write("hola");
        clickOn("#txtCardNumber").write("ds");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid name", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpNumeroTarjeta() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("ds");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid card number", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpNumeroTarjetaVacio() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid card number", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpNumeroTarjetaFechaIncorecta() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("ds");
        clickOn("#txtExpireDate").write("f");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid card number", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpFecha() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("1234567812345678");
        clickOn("#txtExpireDate").write("f");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid expaiering date", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpFechaVacio() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("1234567812345678");
        clickOn("#txtExpireDate");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid expaiering date", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpFechaCvvMal() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("1234567812345678");
        clickOn("#txtExpireDate").write("k");
        clickOn("#txtCvv").write("a");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid expaiering date", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpCvv() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("1234567812345678");
        clickOn("#txtExpireDate").write("12/28");
        clickOn("#txtCvv").write("a");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid Cvv", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpCvvVacio() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("1234567812345678");
        clickOn("#txtExpireDate").write("12/28");
        clickOn("#txtCvv");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid Cvv", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void confirmarCompra() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("1234567812345678");
        clickOn("#txtExpireDate").write("12/28");
        clickOn("#txtCvv").write("123");
        clickOn("#btnConfirmPurchase");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Purchase confirmed.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void confirmarCompraCambioVentana() {
        clickOn("#txtNameSurname").write("a b");
        clickOn("#txtCardNumber").write("1234567812345678");
        clickOn("#txtExpireDate").write("12/28");
        clickOn("#txtCvv").write("123");
        clickOn("#btnConfirmPurchase");
        clickOn("Aceptar");
        verifyThat("#products", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#btnHome");
        verifyThat("#products", isVisible());
    }
}
