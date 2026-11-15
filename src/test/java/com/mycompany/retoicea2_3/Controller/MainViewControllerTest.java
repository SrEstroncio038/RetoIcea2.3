package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Model.Admin;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Worker;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class MainViewControllerTest extends ApplicationTest {

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/mycompany/retoicea2_3/MainView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @BeforeEach
    void resetState() {
        MainViewController.logedUser = null;
        MainViewController.selectedProduct = null;
    }

    private void launchApp() {
        interact(new Runnable() {
            @Override
            public void run() {
                try {
                    new App().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    private Parent currentRoot() {
        return stage.getScene().getRoot();
    }

    private Button button(String id) {
        return lookup(id).queryAs(Button.class);
    }

    @SuppressWarnings("unchecked")
    private ListView<String> productList() {
        return lookup("#products").queryAs(ListView.class);
    }

    @Test
    void listaProductos_muestraLosSeisProductosIniciales() {
        assertEquals(6, productList().getItems().size());
    }

    @Test
    void listaProductos_muestraCadaProductoConPrecioYStock() {
        ObservableList<String> items = productList().getItems();
        String[] esperados = {
            "Producto:Silla de roble, precio: 79.99€ | Stock restante: 40",
            "Producto:Mesa de comedor, precio: 249.5€ | Stock restante: 12",
            "Producto:Estantería metálica, precio: 59.9€ | Stock restante: 25",
            "Producto:Sofá tres plazas, precio: 599.0€ | Stock restante: 6",
            "Producto:Lámpara de pie, precio: 45.75€ | Stock restante: 60",
            "Producto:Escritorio con cajones, precio: 129.99€ | Stock restante: 18"
        };
        for (String esperado : esperados) {
            boolean encontrado = false;
            for (String item : items) {
                if (item.contains(esperado)) {
                    encontrado = true;
                    break;
                }
            }
            assertTrue(encontrado);
        }
    }

    @Test
    void vista_muestraTituloYSubtitulo() {
        Labeled titulo = lookup("Welcome to ICEA!").queryLabeled();
        Labeled subtitulo = lookup("List of Available Products").queryLabeled();
        assertNotNull(titulo);
        assertNotNull(subtitulo);
    }

    @Test
    void vista_botonesTienenElTextoEsperado() {
        assertEquals("Login", button("#login").getText());
        assertEquals("Sign in", button("#singin").getText());
        assertEquals("Log Out", button("#logout").getText());
        assertEquals("Manager Products", button("#mProduct").getText());
        assertEquals("Manager Workers", button("#mworker").getText());
    }

    @Test
    void sinUsuario_botonesDeUsuarioOcultos() {
        assertFalse(button("#profile").isVisible());
        assertFalse(button("#mworker").isVisible());
        assertFalse(button("#mProduct").isVisible());
        assertFalse(button("#logout").isVisible());
    }

    @Test
    void sinUsuario_loginYSigninVisibles() {
        assertTrue(button("#login").isVisible());
        assertTrue(button("#singin").isVisible());
    }

    @Test
    void initialize_dejaSelectedProductEnNull() {
        assertNull(MainViewController.selectedProduct);
    }

    @Test
    void usuarioAdmin_muestraManagerProducts() {
        MainViewController.logedUser = crearAdmin();
        launchApp();
        assertTrue(button("#mProduct").isVisible());
        assertFalse(button("#profile").isVisible());
    }

    @Test
    void usuarioAdmin_logoutVisible() {
        MainViewController.logedUser = crearAdmin();
        launchApp();
        assertTrue(button("#logout").isVisible());
    }

    @Test
    void usuarioClient_muestraPerfilConSuNombre() {
        MainViewController.logedUser = crearClient("Asier");
        launchApp();
        Button profile = button("#profile");
        assertTrue(profile.isVisible());
        assertEquals("Asier", profile.getText());
        assertFalse(button("#mProduct").isVisible());
    }

    @Test
    void usuarioClient_logoutVisible() {
        MainViewController.logedUser = crearClient("Asier");
        launchApp();
        assertTrue(button("#logout").isVisible());
    }

    @Test
    void usuarioWorker_noMuestraManagerProducts() {
        MainViewController.logedUser = crearWorker();
        launchApp();
        assertFalse(button("#mworker").isVisible());
        assertFalse(button("#profile").isVisible());
    }

    @Test
    void usuarioWorker_logoutVisible() {
        MainViewController.logedUser = crearWorker();
        launchApp();
        assertTrue(button("#logout").isVisible());
    }

    @Test
    void ManageWorker_visible() {
        MainViewController.logedUser = crearAdmin();
        launchApp();
        assertTrue(button("#mProduct").isVisible());
    }

    @Test
    void clickManageWorker_cambiaDeVista() {
        MainViewController.logedUser = crearAdmin();
        launchApp();
        Parent before = currentRoot();
        clickOn("#mworker");
        assertNotSame(before, currentRoot());
    }

    @Test
    void ManageProduct_visible() {
        MainViewController.logedUser = crearWorker();
        launchApp();
        verifyThat("#mProduct", isVisible());
    }

    @Test
    void clickManageProduct_cambiaDeVista() {
        MainViewController.logedUser = crearWorker();
        launchApp();
        Parent befor = currentRoot();
        clickOn("#mProduct");
        assertNotSame(befor, currentRoot());

    }

    @Test
    void clickLogin_cambiaDeVista() {
        Parent before = currentRoot();
        clickOn("#login");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotSame(before, currentRoot());
    }

    @Test
    void clickSignIn_cambiaDeVista() {
        Parent before = currentRoot();
        clickOn("#singin");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotSame(before, currentRoot());

    }

    @Test
    void logOut_borraUsuarioYRecargaMainView() {
        MainViewController.logedUser = crearClient("Asier");
        launchApp();
        Parent before = currentRoot();
        clickOn("#logout");
        WaitForAsyncUtils.waitForFxEvents();
        assertNull(MainViewController.logedUser);
        assertNotSame(before, currentRoot());
        assertTrue(button("#login").isVisible());
        assertFalse(button("#profile").isVisible());
    }

    @Test
    void listaProductos_existeYTieneItemsAsignados() {
        ListView<String> list = productList();
        assertNotNull(list);
        assertNotNull(list.getItems());
    }

    @Test
    void unSoloClick_noAbreProducto() {
        assumeTrue(!productList().getItems().isEmpty());
        Parent before = currentRoot();
        clickOn(lookup(".list-cell").queryAll().iterator().next());
        WaitForAsyncUtils.waitForFxEvents();
        assertSame(before, currentRoot());
        assertNull(MainViewController.selectedProduct);
    }

    @Test
    void dobleClick_abreViewProduct() {
        assumeTrue(!productList().getItems().isEmpty());
        Parent before = currentRoot();
        doubleClickOn(lookup(".list-cell").queryAll().iterator().next());
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(MainViewController.selectedProduct);
        assertNotSame(before, currentRoot());
    }

    private Admin crearAdmin() {
        return new Admin();
    }

    private Client crearClient(String nombre) {
        Client c = new Client();
        c.setName(nombre);
        return c;
    }

    private Worker crearWorker() {
        return new Worker();
    }
}
