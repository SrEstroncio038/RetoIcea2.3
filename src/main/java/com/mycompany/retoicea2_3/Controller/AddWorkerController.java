/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationWorkerBD;
import com.mycompany.retoicea2_3.Dao.WorkerDAO;
import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Department;
import com.mycompany.retoicea2_3.Model.Worker;
import com.mycompany.retoicea2_3.main.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asola
 */
public class AddWorkerController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurnames;
    @FXML
    private TextField txtAddress;
    @FXML
    private ComboBox<Department> cmbDepartment;
    @FXML
    private TextField txtSeniority;
    @FXML 
    private DatePicker dpBirthday;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;
    
    WorkerController workerController = new WorkerController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbDepartment.setItems(FXCollections.observableArrayList(Department.values()));
        Worker workerToModify = ManageWorkersController.selectedWorker;
        
        if (workerToModify != null) {
            txtName.setText(workerToModify.getName());
            txtSurnames.setText(workerToModify.getSurname());
            
            if (workerToModify.getPassword() != null) {
                txtPassword.setText(workerToModify.getPassword());
            }
            
            if (workerToModify.getBirthDate() != null) {
                dpBirthday.setValue(workerToModify.getBirthDate());
            }
         
            txtSeniority.setText(String.valueOf(workerToModify.getSeniority()));

            if (workerToModify.getAddress() != null) {
                var addr = workerToModify.getAddress();
                txtAddress.setText(addr.getName() + ", " + addr.getHouseNumber() + ", " + addr.getFloor() + addr.getLetter());
            }
            
            if (workerToModify.getDepartment() != null) {
                cmbDepartment.setValue(workerToModify.getDepartment());
            }
            
            txtUser.setText(workerToModify.getUser());
        }
    }
    
    
    @FXML
    public void save() throws IOException {
        String name = txtName.getText();
        String surname = txtSurnames.getText();
        String user = txtUser.getText();
        String password = txtPassword != null ? txtPassword.getText() : "";
        Department department = cmbDepartment.getValue();
        LocalDate birthDate = dpBirthday.getValue();
        int seniority = 0;
        try {
            if (txtSeniority.getText() != null && !txtSeniority.getText().isEmpty()) {
                seniority = Integer.parseInt(txtSeniority.getText());
            }
        } catch (NumberFormatException e) {
            seniority = 0;
        }
        
        boolean isValid = true;
        Addres address = null;
        if (isValid) {
            String[] addrParts = txtAddress.getText().split(",");
            if (addrParts.length < 3) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Address Format Error");
                alert.setContentText("Use the format: Street, Number, FloorLetter (e.g. Gran Via, 12, 5C)");
                alert.showAndWait();
                isValid = false;
            } else {
                try {
                    String streetName = addrParts[0].trim();
                    int houseNumber = Integer.parseInt(addrParts[1].trim());
                    
                    String floorAndLetter = addrParts[2].trim();
                    String floorDigits = floorAndLetter.replaceAll("[^0-9]", "");
                    String letterPart = floorAndLetter.replaceAll("[0-9]", "");
                    
                    int floor = floorDigits.isEmpty() ? 0 : Integer.parseInt(floorDigits);
                    char letter = letterPart.isEmpty() ? ' ' : letterPart.charAt(0);
                    
                    address = new Addres(houseNumber, streetName, floor, letter);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Address Format Error");
                    alert.setContentText("House number and floor must be valid numbers.");
                    alert.showAndWait();
                    isValid = false;
                }
            }
        }
        
        if(isValid){
            Worker workerToModify = ManageWorkersController.selectedWorker;
        
            if (workerToModify != null) {
                workerToModify.setName(name);
                workerToModify.setSurname(surname);
                workerToModify.setUser(user);
                workerToModify.setPassword(password);
                workerToModify.setDepartment(department);
                workerToModify.setBirthDate(birthDate);
                workerToModify.setSeniority(seniority);
                workerToModify.setAddress(address);
                workerController.updateWorker(workerToModify, ManageWorkersController.selectedUserId);
            } else {
                Worker w = new Worker(department, name, surname, "", password, user, birthDate, null);
                w.setSeniority(seniority);
                workerController.insertWorker(w);
            }
        }
        
        App.setRoot("ManageWorkers");
    }
    
    @FXML
    public void goHome() throws IOException{
        App.setRoot("MainView");
    }
    
    @FXML
    public void back() throws IOException{
        App.setRoot("ManageWorkers");
    }
    
    private Addres parseAddress(String rawAddress) {
        String[] parts = rawAddress.split(",");
        String streetName = parts[0].trim();
        int houseNumber = 0;
        int floor = 0;
        char letter = ' ';

        try {
            if (parts.length > 1) houseNumber = Integer.parseInt(parts[1].trim());
            if (parts.length > 2) floor = Integer.parseInt(parts[2].trim());
            if (parts.length > 3 && !parts[3].trim().isEmpty()) letter = parts[3].trim().charAt(0);
        } catch (NumberFormatException e) {
            System.out.println("Error in adress format: "+e);
        }
        return new Addres(houseNumber, streetName, floor, letter);
    }
    
}
