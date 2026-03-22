package store.orders.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) for Order.
 * Encapsulates order details for transfer between different layers of the application.
 */
public class OrderDTO {
    private int orderId;
    private String status;
    private double totalAmount;
    private List<OrderItemDTO> items;

    /**
     * Constructor to create an OrderDTO.
     * @param orderId the ID of the order
     * @param status the status of the order
     * @param totalAmount the total amount of the order
     * @param items the list of items in the order
     */
    public OrderDTO(int orderId, String status, double totalAmount, List<OrderItemDTO> items) {
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    /**
     * Default constructor for OrderDTO.
     */
    public OrderDTO(){}

    /**
     * Returns a string representation of the OrderDTO.
     * @return a string containing order details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Total Amount: $").append(totalAmount).append("\n");
        sb.append("Items:\n");
        for (OrderItemDTO item : items) {
            sb.append(" - ").append(item.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Gets the order ID.
     * @return the order ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Gets the order status.
     * @return the order status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the total amount of the order.
     * @return the total amount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Gets the list of items in the order.
     * @return the list of OrderItemDTOs
     */
    public List<OrderItemDTO> getItems() {
        return items;
    }
}
