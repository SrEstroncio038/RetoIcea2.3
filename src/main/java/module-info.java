module com.mycompany.retoicea2_3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.retoicea2_3 to javafx.fxml;
    exports com.mycompany.retoicea2_3;
}
