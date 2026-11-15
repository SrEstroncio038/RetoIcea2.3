/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationUserBD;
import com.mycompany.retoicea2_3.Dao.UserDAO;
import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author asola
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
