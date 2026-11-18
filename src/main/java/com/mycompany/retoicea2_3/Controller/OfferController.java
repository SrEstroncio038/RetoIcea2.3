package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationOfferBD;
import com.mycompany.retoicea2_3.Dao.OfferDAO;
import com.mycompany.retoicea2_3.Model.Offer;

/**
 * Controlador encargado de la gestión de ofertas.
 * Actúa como intermediario entre la interfaz de usuario
 * y la capa de acceso a datos para realizar operaciones
 * relacionadas con las ofertas.
 *
 * @author G.2
 */
public class OfferController {

    private final OfferDAO dao = new ImplementationOfferBD();

    public void addOffer(Offer offer) {
        dao.addOffer(offer);
    }

    public void deleteOffer(Offer offer) {
        dao.deleteOffer(offer);
    }

    /**
 * Obtiene una oferta concreta a partir de los datos proporcionados.
 *
 * @param o oferta utilizada como criterio de búsqueda.
 * @return la oferta encontrada o null si no existe.
 */
    public Offer showOneOffer(Offer o){
        return dao.showOneOffer(o);
    }

}
