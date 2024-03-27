package edu.unimagdalena.marketplace.enumutil;

public enum OrderStatus {
    SEND("ENVIADO"),
    DELIVERED("ENTREGADO"),
    PENDING("PENDIENTE");

    private final String name;

    private OrderStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
