package edu.unimagdalena.marketplace.constant;

public class ValidationMessage {
    public static final String ProductNotFound = "Lo sentimos, producto no encontrado";
    public static final String CustomerNotFound = "Lo sentimos, cliente no encontrado";
    public static final String OrderNotFound = "Lo sentimos, pedido no encontrado";
    public static final String ProductWithOrders = "Lo sentimos, no se puede realizar la operación ya que el producto tiene pedidos registrados";
    public static final String CustomerEmailIsBusy = "Lo sentimos, el email se encuentra ocupado";
    public static final String CustomerWithOrders = "Lo sentimos, no se puede realizar la operación ya que el cliente tiene pedidos registrados";
    public static final String WrongDateFormat = "El formato de la fecha debe ser yyyy-MM-dd";
    public static final String OrderStatusIsNotValid = "El estado del pedido no es válido";
    public static final String StartDateIsBlank = "Debe proporcionar la fecha de inicio";
    public static final String EndDateIsBlank = "Debe proporcionar la fecha final";
    public static final String PaymentNoFound="Lo sentimos, no se encontraron pagos";
}
