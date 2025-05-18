package RestaurantManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

                mealQuantities.put(meal, mealQuantities.getOrDefault(meal, 0) + 1);
                return true;
            }
        }
        System.out.println("Menu '" + mealName + "' not found on the menu.");
        return false;
    }

    //Add a meal from the menu to the customer's order
    // mealName: The name of the meal to add
    //return true if meal was added successfully, false otherwise

    @Override
    public double calculateBill() {
        double total = 0;
        for (Map.Entry<Meal, Integer> entry : mealQuantities.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
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
