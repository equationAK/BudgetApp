package BudgetApp;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class appMethods {

    private static Scanner reader = new Scanner(System.in);
    private static Scanner scanner = new Scanner(System.in);

    static File file = new File(Main.filePath);


    static void printMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
    }

    static void printMenuAddCategory() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
    }

    static void printMenuShowCategory() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
    }

    static void printMenuShowSorting() {
        System.out.println("How do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");
    }

    static void printSubMenuShowSorting() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
    }

    // method to get balance

    static Double getBalance(Map<String,Double> purchases, Double income){
        Double sum = 0.0;
        for (Map.Entry<String, Double> entry : purchases.entrySet()) {
            sum += entry.getValue();
        }
        return income - sum;
    }

    static Double getSumByCategory(Map<String,Double> purchases, String category ,Map<String,String> categories){
        Double sum = 0.0;
        List<String> categoryList = new ArrayList<>();
        for(Map.Entry<String, String> entry : categories.entrySet()) {
            if(category.equals(entry.getValue()))
                categoryList.add(entry.getKey());
        }
        for (String str : categoryList) {
            if (purchases.containsKey(str))
                sum += purchases.get(str);
        }
        return sum;
    }

    static void printSortByType() {
        DecimalFormat df = new DecimalFormat("0.00");
        Double sumFood = getSumByCategory(purchases.purchasesInstance.getPurchacesList(), "Food",purchases.purchasesInstance.getCategories());
        Double sumEntertainment = getSumByCategory(purchases.purchasesInstance.getPurchacesList(), "Entertainment",purchases.purchasesInstance.getCategories());
        Double sumClothes = getSumByCategory(purchases.purchasesInstance.getPurchacesList(), "Clothes",purchases.purchasesInstance.getCategories());
        Double sumOthers = getSumByCategory(purchases.purchasesInstance.getPurchacesList(), "Other",purchases.purchasesInstance.getCategories());
        Double totalSum = sumFood + sumEntertainment + sumClothes + sumOthers;

        System.out.println("Types:");
        System.out.printf("%s - $%s\n","Food",df.format(sumFood));
        System.out.printf("%s - $%s\n","Entertainment",df.format(sumEntertainment));
        System.out.printf("%s - $%s\n","Clothes",df.format(sumClothes));
        System.out.printf("%s - $%s\n","Other",df.format(sumOthers));
        System.out.printf("Total sum: $%s\n",totalSum);
    }

    static void printSortByType(String category) {
        DecimalFormat df = new DecimalFormat("#.00");
        Double sumCategory = getSumByCategory(purchases.purchasesInstance.getPurchacesList(), category,purchases.purchasesInstance.getCategories());
        sortByType(category);
        System.out.printf("Total sum: $%s\n\n",df.format(sumCategory));
    }

    static Map<String,Double> getListByCategory(Map<String,Double> purchases, String category ,Map<String,String> categories){

        Map<String,Double> ListByCategory = new TreeMap<>();
        List<String> categoryList = new ArrayList<>();
        for(Map.Entry<String, String> entry : categories.entrySet()) {
            if(category.equals(entry.getValue()))
                categoryList.add(entry.getKey());
        }
        for (String str : categoryList) {
            if (purchases.containsKey(str))
                ListByCategory.put(str,purchases.get(str));
        }
        return ListByCategory;
    }

    static Double getSum(Map<String,Double> purchases, Double income){
        Double sum = 0.0;
        for (Map.Entry<String, Double> entry : purchases.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    static Double getIncome() {
        return Main.income;
    }

    static Double getPriceUsingRegex(String input) {
        String output = "";
        Pattern pattern = Pattern.compile("\\s([\\$€]\\d+([\\.,]\\d{2}?))|(\\d+([\\.,]\\d{2}?)[\\$€])\\s?");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find())
        {
            output = matcher.group(0);
        }
        return Double.parseDouble(output);
    }

    static void printAllPurchasesList() {
        DecimalFormat df = new DecimalFormat("#.00");
        Double sum = 0.0;
        for (Map.Entry<String, Double> entry : purchases.getPurchaseInstance().getPurchacesList().entrySet()) {
            sum += entry.getValue();
            System.out.println(entry.getKey() + " $" + df.format(entry.getValue()));
        }
        System.out.println("Total sum: $" + df.format(sum));
        System.out.println("\n");
    }

    static void printCategoryPurchasesList(String category) {
        DecimalFormat df = new DecimalFormat("#.00");
        Double sum = 0.0;
        List<String> categoryList = new ArrayList<>();
        for(Map.Entry<String, String> entry : purchases.getPurchaseInstance().categories.entrySet()) {
            if(category.equals(entry.getValue()))
                categoryList.add(entry.getKey());
        }
        System.out.println(category + ":");
        for(String str : categoryList) {
            if (purchases.getPurchaseInstance().getPurchacesList().containsKey(str)) {
                sum += purchases.getPurchaseInstance().getPurchacesList().get(str);
                System.out.println(str + " $" + df.format(purchases.getPurchaseInstance().getPurchacesList().get(str)));
            }
        }
        System.out.println("Total sum: $" + df.format(sum));
        System.out.println("\n");
    }

    // method to call SubMenu for add Purchases

    static void subMenuAddPurchase() {
        printMenuAddCategory();
        int option = Integer.parseInt(reader.next());
        while (true) {

            if (option == 5) {
                break;
            }
            if (option == 1) {
                purchases.getPurchaseInstance().putItemToPurchacesList("Food");
                printMenuAddCategory();
            }
            if (option == 2) {
                purchases.getPurchaseInstance().putItemToPurchacesList("Clothes");
                printMenuAddCategory();
            }
            if (option == 3) {
                purchases.getPurchaseInstance().putItemToPurchacesList("Entertainment");
                printMenuAddCategory();
            }
            if (option == 4) {
                purchases.getPurchaseInstance().putItemToPurchacesList("Other");
                printMenuAddCategory();
            }
            option = Integer.parseInt(reader.next());
            System.out.println("\n");
        }
    }


    // method to call Submenu to show lists

    static void subMenuShowPurchases() throws Exception {
        printMenuShowCategory();
        int option = Integer.parseInt(reader.next());
        System.out.println("\n");
        while (true) {
            if (option == 6) {
                break;
            }
            if (option == 5) {
                printAllPurchasesList();
                printMenuShowCategory();
            }
            if (option == 1) {
                printCategoryPurchasesList("Food");
                printMenuShowCategory();
            }
            if (option == 2) {
                printCategoryPurchasesList("Clothes");
                printMenuShowCategory();
            }
            if (option == 3) {
                printCategoryPurchasesList("Entertainment");
                printMenuShowCategory();
            }
            if (option == 4) {
                printCategoryPurchasesList("Other");
                printMenuShowCategory();
            }
            option = Integer.parseInt(reader.next());
            System.out.println("\n");
        }
    }

    // subMenu sort By category

    static void subMenuSortByCategory() throws Exception {
        printSubMenuShowSorting();
        int option = Integer.parseInt(reader.next());
        System.out.println("\n");
        if (option == 1) {
            //getListByCategory(purchases.purchasesInstance.getPurchacesList(), "Food",purchases.purchasesInstance.getCategories());
            sortByType("Food");
        }
        if (option == 2) {
            //getListByCategory(purchases.purchasesInstance.getPurchacesList(), "Clothes",purchases.purchasesInstance.getCategories());
            sortByType("Clothes");
        }
        if (option == 3) {
            //getListByCategory(purchases.purchasesInstance.getPurchacesList(), "Entertainment",purchases.purchasesInstance.getCategories());
            sortByType("Entertainment");
        }
        if (option == 4) {
            //getListByCategory(purchases.purchasesInstance.getPurchacesList(), "Other",purchases.purchasesInstance.getCategories());
            sortByType("Other");
        }
        System.out.println("\n");
    }

    // sub menu for sort actions

    static void subMenuShowSorting() throws Exception {
        DecimalFormat df = new DecimalFormat("#.00");
        printMenuShowSorting();
        int option = Integer.parseInt(reader.next());
        System.out.println("\n");
        while (true) {
            if (option == 4) {
                break;
            }
            if (option == 1) {
                if(purchases.getPurchaseInstance().getPurchacesList().isEmpty()) {
                    System.out.println("The purchase list is empty");
                    System.out.println("\n");
                } else {
                    Stream<Map.Entry<String,Double>> sorted =
                            purchases.getPurchaseInstance().getPurchacesList().entrySet().stream()
                                    .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()));
                    Stream<Map.Entry<String,Double>> sortedFinal = sorted.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
                    System.out.println("All:");
                    sortedFinal.forEach(stringDoubleEntry -> System.out.println(stringDoubleEntry.getKey() + " $" + df.format(stringDoubleEntry.getValue())));
                }
            }
            if (option == 2) {
                if(purchases.getPurchaseInstance().getPurchacesList().isEmpty()) {
                    System.out.println("\n");
                    System.out.println("Types:");
                    System.out.printf("%s - $%s\n","Food","0");
                    System.out.printf("%s - $%s\n","Entertainment","0");
                    System.out.printf("%s - $%s\n","Clothes","0");
                    System.out.printf("%s - $%s\n","Other","0");
                    System.out.printf("Total sum: $%s\n\n","0");
                } else {
                    printSortByType();
                }
            }
            if (option == 3) {
                System.out.println("\n");
                printSubMenuShowSorting();
                option = Integer.parseInt(reader.next());
                System.out.println("\n");
                if (option == 1) {
                    if(!purchases.getPurchaseInstance().categories.containsValue("Food")) {
                        System.out.println("The purchase list is empty");
                        System.out.println("\n");
                    } else
                        printSortByType("Food");
                }
                if (option == 2) {
                    if(!purchases.getPurchaseInstance().categories.containsValue("Clothes")) {
                        System.out.println("The purchase list is empty");
                        System.out.println("\n");
                    } else
                        printSortByType("Clothes");
                }
                if (option == 3) {
                    if(!purchases.getPurchaseInstance().categories.containsValue("Entertainment")) {
                        System.out.println("The purchase list is empty");
                        System.out.println("\n");
                    } else
                        printSortByType("Entertainment");
                }
                if (option == 4) {
                    if(!purchases.getPurchaseInstance().categories.containsValue("Other")) {
                        System.out.println("The purchase list is empty");
                        System.out.println("\n");
                    } else
                        printSortByType("Other");
                }
            }
            System.out.println("\n");
            printMenuShowSorting();
            option = Integer.parseInt(reader.next());
            System.out.println("\n");
        }
    }


    //sort method

    static void sortByType(String category) {
        DecimalFormat df = new DecimalFormat("0.00");
        Map<String,Double> listByCategory = new TreeMap<>();
        listByCategory = getListByCategory(purchases.purchasesInstance.getPurchacesList(), category, purchases.getPurchaseInstance().getCategories());
        Stream<Map.Entry<String,Double>> sortedByType =
                listByCategory.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        System.out.println(category + ":");
        sortedByType.forEach(stringDoubleEntry -> System.out.println(stringDoubleEntry.getKey() + " $" + df.format(stringDoubleEntry.getValue())));

    }

    static void saveFile() throws IOException {
        FileWriter writer = new FileWriter(file);
        DecimalFormat df = new DecimalFormat("#.00");
        writer.write("Balance: $" + String.valueOf(Main.income) + "\n");
        for (Map.Entry<String, Double> entry : purchases.getPurchaseInstance().getPurchacesList().entrySet()) {
            if(purchases.getPurchaseInstance().categories.containsKey(entry.getKey())) {
                writer.write(purchases.getPurchaseInstance().categories.get(entry.getKey())
                        + "," + entry.getKey() + ",$" + df.format(entry.getValue()));
                writer.write("\n");
            }
        }
        writer.close();
    }

    static String readFileAsString(String fileName) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    static void loadFile() throws Exception {

        String data = readFileAsString(Main.filePath);
        String[] arr = data.split("\n");
        String[] line;
        line = arr[0].split("\\s");
        Main.income = Double.parseDouble(line[1].substring(1));
        for(int i = 1; i < arr.length; i++) {
            line = arr[i].split(",");
            purchases.getPurchaseInstance().putItemToPurchacesList(line[1],Double.parseDouble(line[2].substring(1)),line[0]);
            //purchases.getPurchaseInstance().purchacesList.put(line[1],Double.parseDouble(line[2].substring(1)));
            //purchases.getPurchaseInstance().categories.put(line[0],line[1]);
        }
    }
}
