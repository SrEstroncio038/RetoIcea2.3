/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationProductBD;
import com.mycompany.retoicea2_3.Dao.ProductDAO;
import com.mycompany.retoicea2_3.Model.Product;
import javafx.collections.ObservableList;

/**
 *
 * @author asola
 */
public class ProductController {

    private final ProductDAO dao = new ImplementationProductBD();

    public ObservableList<String> showAllProductos() {
        return dao.showAllProductos();
    }

    public Product showOneProduct(String name) {
        return dao.showOneProduct(name);
    }

    public void addProduct(Product p) {
        dao.addProduct(p);
    }
}
