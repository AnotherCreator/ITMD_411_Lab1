/*
Josh Reginaldo
ITMD-411

Lab 1 - Bank Account Simulator Program

This program will prompt the user to select from multiple options such as creating an initial balance and
entering deposits/ withdrawals. It will also allow them to see their account info by printing the data. */

import java.util.Scanner;

public class AccountHolderTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Init user balance + acct
        System.out.print("Enter your initial balance: ");
        double initialUserBal = scan.nextDouble();
        while (initialUserBal < 0) {
            System.out.print("Negative initial value, try again: ");
            initialUserBal = scan.nextDouble();
        }
        AccountHolder userAccount = new AccountHolder(initialUserBal);
        AccountHolder.setAnnualInterestRate(.04);

        // Prompt user for new deposit
        System.out.print("Enter a deposit amount: ");
        double userDeposit = scan.nextDouble();
        while (userDeposit < 0) {
            System.out.print("Negative deposit value, try again: ");
            userDeposit = scan.nextDouble();
        }
        userAccount.deposit(userDeposit);

        // Prompt user for new withdrawal
        // Acct balance cannot drop below $50 after withdrawal
        System.out.print("Enter a withdrawal amount: ");
        double userWithdrawal = scan.nextDouble();
        while (userWithdrawal < 0) {
            System.out.print("Negative withdrawal value, try again: ");
            userWithdrawal = scan.nextDouble();
        }

        double checkBalance = userAccount.getBalance() - userWithdrawal;
        while (checkBalance < 50) {
            System.out.print("Account balance under $50, try again: ");
            userWithdrawal = scan.nextDouble();
            checkBalance = userAccount.getBalance() - userWithdrawal;
        }
        userAccount.withdrawal(userWithdrawal);

        // Display ending balance including 4% interest rate
        userAccount.monthlyInterest();
        System.out.printf("Balance with a %.2f percent interest applied: %.2f",
                AccountHolder.getAnnualInterestRate() * 100, userAccount.getBalance());
    }
}
