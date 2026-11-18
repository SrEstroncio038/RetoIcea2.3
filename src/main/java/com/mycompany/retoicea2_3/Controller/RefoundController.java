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

<<<<<<< HEAD
    public void createRefound(Refound r) {
=======
    /**
 * Registra una nueva devolución en el sistema.
 *
 * @param r devolución que se desea almacenar.
 */
    public void createRefound(Refounds r) {
>>>>>>> d2dec5f339456f6303c47d497254a2d9681c0a18
        dao.createRefound(r);
    }
}
