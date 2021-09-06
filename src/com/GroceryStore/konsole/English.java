package com.GroceryStore.konsole;

import com.GroceryStore.Store;

import java.util.Scanner;

public class English extends UI {
   // protected Store store;
    public English(Store store) {
        super(store);


        MENU = new String[]{
                "1. Add product to inventory",
                "2. Throw away product to inventory",
                "3. List products ",
                "4. Sell product",
                "5. Quit"
        };

        PRODUCT_TYPES = new String[]{
                "1. Drink ",
                "2. Fruit "
        };
        PRODUCT_FIELDS = new String[]{
                "Name: ", "Price: ", "ID: ", "Description: "
        };
        DRINK_FIELDS = new String[]{
                "Volume: ", "Volume unit code: "
        };
        FRUIT_FIELDS = new String[]{
                "Hardness Level: ", "Is Organic: "
        };
        ERROR_MSGS = new String[]{"OK", "Invalid number", "General error",
                "Must enter something", "Product not found"};

        WELCOME = "Welcome to ";
        MENU_PROMPT = "What do you want to do?";
        SELECT_PROMPT = " Enter Selection";
        PRODUCT_PROMPT = "Enter Product";
        CANCEL_PROMPT = "PLEASE ENTER TO CANCEL";
    }

    protected static Scanner scanner = new Scanner(System.in);

}


