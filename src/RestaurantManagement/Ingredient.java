/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagement;

/**
 * Ingredient class for managing individual ingredients
 * name: The name of the ingredient
 * price: The price of the ingredient
 * @author ashongtical
 */
public class Ingredient implements Priceable {
    private String name;
    private double price;

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  name + " (RMB" + price + ")";
    }
}
