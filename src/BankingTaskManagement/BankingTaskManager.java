package BankingTaskManagement;

import java.util.ArrayList;
import java.util.HashMap;

public class BankingTaskManager {

    HashMap<String, BankAccount> accounts;
    protected TaskManager taskManager;

    public BankingTaskManager() {
        this.accounts = new HashMap<>();
        this.taskManager = new TaskManager();
    }

    public void createAccount(String accountNumber, double balance, double annualInterestRate) {
        if (balance < 25) {
            System.out.println("Account " + accountNumber + " is not active due to insufficient balance.");
            return;
        } else if (annualInterestRate < 0 || annualInterestRate > 100) {
            System.out.println("Account " + accountNumber + " is not available due to incorrect input!");
            return;
        }
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account " + accountNumber + " already exists!");
            return;
        }

        BankAccount bankAccount = new BankAccount(balance, annualInterestRate);
        accounts.put(accountNumber, bankAccount);
        System.out.println("Account " + accountNumber + " has been created successfully!");
        viewAccountDetails(accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            account.deposit(amount);
            taskManager.addTask("Deposit RMB " + amount + " to Account " + accountNumber);
            System.out.println("Account " + accountNumber + " deposit successful!");
            updateTaskLists();
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            if (account.getBalance() >= 25 && (account.getBalance() - amount >= 0)) {
                account.withdraw(amount);
                taskManager.addTask("Withdraw RMB " + amount + " from Account " + accountNumber);
                System.out.println("Account " + accountNumber + " withdraw successful!");
                updateTaskLists();
            } else {
                System.out.println("Balance must be at least RMB 25.");
            }
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void viewAccountDetails(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.get(accountNumber).displayAccountDetails();
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void viewTasks() {
        taskManager.displayTasks();
    }

    public void addTask(String task) {
        taskManager.addTask(task);
    }

    public void removeTask(String priority, int index) {
        taskManager.removeTask(priority, index);
    }

    public void changePriority(String priority, int index) {
        taskManager.changePriority(priority, index);
    }

    public void promoteTask(int index) {
        taskManager.promoteTask(index);
    }

    public ArrayList<String> getHighPriorityTasks() {
        return taskManager.getHighPriorityTasks();
    }

    public ArrayList<String> getLowPriorityTasks() {
        return taskManager.getLowPriorityTasks();
    }

    public void updateTaskLists() {
        // This should be implemented in GUI class
    }
    public boolean accountExists(String accountNumber) {
    return accounts.containsKey(accountNumber);
}

}
