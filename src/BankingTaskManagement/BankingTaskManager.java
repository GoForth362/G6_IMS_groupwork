/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingTaskManagement;

import java.util.ArrayList;
import java.util.HashMap;

public class BankingTaskManager {

    public HashMap<String, BankAccount> accounts;
    // use HashMap for new accounts, task manager should TaskManager type
    public TaskManager taskManager;
    //create new HashMap object for accounts and new object for Task Manager
    public BankingTaskManager() {
        this.accounts = new HashMap<>();
        this.taskManager = new TaskManager();
    }

    // Create (add) a new bank account
    public void createAccount(String accountNumber, double balance, double annualInterestRate) {
        //if balance is less than 25, print error message and return
        if (balance < 25) {
            System.out.println("Account " + accountNumber + " is not active due to insufficient balance.");
            return;
        }else if (annualInterestRate < 0 || annualInterestRate > 100){
            System.out.println("Account " + accountNumber + " is not available due to incorrect input!");
            return;
        }
        //To check if account exists
        if(accounts.containsKey(accountNumber)){// To check if account exists
            System.out.println("Account " + accountNumber + " already exists!");
            return;
        }
        BankAccount bankAccount = new  BankAccount(balance,annualInterestRate);
        accounts.put(accountNumber,bankAccount);
        System.out.println("Account " + accountNumber + " has been created successfully!");
        viewAccountDetails(accountNumber);
    }

    // Deposit money
    // check if account has an account number, if yes get and add balance to the account number
    public void deposit(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)){
            BankAccount account = accounts.get(accountNumber);
            account.deposit(amount);
            taskManager.addTask("Withdraw RMB "+amount+" to Amount "+accountNumber);
            System.out.println("Account " + accountNumber + " deposit successfully!");
        }else {
            System.out.println("account don't have an account number");
        }
    }

    // Withdraw money
    // check if account has an account number, if yes get and subtract the amount from the balance of the account number
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (accounts.containsKey(accountNumber)){
            if(account.getBalance() >= 25 && (account.getBalance()-amount >= 0)){
                account.withdraw(amount);
                taskManager.addTask("Deposit RMB "+amount+" to Amount "+accountNumber);
                System.out.println("Account " + accountNumber + " withdraw successfully!");
            }else {
                System.out.println("Balance must be greater than 0");
            }
        }else {
            System.out.println("account don't have an account number");
        }
    }

    // View account details
    // check if account has an account number, if yes get and print the details
    public void viewAccountDetails(String accountNumber) {
        accounts.get(accountNumber).displayAccountDetails();
    }

    // Manage tasks
    public void viewTasks() {
        taskManager.displayTasks();
    }
    
    // New method to add a task
    public void addTask(String task) {
        taskManager.addTask(task);
    }

    // New method to remove a task
    public void removeTask(String priority, int index) {
        taskManager.removeTask(priority, index);
    }

    // New method to change task priority
    public void changePriority(String priority, int index) {
        taskManager.changePriority(priority, index);
    }

    // New method to promote a task
    public void promoteTask(int index) {
        taskManager.promoteTask(index);
    }

    // New method to get high-priority tasks
    public ArrayList<String> getHighPriorityTasks() {
        return taskManager.getHighPriorityTasks();
    }

    // New method to get low-priority tasks
    public ArrayList<String> getLowPriorityTasks() {
        return taskManager.getLowPriorityTasks();
    }

}
