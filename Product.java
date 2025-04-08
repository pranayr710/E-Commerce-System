
package ecommerce.core;

public abstract class Product {
    private String id;
    private String name;
    private double price;
    private ECommerceApp.ProductCategory category;

    public Product(String id, String name, double price, ECommerceApp.ProductCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public abstract double calculatePrice();

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public ECommerceApp.ProductCategory getCategory() { return category; }
}