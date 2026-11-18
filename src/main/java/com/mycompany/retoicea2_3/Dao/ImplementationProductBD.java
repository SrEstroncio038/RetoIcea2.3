package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Model.*;
import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
import com.mongodb.client.MongoCollection;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import java.util.List;
import javafx.collections.ObservableList;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
import com.mycompany.retoicea2_3.Model.Product;
import org.bson.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;

/**
Implementación de la interfaz ProductDAO para gestionar los productos
mediante una base de datos MongoDB.
Permite consultar todos los productos, buscar un producto por su nombre
y añadir nuevos productos a la colección products.

@author G.2
*/
public class ImplementationProductBD implements ProductDAO {

    /**
Obtiene todos los productos almacenados en la base de datos.
Recupera sus nombres, precios y stock y los añade a una lista observable.

@return lista observable con la información de todos los productos.
*/
    @Override
    public ObservableList<String> showAllProductos() {
        MongoCollection<Document> resultados = DBConection.getInstancia().getColeccion("products");
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Document doc : resultados.find()) {
            items.add("Producto:" + doc.getString("productName") + ", precio: " + doc.getDouble("price") + "€" + " | Stock restante: " + doc.getInteger("stock") + " precio: " + doc.getDouble("price"));
        }
        return items;
    }

    /**
Busca un producto en la base de datos mediante su nombre.
Recupera también sus características, como el material, color,
altura, peso y anchura.

@param name nombre del producto que se desea buscar.
@return producto encontrado con sus características o null si no existe.
*/
    @Override
    public Product showOneProduct(String name) {
        Characteristics c = new Characteristics();
        MongoCollection<Document> resultados = DBConection.getInstancia().getColeccion("products");
        Document producto = resultados.find(Filters.eq("productName", name)).first();
        if (producto == null) {
            return null;
        }
        Document cahr = producto.get("characteristics", Document.class);
        Product p = null;
        if (cahr != null) {
            c.setColor(cahr.getString("color"));
            c.setMaterial(cahr.getString("material"));
            c.setHeight(cahr.get("height", Number.class).floatValue());
            c.setWeight(cahr.get("weight", Number.class).floatValue());
            c.setWidth(cahr.get("width", Number.class).floatValue());
            p = new Product(producto.getObjectId("productId"), producto.getString("productName"), producto.get("price", Number.class).floatValue(), producto.getInteger("stock"), producto.getBoolean("new"), c);
        }
        return p;
    }

    /**
Añade un nuevo producto a la colección products de MongoDB.
Antes de insertarlo, comprueba si ya existe otro producto con el mismo nombre.
Si existe, lanza una excepción para evitar duplicados.

@param p producto que se desea añadir a la base de datos.
@throws ProductExistsException si el producto ya existe.
*/
    @Override
    public void addProduct(Product p) throws ProductExistsException {
        if (showOneProduct(p.getNombre()) != null) {
            throw new ProductExistsException("Product already exists.");
        }
        ObjectId productId = new ObjectId();
        Document product = new Document("productId", productId)
                .append("productName", p.getNombre())
                .append("price", p.getPrecio())
                .append("stock", p.getStock())
                .append("new", p.isSeminuevo())
                .append("characteristics", new Document("characteristicId", new ObjectId())
                        .append("material", p.getCharacteristics().getMaterial())
                        .append("color", p.getCharacteristics().getColor())
                        .append("height", p.getCharacteristics().getHeight())
                        .append("width", p.getCharacteristics().getWidth())
                        .append("weight", p.getCharacteristics().getWeight()));

        MongoCollection<Document> productos = DBConection.getInstancia().getColeccion("products");
        productos.insertOne(product);
    }

}
