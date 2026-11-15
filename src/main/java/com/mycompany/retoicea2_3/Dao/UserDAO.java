package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Model.Client;
import com.mycompany.retoicea2_3.Model.Product;
import com.mycompany.retoicea2_3.Model.Usuario;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public interface UserDAO {

    public boolean clientRegistration(Client c);

    public ArrayList<Usuario> obtainUser();
}
