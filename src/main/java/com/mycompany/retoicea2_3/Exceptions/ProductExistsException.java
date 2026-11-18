package com.mycompany.retoicea2_3.Exceptions;

/**
Excepción que se lanza cuando se intenta añadir un producto
que ya existe.

@author G.2
*/
public class ProductExistsException  extends Exception{
    public ProductExistsException(String mensaje){
        super(mensaje);
    }
}
