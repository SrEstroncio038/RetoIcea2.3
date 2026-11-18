package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Usuario;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
Interfaz DAO encargada de definir las operaciones relacionadas
con la gestión de usuarios y el registro de clientes.

@author G.2
*/
public interface UserDAO {

    public boolean clientRegistration(Client c);

    public ArrayList<Usuario> obtainUser();
}
