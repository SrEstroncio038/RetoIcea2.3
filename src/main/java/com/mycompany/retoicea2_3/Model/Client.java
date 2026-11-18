package com.mycompany.retoicea2_3.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client extends Usuario {

    private int id;
    private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
    private ArrayList<Refound> refounds = new ArrayList<Refound>();
    private ArrayList<Product> soldProducts = new ArrayList<Product>();
    private ArrayList<Product> cart = new ArrayList<Product>();

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(Product cart) {
        this.cart.add(cart);
    }

    public void deleteCart() {
        this.cart = new ArrayList<Product>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchase purchases) {
        this.purchases.add(purchases);
    }

    public ArrayList<Refound> getRefounds() {
        return refounds;
    }

    public void setRefounds(Refound refounds) {
        this.refounds.add(refounds);
    }

    public ArrayList<Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Product soldProducts) {
        this.soldProducts.add(soldProducts);
    }

    public Client() {
    }

    public Client(String name, String surname, String birthPlace, String pasword, String user, LocalDate birthDate, Addres address) {
        super(name, surname, birthPlace, pasword, user, birthDate, address);
        this.purchases = new ArrayList<Purchase>();
        this.refounds = new ArrayList<Refound>();
        this.soldProducts = new ArrayList<Product>();
    }

}
