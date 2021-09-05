package com.GroceryStore.console;

import com.GroceryStore.Store;
import com.GroceryStore.products.Drink;
import com.GroceryStore.products.Fruit;
import com.GroceryStore.products.Product;

import java.util.Scanner;


public class UI {
    private Store store;
    private static Scanner scanner = new Scanner(System.in);

    public UI(Store store) {  //CONSTRUCTOR
        this.store = store;
    }

    private final static String[] MENU = new String[]{
            "1. Add product to inventory",
            "2. Throw away product to inventory",
            "3. List products ",
            "4. Sell product",
            "5. Quit"
    };


    private final static String[] PRODUCT_TYPES = new String[]{
            "1. Drink",
            "2. Fruit"
    };

    private final static String WELCOME = "Welcome to";
    private final static String MENUPROMPT = "What do you want to do?";
    private final static String SELECT_PROMPT = " Enter selection";
    private final static String PRODUCT_PROMPT = "Enter Product";
    private final static String[] ERROR_MSGS = new String [] {"OK", "Invalid number", "General error"};

    public static void displayOptions(String prompt, String[] options) { // prompt is the first line to print ( to do)
        System.out.println(prompt);
        for (String option : options) {
            System.out.println(option);
        }
    }

    public static void welcome(String name) {
        System.out.println(WELCOME + name + " !");
    }


    public boolean start() { //
        welcome(store.getName());
        displayOptions(MENUPROMPT, MENU);
        int choice = getInt(1, 5, SELECT_PROMPT);
        return handleMenuSelection(choice);
    }

    public static int getInt(int min, int max, String prompt) {
        int option = min - 1;
        do {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(e);
                System.out.println("Input numbers only!");
            }
//            finally {
//                System.out.println("Something was entered");
//            }
        } while (option < min || option > max);
        return option;
    }

    public static String getString(String prompt, boolean isRequired) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine();
            if (isRequired && input.length() == 0) {
                System.out.println("Must enter something");
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
            default -> System.out.println("Invalid num received");
        }
        return true;
    }

    private void addProduct() {
        displayOptions(MENUPROMPT, PRODUCT_TYPES);
        int choice = getInt(1, PRODUCT_TYPES.length, SELECT_PROMPT);
        Product newProduct;
        switch (choice) {
            case 1 -> newProduct = getDrinkDetails();
            case 2 -> newProduct = getFruitDetails();
            default -> {
                System.out.println("error bad type");
                newProduct = null;
            }
        }
        store.addToInventory(newProduct);
    }

    private static Fruit getFruitDetails() {
        return new Fruit(
                getString("Enter fruit name", true),
                getInt(1, Integer.MAX_VALUE, "Price?"),
                getString("ID", true),
                getString("Description", false),
                getInt(1, 10, "Hardness 1-10?")
        );
    }

    private static Drink getDrinkDetails() {
        return new Drink(
                getString("DrinkName", true),
                getInt(1, Integer.MAX_VALUE, "Price?"),
                getString("Id ", true),
                getString("Description", false),
                getInt(1, Integer.MAX_VALUE, "Volume"),
                getInt(0, Drink.UNITS.length - 1, "Volume unit")
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
        Product prod = selectProduct("Which product id go throw away? press enter to cancel");
        if (prod == null) {
            System.out.println("404 - product not found");
            return;
        }
        store.throwAway(prod);
    }

    private void sellProduct() {
        Product prod = selectProduct("Which product would you like to sell? Press enter to cancel?");
        if (prod == null) {
            System.out.println("404 - product not found");
            return;
        }
        store.purchase(prod);
    }
}

