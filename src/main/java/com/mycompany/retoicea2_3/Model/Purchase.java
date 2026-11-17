package com.mycompany.retoicea2_3.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import org.bson.types.ObjectId;

public class Purchase {

    private ObjectId orderId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private double totalPrice;
    private Addres deliveryAddress;
    private ArrayList<Product> boughtProducts;

    public ObjectId getOrderId() {
        return orderId;
    }

    public void setOrderId(ObjectId orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Addres getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Addres deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public ArrayList<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(ArrayList<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Purchase() {
    }

    public Purchase(ObjectId orderId, LocalDate orderDate, LocalDate deliveryDate, double totalPrice, Addres deliveryAddress, ArrayList<Product> boughtProducts) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.boughtProducts = boughtProducts;
    }

    @Override
    public String toString() {
        return orderDate + " - " + boughtProducts.size() + " productos - " + totalPrice + " €";
    }
}
