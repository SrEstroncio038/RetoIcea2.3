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
Implementación de la interfaz OfferDAO para gestionar las ofertas
mediante una base de datos MongoDB.
Permite consultar, insertar y eliminar ofertas de la colección offers.

@author G.2
*/
public class ImplementationOfferBD implements OfferDAO {

    /**
Busca una oferta en la base de datos utilizando el identificador
del producto, el descuento y la fecha de finalización.

@param o oferta con los datos necesarios para realizar la búsqueda.
@return oferta encontrada en la base de datos.
*/
    @Override
    public Offer showOneOffer(Offer o) {
        Offer oferta = null;
        MongoCollection<Document> offers = DBConection.getInstancia().getColeccion("offers");
        Document offer = offers.find(Filters.and(Filters.eq("productId", o.getProductId()), Filters.eq("discount", o.getDiscount()), Filters.eq("finishDate", Date.from(o.getFinishDate().atStartOfDay(ZoneId.systemDefault()).toInstant())))).first();
        oferta = new Offer(offer.getObjectId("productId"), offer.getInteger("discount"), offer.getDate("finishDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return oferta;
    }

    /**
Añade una nueva oferta a la colección offers de MongoDB.
Genera un identificador único para la oferta y almacena
el producto, el descuento y la fecha de finalización.

@param o oferta que se desea añadir a la base de datos.
*/
    @Override
    public void addOffer(Offer o) {
        Document offer = new Document("offerId", new ObjectId())
                .append("productId", o.getProductId())
                .append("discount", o.getDiscount())
                .append("finishDate", Date.from(o.getFinishDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        MongoCollection<Document> ofertas = DBConection.getInstancia().getColeccion("offers");
        ofertas.insertOne(offer);
    }

    /**
Elimina una oferta de la colección offers utilizando
el identificador del producto asociado.

@param offer oferta que se desea eliminar.
*/
    @Override
    public void deleteOffer(Offer offer) {
        MongoCollection<Document> ofertas = DBConection.getInstancia().getColeccion("offers");
        ofertas.findOneAndDelete(Filters.eq("productId", offer.getProductId()));
    }

}
