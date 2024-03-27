package edu.unimagdalena.marketplace.enumutil;

public enum PaymentMethod {
    CASH("EFECTIVO"),
    CREDIT_CARD("TARJETA_CREDITO"),
    PAYPAL("PAYPAL"),
    NEQUI("NEQUI"),
    DAVIPLATA("DAVIPLATA"),
    PSE("PSE");

    private final String name;

    private PaymentMethod(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

