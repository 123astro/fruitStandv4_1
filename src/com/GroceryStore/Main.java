package com.GroceryStore;

import com.GroceryStore.console.UI;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("Jons");
        UI ui = new UI();
        while (true) {
            ui.start(store);
        }
    }
}
