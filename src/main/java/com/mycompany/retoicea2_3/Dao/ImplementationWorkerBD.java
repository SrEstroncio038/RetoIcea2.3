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
import org.bson.types.ObjectId;

/**
 * Implementación de la interfaz WorkerDAO para gestionar los trabajadores
 * mediante una base de datos MongoDB. Permite consultar todos los trabajadores,
 * obtener la información de un trabajador concreto y eliminar trabajadores de
 * la base de datos.
 *
 * @author G.2
 */
public class ImplementationWorkerBD implements WorkerDAO {

    /**
     * Obtiene todos los trabajadores registrados en la colección users.
     * Recupera sus nombres, apellidos e identificadores y los almacena en una
     * lista observable.
     *
     * @return lista observable con la información de los trabajadores.
     */
    @Override
    public ObservableList<String> showAllWorkers() {
        MongoCollection<Document> resultados = DBConection.getInstancia().getColeccion("users");
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Document doc : resultados.find(Filters.eq("userType", "Worker"))) {
            items.add(doc.getString("name") + " " + doc.getString("surname") + " --> " + doc.getObjectId("userId"));
        }
        return items;
    }

    /**
     * Busca un trabajador en la base de datos mediante su identificador.
     * Recupera sus datos personales, departamento, fecha de nacimiento y
     * dirección para construir un objeto Worker.
     *
     * @param userId identificador del trabajador que se desea consultar.
     * @return objeto Worker con sus datos o null si no se encuentra.
     */
    @Override
    public Worker showOneWorker(String userId) {
        MongoCollection<Document> resultados = DBConection.getInstancia().getColeccion("users");
        Document trabajadorDoc = resultados.find(Filters.eq("userId", new ObjectId(userId))).first();
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
        Integer seniority = trabajadorDoc.getInteger("seniority");
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
        w.setSeniority(seniority);
        return w;
    }

    @Override
    public void deleteWorker(String userId) {
        MongoCollection<Document> resultados = DBConection.getInstancia().getColeccion("users");
        resultados.deleteOne(Filters.eq("userId", new ObjectId(userId)));
    }

    @Override
    public void updateWorker(Worker worker, String userId) {
        MongoCollection<Document> collection = DBConection.getInstancia().getColeccion("users");
        Document addressDoc = null;
        if (worker.getAddress() != null) {
            addressDoc = new Document("houseNumber", worker.getAddress().getHouseNumber())
                    .append("streetName", worker.getAddress().getName())
                    .append("floor", worker.getAddress().getFloor())
                    .append("floorLetter", String.valueOf(worker.getAddress().getLetter()));
        }
        java.util.Date birthDateDoc = null;
        if (worker.getBirthDate() != null) {
            birthDateDoc = java.util.Date.from(worker.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        Document workerDoc = new Document("name", worker.getName())
                .append("surname", worker.getSurname())
                .append("username", worker.getUser())
                .append("password", worker.getPassword() != null ? worker.getPassword() : "")
                .append("department", worker.getDepartment() != null ? worker.getDepartment().name() : null)
                .append("seniority", worker.getSeniority())
                .append("birthdate", birthDateDoc)
                .append("address", addressDoc);
        collection.updateOne(
                Filters.eq("userId", new ObjectId(userId)),
                new Document("$set", workerDoc)
        );
    }

    @Override
    public void insertWorker(Worker worker) {
        MongoCollection<Document> collection = DBConection.getInstancia().getColeccion("users");
        java.util.Date birthDateDoc = null;
        if (worker.getBirthDate() != null) {
            birthDateDoc = java.util.Date.from(worker.getBirthDate().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        }
        Document addressDoc = null;
        if (worker.getAddress() != null) {
            addressDoc = new Document("houseNumber", worker.getAddress().getHouseNumber())
                    .append("streetName", worker.getAddress().getName())
                    .append("floor", worker.getAddress().getFloor())
                    .append("floorLetter", String.valueOf(worker.getAddress().getLetter()));
        }
        Document workerDoc = new Document("userType", "Worker")
                .append("name", worker.getName())
                .append("surname", worker.getSurname())
                .append("username", worker.getUser())
                .append("password", worker.getPassword() != null ? worker.getPassword() : "")
                .append("department", worker.getDepartment() != null ? worker.getDepartment().name() : null)
                .append("seniority", worker.getSeniority())
                .append("birthdate", birthDateDoc)
                .append("address", addressDoc)
                .append("userId", new org.bson.types.ObjectId());
        collection.insertOne(workerDoc);

    }
}
