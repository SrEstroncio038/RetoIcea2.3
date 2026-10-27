/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Model;

import java.util.ArrayList;

/**
 *
 * @author asola
 */
public class Client extends Usuario {
    private int id;
    private ArrayList<Purchase> purchases;
    private ArrayList<Refounds> refounds;
    private ArrayList<Product> soldProducts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    public ArrayList<Refounds> getRefounds() {
        return refounds;
    }

    public void setRefounds(ArrayList<Refounds> refounds) {
        this.refounds = refounds;
    }

    public ArrayList<Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ArrayList<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }
    
    
}
