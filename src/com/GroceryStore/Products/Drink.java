package com.GroceryStore.Products;

public class Drink extends Product {
    private final static String[] UNITS = new String[]{"Gal", "oz", "liters", "ml", "pint", "qt"};
    private int volume;
    private String volumeUnit;

    public Drink(String name, int price, String id, String description, int volume, String volumeUnit){
        super(name, price, id, description);
        this.volume = volume;
        this.volumeUnit = volumeUnit;
    }
    public Drink(String name, int price, String id, String description, int volume, int volumeUnit){
        super(name, price, id, description);
        this.volume = volume;
        this.volumeUnit = UNITS[volumeUnit];
    }
}

