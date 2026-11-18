package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Refounds;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

public class ImplementationRefoundBD implements RefoundDAO {

    @Override
    public void createRefound(Refounds r) {
        MongoCollection<Document> collection = DBConection.getInstancia().getColeccion("refund");
        List<Document> productos = new ArrayList<>();
        for (Product p : r.getRefundProducts()) {
            productos.add(new Document("id", p.getId())
                    .append("nombre", p.getNombre())
                    .append("precio", p.getPrecio())
                    .append("stock", p.getStock())
                    .append("seminuevo", p.isSeminuevo())
                    .append("characteristics", p.getCharacteristics()));
        }
        Document refound = new Document("refundId", r.getRefundId())
                .append("refundDate", Date.from(r.getRefundDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .append("totalPrice", r.getTotalPrice())
                .append("refundProducts", productos);
        collection.insertOne(refound);
    }

}
