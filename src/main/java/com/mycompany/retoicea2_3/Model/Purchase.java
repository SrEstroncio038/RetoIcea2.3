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
public class Purchase {
    private int number;
    private LocalDate deliveryDate;
    private float totalPrice;
    private ArrayList<Product> products;
    private LocalDate buyDate;
    private Address address;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Purchase() {
    }

    public Purchase(int number, LocalDate deliveryDate, float totalPrice, ArrayList<Product> products, LocalDate buyDate, Address address) {
        this.number = number;
        this.deliveryDate = deliveryDate;
        this.totalPrice = totalPrice;
        this.products = products;
        this.buyDate = buyDate;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Purchase{" + "number=" + number + ", deliveryDate=" + deliveryDate + ", totalPrice=" + totalPrice + ", products=" + products + ", buyDate=" + buyDate + ", address=" + address + '}';
    }
}
