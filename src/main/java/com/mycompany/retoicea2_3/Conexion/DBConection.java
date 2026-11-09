package com.mycompany.retoicea2_3.Conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConection {

    private static final String ARCHIVO_PROPIEDADES = "/com/mycompany/retoicea2_3/db.properties";

    private static DBConection instancia;
    private MongoClient cliente;
    private MongoDatabase baseDatos;

    private DBConection() {
        Properties props = cargarPropiedades();

        String uri = props.getProperty("db.uri");
        String nombreBD = props.getProperty("db.database");

        if (uri == null || nombreBD == null) {
            throw new RuntimeException(
                    "Faltan db.uri o db.database en " + ARCHIVO_PROPIEDADES);
        }

        try {
            cliente = MongoClients.create(uri);
            baseDatos = cliente.getDatabase(nombreBD);
            System.out.println("Conectado a MongoDB: " + nombreBD);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con MongoDB", e);
        }
    }

    private Properties cargarPropiedades() {
        Properties props = new Properties();
        try (InputStream is = DBConection.class.getResourceAsStream(ARCHIVO_PROPIEDADES)) {
            if (is == null) {
                throw new RuntimeException("No se encontró " + ARCHIVO_PROPIEDADES
                        + " en el classpath");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo " + ARCHIVO_PROPIEDADES, e);
        }
        return props;
    }

    public static synchronized DBConection getInstancia() {
        if (instancia == null) {
            instancia = new DBConection();
        }
        return instancia;
    }

    public MongoDatabase getBaseDatos() {
        return baseDatos;
    }

    public MongoCollection<Document> getColeccion(String nombre) {
        return baseDatos.getCollection(nombre);
    }

    public void cerrar() {
        if (cliente != null) {
            cliente.close();
            instancia = null;
            System.out.println("Conexión cerrada");
        }
    }
}
