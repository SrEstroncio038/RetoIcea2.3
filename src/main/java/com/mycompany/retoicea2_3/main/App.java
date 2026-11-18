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
Clase principal de la aplicación JavaFX.
Se encarga de iniciar la aplicación y cargar las vistas FXML.

@author G.2
*/
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("MainView"), 640, 480);
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