package com.GroceryStore;

import com.GroceryStore.Products.Product;
import com.GroceryStore.Products.Fruit;
import com.GroceryStore.Products.Drink;

import java.util.ArrayList;
import java.util.List;

public class Store {

    public List<Product> getInventory() {
        return inventory;
    }

    private List<Product> inventory = new ArrayList<>();
    private int balance = 500_00;  //set your default value here.
    private String name;

    public Store(String name){
        this.name = name;
    }

    // TODO: add given product to the collection
    public void addToInventory(Product product){
    inventory.add(product);
    }

     //TODO : DRINK VERSION
    public void addToInventory(String name, int price, String id, String description, int volume, String volumeUnit) {
        Drink drink = new Drink(name, price, id, description, volume, volumeUnit);
        addToInventory(drink);
    }

    public void addToInventory(String name, int price, String id, String description, int volume, int volumeUnit) {
        Drink drink = new Drink(name, price, id, description, volume, volumeUnit);
        addToInventory(drink);
    }


 //TODO :Fruit version

    public void addToInventory(String name, int price, String id, String description, int hardness, boolean isOrganic) {
        Fruit fruit = new Fruit(name, price, id,  description, hardness, isOrganic);
        addToInventory(fruit);
    }

    public void addToInventory(String name, int price, String id, String description, int hardness) {
        Fruit fruit = new Fruit(name, price, id,  description, hardness);
        addToInventory(fruit);
    }

}
