/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Model.*;
import com.mongodb.client.MongoCollection;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import java.util.List;
import javafx.collections.ObservableList;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.retoicea2_3.Model.Product;
import org.bson.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asola
 */
public class ImplementationProductBD implements ProductDAO {

    @Override
    public ObservableList<String> showAllProductos() {
        MongoCollection<Document> resultados = DBConection.getInstancia().getColeccion("products");
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Document doc : resultados.find()) {
            items.add("Producto:" + doc.getString("productName") + ", precio: " + doc.getDouble("price") + "€" + " | Stock restante: " + doc.getInteger("stock") + " precio: " + doc.getDouble("price"));
        }
        return items;
    }

    @Override
    public Product showOneProduct(String name) {
        Characteristics c = new Characteristics();
        MongoCollection<Document> resultados = DBConection.getInstancia().getColeccion("products");
        Document producto = resultados.find(eq("productName", name)).first();
        Document cahr = producto.get("characteristics", Document.class);
        if (cahr != null) {
            c.setColor(cahr.getString("color"));
            c.setMaterial(cahr.getString("material"));
            c.setHeight(cahr.get("height", Number.class).floatValue());
            c.setWeight(cahr.get("weight", Number.class).floatValue());
            c.setWidth(cahr.get("width", Number.class).floatValue());
        }
        Product p = new Product(producto.getObjectId("_id"), producto.getString("productName"), producto.get("price", Number.class).floatValue(), producto.getInteger("stock"), producto.getBoolean("new"), c);
        return p;
    }

    @Override
    public void addProduct(Product p) {
        Document product = new Document("productName", p.getNombre())
                .append("price", p.getPrecio())
                .append("stock", p.getStock())
                .append("new", p.isSeminuevo());
        product.append("characteristics", new Document("material", p.getCharacteristics().getMaterial())
                .append("color", p.getCharacteristics().getColor())
                .append("height", p.getCharacteristics().getHeight())
                .append("width", p.getCharacteristics().getWidth())
                .append("weight", p.getCharacteristics().getWeight()));

        MongoCollection<Document> productos = DBConection.getInstancia().getColeccion("products");
        productos.insertOne(product);
        p.setId(product.getObjectId("_id"));
    }

}
