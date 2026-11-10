/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Offer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 *
 * @author asola
 */
public class ImplementationOfferBD implements OfferDAO {

    @Override
    public Offer showOneOffer(Offer o) {
        Offer oferta = null;
        MongoCollection<Document> offers = DBConection.getInstancia().getColeccion("offers");
        Document offer = offers.find(Filters.and(Filters.eq("productId", o.getProductId()), Filters.eq("discount", o.getDiscount()), Filters.eq("finishDate", Date.from(o.getFinishDate().atStartOfDay(ZoneId.systemDefault()).toInstant())))).first();
        oferta = new Offer(offer.getObjectId("productId"), offer.getInteger("discount"), offer.getDate("finishDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return oferta;
    }

    @Override
    public void addOffer(Offer o) {
        Document offer = new Document("offerId", new ObjectId())
                .append("productId", o.getProductId())
                .append("discount", o.getDiscount())
                .append("finishDate", Date.from(o.getFinishDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        MongoCollection<Document> ofertas = DBConection.getInstancia().getColeccion("offers");
        ofertas.insertOne(offer);
    }

    @Override
    public void deleteOffer(Offer offer) {
        MongoCollection<Document> ofertas = DBConection.getInstancia().getColeccion("offers");
        ofertas.findOneAndDelete(Filters.eq("productId", offer.getProductId()));
    }

}
