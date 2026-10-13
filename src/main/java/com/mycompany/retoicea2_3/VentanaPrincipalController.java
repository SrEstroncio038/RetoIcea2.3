package com.mycompany.retoicea2_3;

import java.io.IOException;
import javafx.fxml.FXML;

public class VentanaPrincipalController {

    @FXML
    private void switchToSecondary() throws IOException {
        Main.setRoot("secondary");
    }
}
