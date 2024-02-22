package finance.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final List<FinanceRecord> records = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Do you want to add a record, or delete a record?");

        String task = "add";
        String userInput = scanner.nextLine();
        if (task.equalsIgnoreCase(userInput)) {
            addRecord();
        } else {
            if (records.isEmpty()) {
                System.out.println("There are currently no records in our system. Add a new record?");
                if (scanner.nextBoolean()) {
                    addRecord();
                }
            } else {
                System.out.println("What record do you want to delete?");
                try {
                    int id = scanner.nextInt();
                    deleteRecordById(id);
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid ID.");
                }
            }
        }
        showBalance();
    }

    public static void addRecord() {
        boolean addNewFinance = true;
        boolean showList = false;
        int id = 0;

        while (addNewFinance) {
            System.out.println("Add new finance:");

            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();

                FinanceRecord record = new FinanceRecord(id, amount);
                records.add(record);
                id++;

                System.out.println("Added financial record: " + amount + " with the ID of " + id);

                System.out.println("Would you like to add another finance? (true/false)");

                addNewFinance = scanner.nextBoolean();
                if (!addNewFinance) {
                    System.out.println("Would you like to list all finances? (true/false)");
                    showList = scanner.nextBoolean();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.nextLine();
            }
        }
        if (showList) {
            listRecords();
        }
    }

    public static void listRecords() {
        System.out.println("Listing all financial records:");
        for (FinanceRecord record : records) {
            System.out.println(record);
        }
        System.out.println("Do you want to delete a record?");
        if (scanner.nextBoolean()) {
            System.out.println("Which one?");
            try {
                int id = scanner.nextInt();
                deleteRecordById(id);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid ID.");
            }
        }
    }

    public static void deleteRecordById(int id) {
        FinanceRecord recordToRemove = records.stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .orElse(null);

        if (recordToRemove != null) {
            records.remove(recordToRemove);
            System.out.println("Record with ID " + id + " has been deleted.");
        } else {
            System.out.println("Record with ID " + id + " not found.");
        }
        listRecords();
    }

    public static void showBalance() {
        double totalBalance = 0.0;
        for (FinanceRecord record : records) {
            totalBalance += record.getAmount();
        }
        System.out.println("Total balance: " + totalBalance);
    }
}
