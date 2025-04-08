package ecommerce.payment;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}