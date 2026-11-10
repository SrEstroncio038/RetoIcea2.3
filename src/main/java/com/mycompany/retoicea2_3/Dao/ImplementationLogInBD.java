package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Admin;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Usuario;
import com.mycompany.retoicea2_3.Model.Worker;
import java.util.ArrayList;
import org.bson.Document;

public class ImplementationLogInBD implements LogInDAO {

    @Override
    public ArrayList<Usuario> obtainUser() {

        ArrayList<Usuario> lista = new ArrayList<>();

        MongoDatabase db = DBConection.getInstancia().getBaseDatos();
        MongoCollection<Document> users = db.getCollection("users");

        for (Document doc : users.find()) { //para asignarles el tipo
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
