/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingTaskManagement;

import java.util.ArrayList;
import java.util.HashMap;



public class TaskManager {

    ArrayList<String> highPriority = new ArrayList<>();
    ArrayList<String> lowPriority = new ArrayList<>();
    //use ArrayList to manage the data
    HashMap<String,ArrayList<String>> priorities = new HashMap<>();


    public TaskManager() {
        priorities.put("high",highPriority);
        priorities.put("low",lowPriority);
        //ArrayList<ArrayList<String>> priorities = new ArrayList<>();
       //create new ArrayList object for priorities
    }

    // Add a task
    public void addTask(String task) {
        char ch = '*';
        int index = task.indexOf(ch);
        if (index != -1) {
            highPriority.add(task);
        }else {
            lowPriority.add(task);
        }
    }

    // Remove a task
    public void removeTask(String priority, int index) {
        priorities.get(priority).remove(index);
    }

    // Change task priority
    // allows moving tasks between high and low priority lists
    public void changePriority(String priority, int index) {
        if (priority.equals("high")){
            lowPriority.add(priorities.get(priority).get(index));
            removeTask(priority,index);
        }else if(priority.equals("low")){
            highPriority.add(priorities.get(priority).get(index));
            removeTask(priority,index);
        }
    }

    // Promote a high-priority task 
    //moves a high-priority task up in the list
    public void promoteTask(int index) {
        int i = 0;
        String task = highPriority.get(index);
        highPriority.set(index, highPriority.get(i));
        highPriority.set(i, task);
    }
    
//     list getters
    // Get high-priority tasks
    // return copies of the lists to avoid direct modification
    public ArrayList<String> getHighPriorityTasks() {
        return new ArrayList<>(highPriority);
    }

    // Get low-priority tasks
    // return copies of the lists to avoid direct modification
    public ArrayList<String> getLowPriorityTasks() {
        return new ArrayList<>(lowPriority);
    }

    // Display tasks
    public void displayTasks() {
        System.out.println("\nHigh Priority Tasks:");
        for (int i = 0; i < highPriority.size(); i++) {
            System.out.println(i + ". " + highPriority.get(i));
        }

        System.out.println("\nLow Priority Tasks:");
        for (int i = 0; i < lowPriority.size(); i++) {
            System.out.println(i + ". " + lowPriority.get(i));
        }
    }
}
