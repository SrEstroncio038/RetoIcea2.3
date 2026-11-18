package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Model.Offer;

/**
Interfaz que define las operaciones para gestionar las ofertas.
Establece los métodos necesarios para añadir, eliminar
y consultar ofertas en la base de datos.

@author G.2
*/
public interface OfferDAO {

    public void addOffer(Offer offer);
    
    public void deleteOffer(Offer offer);
    
    public Offer showOneOffer(Offer o);
}
