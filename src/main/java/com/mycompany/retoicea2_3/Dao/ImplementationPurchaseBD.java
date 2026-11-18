package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Addres;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Purchase;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

public class ImplementationPurchaseBD implements PurchaseDAO {

    @Override
    public void createPurchase(Purchase p) {
        MongoCollection<Document> collection = DBConection.getInstancia().getColeccion("orders");

        Addres a = p.getDeliveryAddress();
        Document address = new Document("streetName", a.getName())
                .append("houseNumber", a.getHouseNumber())
                .append("floor", a.getFloor())
                .append("floorLetter", String.valueOf(a.getLetter()));
        List<Document> products = new ArrayList<>();
        for (Product pr : p.getBoughtProducts()) {
            products.add(new Document("productId", pr.getId())
                    .append("productName", pr.getNombre())
                    .append("price", (double) pr.getPrecio()));
        }
        Document purcahse = new Document("orderId", p.getOrderId())
                .append("orderDate", Date.from(p.getOrderDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .append("deliveryDate", Date.from(p.getDeliveryDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .append("totalPrice", p.getTotalPrice())
                .append("deliveryAddress", address)
                .append("boughtProducts", products);

        collection.insertOne(purcahse);
    }

}
