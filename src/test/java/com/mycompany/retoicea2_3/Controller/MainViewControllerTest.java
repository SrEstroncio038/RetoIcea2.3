package com.mycompany.retoicea2_3.Controller;

import static com.mongodb.assertions.Assertions.assertTrue;
import com.mycompany.retoicea2_3.Model.Admin;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Worker;
import com.mycompany.retoicea2_3.main.App;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;
import static org.testfx.matcher.control.ListViewMatchers.hasListCell;
import static org.testfx.matcher.control.ListViewMatchers.isEmpty;

class MainViewControllerTest extends ApplicationTest {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        MainViewController.logedUser = null;
        MainViewController.selectedProduct = null;
        new App().start(stage);
    }

    @BeforeEach
    void resetState() {
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

    @SuppressWarnings("unchecked")
    private ListView<String> productList() {
        return lookup("#products").queryAs(ListView.class);
    }

    @Test
    public void listaProductos_muestraLosSeisProductosIniciales() {
        verifyThat("#products", hasItems(6));
    }

    @Test
    public void listaProductos_muestraCadaProductoConPrecioYStock() {
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
            assertTrue(encontrado, "No aparece en la lista: " + esperado);
        }
    }

    @Test
    public void listaProductos_existeYTieneItemsAsignados() {
        verifyThat("#products", isNotNull());
        verifyThat("#products", isVisible());
        verifyThat("#products", not(isEmpty()));
    }

    @Test
    public void vista_muestraTituloYSubtitulo() {
        verifyThat("Welcome to ICEA!", isVisible());
        verifyThat("List of Available Products", isVisible());
    }

    @Test
    public void vista_botonesTienenElTextoEsperado() {
        verifyThat("#login", hasText("Login"));
        verifyThat("#singin", hasText("Sign in"));
        verifyThat("#logout", hasText("Log Out"));
        verifyThat("#mProduct", hasText("Manager Products"));
        verifyThat("#mworker", hasText("Manager Workers"));
    }

    @Test
    public void sinUsuario_botonesDeUsuarioOcultos() {
        verifyThat("#profile", isInvisible());
        verifyThat("#mworker", isInvisible());
        verifyThat("#mProduct", isInvisible());
        verifyThat("#logout", isInvisible());
    }

    @Test
    public void sinUsuario_loginYSigninVisibles() {
        verifyThat("#login", isVisible());
        verifyThat("#singin", isVisible());
    }

    @Test
    public void initialize_dejaSelectedProductEnNull() {
        assertNull(MainViewController.selectedProduct);
    }

    @Test
    public void clickLogin_cambiaDeVista() {
        clickOn("#login");
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#txtUser", isVisible());
    }

    @Test
    public void clickSignIn_cambiaDeVista() {
        clickOn("#singin");
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#user", isVisible());
    }

    @Test
    public void unSoloClick_noAbreProducto() {
        assumeTrue(!productList().getItems().isEmpty());
        clickOn(lookup(".list-cell").queryAll().iterator().next());
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#products", isVisible());
        assertNull(MainViewController.selectedProduct);
    }

    @Test
    public void dobleClick_abreViewProduct() {
        assumeTrue(!productList().getItems().isEmpty());
        doubleClickOn(lookup(".list-cell").queryAll().iterator().next());
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(MainViewController.selectedProduct);
        verifyThat("#lblPrice", isVisible());
    }

    @Test
    public void usuarioAdmin_muestraManagerProducts() {
        MainViewController.logedUser = crearAdmin();
        launchApp();
        verifyThat("#mProduct", isVisible());
        verifyThat("#profile", isInvisible());
    }

    @Test
    public void usuarioAdmin_logoutVisible() {
        MainViewController.logedUser = crearAdmin();
        launchApp();
        verifyThat("#logout", isVisible());
    }

    @Test
    public void usuarioClient_muestraPerfilConSuNombre() {
        MainViewController.logedUser = crearClient("Asier");
        launchApp();
        verifyThat("#profile", isVisible());
        verifyThat("#profile", hasText("Asier"));
        verifyThat("#mProduct", isInvisible());
    }

    @Test
    public void usuarioClient_logoutVisible() {
        MainViewController.logedUser = crearClient("Asier");
        launchApp();
        verifyThat("#logout", isVisible());
    }

    @Test
    public void usuarioWorker_noMuestraManagerWorkersNiPerfil() {
        MainViewController.logedUser = crearWorker();
        launchApp();
        verifyThat("#mworker", isInvisible());
        verifyThat("#profile", isInvisible());
    }

    @Test
    public void usuarioWorker_logoutVisible() {
        MainViewController.logedUser = crearWorker();
        launchApp();
        verifyThat("#logout", isVisible());
    }

    @Test
    public void ManageWorker_visible() {
        MainViewController.logedUser = crearAdmin();
        launchApp();
        verifyThat("#mworker", isVisible());
    }

    @Test
    public void clickManageWorker_cambiaDeVista() {
        MainViewController.logedUser = crearAdmin();
        launchApp();
        clickOn("#mworker");
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#btnModify", isVisible());
    }

    @Test
    public void ManageProduct_visible() {
        MainViewController.logedUser = crearWorker();
        launchApp();
        verifyThat("#mProduct", isVisible());
    }

    @Test
    public void clickManageProduct_cambiaDeVista() {
        MainViewController.logedUser = crearWorker();
        launchApp();
        clickOn("#mProduct");
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#btnModify", isVisible());
    }

    @Test
    public void logOut_borraUsuarioYRecargaMainView() {
        MainViewController.logedUser = crearClient("Asier");
        launchApp();
        clickOn("#logout");
        WaitForAsyncUtils.waitForFxEvents();
        assertNull(MainViewController.logedUser);
        verifyThat("#login", isVisible());
        verifyThat("#singin", isVisible());
        verifyThat("#logout", isInvisible());
        verifyThat("#profile", isInvisible());
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
