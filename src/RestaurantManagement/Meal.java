/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagement;

/**
 * Meal class for managing meals composed of ingredients
 * name: the meals name
 * Ingredients: list of ingrdients
 * @author ashongtical
 */

import java.util.*;
public class Meal implements Priceable {
    private String name;
    private ArrayList<Ingredient> ingredients;

    public Meal(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        StringJoiner joiner = new StringJoiner(", ");
        for (Ingredient item : ingredients) {
            joiner.add(item.getName());
        }
        String result = joiner.toString();
        return result;
    }

    @Override
    public String toString() {
        double price = getPrice();
        return name + " - RMB" + price + "\n" +
               "Ingredients: " + getIngredients();
    }


    @Override
    public double getPrice() {
        double price = 0;
        for (Ingredient i : ingredients){
            price = price + i.getPrice();
        }
        return price;
    }
}
