/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the Customer class representing a customer in the store.
 */
package store.core;

import store.cart.Cart;
import store.cart.CartItem;
import store.engine.StoreEngine;
import store.orders.Order;
import store.products.Product;

import java.util.List;

/**
 * The Customer class represents a customer in the store.
 * It extends the User class and includes a shopping cart and order history.
 */
public class Customer extends User{
    private Cart cart;
    private List<Order> orderHistory;

    /**
     * Constructor for Customer class.
     * @param userName username of the customer
     * @param email email of the customer
     */
    public Customer(String userName, String email){
        super(userName, email);
        this.cart = new Cart();
        this.orderHistory = new java.util.ArrayList<>();
    }

    /**
     * Add a product to the shopping cart.
     * @param p the product to add
     * @param quantity the quantity of the product to add
     * @return true if the product was added successfully, false otherwise
     */
    public boolean addToCart(Product p, int quantity){
        return this.cart.addItem(p, quantity);
    }

    /**
     * Remove a product from the shopping cart.
     * @param p the product to remove
     * @return true if the product was removed successfully, false otherwise
     */
    public boolean removeFromCart(Product p){
        return this.cart.removeItem(p);
    }

    /**
     * Checkout the shopping cart and create an order.
     * @return true if the checkout was successful, false otherwise
     */
    public boolean checkout(){
        StoreEngine engine = StoreEngine.getInstance();
        List<CartItem> items = cart.getItems();
        Cart cart = new Cart(this.cart); //create a copy of the cart
        Order order = engine.createOrderFromCart(cart);

        if (order != null){
            this.orderHistory.add(order);
            engine.findTheProductsAndUpdateStock(items, this);
            return true;
        }
        return false;
    }

    /**
    * Get the shopping cart.
    * @return the shopping cart
     */
    public Cart getCart(){
        return this.cart;
    }

    /**
     * Get the order history.
     * @return the list of orders in the order history
     */
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

}
