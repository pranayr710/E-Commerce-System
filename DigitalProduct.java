package ecommerce.core;

public class DigitalProduct extends Product {
    private String downloadLink;

    public DigitalProduct(String id, String name, double price, 
                         String downloadLink, 
                         ECommerceApp.ProductCategory category) {
        super(id, name, price, category);
        this.downloadLink = downloadLink;
    }

    @Override
    public double calculatePrice() {
        return getPrice(); // No additional costs
    }

    // Getters
    public String getDownloadLink() { return downloadLink; }
}