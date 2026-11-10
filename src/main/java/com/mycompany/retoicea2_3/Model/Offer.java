/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Model;

import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author asola
 */
public class Offer {

    private ObjectId productId;
    private int discount;
    private LocalDate finishDate;

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Offer(ObjectId productId, int discount, LocalDate finishDate) {
        this.productId = productId;
        this.discount = discount;
        this.finishDate = finishDate;
    }

    public Offer() {
    }

    @Override
    public String toString() {
        return "Offer{" + "productId=" + productId + ", discount=" + discount + ", finishDate=" + finishDate + '}';
    }

    
    
}
