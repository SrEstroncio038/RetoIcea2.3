/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationProductBD;
import com.mycompany.retoicea2_3.Dao.ProductDAO;
import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
import com.mycompany.retoicea2_3.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asola
 */
public class ProductController {

    private final ProductDAO dao = new ImplementationProductBD();

    public ObservableList<Product> showAllProductos() {
        return dao.showAllProductos();
    }
    
    public ObservableList<String> showAllProductNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Product p : dao.showAllProductos()) {
            names.add(p.getNombre());
        }
        return names;
    }

    public Product showOneProduct(String name) {
        return dao.showOneProduct(name);
    }

    public void addProduct(Product p) throws ProductExistsException{
        dao.addProduct(p);
    }
    
    public void removeProduct(String productName) {
        dao.removeProduct(productName);
    }
}
