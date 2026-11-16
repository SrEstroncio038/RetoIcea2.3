package com.mycompany.retoicea2_3.main;

import com.mycompany.retoicea2_3.Controller.MainViewController;
import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Client cliente = new Client("Juan", "Perez", "Bilbao", "password123", "juanp",
                LocalDate.of(2000, 1, 1), new Addres(12, "Calle Mayor", 3, 'A'));

        cliente.setCart(new Product(new ObjectId(), "Silla de roble", 79.99f, 40, false,
                new Characteristics("Roble", "Marrón", 5f, 90f, 40f)));
        cliente.setCart(new Product(new ObjectId(), "Mesa de comedor", 249.5f, 12, false,
                new Characteristics("Madera", "Blanco", 25f, 75f, 120f)));
        MainViewController.logedUser = cliente;

        scene = new Scene(loadFXML("ConfirmPurchase"), 640, 480);
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
}
