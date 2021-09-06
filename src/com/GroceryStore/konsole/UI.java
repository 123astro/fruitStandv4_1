package com.GroceryStore.konsole;

import com.GroceryStore.Store;
import com.GroceryStore.products.Drink;
import com.GroceryStore.products.Fruit;
import com.GroceryStore.products.Product;

import java.util.Scanner;

public abstract class UI {
    protected String[] MENU;
    protected String[] PRODUCT_TYPES;
    protected String[] PRODUCT_FIELDS;
    protected String[] DRINK_FIELDS;
    protected String[] FRUIT_FIELDS;
    protected String[] ERROR_MSGS;
    protected String WELCOME;
    protected String MENU_PROMPT;
    protected String SELECT_PROMPT;
    protected String PRODUCT_PROMPT;
    protected String CANCEL_PROMPT;
    protected Store store;

    protected static Scanner scanner = new Scanner(System.in);

    public UI(Store store) {
        this.store = store;
    }

    public void welcome(String name) {
        System.out.println(WELCOME + name + " !");
    }

    public void displayOptions(String prompt, String[] options) { // prompt is the first line to print ( to do)
        System.out.println(prompt);
        for (String option : options) {
            System.out.println(option);
        }
    }

    public boolean start() { //
        welcome(store.getName());
        displayOptions(MENU_PROMPT, MENU);
        int choice = getInt(1, 6, SELECT_PROMPT);
        return handleMenuSelection(choice);
    }

    public int getInt(int min, int max, String prompt) {
        int option = min - 1;
        do {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_MSGS[1]);
            }
        } while (option < min || option > max);
        return option;
    }

    public String getString(String prompt, boolean isRequired) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine();
            if (isRequired && input.length() == 0) {
                System.out.println(ERROR_MSGS[3]);
                continue;// skip the break
            }
            break; //
        } while (true);
        return input;
    }

    public boolean handleMenuSelection(int choice) {
        switch (choice) {
            case 1 -> addProduct();
            case 2 -> throwAwayProduct();
            case 3 -> displayProduct();
            case 4 -> sellProduct();
            case 5 -> {
                return false;
            }
            // case 6 -> setLang();
            default -> System.out.println(ERROR_MSGS[1]);
        }
        return true;
    }

    private void addProduct() {
        displayOptions(PRODUCT_PROMPT, PRODUCT_TYPES);
        int choice = getInt(1, PRODUCT_TYPES.length, SELECT_PROMPT);
        Product newProduct;
        switch (choice) {
            case 1 -> newProduct = getDrinkDetails();
            case 2 -> newProduct = getFruitDetails();
            default -> {
                System.out.println(ERROR_MSGS[1]);
                newProduct = null;
            }
        }
        store.addToInventory(newProduct);
    }

    private Drink getDrinkDetails() {
        return new Drink(
                getString(PRODUCT_FIELDS[0], true),
                getInt(1, Integer.MAX_VALUE, PRODUCT_FIELDS[1]),
                getString(PRODUCT_FIELDS[2], true),
                getString(PRODUCT_FIELDS[3], false),
                getInt(1, Integer.MAX_VALUE, DRINK_FIELDS[0]),
                getInt(0, Drink.UNITS.length - 1, DRINK_FIELDS[1])
        );
    }

    private Fruit getFruitDetails() {
        return new Fruit(
                getString(PRODUCT_FIELDS[0], true),
                getInt(1, Integer.MAX_VALUE, PRODUCT_FIELDS[1]),
                getString(PRODUCT_FIELDS[2], true),
                getString(PRODUCT_FIELDS[3], false),
                getInt(1, 10, FRUIT_FIELDS[0])
        );
    }

    private void displayProduct() {
        System.out.println(store.getInventory());
    }

    private Product selectProduct(String prompt) {
        displayProduct();
        String choice = getString(prompt, false);
        return store.getProduct(choice);
    }

    private void throwAwayProduct() {
        Product prod = selectProduct(SELECT_PROMPT + " " + CANCEL_PROMPT);
        if (prod == null) {
            System.out.println(ERROR_MSGS[4]);
            return;
        }
        store.throwAway(prod);
    }

    private void sellProduct() {
        Product prod = selectProduct(SELECT_PROMPT + " " + CANCEL_PROMPT);
        if (prod == null) {
            System.out.println(ERROR_MSGS[4]);
            return;
        }
        store.purchase(prod);
    }

}
