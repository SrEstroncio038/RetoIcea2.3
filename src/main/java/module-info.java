module com.mycompany.retoicea2_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires java.base;

    opens com.mycompany.retoicea2_3 to javafx.fxml;
    opens com.mycompany.retoicea2_3.main to javafx.fxml;
    opens com.mycompany.retoicea2_3.Controller to javafx.fxml;
    opens com.mycompany.retoicea2_3.Model to javafx.base, javafx.fxml;

    exports com.mycompany.retoicea2_3.main;
    exports com.mycompany.retoicea2_3.Controller;
    exports com.mycompany.retoicea2_3.Model;
}