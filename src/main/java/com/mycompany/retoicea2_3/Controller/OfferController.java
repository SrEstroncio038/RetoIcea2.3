package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationOfferBD;
import com.mycompany.retoicea2_3.Dao.OfferDAO;
import com.mycompany.retoicea2_3.Model.Offer;

public class OfferController {

    private final OfferDAO dao = new ImplementationOfferBD();

    public void addOffer(Offer offer) {
        dao.addOffer(offer);
    }

    public void deleteOffer(Offer offer) {
        dao.deleteOffer(offer);
    }

    public Offer showOneOffer(Offer o){
        return dao.showOneOffer(o);
    }

}
