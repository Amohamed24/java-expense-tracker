import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // EXPENSE TRACKER
        List<Expense> expenses = new ArrayList<>();
        ExpenseTracker tracker = new ExpenseTracker();

        System.out.println("Welcome to Expense Tracker! What's your name? :");
        String name = scanner.nextLine();

        if (name.equalsIgnoreCase("q")) {
            System.out.println("Thanks for playing " + name);
            return;
        }

        while (true) {
            System.out.println("*******************************************************************************");
            System.out.println("Select: 1) Create an expense or 2) View an expense (type 'q' to quit) :");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            int action;
            try {
                action = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid. Please enter 1.");
                continue;
            }

            if (action == 1) {
                Expense expense = tracker.createAnExpense();
                if (expense != null) {
                    expenses.add(expense);
                }
            } else {
                System.out.println("Invalid option.");
            }
        }

        // Final summary
        System.out.println("\n========== Expense Summary ==========");
        for (Expense e : expenses) {
            System.out.printf("%s (%s): $%.2f\n", e.getName(), e.getCategory(), e.getAmount());
        }

        System.out.println("Thanks for using Expense Tracker!");
        scanner.close();
    }

    static class Expense {
        private final String name;
        private final String category;
        private final double amount;

        public Expense(String name, String category, double amount) {
            this.name = name;
            this.category = category;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }
    }

    static class ExpenseTracker {
        public Expense createAnExpense() {
            System.out.println("What's the name of the expense?");
            String expenseName = scanner.nextLine();

            System.out.println("What category is this under?");
            String category = scanner.nextLine();

            System.out.println("How much is " + expenseName + "?");
            double amount;
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Amount cannot be negative");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please try again");
                return null;
            }

            System.out.println("Added: " + category + " - " + expenseName + ": $" + amount);
            return new Expense(expenseName, category, amount);
        }
    }
}
