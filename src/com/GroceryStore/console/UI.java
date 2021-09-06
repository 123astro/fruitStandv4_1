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
        setLang();
    }

//    private final static String[] MENU = new String[]{
//            "1. Add product to inventory",
//            "2. Throw away product to inventory",
//            "3. List products ",
//            "4. Sell product",
//            "5. Quit"
//    };
//
//
//    private final static String[] PRODUCT_TYPES = new String[]{
//            "1. Drink",
//            "2. Fruit"
//    };
//
//    private final static String WELCOME = "Welcome to ";
//    private final static String MENUPROMPT = "What do you want to do?";
//    private final static String SELECT_PROMPT = " Enter Selection";
//    private final static String PRODUCT_PROMPT = "Enter Product";
//    private final static String CANCEL_PROMPT = "PLEASE ENTER TO CANCEL";
//    private final static String[] ERROR_MSGS = new String [] {"OK", "Invalid number", "General error",
//            "Must enter something", "Product not found"};
//    private final static String[] PRODUCT_FIELDS = new String [] {
//            "Name", "Price", "ID", "Description"
//    };
//
//    private final static String[] DRINK_FIELDS = new String[] {
//           "Volume: ", "Volume unit code: "
//    };
//
//    private final static String[] FRUIT_FIELDS = new String[] {
//            "Hardness Level 1-10:", "Is organic"
//    };

    private Language lang;

    private void setLang() {
        int choice = getInt(1,2, "1. English\n2. Español");
        switch (choice) {
            case 1 -> lang = new English();
            case 2 -> lang = new Spanish();
        }
    }

    public static void displayOptions(String prompt, String[] options) { // prompt is the first line to print ( to do)
        System.out.println(prompt);
        for (String option : options) {
            System.out.println(option);
        }
    }

    public void welcome(String name) {
        System.out.println(lang.WELCOME() + name + " !");
    }


    public boolean start() { //
        welcome(store.getName());
        displayOptions(lang.MENU_PROMPT(), lang.MENU());
        int choice = getInt(1, 6, lang.SELECT_PROMPT());
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
                System.out.println(e);
                System.out.println(lang.ERROR_MSGS()[1]);
            } catch (Exception err) {
                System.out.println(lang.ERROR_MSGS()[2]);
            }
//            finally {lang.
//                System.out.println("Something was entered");
//            }
        } while (option < min || option > max);
        return option;
    }

    public String getString(String prompt, boolean isRequired) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine();
            if (isRequired && input.length() == 0) {
                System.out.println(lang.ERROR_MSGS()[3]);
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
            case 6 -> setLang();
            default -> System.out.println(lang.ERROR_MSGS()[1]);
        }
        return true;
    }

    private void addProduct() {
        displayOptions(lang.PRODUCT_PROMPT(), lang.PRODUCT_TYPES());
        int choice = getInt(1, lang.PRODUCT_TYPES().length, lang.SELECT_PROMPT());
        Product newProduct;
        switch (choice) {
            case 1 -> newProduct = getDrinkDetails();
            case 2 -> newProduct = getFruitDetails();
            default -> {
                System.out.println(lang.ERROR_MSGS()[1]);
                newProduct = null;
            }
        }
        store.addToInventory(newProduct);
    }

    private Fruit getFruitDetails() {
        return new Fruit(
                getString(lang.PRODUCT_FIELDS()[0], true),
                getInt(1, Integer.MAX_VALUE, lang.PRODUCT_FIELDS()[1]),
                getString(lang.PRODUCT_FIELDS()[2], true),
                getString(lang.PRODUCT_FIELDS()[3], false),
                getInt(1, 10, lang.FRUIT_FIELDS()[0])
        );
    }

    private Drink getDrinkDetails() {
        return new Drink(
                getString(lang.PRODUCT_FIELDS()[0], true),
                getInt(1, Integer.MAX_VALUE, lang.PRODUCT_FIELDS()[1]),
                getString(lang.PRODUCT_FIELDS()[2], true),
                getString(lang.PRODUCT_FIELDS()[3], false),
                getInt(1, Integer.MAX_VALUE, lang.DRINK_FIELDS()[0]),
                getInt(0, Drink.UNITS.length - 1, lang.DRINK_FIELDS()[1])
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
        Product prod = selectProduct(lang.SELECT_PROMPT() + " " + lang.CANCEL_PROMPT());
        if (prod == null) {
            System.out.println(lang.ERROR_MSGS()[4]);
            return;
        }
        store.throwAway(prod);
    }

    private void sellProduct() {
        Product prod = selectProduct(lang.SELECT_PROMPT() + " " + lang.CANCEL_PROMPT());
        if (prod == null) {
            System.out.println(lang.ERROR_MSGS()[4]);
            return;
        }
        store.purchase(prod);
    }
}

