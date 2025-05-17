/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Restaurant billing implementation that handles 
 * customer orders and bill calculation
 * @author ashongtical
 */

public class RestaurantBilling extends RestaurantBillingSystem {

    protected ArrayList<Meal> order;
    protected HashMap<Meal, Integer> mealQuantities;

    public RestaurantBilling(){
        this.order = new ArrayList<>();
        this.mealQuantities = new HashMap<>();
    }
    //Constructor to initialize order list and meal quantities map


    @Override
    public boolean addMealToOrder(String mealName) {
        for (Meal meal : menu){
            if (meal.getName().equals(mealName)){
                order.add(meal);
                if (mealQuantities.containsKey(meal)){
                Integer currentValue = mealQuantities.get(meal);
                int updatedValue = currentValue + 1;
                mealQuantities.put(meal,updatedValue);
                }else {
                    mealQuantities.put(meal,1);
                }
                return true;
            }
        }
        System.out.println("Menu '" + mealName + "'not found on the menu.");
        return false;
    }
    //Add a meal from the menu to the customer's order
    // mealName: The name of the meal to add
    //return true if meal was added successfully, false otherwise

    @Override
    public double calculateBill() {
        double total = 0;
        for (Meal meal : order){
            total = total + meal.getPrice();
        }
        return total;
    }
    //Override the calculateBill() and calculate the total bill for the customer's order
    //return the total bill amount

    public void clearOrder(){
        order.clear();
        mealQuantities.clear();
        System.out.println("clear all meal");
    }
    //Clear the current order
    
    
    public void displayOrder(){
        for (Map.Entry<Meal, Integer> entry : mealQuantities.entrySet()) {
            System.out.println("mealName: " + entry.getKey() + ", quantities: " + entry.getValue());
        }
        double total = this.calculateBill();
        System.out.println("current total price : " + total);
    }
    //Display the current order with meal quantities and total price


    public String generateBill(String customerName){
        String string = mealQuantities.entrySet().stream()
                .map(entry -> entry.getKey() + " quantity is : " + entry.getValue())
                .collect(Collectors.joining(", "));
        String result = string + "\n" + "customerName : " + customerName;

        return result;
    }
    //Generate a formatted bill for the customer
    //customerName: The name of the customer
    //return String representation of the bill
    
    
}
