/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the Order class representing a customer's order in the store.
 */
package store.orders;

import store.cart.CartItem;
import store.core.Persistable;
import store.io.SaveOrder;

import java.io.IOException;
import java.util.List;

/**
 * The Order class represents a customer's order in the store.
 * It contains a list of CartItem objects, the total amount, and the order status.
 */
public class Order implements Persistable {
    private int orderID = 0;
    private final List<CartItem> items;
    private final double totalAmount;
    private OrderStatus status;

    /**
     * Constructor for Order class.
     * @param items list of CartItem objects in the order
     * @param totalAmount total amount of the order
     */
    public Order(List<CartItem> items, double totalAmount) {
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.NEW;
    }

    /**
     * Get the order ID.
     * @param id order ID to set
     * @return true if the order ID was set successfully, false otherwise
     */
    public boolean setOrderID(int id){
        if(this.orderID == 0 && id > 0){
            this.orderID = id;
            return true;
        }
        return false;
    }

    /**
     * Pay for the order.
     * @return true if the order was paid successfully, false otherwise
     */
    public boolean pay(){
        if(this.status == OrderStatus.NEW){
            this.status = OrderStatus.PAID;
            return true;
        }
        return false;
    }

    /**
     * Ship the order.
     * @return true if the order was shipped successfully, false otherwise
     */
    public boolean ship(){
        if(this.status == OrderStatus.PAID){
            this.status = OrderStatus.SHIPPED;
            return true;
        }
        return false;
    }

    /**
     * Deliver the order.
     * @return true if the order was delivered successfully, false otherwise
     */
    public boolean deliver(){
        if(this.status == OrderStatus.SHIPPED){
            this.status = OrderStatus.DELIVERED;
            return true;
        }
        return false;
    }

    /**
     * Get the order details as a string.
     * @return string representation of the order details
     */
    public String getOrderDetails(){
        String str = "Order ID: " + this.orderID + " | \n";
        str += "Status: " + this.status + " | \n";
        str += "Total Amount: $" + this.totalAmount + " \n";
        return str;
    }

    /**
     * Get the list of items in the order.
     * @return list of CartItem objects in the order
     */
    public List<CartItem> getItems(){
        return this.items;
    }

    /**
     * Get a string representation of the order.
     * @return string representation of the order
     */
    @Override
    public String toString() {
        String str = "Order ID: " + this.orderID + "\n";
        str += "Status: " + this.status + "\n";
        str += "Total Amount: $" + this.totalAmount + "\n";
        str += "Items:\n";
        for(CartItem item: items){
            str += item.toString() + "\n";
        }
        return str;
    }

    /**
     * Compares this order to the specified object for equality.
     * @param obj   the reference object with which to compare.
     * @return true if this order is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Order o = (Order)obj;
        return this.orderID == ((Order) obj).orderID;
    }

    /**
     * Save the order to a file.
     * @param path the file path to save the order
     */
    @Override
    public void saveToFile(String path){
        SaveOrder saver = new SaveOrder();
        try {
            saver.saveOrderToFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the order ID.
     * @return the order ID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Get the order status.
     * @return the order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Set the order status.
     * @param status the order status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Get the total amount of the order.
     * @return the total amount of the order
     */
    public double getTotalAmount() {
        return totalAmount;
    }
}
