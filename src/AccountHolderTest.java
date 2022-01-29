/*
Josh Reginaldo
ITMD-411

Lab 1 - Bank Account Simulator Program

This program will prompt the user to select from multiple options after creating an initial balance including depositing, withdrawing, and displaying the current balance.

Since an account can't have <$50 after withdrawing, I also made it the minimum to create an account as well
*/

public class AccountHolderTest {
    public static void main(String[] args) {
        AccountMenu menu = new AccountMenu();

        // Ask for initial deposit
        double UserBal = menu.promptInitialDeposit();

        // Create new userAccount
        AccountHolder userAccount = new AccountHolder(UserBal);
        AccountHolder.setAnnualInterestRate(.04);

        // Display menu prompts
        int menuChoice = menu.displayPrompts();
        menu.displayPromptActions(menuChoice, userAccount);

        // Set and display current balance when exiting
        userAccount.monthlyInterest();
        System.out.printf("Balance with a %%%.2f percent interest applied: $%,.2f\n",
                AccountHolder.getAnnualInterestRate() * 100, userAccount.getBalance());

        System.out.print("""
                ===============
                Exiting Program
                ===============
                """);
    }
}
