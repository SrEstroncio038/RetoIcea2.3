package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Purchase;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class RefoundTest extends ApplicationTest {

    private Client cliente;

    @Override
    public void start(Stage stage) throws IOException {
        Addres direccion = new Addres(12, "Calle Mayor", 3, 'A');
        cliente = new Client("Juan", "Perez", "Bilbao", "password123", "juanp",
                LocalDate.of(2000, 1, 1), direccion);

        ArrayList<Product> productosComprados = new ArrayList<>();
        // Pasamos null en Characteristics para evitar el CodecConfigurationException de MongoDB al guardar el Refound
        productosComprados.add(new Product(new ObjectId(), "Silla de roble", 79.99f, 40, false, null));
        productosComprados.add(new Product(new ObjectId(), "Mesa de comedor", 249.5f, 12, false, null));

        Purchase compra = new Purchase(new ObjectId(), LocalDate.now(), LocalDate.now().plusDays(3), 329.49, direccion, productosComprados);
        cliente.setPurchases(compra);

        MainViewController.logedUser = cliente;

        new App().start(stage);
        App.setRoot("Refound");
    }

    @Test
    public void cargarProductosComprados() {
        ObservableList<Product> products = lookup("#listProduct").queryAs(ListView.class).getItems();
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
    public void popUpSinSeleccionarProducto() {
        clickOn("#btnRefund");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Select at least one product to refund.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void reembolsarUnProducto() {
        clickOn("Silla de roble - 79,99 € (nuevo)");
        clickOn("#btnRefund");

        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Product refounded: ", dialogPane.getContentText());
        clickOn("Aceptar");

        ObservableList<Product> products = lookup("#listProduct").queryAs(ListView.class).getItems();
        assertEquals(1, products.size());
        assertFalse(cliente.getRefounds().isEmpty());
    }

    @Test
    public void reembolsarVariosProductos() {
        clickOn("Silla de roble - 79,99 € (nuevo)");
        press(KeyCode.CONTROL).clickOn("Mesa de comedor - 249,50 € (nuevo)").release(KeyCode.CONTROL);
        clickOn("#btnRefund");

        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Product refounded: ", dialogPane.getContentText());
        clickOn("Aceptar");

        ObservableList<Product> products = lookup("#listProduct").queryAs(ListView.class).getItems();
        assertTrue(products.isEmpty());
    }

    @Test
    public void goBack() {
        clickOn("#btnBack");
        // Verificamos el Label del nombre en UserProfile o el texto "Juan" cargado en initialize()
        verifyThat("#lblName", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#btnHome");
        verifyThat("#products", isVisible());
    }
}
