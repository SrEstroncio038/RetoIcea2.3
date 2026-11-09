/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import java.util.List;
import javafx.collections.ObservableList;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asola
 */
public class ImplementationProductBD implements ProductDAO {

    @Override
    public ObservableList<String> mostrarProductos(){
        MongoCollection<Document> resultados = DBConection.getInstancia().getColeccion("products");
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Document doc : resultados.find()) {
            items.add(doc.getString("productName") + " precio: " + doc.getDouble("price") + "€"  + " | Stock restante: " + doc.getInteger("stock") + " precio: " + doc.getDouble("price"));
        }
        return items;
    }
    
    
}
