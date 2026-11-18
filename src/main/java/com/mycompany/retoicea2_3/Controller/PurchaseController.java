
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationPurchaseBD;
import com.mycompany.retoicea2_3.Dao.PurchaseDAO;
import com.mycompany.retoicea2_3.Model.Purchase;

/**
 * Controlador encargado de la gestión de compras.
 * Actúa como intermediario entre la interfaz de usuario
 * y la capa de acceso a datos para registrar las compras
 * realizadas por los clientes.
 *
 * @author G.2
 */
public class PurchaseController {
    private PurchaseDAO dao = new ImplementationPurchaseBD();
    
    public void createPurchase(Purchase p){
        dao.createPurchase(p);
    }
}
