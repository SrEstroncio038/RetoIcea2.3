package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Refounds;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
Implementación de la interfaz RefoundDAO para gestionar las devoluciones
mediante una base de datos MongoDB.
Permite registrar las devoluciones realizadas, almacenando la información
de los productos devueltos, sus características y el importe total.

@author G.2
*/
public class ImplementationRefoundBD implements RefoundDAO {

    /**
Registra una nueva devolución en la colección refund de MongoDB.
Obtiene los datos de los productos devueltos y sus características,
y los almacena junto con la fecha de devolución y el importe total.

@param r devolución que se desea registrar en la base de datos.
*/
    @Override
    public void createRefound(Refounds r) {
        MongoCollection<Document> collection = DBConection.getInstancia().getColeccion("refund");
        List<Document> productos = new ArrayList<>();
        for (Product p : r.getRefundProducts()) {
            Document charDoc = null;
            Characteristics c = p.getCharacteristics();
            if (c != null) {
                charDoc = new Document("material", c.getMaterial())
                        .append("color", c.getColor())
                        .append("weight", c.getWeight())
                        .append("height", c.getHeight())
                        .append("width", c.getWidth());
            }
            productos.add(new Document("id", p.getId())
                    .append("nombre", p.getNombre())
                    .append("precio", p.getPrecio())
                    .append("stock", p.getStock())
                    .append("seminuevo", p.isSeminuevo())
                    .append("characteristics", charDoc));
        }
        Document refound = new Document("refundId", r.getRefundId())
                .append("refundDate", Date.from(r.getRefundDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .append("totalPrice", r.getTotalPrice())
                .append("refundProducts", productos);
        collection.insertOne(refound);
    }

}
