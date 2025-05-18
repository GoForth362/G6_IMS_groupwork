package BankingTaskManagement;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    ArrayList<String> highPriority = new ArrayList<>();
    ArrayList<String> lowPriority = new ArrayList<>();
    HashMap<String, ArrayList<String>> priorities = new HashMap<>();

    public TaskManager() {
        priorities.put("high", highPriority);
        priorities.put("low", lowPriority);
    }

    public void addTask(String task) {
        char ch = '*';
        int index = task.indexOf(ch);
        if (index != -1) {
            highPriority.add(task);
        } else {
            lowPriority.add(task);
        }
    }

    public void removeTask(String priority, int index) {
        priorities.get(priority).remove(index);
    }

    public void changePriority(String priority, int index) {
        if (priority.equals("high")) {
            lowPriority.add(priorities.get(priority).get(index));
            removeTask(priority, index);
        } else if (priority.equals("low")) {
            highPriority.add(priorities.get(priority).get(index));
            removeTask(priority, index);
        }
    }

    public void promoteTask(int index) {
        int i = 0;
        String task = highPriority.get(index);
        highPriority.set(index, highPriority.get(i));
        highPriority.set(i, task);
    }

    public ArrayList<String> getHighPriorityTasks() {
        return new ArrayList<>(highPriority);
    }

    public ArrayList<String> getLowPriorityTasks() {
        return new ArrayList<>(lowPriority);
    }

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
