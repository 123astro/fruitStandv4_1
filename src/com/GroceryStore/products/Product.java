package com.GroceryStore.products;

import com.util.Formatter;

public class Product {
    private String name;
    private int price;
    public final String id;
    private String description;

    public Product(String name, int price, String id, String description){
        this.name =name;
        this.price = price;
        this.id = id;
        this.description = description;
    }



    @Override
    public String toString(){
        String output = "{id: " + id + " | name: " + name + " | price: " + Formatter.getDisplayPrice(price) + " | ";

        return output;
    }

    public int getPrice() {
        return price;
    }
}
