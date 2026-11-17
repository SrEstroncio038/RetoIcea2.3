package com.mycompany.retoicea2_3.main;

import com.mycompany.retoicea2_3.Controller.MainViewController;
import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Purchase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.bson.types.ObjectId;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        MainViewController.logedUser = crearClienteGenerico();
        scene = new Scene(loadFXML("UserProfile"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/retoicea2_3/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    private Client crearClienteGenerico() {
        Addres address = new Addres();

        Client client = new Client(
                "Juan", "Pérez", "Bilbao", "1234", "juanp",
                LocalDate.of(1995, 5, 20), address
        );
        client.setId(1);

        // Productos de prueba
        Product silla = new Product(new ObjectId(), "Silla de roble", 79.99f, 10, false, null);
        Product mesa = new Product(new ObjectId(), "Mesa de comedor", 149.50f, 5, true, null);
        Product lampara = new Product(new ObjectId(), "Lámpara de pie", 24.50f, 20, false, null);
        Product sofa = new Product(new ObjectId(), "Sofá de 3 plazas", 399.00f, 3, false, null);
        Product estanteria = new Product(new ObjectId(), "Estantería", 59.90f, 8, true, null);
        Product alfombra = new Product(new ObjectId(), "Alfombra", 45.00f, 12, false, null);

        // Compra 1: silla + mesa
        client.setPurchases(crearCompra(address, 10, 5, silla, mesa));

        // Compra 2: lámpara
        client.setPurchases(crearCompra(address, 3, -2, lampara));

        // Compra 3: sofá
        client.setPurchases(crearCompra(address, 20, 14, sofa));

        // Compra 4: estantería + alfombra
        client.setPurchases(crearCompra(address, 1, -4, estanteria, alfombra));

        return client;
    }

    private Purchase crearCompra(Addres address, int diasDesdePedido, int diasDesdeEntrega, Product... productos) {
        ArrayList<Product> lista = new ArrayList<>(Arrays.asList(productos));

        double total = 0;
        for (Product p : lista) {
            total += p.getPrecio();
        }
        total = Math.round(total * 100.0) / 100.0;

        return new Purchase(
                new ObjectId(),
                LocalDate.now().minusDays(diasDesdePedido),
                LocalDate.now().minusDays(diasDesdeEntrega),
                total,
                address,
                lista
        );
    }
}
