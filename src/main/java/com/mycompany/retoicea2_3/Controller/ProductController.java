
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationProductBD;
import com.mycompany.retoicea2_3.Dao.ProductDAO;
import com.mycompany.retoicea2_3.Exceptions.ProductExistsException;
import com.mycompany.retoicea2_3.Model.Product;
import javafx.collections.ObservableList;

/**
 * Controlador encargado de la gestión de productos.
 * Actúa como intermediario entre la interfaz de usuario
 * y la capa de acceso a datos para consultar y registrar
 * productos en el sistema.
 *
 * @author G.2
 */
public class ProductController {

    private final ProductDAO dao = new ImplementationProductBD();

    /**
 * Obtiene todos los productos registrados en el sistema.
 *
 * @return lista observable con los productos disponibles.
 */
    public ObservableList<String> showAllProductos() {
        return dao.showAllProductos();
    }

    /**
 * Busca un producto a partir de su nombre.
 *
 * @param name nombre del producto a buscar.
 * @return producto encontrado o null si no existe.
 */
    public Product showOneProduct(String name) {
        return dao.showOneProduct(name);
    }

    /**
 * Añade un nuevo producto al sistema.
 *
 * @param p producto que se desea registrar.
 * @throws ProductExistsException si el producto ya existe.
 */
    public void addProduct(Product p) throws ProductExistsException{
        dao.addProduct(p);
    }
}
