package com.mycompany.retoicea2_3.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.ComboBoxMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CreateOfferControllerTest extends ApplicationTest {

    private boolean ofertaCreada = false;

    @Override
    public void start(Stage stage) throws IOException {
        new App().start(stage);
        App.setRoot("CreateOffer");
    }

    @AfterEach
    public void limpiarBaseDeDatos() {
        if (ofertaCreada) {
            MongoDatabase db = DBConection.getInstancia().getBaseDatos();
            MongoCollection<Document> offers = db.getCollection("offers");
            offers.deleteMany(new Document("discount", 12.0));
            offers.deleteMany(new Document("discount", 12));
            ofertaCreada = false;
        }
    }

    @Test
    public void cargarProductos() {
        ObservableList<String> products = lookup("#cmbProduct").queryAs(ComboBox.class).getItems();
        String[] productos = {
            "Producto:Silla de roble, precio: 79.99€ | Stock restante: 40 precio: 79.99",
            "Producto:Mesa de comedor, precio: 249.5€ | Stock restante: 12 precio: 249.5",
            "Producto:Estantería metálica, precio: 59.9€ | Stock restante: 25 precio: 59.9",
            "Producto:Sofá tres plazas, precio: 599.0€ | Stock restante: 6 precio: 599.0",
            "Producto:Lámpara de pie, precio: 45.75€ | Stock restante: 60 precio: 45.75",
            "Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99"
        };
        for (String p : productos) {
            boolean encontrado = false;
            for (String producto : products) {
                if (producto.equalsIgnoreCase(p)) {
                    encontrado = true;
                    break;
                }
            }
            assertTrue(encontrado);
        }
    }

    @Test
    public void alertComboBoxProductos() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        verifyThat("#cmbProduct", ComboBoxMatchers.hasSelectedItem("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99"));
    }

    @Test
    public void errorSiNoHayProductoSeleccionado() {
        clickOn("#btnAdd");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("No product selected try again.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorDescuentoNull() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#btnAdd");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid discound", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorDescuentoNotValid() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#txtDiscount").write("abc");
        clickOn("#btnAdd");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid discound", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorFechaNull() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#txtDiscount").write("12");
        clickOn("#btnAdd");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid date", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorFechaNotValid() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#txtDiscount").write("12");
        DatePicker datePicker = lookup("#date").queryAs(DatePicker.class);
        datePicker.setValue(LocalDate.now().minusYears(1));
        clickOn("#btnAdd");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid date", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void createOffer() {
        ofertaCreada = true;
        clickOn("#cmbProduct");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#txtDiscount").write("12");
        DatePicker datePicker = lookup("#date").queryAs(DatePicker.class);
        datePicker.setValue(LocalDate.now().plusYears(1));
        clickOn("#btnAdd");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Offer created successfully", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void borrarOferta() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#txtDiscount").write("12");
        DatePicker datePicker = lookup("#date").queryAs(DatePicker.class);
        datePicker.setValue(LocalDate.now().plusYears(1));
        clickOn("#btnAdd");
        clickOn("Aceptar");
        clickOn("#btnRemove");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Offer deleted successfully", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorSiNoHayProductoSeleccionadoBorrar() {
        clickOn("#btnRemove");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("No product selected try again.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorDescuentoNullBorrar() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#btnRemove");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid discound", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorDescuentoNotValidBorrar() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#txtDiscount").write("abc");
        clickOn("#btnRemove");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid discound", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorFechaNullBorrar() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#txtDiscount").write("12");
        clickOn("#btnRemove");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid date", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void errorFechaNotValidBorrar() {
        clickOn("#cmbProduct");
        clickOn("Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18 precio: 129.99");
        clickOn("#txtDiscount").write("12");
        DatePicker datePicker = lookup("#date").queryAs(DatePicker.class);
        datePicker.setValue(LocalDate.now().minusYears(1));
        clickOn("#btnRemove");
        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Not a valid date", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void goHome() {
        clickOn("#btnHome");
        verifyThat("#products", isVisible());
    }
}
