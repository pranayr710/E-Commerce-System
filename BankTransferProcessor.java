package ecommerce.payment;

public class BankTransferProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing bank transfer of $" + amount);
        System.out.println("Please transfer the amount to account: 123456789");
        return true; 
    }
}