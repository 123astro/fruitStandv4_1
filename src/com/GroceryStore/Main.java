package com.GroceryStore;

import com.GroceryStore.console.UI;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("Keith's");
        UI ui = new UI(store);
        while (ui.start()) ;
    }
}