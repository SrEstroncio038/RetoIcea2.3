/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Model;

/**
 *
 * @author asola
 */
class Product {
    private int id;
    private String nombre;
    private float precio;
    private int stock;
    private boolean seminuevo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isSeminuevo() {
        return seminuevo;
    }

    public void setSeminuevo(boolean seminuevo) {
        this.seminuevo = seminuevo;
    }

    public Product() {
    }
    
    public Product(int id, String nombre, float precio, int stock, boolean seminuevo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.seminuevo = seminuevo;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", seminuevo=" + seminuevo + '}';
    }
    
}
