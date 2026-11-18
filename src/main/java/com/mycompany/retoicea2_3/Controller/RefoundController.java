package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationRefoundBD;
import com.mycompany.retoicea2_3.Dao.RefoundDAO;
import com.mycompany.retoicea2_3.Model.Refound;

/**
 * Controlador encargado de la gestión de devoluciones.
 * Actúa como intermediario entre la interfaz de usuario
 * y la capa de acceso a datos para registrar solicitudes
 * de devolución en el sistema.
 *
 * @author G.2
 */
public class RefoundController {

    private RefoundDAO dao = new ImplementationRefoundBD();


    /**
 * Registra una nueva devolución en el sistema.
 *
 * @param r devolución que se desea almacenar.
 */
    public void createRefound(Refound r) {
        dao.createRefound(r);
    }
}
