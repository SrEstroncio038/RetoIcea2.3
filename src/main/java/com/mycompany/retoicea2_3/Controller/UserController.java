package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationUserBD;
import com.mycompany.retoicea2_3.Dao.UserDAO;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Usuario;
import java.util.ArrayList;

/**
 * Controlador encargado de la gestión de usuarios.
 * Actúa como intermediario entre la interfaz de usuario
 * y la capa de acceso a datos para consultar usuarios
 * y registrar nuevos clientes en el sistema.
 *
 * @author G.2
 */
public class UserController {

    private UserDAO dao = new ImplementationUserBD();

    public ArrayList<Usuario> obtainUser(){
        return dao.obtainUser();
    }
    
    public boolean clientRegistration(Client c){
        return dao.clientRegistration(c);
    }
}
