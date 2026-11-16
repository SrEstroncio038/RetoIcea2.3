/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationPurchaseBD;
import com.mycompany.retoicea2_3.Dao.PurchaseDAO;
import com.mycompany.retoicea2_3.Model.Purchase;

/**
 *
 * @author asola
 */
public class PurchaseController {
    private PurchaseDAO dao = new ImplementationPurchaseBD();
    
    public void createPurchase(Purchase p){
        dao.createPurchase(p);
    }
}
