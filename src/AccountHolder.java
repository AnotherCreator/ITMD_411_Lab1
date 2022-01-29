public class AccountHolder {
    // Variables
    private static double annualInterestRate;
    private double balance;

    // Getters
    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }
    public double getBalance() {
        return balance;
    }

    // Setters
    public static void setAnnualInterestRate(double annualInterestRate) {
        AccountHolder.annualInterestRate = annualInterestRate;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Methods
    public void deposit(double userAmount) {
        setBalance(getBalance() + userAmount);
    }

    public void withdrawal(double userAmount) {
        setBalance(getBalance() - userAmount);
    }

    public void monthlyInterest() {
        setBalance(getBalance() + (getBalance() * getAnnualInterestRate() / 12.0));
    }

    public void displayBalance() {
        System.out.printf("Balance with a %%%.2f percent interest applied: $%,.2f\n", getAnnualInterestRate() * 100,
                getBalance() + (getBalance() * getAnnualInterestRate() / 12.0));
    }

    // Constructor
    public AccountHolder(double initialBalance) {
        setBalance(initialBalance);
    }
}
