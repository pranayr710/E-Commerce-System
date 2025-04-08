
package ecommerce.core;

public class PhysicalProduct extends Product {
    private double weight;
    private String dimensions;

    public PhysicalProduct(String id, String name, double price, 
                         double weight, String dimensions, 
                         ECommerceApp.ProductCategory category) {
        super(id, name, price, category);
        this.weight = weight;
        this.dimensions = dimensions;
    }

    @Override
    public double calculatePrice() {
        return getPrice() + (weight * 0.5); // Shipping cost
    }

    // Getters
    public double getWeight() { return weight; }
    public String getDimensions() { return dimensions; }
}