package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
import com.mycompany.retoicea2_3.Model.Product;
import javafx.collections.ObservableList;

public interface ProductDAO {
    public ObservableList<String> showAllProductos(); 
    
    public Product showOneProduct(String name);
    
    public void addProduct(Product p) throws ProductExistsException;
}
