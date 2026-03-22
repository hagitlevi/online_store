package store.orders.dto;

/**
 * Data Transfer Object (DTO) representing an item in an order.
 * Contains product name, category, quantity, and price.
 */
public class OrderItemDTO {
    private String productName;
    private String category;
    private int quantity;
    private double price;

    /**
     * Constructor to create OrderItemDTO with specified details.
     * @param productName the name of the product
     * @param category the category of the product
     * @param quantity the quantity of the product ordered
     * @param price the price of the product
     */
    public OrderItemDTO(String productName, String category, int quantity, double price) {
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Default constructor for OrderItemDTO.
     */
    public OrderItemDTO(){}

    /**
     * Gets the product name.
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Gets the product category.
     * @return the product category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the quantity ordered.
     * @return the quantity ordered
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the product price.
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the OrderItemDTO.
     * @return a string representation of the OrderItemDTO
     */
    @Override
    public String toString() {
        return "Product: " + productName + ", Category: " + category + ", Quantity: " + quantity + ", Price: $" + price;
    }
}




