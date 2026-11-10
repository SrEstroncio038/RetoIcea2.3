/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationOfferBD;
import com.mycompany.retoicea2_3.Dao.OfferDAO;
import com.mycompany.retoicea2_3.Model.Offer;

/**
 *
 * @author asola
 */
public class OfferController {
    private final OfferDAO dao = new ImplementationOfferBD();
    
    public void addOffer(Offer o){
        dao.addOffer(o);
    }
}
