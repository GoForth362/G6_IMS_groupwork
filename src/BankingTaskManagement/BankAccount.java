package BankingTaskManagement;

public class BankAccount {
    private double balance;
    private double annualInterestRate;
    private int depositsCount;
    private int withdrawalsCount;
    private double monthlyServiceCharges;

    // Constructor
    public BankAccount(double balance, double annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.depositsCount = 0;
        this.withdrawalsCount = 0;
        this.monthlyServiceCharges = 0;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            depositsCount++;
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && balance > amount) {
            balance -= amount;
            withdrawalsCount++;
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    // Calculate monthly interest
    public void calcInterest() {
        double monthInterestRate = Math.pow(1 + annualInterestRate / 100, 1.0 / 12) - 1;
        balance = balance * (1 + monthInterestRate);
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

    // Getters
    public double getBalance() {
        return balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getDepositsCount() {
        return depositsCount;
    }

    public int getWithdrawalsCount() {
        return withdrawalsCount;
    }

    public double getMonthlyServiceCharges() {
        return monthlyServiceCharges;
    }

}
