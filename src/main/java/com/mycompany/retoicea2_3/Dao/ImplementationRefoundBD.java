package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Characteristics;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Refound;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author asola
 */
public class ImplementationRefoundBD implements RefoundDAO {

    @Override
    public void createRefound(Refound r) {
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
