
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationPurchaseBD;
import com.mycompany.retoicea2_3.Dao.PurchaseDAO;
import com.mycompany.retoicea2_3.Model.Purchase;

public class PurchaseController {
    private PurchaseDAO dao = new ImplementationPurchaseBD();
    
    public void createPurchase(Purchase p){
        dao.createPurchase(p);
    }
}
