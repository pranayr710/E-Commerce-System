package ecommerce.core;

public class Customer extends User {
    private String shippingAddress;

    public Customer(String id, String name, String email, 
                   String shippingAddress) {
        super(id, name, email);
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String address) { 
        this.shippingAddress = address; 
    }
}