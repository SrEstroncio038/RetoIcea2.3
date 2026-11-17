package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Worker;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Department;
import java.time.LocalDate;
import org.bson.Document;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.conversions.Bson;

public class ImplementationWorkerBD implements WorkerDAO{
    
    @Override
    public ObservableList<String> showAllWorkers(){
        MongoCollection<Document> resultados=DBConection.getInstancia().getColeccion("users");
        ObservableList<String> items=FXCollections.observableArrayList();
        for (Document doc: resultados.find(Filters.eq("userType", "Worker"))){
            items.add(doc.getString("name")+" "+doc.getString("surname")+" "+doc.getString("userId"));
        }
        return items;
    }
    
    @Override
    public Worker showOneWorker(String userId){
        MongoCollection<Document> resultados=DBConection.getInstancia().getColeccion("users");
        Document trabajadorDoc = resultados.find(Filters.eq("userId", userId)).first();
        if (trabajadorDoc == null) {
            return null;
        }
        Department department = null;
        String deptStr = trabajadorDoc.getString("department");
        if (deptStr != null) {
            try {
                department = Department.valueOf(deptStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                department = null;
            }
        }
        String name = trabajadorDoc.getString("name");
        String surname = trabajadorDoc.getString("surname");
        String username = trabajadorDoc.getString("username");
        String password = trabajadorDoc.getString("password");
        java.util.Date mongoDate = trabajadorDoc.getDate("birthdate");
        LocalDate birthDate = null;
        if (mongoDate != null) {
            birthDate = mongoDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }
        Document addressDoc = trabajadorDoc.get("address", Document.class);
        Addres address = null;
        if (addressDoc != null) {
            int houseNumber = addressDoc.getInteger("houseNumber");
            String streetName = addressDoc.getString("streetName");
            int floor = addressDoc.getInteger("floor");
            String floorLetterStr = addressDoc.getString("floorLetter");
            char letter = ' ';
            if (floorLetterStr != null && !floorLetterStr.isEmpty()) {
                letter = floorLetterStr.charAt(0);
            }
            address = new Addres(houseNumber, streetName, floor, letter);
        }
        Worker w = new Worker(department, name, surname, "", password, username, birthDate, address);
        return w;
    }
    
    private Worker documentToWorker(Document doc){
        Worker worker=new Worker();
        try{
            worker.setId(doc.getInteger("id", 0));
            worker.setSeniority(doc.getInteger("seniority", 0));
            String departmentString=doc.getString("department");
            if(departmentString==null){
                worker.setDepartment(Department.valueOf(departmentString));
            }
            worker.setUser(doc.getString("user"));
            worker.setName(doc.getString("name"));
            worker.setSurname(doc.getString("surname"));
            worker.setBirthPlace(doc.getString("birthPlace"));
            
        }catch(Exception e) {
            System.out.println("Error obtaining workers: " + e.getMessage());
            worker=null;
        }
        return worker;
    }
    
    
    
            
}
