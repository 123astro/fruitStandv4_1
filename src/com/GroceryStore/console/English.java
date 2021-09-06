package com.GroceryStore.console;

public class English implements Language {

    @Override
    public String[] MENU() {
        return new String[]{
                "1. Add product to inventory",
                "2. Throw away product to inventory",
                "3. List products ",
                "4. Sell product",
                "5. Quit",
                "6. Change Language"
        };
    }

    @Override
    public String[] PRODUCT_TYPES() {
        return new String[]{
                "1. Drink",
                "2. Fruit"
        };
    }

    @Override
    public String WELCOME() {
        return "Welcome to ";
    }

    @Override
    public String MENU_PROMPT() {
        return "What do you want to do?";
    }

    @Override
    public String SELECT_PROMPT() {
        return " Enter Selection";
    }

    @Override
    public String PRODUCT_PROMPT() {
        return "Enter Product";
    }

    @Override
    public String CANCEL_PROMPT() {
        return "PLEASE ENTER TO CANCEL";
    }

    @Override
    public String[] ERROR_MSGS() {
        return new String[]{"OK", "Invalid number", "General error",
                "Must enter something", "Product not found"};
    }

    @Override
    public String[] PRODUCT_FIELDS() {
        return new String[]{
                "Name", "Price", "ID", "Description"
        };
    }

    @Override
    public String[] DRINK_FIELDS() {
        return new String[]{
                "Volume: ", "Volume unit code: "
        };
    }

    @Override
    public String[] FRUIT_FIELDS() {
        return new String[]{
                "Hardness Level 1-10:", "Is organic"
        };
    }
}