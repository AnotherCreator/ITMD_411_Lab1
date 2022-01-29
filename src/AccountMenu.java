import java.util.Scanner;

public class AccountMenu {
    // Methods
    public double promptInitialDeposit() {
        Scanner scan = new Scanner(System.in);

        String Prompt = ("""
                
                INITIALIZE BANK ACCOUNT
                =======================
                Minimum $50 deposit value
                Enter initial balance: $""");
        System.out.print(Prompt);
        String initialUserBal = scan.next();

        // Check for non-numeric + negative values
        boolean check = numericValCheck(initialUserBal);
        // 'true' values for valid entries
        while (!check) {
            System.out.print(Prompt);
            initialUserBal = scan.next();
            check = numericValCheck(initialUserBal);
        }

        // Check if initial deposit < 50
        double UserBal = Double.parseDouble(initialUserBal);
        while (UserBal < 50) {
            System.out.print("\nAccount must have at least $50, try again: $");
            initialUserBal = scan.next();

            check = numericValCheck(initialUserBal);
            while (!check) {
                System.out.print(Prompt);
                initialUserBal = scan.next();
                check = numericValCheck(initialUserBal);
            }
            UserBal = Double.parseDouble(initialUserBal);
        }

        return Double.parseDouble(initialUserBal);
    }

    public int displayPrompts() {
        Scanner scan = new Scanner(System.in);
        String menuChoice;

        System.out.print("""
                \t
                BANK ACCOUNT MANAGER
                ====================
                1. Deposit
                2. Withdraw
                3. Display Balance
                4. Exit Program

                Enter Option:\s
                """);
        menuChoice = scan.next();

        // Check for non-numeric + negative values
        boolean check = numericValCheck(menuChoice);
        while (!check) {
            menuChoice = scan.next();
            check = numericValCheck(menuChoice);
        }
        return Integer.parseInt(menuChoice);
    }

    public void displayPromptActions (int menuChoice, AccountHolder userAccount) {
        Scanner scan = new Scanner(System.in);
        boolean check;

        while (menuChoice != 4) {
            if (menuChoice > 0 && menuChoice < 4) {
                switch (menuChoice) {
                    // Prompt user for new deposit
                    case 1 -> {
                        System.out.print("Enter a deposit amount: $");
                        String userDeposit = scan.next();

                        // Check for non-numeric + negative values
                        check = numericValCheck(userDeposit);
                        while (!check) {
                            userDeposit = scan.next();
                            check = numericValCheck(userDeposit);
                        }
                        userAccount.deposit(Double.parseDouble(userDeposit));
                        menuChoice = displayPrompts();
                    }

                    // Prompt user for new withdrawal
                    // Acct balance cannot drop below $50 after withdrawal
                    case 2 -> {
                        System.out.printf("Available withdrawal amount: %,.2f\n" +
                                "Enter a withdrawal amount: $", userAccount.getBalance() - 50);
                        String userWithdrawal = scan.next();

                        // Check for non-numeric + negative values
                        check = numericValCheck(userWithdrawal);
                        while (!check) {
                            userWithdrawal = scan.next();
                            check = numericValCheck(userWithdrawal);
                        }

                        // Check if balance < 50
                        double checkBalance = userAccount.getBalance() - Double.parseDouble(userWithdrawal);
                        while (checkBalance < 50) {
                            System.out.printf("Tentative Balance: %,.2f\n" +
                                    "Account balance under $50, try again: $", checkBalance);
                            userWithdrawal = scan.next();

                            // Check for non-numeric + negative values
                            check = numericValCheck(userWithdrawal);
                            while (!check) {
                                userWithdrawal = scan.next();
                                check = numericValCheck(userWithdrawal);
                            }
                            checkBalance = userAccount.getBalance() - Double.parseDouble(userWithdrawal);
                        }
                        userAccount.withdrawal(Double.parseDouble(userWithdrawal));
                        menuChoice = displayPrompts();
                    }

                    // Display tentative ending balance including 4% interest rate
                    case 3 -> {
                        userAccount.displayBalance();
                        menuChoice = displayPrompts();
                    }
                }
            }
            else {
                System.out.print("Option does not exit, try again: \n");
                menuChoice = displayPrompts();
            }
        }
    }

    public boolean numericValCheck(String userInput) {
        try {
            double val = Double.parseDouble(userInput);
            if (val < 0) {
                System.out.print("Negative value, try again: ");
                return false;
            }
            return true;
        }
        catch (NumberFormatException e) {
            System.out.print("Non-numeric value, try again: ");
            return false;
        }
    }
}
