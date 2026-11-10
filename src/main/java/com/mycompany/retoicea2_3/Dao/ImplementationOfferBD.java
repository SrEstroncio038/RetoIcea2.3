/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.MongoCollection;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Offer;
import org.bson.Document;

/**
 *
 * @author asola
 */
public class ImplementationOfferBD implements OfferDAO {

    @Override
    public void addOffer(Offer o) {
        Document offer = new Document("productId", o.getProductId())
                .append("discount", o.getDiscount())
                .append("finishDate", o.getFinishDate());
        MongoCollection<Document> ofertas = DBConection.getInstancia().getColeccion("offers");
        ofertas.insertOne(offer);
    }

}
