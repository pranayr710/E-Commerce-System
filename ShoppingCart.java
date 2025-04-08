package ecommerce.core;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addItem(Product product, int quantity) {
        items.merge(product, quantity, Integer::sum);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public double calculateTotal() {
        return items.entrySet().stream()
                   .mapToDouble(e -> e.getKey().calculatePrice() * e.getValue())
                   .sum();
    }

    public Map<Product, Integer> getItems() { return items; }
}