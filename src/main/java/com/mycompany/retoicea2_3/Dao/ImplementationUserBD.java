/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Client;
import org.bson.Document;

/**
 *
 * @author asola
 */
public class ImplementationUserBD implements UserDAO {

    @Override
    public void clientRegistration(Client c) {

        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");

        Document address = new Document()
                .append("streetName", c.getAddress().getName())
                .append("houseNumber", c.getAddress().getHouseNumber())
                .append("floor", c.getAddress().getFloor())
                .append("floorLetter", String.valueOf(c.getAddress().getLetter()));

        Document user = new Document()
                .append("username", c.getUser())
                .append("name", c.getName())
                .append("surname", c.getSurname())
                .append("password", c.getPasword())
                .append("birthdate", c.getBirthDate())
                .append("address", address)
                .append("userType", "Client");

        users.insertOne(user);
    }

}
