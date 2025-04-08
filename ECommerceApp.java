package ecommerce.core;
import java.util.Scanner;
import ecommerce.payment.*;

public class ECommerceApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ShoppingCart currentCart = new ShoppingCart();
    
    public static enum ProductCategory {
        PREMIUM, REGULAR, SALE, DIGITAL
    }
    
    public static void main(String[] args) {
        System.out.println("=== Welcome to E-Commerce System ===");
        
        Product[] availableProducts = {
            new PhysicalProduct("P100", "MacBook Pro 16\"", 2499.99, 4.2, "14x9.8x0.6", ProductCategory.PREMIUM),
            new PhysicalProduct("P101", "Sony WH-1000XM5", 399.99, 0.6, "7.5x3x8", ProductCategory.PREMIUM),
            new PhysicalProduct("P102", "Dyson Airwrap", 599.99, 2.8, "12x5x4", ProductCategory.PREMIUM),
            new PhysicalProduct("P200", "Wireless Earbuds", 79.99, 0.2, "2x2x1", ProductCategory.REGULAR),
            new PhysicalProduct("P201", "Backpack", 49.99, 1.2, "18x12x6", ProductCategory.REGULAR),
            new PhysicalProduct("P202", "Desk Lamp", 29.99, 1.5, "10x5x5", ProductCategory.REGULAR),
            new PhysicalProduct("P300", "Last Year's Smartphone", 299.99, 0.4, "6x3x0.3", ProductCategory.SALE),
            new PhysicalProduct("P301", "Overstock Headphones", 59.99, 0.3, "6x4x3", ProductCategory.SALE),
            new DigitalProduct("D100", "Photoshop Masterclass", 199.99, "download.com/ps", ProductCategory.DIGITAL),
            new DigitalProduct("D101", "Python Bootcamp", 149.99, "download.com/python", ProductCategory.DIGITAL),
            new DigitalProduct("D102", "E-Book Bundle", 29.99, "download.com/ebooks", ProductCategory.DIGITAL),
            new DigitalProduct("D103", "Music Album", 9.99, "download.com/music", ProductCategory.DIGITAL)
        };
        
        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Browse All Products");
            System.out.println("2. Browse by Category");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    browseProducts(availableProducts, "All Products");
                    break;
                case 2:
                    browseByCategory(availableProducts);
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void browseByCategory(Product[] products) {
        System.out.println("\n=== Categories ===");
        System.out.println("1. Premium Products");
        System.out.println("2. Regular Products");
        System.out.println("3. Sale Items");
        System.out.println("4. Digital Products");
        System.out.print("Enter category number: ");
        
        int catChoice = scanner.nextInt();
        scanner.nextLine();
        
        ProductCategory selectedCat = null;
        String categoryName = "";
        
        switch (catChoice) {
            case 1: 
                selectedCat = ProductCategory.PREMIUM;
                categoryName = "Premium Products";
                break;
            case 2:
                selectedCat = ProductCategory.REGULAR;
                categoryName = "Regular Products";
                break;
            case 3:
                selectedCat = ProductCategory.SALE;
                categoryName = "Sale Items";
                break;
            case 4:
                selectedCat = ProductCategory.DIGITAL;
                categoryName = "Digital Products";
                break;
            default:
                System.out.println("Invalid category!");
                return;
        }
        
        browseProducts(products, categoryName, selectedCat);
    }
    
    private static void browseProducts(Product[] products, String title) {
        browseProducts(products, title, null);
    }
    
    private static void browseProducts(Product[] products, String title, ProductCategory filter) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("ID   Category    Name                          Price");
        System.out.println("----------------------------------------------------");
        
        int displayNumber = 1;
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            if (filter != null && !p.getCategory().equals(filter)) {
                continue;
            }
            
            System.out.printf("%-4d %-10s %-30s $%-10.2f%n", 
                displayNumber++, 
                p.getCategory(),
                p.getName(), 
                p.calculatePrice());
        }
        
        System.out.print("\nEnter product number to add to cart (0 to go back): ");
        int productChoice = scanner.nextInt();
        scanner.nextLine();
        
        if (productChoice == 0) return;
        
        int actualIndex = -1;
        int count = 0;
        for (int i = 0; i < products.length; i++) {
            if (filter == null || products[i].getCategory().equals(filter)) {
                count++;
                if (count == productChoice) {
                    actualIndex = i;
                    break;
                }
            }
        }
        
        if (actualIndex == -1) {
            System.out.println("Invalid product number!");
            return;
        }
        
        Product selectedProduct = products[actualIndex];
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        currentCart.addItem(selectedProduct, quantity);
        System.out.printf("Added %d x %s to your cart.%n", quantity, selectedProduct.getName());
    }
    
    private static void viewCart() {
        System.out.println("\n=== Your Shopping Cart ===");
        if (currentCart.getItems().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        
        currentCart.getItems().forEach((product, quantity) -> {
            System.out.printf("%-20s x%-3d $%-10.2f $%-10.2f%n",
                product.getName(),
                quantity,
                product.calculatePrice(),
                product.calculatePrice() * quantity);
        });
        System.out.printf("%-20s %6s %10s $%-10.2f%n", 
            "TOTAL", "", "", currentCart.calculateTotal());
    }
    
    private static void checkout() {
        if (currentCart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }

        System.out.println("\n=== Checkout ===");
        viewCart();

        System.out.println("\nEnter your details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Shipping Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer("CUST" + System.currentTimeMillis(), name, email, address);

        PaymentMethod method = null;
        while (method == null) {
            System.out.println("\nSelect payment method:");
            System.out.println("1. Credit Card");
            System.out.println("2. PayPal");
            System.out.println("3. Bank Transfer");
            System.out.print("Enter choice (1-3): ");
            
            try {
                int paymentChoice = scanner.nextInt();
                scanner.nextLine();
                
                switch (paymentChoice) {
                    case 1: method = PaymentMethod.CREDIT_CARD; break;
                    case 2: method = PaymentMethod.PAYPAL; break;
                    case 3: method = PaymentMethod.BANK_TRANSFER; break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        Order order = new Order("ORD" + System.currentTimeMillis());
        currentCart.getItems().forEach((p,q) -> order.addItem(p,q));

        try {
            PaymentProcessor processor = PaymentFactory.getProcessor(method);
            boolean paymentSuccess = processor.processPayment(order.calculateTotal());

            System.out.println("\n=== Order Summary ===");
            System.out.println("Order #: " + order.getId());
            System.out.println("Customer: " + customer.getName());
            System.out.println("Payment Method: " + method);
            System.out.println("Total: $" + order.calculateTotal());
            System.out.println("Status: " + (paymentSuccess ? "PAYMENT SUCCESSFUL" : "PAYMENT FAILED"));

            if (paymentSuccess) {
                System.out.println("\nThank you for your order! A confirmation has been sent to " + customer.getEmail());
                currentCart = new ShoppingCart();
            } else {
                System.out.println("\nPlease try another payment method.");
            }
        } catch (Exception e) {
            System.out.println("\nError processing payment: " + e.getMessage());
            System.out.println("Please try again or contact support.");
        }
    }
}