package com.GroceryStore;

//import com.GroceryStore.console.UI;
import com.GroceryStore.konsole.English;
import com.GroceryStore.konsole.UI;


public class Main {
    public static void main(String[] args) {
        Store store = new Store("Keith's");
        UI ui = new English(store);
        while (ui.start()) ;
    }
}