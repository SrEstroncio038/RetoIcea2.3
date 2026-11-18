package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Admin;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Usuario;
import com.mycompany.retoicea2_3.Model.Worker;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
Implementación de la interfaz UserDAO para gestionar los usuarios
mediante una base de datos MongoDB.
Permite registrar clientes, comprobar si un nombre de usuario existe,
validar contraseñas y obtener los usuarios almacenados en la base de datos.

@author G.2
*/
public class ImplementationUserBD implements UserDAO {

    /**
Registra un nuevo cliente en la colección users de MongoDB.
Antes de realizar el registro, comprueba si el nombre de usuario
ya existe. Si no existe, almacena sus datos personales,
dirección y tipo de usuario.

@param c cliente que se desea registrar.
@return true si el nombre de usuario ya existe, false si no existe.
*/
    @Override
    public boolean clientRegistration(Client c) {

        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");
        boolean exsists = usernameExists(c.getUser());
        if (!exsists) {
            Document address = new Document()
                    .append("streetName", c.getAddress().getName())
                    .append("houseNumber", c.getAddress().getHouseNumber())
                    .append("floor", c.getAddress().getFloor())
                    .append("floorLetter", String.valueOf(c.getAddress().getLetter()));

            Document user = new Document()
                    .append("userId", new ObjectId())
                    .append("username", c.getUser())
                    .append("name", c.getName())
                    .append("surname", c.getSurname())
                    .append("password", c.getPasword())
                    .append("birthdate", c.getBirthDate())
                    .append("address", address)
                    .append("userType", "Client");
            users.insertOne(user);
        }
        return exsists;
    }

    /**
Comprueba si un nombre de usuario ya está registrado en la base de datos.

@param username nombre de usuario que se desea comprobar.
@return true si el usuario existe, false en caso contrario.
*/
    public boolean usernameExists(String username) {
        boolean exsists;
        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");
        Document user = users.find(new Document("username", username)).first();
        if (user != null) {
            exsists = true;
        } else {
            exsists = false;
        }
        return exsists;
    }

    public boolean passwordValid(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$");
    }

    @Override
    public ArrayList<Usuario> obtainUser() {

        ArrayList<Usuario> lista = new ArrayList<>();

        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");

        for (Document doc : users.find()) {
            String type = doc.getString("userType");
            Usuario u;
            switch (type) {
                case "Admin":
                    u = new Admin();
                    break;
                case "Worker":
                    u = new Worker();
                    break;
                default:
                    u = new Client();
                    break;
            }
            u.setName(doc.getString("name"));
            u.setSurname(doc.getString("surname"));
            u.setUser(doc.getString("username"));
            u.setPasword(doc.getString("password"));

            lista.add(u);
        }

        return lista;
    }

}
