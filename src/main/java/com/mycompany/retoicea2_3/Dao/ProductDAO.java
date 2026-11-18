package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
import com.mycompany.retoicea2_3.Model.Product;
import javafx.collections.ObservableList;

/**
Interfaz DAO encargada de definir las operaciones de consulta y gestión de productos.

@author G.2
*/
public interface ProductDAO {
    public ObservableList<String> showAllProductos(); 
    
    public Product showOneProduct(String name);
    
    public void addProduct(Product p) throws ProductExistsException;
}
