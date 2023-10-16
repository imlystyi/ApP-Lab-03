/* Vladyslav Yakubovskyi,
 * Lviv Polytechnic National University, Institute of Computer Science and Information Technologies,
 * Department of Information Systems and Networks, "PI-21" Student group,
 * Discipline "Applied Programming",
 * Laboratory work #03,
 * Task #03,
 * "program" package,
 * "Main.java" file */

package program;

import exceptions.ObjectIsNotArrayListException;
import exceptions.PaidReceiptModifyingException;
import exceptions.ProductIsNotAvailableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args)
            throws IOException, PaidReceiptModifyingException, ProductIsNotAvailableException, ClassNotFoundException,
            ObjectIsNotArrayListException {
        /* Average product price:           51.25
         * Filtered available products:
         *      - "Kramatorskyi Myasokombinat, Meatballs 400g"  (50.0)
         *      - "Vinnytski Frukty, Apple 1kg"                 (55.0)
         *      - "Ternopolianka, Carrot 1kg"                   (60.0)
         *      - "Eidelklass, Paper 400"                       (65.0)
         *      - "Kramatorskyi Myasokombinat, Chicken 1kg"     (85.0)
         * Profit from customer:    242.0
         * Products purchasing map:
         *      - "Kramatorskyi Myasokombinat, Meatballs 400g"  (50.0)
         *      - "Kramatorskyi Myasokombinat, Meatballs 400g"  (50.0)
         *      - "Chornomorka, Hake 300g"                      (40.0)
         *      - "Ternopolianka, Carrot 1kg"                   (60.0)
         *      - "Bag"                                         (1.0)
         * Most popular product:            Bag (1.0)
         * Best daily profit:               235.0
         */


        final String pathToAvailableProductsFile = "C:\\Other\\products";
        final String pathToPurchaseHistory = "C:\\Other\\history";

        final ArrayList<Product> products = getProducts();

        FileService.writeObject(pathToAvailableProductsFile, products);

        final Market market = new Market(pathToAvailableProductsFile, pathToPurchaseHistory);

        final Product product1 = new Product("Eidelklass, Paper 400", 65, ProductType.GOODS);
        final Product product2 = new Product("Eidelklass, Paper 400", 30, ProductType.GOODS);

        System.out.println("I. " + market.getAvailableProductsAveragePrice());         // Must be 49.285...

        market.buyProduct(product1);
        System.out.println("II. " + market.getAvailableProductsAveragePrice());        // Must be 51.25

        market.editProduct(product1, product2);
        System.out.println("III. " + market.getAvailableProductsAveragePrice());       // Must be 46.875

        market.editProduct(product2, product1);
        System.out.println("IV. " + market.getAvailableProductsAveragePrice());        // Must be 51.25

        System.out.println("V.");

        List<Product> filteredProducts = market.filterAvailableProductsByPrice(50, 85, false);
        filteredProducts.forEach(System.out::println);

        final Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_MONTH, -1);

        final Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DAY_OF_MONTH, 2);

        final Calendar date3 = Calendar.getInstance();
        date3.add(Calendar.DAY_OF_MONTH, 10);

        final Calendar date4 = Calendar.getInstance();
        date4.add(Calendar.DAY_OF_MONTH, 1);

        final Calendar date5 = Calendar.getInstance();
        date5.add(Calendar.DAY_OF_MONTH, 11);

        final Receipt receipt1 = new Receipt("Ihor Matskiv", date1);
        market.sellProduct(receipt1, products.get(0));
        market.sellProduct(receipt1, products.get(1));
        market.payCheck(receipt1);

        final Receipt receipt2 = new Receipt("Ihor Matskiv", date2);
        market.sellProduct(receipt2, products.get(2));
        market.sellProduct(receipt2, products.get(3));
        market.payCheck(receipt2);

        final Receipt receipt3 = new Receipt("Ihor Matskiv", date3);
        market.sellProduct(receipt3, products.get(4));
        market.sellProduct(receipt3, products.get(5));
        market.payCheck(receipt3);

        System.out.println("VI. " + market.getProfitFromCustomer("Ihor Matskiv", date4, date5));

        final Receipt receipt4 = new Receipt("Alla Kulik", date1);
        market.sellProduct(receipt4, products.get(1));
        market.sellProduct(receipt4, products.get(1));
        market.sellProduct(receipt4, products.get(3));
        market.payCheck(receipt4);

        final Receipt receipt5 = new Receipt("Alla Kulik", date2);
        market.sellProduct(receipt5, products.get(4));
        market.payCheck(receipt5);

        System.out.println("VII.");
        market.getCustomerPurchasesMap("Alla Kulik").forEach((k, v) -> System.out.println(k + "\t" + v));

        System.out.println("VIII. " + market.getMostPopularProduct());

        System.out.println("IX. " + market.getBestDailyProfit());

        final Market otherMarket = new Market(pathToAvailableProductsFile, pathToPurchaseHistory);
        List<Product> otherFilteredProducts = otherMarket.filterAvailableProductsByPrice(50, 85, false);

        System.out.println("X.");
        otherFilteredProducts.forEach(System.out::println);

        receipt1.generate("C:\\Other\\receipt.txt");
    }

    private static ArrayList<Product> getProducts() {
        final ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("Nasha Ryaba, Chicken 500g", 45, ProductType.MEAT));                   // 0
        products.add(new Product("Kramatorskyi Myasokombinat, Meatballs 400g", 50, ProductType.MEAT));  // 1
        products.add(new Product("Kramatorskyi Myasokombinat, Chicken 1kg", 85, ProductType.MEAT));     // 2
        products.add(new Product("Chornomorka, Hake 300g", 40, ProductType.FISH));                      // 3
        products.add(new Product("Ternopolianka, Carrot 1kg", 60, ProductType.VEGETABLES));             // 4
        products.add(new Product("Vinnytski Frukty, Apple 1kg", 55, ProductType.FRUITS));               // 5
        products.add(new Product("Flair, Pen", 10, ProductType.GOODS));                                 // 6

        return products;
    }
}