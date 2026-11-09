/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationProductBD;
import com.mycompany.retoicea2_3.Dao.ProductDAO;
import javafx.collections.ObservableList;

/**
 *
 * @author asola
 */
public class ProductController {
    private final ProductDAO dao = new ImplementationProductBD();
    
    public ObservableList<String> mostrarProductos(){
        return dao.mostrarProductos();
    }
}
