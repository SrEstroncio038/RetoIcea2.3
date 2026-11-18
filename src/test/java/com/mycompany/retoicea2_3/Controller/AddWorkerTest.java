package com.mycompany.retoicea2_3.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Department;
import com.mycompany.retoicea2_3.Model.Worker;
import com.mycompany.retoicea2_3.main.App;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class AddWorkerTest extends ApplicationTest {

    private Stage stage;
    private String usuarioCreado = null;
    private Document trabajadorOriginal = null;
    private String userIdModificado = null;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        ManageWorkersController.selectedWorker = null;
        ManageWorkersController.selectedUserId = null;
        new App().start(stage);
        App.setRoot("AddWorker");
    }

    @AfterEach
    public void limpiarYRestaurarBaseDeDatos() {
        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");
        if (usuarioCreado != null) {
            users.deleteOne(new Document("username", usuarioCreado));
            usuarioCreado = null;
        }
        if (trabajadorOriginal != null && userIdModificado != null) {
            users.replaceOne(new Document("userId", new ObjectId(userIdModificado)), trabajadorOriginal);
            trabajadorOriginal = null;
            userIdModificado = null;
        }
        ManageWorkersController.selectedWorker = null;
        ManageWorkersController.selectedUserId = null;
    }

    private void launchConTrabajadorSeleccionado() {
        interact(new Runnable() {
            @Override
            public void run() {
                try {
                    App.setRoot("AddWorker");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void popUpFormatoDireccionIncompleto() {
        clickOn("#txtName").write("Carlos");
        clickOn("#txtAddress").write("Gran Via");
        clickOn("#btnSave");

        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("Use the format: Street, Number, FloorLetter (e.g. Gran Via, 12, 5C)", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void popUpNumeroCasaInvalido() {
        clickOn("#txtName").write("Carlos");
        clickOn("#txtAddress").write("Gran Via, abc, 5C");
        clickOn("#btnSave");

        DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);
        assertNotNull(dialogPane);
        assertEquals("House number and floor must be valid numbers.", dialogPane.getContentText());
        clickOn("Aceptar");
    }

    @Test
    public void crearTrabajador() {
        usuarioCreado = "worker_test_" + System.currentTimeMillis();
        clickOn("#txtName").write("Marcos");
        clickOn("#txtSurnames").write("Ruiz");
        clickOn("#txtAddress").write("Gran Via, 12, 5C");
        clickOn("#cmbDepartment");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#txtSeniority").write("3");
        clickOn("#dpBirthday").write("15/05/1995").push(KeyCode.ENTER);
        clickOn("#txtUser").write(usuarioCreado);
        clickOn("#txtPassword").write("Pass1234!");
        clickOn("#btnSave");

        verifyThat("#workers", isVisible());
    }

    @Test
    public void cargarYModificarTrabajador() {
        userIdModificado = "507f1f77bcf86cd799439012";
        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");
        trabajadorOriginal = users.find(new Document("userId", new ObjectId(userIdModificado))).first();

        Worker worker = new Worker(Department.values()[0], "Ana", "Sola", "Bilbao", "Pass1234!", "worker_ana",
                LocalDate.of(1990, 5, 10), new Addres(10, "Calle Ercilla", 2, 'B'));
        worker.setSeniority(5);

        ManageWorkersController.selectedWorker = worker;
        ManageWorkersController.selectedUserId = userIdModificado;
        launchConTrabajadorSeleccionado();

        assertEquals("Ana", lookup("#txtName").queryAs(TextField.class).getText());
        assertEquals("Sola", lookup("#txtSurnames").queryAs(TextField.class).getText());
        assertEquals("Calle Ercilla, 10, 2B", lookup("#txtAddress").queryAs(TextField.class).getText());
        assertEquals("5", lookup("#txtSeniority").queryAs(TextField.class).getText());
        assertEquals("worker_ana", lookup("#txtUser").queryAs(TextField.class).getText());
        assertNotNull(lookup("#cmbDepartment").queryAs(ComboBox.class).getValue());

        doubleClickOn("#txtSeniority").write("6");
        clickOn("#btnSave");

        verifyThat("#workers", isVisible());
    }

    @Test
    public void goBack() {
        clickOn("#btnBack");
        verifyThat("#workers", isVisible());
    }

    @Test
    public void goHome() {
        clickOn("#btnHome");
        verifyThat("#products", isVisible());
    }
}
