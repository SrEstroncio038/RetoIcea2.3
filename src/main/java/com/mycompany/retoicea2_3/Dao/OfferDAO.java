package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Model.Offer;

public interface OfferDAO {

    public void addOffer(Offer offer);
    
    public void deleteOffer(Offer offer);
    
    public Offer showOneOffer(Offer o);
}
