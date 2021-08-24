package com.GroceryStore;

import com.GroceryStore.Products.Fruit;
import com.GroceryStore.Products.Product;

public class Main {
  public static void main(String[] args) {
   // Product prod = new Fruit("apple", 129, "p-001", "red apples" ,5);
    //System.out.println(prod);
    Store store = new Store("Jon");
    store.addToInventory("apple", 234, "p-001", "red apple", 5, true);
    System.out.println(store.getInventory());

  }
}
