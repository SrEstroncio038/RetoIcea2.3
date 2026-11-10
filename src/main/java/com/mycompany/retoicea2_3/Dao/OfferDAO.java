/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Model.Offer;

/**
 *
 * @author asola
 */
public interface OfferDAO {

    public void addOffer(Offer offer);
    
    public void deleteOffer(Offer offer);
    
    public Offer showOneOffer(Offer o);
}
