package ecommerce.core;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private List<OrderItem> items = new ArrayList<>();

    public Order(String id) {
        this.id = id;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public double calculateTotal() {
        return items.stream()
                   .mapToDouble(OrderItem::getItemTotal)
                   .sum();
    }

    public String getId() { return id; }
    public List<OrderItem> getItems() { return items; }
}