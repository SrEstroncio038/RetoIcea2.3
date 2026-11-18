
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationProductBD;
import com.mycompany.retoicea2_3.Dao.ProductDAO;
import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
import com.mycompany.retoicea2_3.Model.Product;
import javafx.collections.ObservableList;

public class ProductController {

    private final ProductDAO dao = new ImplementationProductBD();

    public ObservableList<String> showAllProductos() {
        return dao.showAllProductos();
    }

    public Product showOneProduct(String name) {
        return dao.showOneProduct(name);
    }

    public void addProduct(Product p) throws ProductExistsException{
        dao.addProduct(p);
    }
}
