package ecommerce.payment;

public class PaymentFactory {
    public static PaymentProcessor getProcessor(PaymentMethod method) {
        switch (method) {
            case CREDIT_CARD:
                return new CreditCardProcessor();
            case PAYPAL:
                return new PayPalProcessor();
            case BANK_TRANSFER:
                return new BankTransferProcessor();
            default:
                throw new IllegalArgumentException("Unknown payment method: " + method);
        }
    }
}