/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author asola
 */
public class Refounds {

    private int number;
    private LocalDate refoundDate;
    private float totalPrice;
    private ArrayList<Product> products;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getRefoundDate() {
        return refoundDate;
    }

    public void setRefoundDate(LocalDate refoundDate) {
        this.refoundDate = refoundDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Refounds(int number, LocalDate refoundDate, float totalPrice, ArrayList<Product> products) {
        this.number = number;
        this.refoundDate = refoundDate;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public Refounds() {
    }

    @Override
    public String toString() {
        return "Refounds{" + "number=" + number + ", refoundDate=" + refoundDate + ", totalPrice=" + totalPrice + ", products=" + products + '}';
    }
    
    
}
