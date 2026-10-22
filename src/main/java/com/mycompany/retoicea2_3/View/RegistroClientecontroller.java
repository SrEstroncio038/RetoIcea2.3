package com.mycompany.retoicea2_3;

import java.io.IOException;
import javafx.fxml.FXML;

public class RegistroClientecontroller {

    @FXML
    private void switchToPrimary() throws IOException {
        Main.setRoot("primary");
    }
}