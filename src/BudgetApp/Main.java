package BudgetApp;

import java.io.File;
import java.util.Scanner;

import static BudgetApp.appMethods.printMenu;


public class Main {

    static Double income = 0.0;
    private static Scanner reader = new Scanner(System.in);
    private static Scanner scanner = new Scanner(System.in);


    static String filePath = "/Users/aris/Desktop/purchases.txt";

    public static void main(String[] args) throws Exception {
        appMethods.printMenu();
        int option = Integer.parseInt(reader.next());
        System.out.println("\n");
        while (true) {
            if (option == 0) {
                System.out.println("Bye!");
                System.out.println("\n");
                break;
            } else if (option == 1) {
                System.out.println("Enter income:");
                income += scanner.nextDouble();
                System.out.println("Income was added!");
                System.out.println("\n");
                printMenu();
            } else if (option == 2) {
                appMethods.subMenuAddPurchase();
                System.out.println("\n");
                printMenu();
            } else if (option == 3) {
                if(purchases.getPurchaseInstance().getPurchacesList().isEmpty()) {
                    System.out.println("The purchase list is empty");
                    System.out.println("\n");
                } else
                    appMethods.subMenuShowPurchases();
                printMenu();
            } else if (option == 4) {
                if (purchases.getPurchaseInstance().getPurchacesList().isEmpty()) {
                    System.out.println("Balance: $" + income);
                    System.out.println("\n");
                } else {
                    System.out.println("Balance: $" + appMethods.getBalance(purchases.getPurchaseInstance().getPurchacesList(), appMethods.getIncome()));
                    System.out.println("\n");
                }
                printMenu();
            } else if (option == 5) {
                appMethods.saveFile();
                System.out.println("Purchases were saved!");
                System.out.println("\n");
                printMenu();
            } else if (option == 6) {
                appMethods.loadFile();
                System.out.println("Purchases were loaded!");
                System.out.println("\n");
                printMenu();
            } else if (option == 7) {
                appMethods.subMenuShowSorting();
                System.out.println("\n");
                printMenu();
            } else {
                System.out.println("Incorrect option! Try again.");
                System.out.println("\n");
                printMenu();
            }
            option = Integer.parseInt(reader.next());
            System.out.println("\n");
        }
    }
}