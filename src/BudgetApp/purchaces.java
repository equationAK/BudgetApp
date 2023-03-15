package BudgetApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class purchases {

    private static Double price;
    private static String item;

    private static String category;

    private static Scanner reader = new Scanner(System.in);
    private static Scanner scanner = new Scanner(System.in);


    HashMap<String,Double> purchacesList = new HashMap<>();
    HashMap<String,String> categories = new HashMap<>();


    public void putItemToPurchacesList(String category) {
        System.out.println("\n");
        System.out.println("Enter purchase name:");
        scanner = new Scanner(System.in);
        item = scanner.nextLine();
        System.out.println("Enter its price:");
        scanner = new Scanner(System.in);
        price = scanner.nextDouble();
        System.out.println("Purchase was added!");
        System.out.println("\n");
        purchacesList.put(item , price);
        categories.put(item, category);
    }
    public void putCategoriesToList(String item,String category) {
        categories.put(item , category);
    }

    public void putItemToPurchacesList(String item, Double price, String category) {
        purchacesList.put(item,price);
        categories.put(item,category);

    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public Map<String, Double> getPurchacesList() {
        return purchacesList;
    }

    // singleton for purchases


    static purchases purchasesInstance = new purchases();
    private purchases() {}

    public static purchases getPurchaseInstance(){
        if (purchasesInstance == null)
            purchasesInstance = new purchases();
        return purchasesInstance;
    }

}