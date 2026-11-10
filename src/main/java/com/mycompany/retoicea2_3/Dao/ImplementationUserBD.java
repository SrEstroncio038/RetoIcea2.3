package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Client;
import org.bson.Document;

public class ImplementationUserBD implements UserDAO {

    @Override
    public void clientRegistration(Client c) {

        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");

        Document address = new Document() //para el de address
                .append("streetName", c.getAddress().getName())
                .append("houseNumber", c.getAddress().getHouseNumber())
                .append("floor", c.getAddress().getFloor())
                .append("floorLetter", String.valueOf(c.getAddress().getLetter()));

        Document user = new Document() //para el user como tal
                .append("username", c.getUser())
                .append("name", c.getName())
                .append("surname", c.getSurname())
                .append("password", c.getPasword())
                .append("birthdate", c.getBirthDate())
                .append("address", address)
                .append("userType", "Client");

        users.insertOne(user);
    }

    public boolean usernameExists(String username) { //para que no repita user
        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");
        Document user = users.find(new Document("username", username)).first();
        return user != null;
    }

    public boolean passwordValid(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$" ); //el patron para que sean rollo: Ana12345!
    }

}
