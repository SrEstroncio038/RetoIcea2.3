/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
import com.mycompany.retoicea2_3.Model.Product;
import javafx.collections.ObservableList;

/**
 *
 * @author asola
 */
public interface ProductDAO {
    public ObservableList<String> showAllProductos(); 
    
    public Product showOneProduct(String name);
    
    public void addProduct(Product p) throws ProductExistsException;
}
