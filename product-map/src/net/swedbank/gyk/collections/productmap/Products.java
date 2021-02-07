package net.swedbank.gyk.collections.productmap;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Products {

    Map<Product, ProductStatistics> productStatistics;

    public Products() {
        productStatistics = new HashMap();
    }

    public static void main(String[] args) throws IOException, ParseException {

        List<ProductItem> productList;

        try {
            productList = Reader.readProducts("products.json");
        } catch (FileNotFoundException e) {
            productList = Reader.readProducts("product-set/products.json");
        }

        Products products = new Products();

        for (ProductItem item : productList) {
            products.addProduct(item.getId(), item.getName(), item.getSalesDate(), item.getAmount());
        }

        System.out.printf("We have sold %d unique items\n", products.numberOfProducts());

        products.printAll();

    }

    //implement this
    private void addProduct(String id, String name, LocalDateTime salesDate, double amount) {
        if (!productStatistics.containsKey(new Product(id, name))) {
            productStatistics.put(new Product(id, name), new ProductStatistics(salesDate, amount));
        } else {
            Product productToEdit = new Product(id, name);
            productStatistics.get(productToEdit).updateFirstSalesDate(salesDate);
            productStatistics.get(productToEdit).updateLastSalesDate(salesDate);
            productStatistics.get(productToEdit).updateSalesAmount(amount);
        }
    }


    //implement this
    public int numberOfProducts() {
        return productStatistics.size();
    }

    public void printAll() {
        for (Map.Entry<Product, ProductStatistics> productEntry : productStatistics.entrySet()) {
            Product product = productEntry.getKey();
            ProductStatistics stat = productEntry.getValue();

            // implement printing here
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String firstSaleOn = stat.getFirstSaleOn().format(formatter);
            String lastSaleOn = stat.getLastSaleOn().format(formatter);

            System.out.println("****************************");
            System.out.printf("Product: %s\nProduct name: %s\nFirst sale date: %s\nLast sale date: %s\nItems sold: %.2f\n", product.getId(), product.getName(), firstSaleOn, lastSaleOn, stat.getSalesAmount());
        }
    }


}
