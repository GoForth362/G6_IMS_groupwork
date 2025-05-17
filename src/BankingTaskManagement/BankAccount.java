/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingTaskManagement;

public class BankAccount {
    private double balance;
    public double annualInterestRate;
    private int depositsCount;
    private int withdrawalsCount;
    private double monthlyServiceCharges;


    //initialise by setting values of balance, annual Interest Rate, deposit count, monthlyservice charges, withdrawal count
    BankAccount(double balance, double annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.depositsCount = 0;
        this.withdrawalsCount = 0;
        this.monthlyServiceCharges = 0;
    }

    // The Deposit method to put 
    // check if amount to be deposited is 0 or less otherwise add amount to balance
    public void deposit(double amount) {
        if (amount > 0){
            balance = balance + amount;
            depositsCount++;
        }
        else {
            System.out.println("deposited is 0 or less");
        }
    }

    // Withdraw method to take money from the bank
    // check if balance is less than RMB 25 and deny withdrawal
    // 
    public void withdraw(double amount) {
        if (amount > 0 && balance > amount){
            balance = balance - amount;
            withdrawalsCount++;
        }
        else {
            System.out.println("not sufficient funds");
        }
    }

    // Calculate monthly interest
    public void calcInterest() {
        double monthInterestRate = Math.pow(1+annualInterestRate/100,1/12)-1;
        balance = balance*(1+monthInterestRate);
    }

    // Monthly processing
    public void monthlyProcess() {
        balance -= monthlyServiceCharges;
        calcInterest();
        depositsCount = 0;
        withdrawalsCount = 0;
        monthlyServiceCharges = 0;
        System.out.println("Monthly processing completed.");
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("Balance: RMB " + balance);
        System.out.println("Deposits this month: " + depositsCount);
        System.out.println("Withdrawals this month: " + withdrawalsCount);
    }

    public double getBalance() {
        return balance;
    }
}
