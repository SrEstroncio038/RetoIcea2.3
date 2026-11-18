package com.mycompany.retoicea2_3.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import org.bson.types.ObjectId;

public class Refound {

    private ObjectId refundId;
    private LocalDate refundDate;
    private double totalPrice;
    private ArrayList<Product> refundProducts;

    public Refound() {
    }

    public Refound(ObjectId refundId, LocalDate refundDate, double totalPrice, ArrayList<Product> refundProducts) {
        this.refundId = refundId;
        this.refundDate = refundDate;
        this.totalPrice = totalPrice;
        this.refundProducts = refundProducts;
    }

    public ObjectId getRefundId() {
        return refundId;
    }

    public void setRefundId(ObjectId refundId) {
        this.refundId = refundId;
    }

    public LocalDate getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Product> getRefundProducts() {
        return refundProducts;
    }

    public void setRefundProducts(ArrayList<Product> refundProducts) {
        this.refundProducts = refundProducts;
    }

    @Override
    public String toString() {
        return refundDate + " - " + totalPrice + " €";
    }
}
