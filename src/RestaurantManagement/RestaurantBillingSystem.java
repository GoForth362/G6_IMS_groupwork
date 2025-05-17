/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagement;

import java.util.ArrayList;

/**
 * Abstract class that provides structure 
 * for the billing system
 * @author ashongtical
 */

public abstract class RestaurantBillingSystem {

    public ArrayList<Meal> menu;
    public RestaurantBillingSystem() {
        this.menu = new ArrayList<>();
    }
   //Constructor to initialize the menu


    public void addMeal(Meal meal){
        if (menu.contains(meal)){
            return;
        }
        menu.add(meal);
        System.out.println("Add '" + meal.getName() + "' to the menu.");
    }
    //Add a meal to the menu
    //meal: The meal to add


    public boolean removeMeal(String mealName) {
        if (menu.isEmpty()) {
            System.out.println("No meal in menu to remove.");
            return false;
        }

        for (Meal meal : menu) {
            if (meal.getName().equals(mealName)) {
                menu.remove(meal);
                System.out.println("Removed '" + mealName + "' from the menu.");
                return true;
            } else {
                System.out.println(mealName + " not found in menu.");
            }
        }
        return false;
    }
    //Remove a meal from the menu by name
    //mealName The name of the meal to remove
    //return true if meal was removed, false otherwise


    public void displayMenu(){
        if (menu.isEmpty()){
            System.out.println("The menu is currently empty.");
        }else {
            System.out.println("\n========= MENU ==========");
            for (Meal meal : menu) {
                System.out.println(meal);
            }
            System.out.println("==========================");
        }
    }
    //Display all meals on the menu


    public void findMealByName(String mealName){
        for (Meal meal : menu) {
            if (meal.getName().equals(mealName)) {
                System.out.println(mealName + " found in menu.");
            } else {
                System.out.println(mealName + " not found in menu.");
            }
        }
    }
    //Find a meal on the menu by name
    //mealName: The name of the meal to find
    //The meal if found, null otherwise


    public abstract boolean addMealToOrder(String mealName);
    //Abstract method for adding a meal to order
    //mealName The name of the meal to add to the order
    // return boolean indicating success or failure



    public abstract double calculateBill();
    //Abstract method for calculating the bill
    //return The total bill amount

}
