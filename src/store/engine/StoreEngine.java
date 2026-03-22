/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the StoreEngine class which is a singleton managing products, customers, and orders in the store.
 */
package store.engine;

import store.cart.Cart;
import store.cart.CartItem;
import store.core.Customer;
import store.core.SystemUpdatable;
import store.gui.view.StoreObserver;
import store.orders.Order;
import store.products.Category;
import store.products.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * The StoreEngine class is a singleton that manages products, customers, and orders in the store.
 */
public class StoreEngine {
    private static StoreEngine instance = null;
    private final List<Product> products;
    private List<Order> allOrders;
    private final List<Customer> customers;
    private int NextOrderID = 1;
    private List<StoreObserver> observers = new ArrayList<>();

    /**
     * Private constructor to prevent instantiation from outside (Singleton pattern).
     */
    private StoreEngine(){
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.allOrders = new ArrayList<>();
    }

    /**
     * Register an observer to be notified of system updates.
     */
    private void notifyObservers(){
        for(StoreObserver s: observers){
            s.refreshProducts();
        }
    }

    /**
     * Add an observer to the list of observers.
     * @param o the observer to add
     */
    public void addObserver(StoreObserver o) {
        observers.add(o);
    }

    /**
     * Remove an observer from the list of observers.
     * @param o the observer to remove
     */
    public void removeObserver(StoreObserver o) {
        observers.remove(o);
    }

    /**
     * Get the singleton instance of StoreEngine.
     * @return the singleton instance of StoreEngine
     */
    public static StoreEngine getInstance(){
        if (instance == null) {
            synchronized (StoreEngine.class) {
                if (instance == null) {
                    instance = new StoreEngine();
                }
            }
        }
        return instance;
    }

    /**
     * Add a product to the store.
     * @param p the product to add
     */
    public void addProduct(Product p){
        this.products.add(p);
        notifyObservers();
    }

    /**
     * Get a list of available products (with stock > 0).
     * @return list of available products, or null if no products are available
     */
    public List<Product> getAvailableProducts(){
        if(this.products.isEmpty()){
            return null;
        }
        List<Product> availableProducts = new ArrayList<>();
        for(Product p: this.products) {
            if (p.getStock() > 0) {
                availableProducts.add(p);
            }
        }
        return availableProducts;
    }

    /**
     * Register a new customer.
     * @param c the customer to register
     * @return true if the customer was registered successfully, false otherwise
     */
    public boolean registerCustomer(Customer c){
        if(c == null) return false;
        if(this.customers.isEmpty()){
            return this.customers.add(c);
        }
        for(Customer cust: this.customers){
            if(cust.equals(c)){
                return false;
            }
        }
        return this.customers.add(c);
    }

    /**
     * Create an order from a cart.
     * @param cart the cart to create the order from
     * @return the created order, or null if the order could not be created
     */
    public Order createOrderFromCart(Cart cart){
        if(cart.getItems().isEmpty()) return null;
        double totalAmount = cart.calculateTotal();
        Order newOrder = new Order(cart.getItems(), totalAmount);
        if(!newOrder.setOrderID(NextOrderID)){
            return null;
        }
        NextOrderID++;
        this.allOrders.add(newOrder);

        /**   $$
        for(Order order: this.allOrders){
            order.saveToFile("src/resources/orders.json");
        }
         */
        notifyObservers();
        notifyObservers();
        return newOrder;
    }

    public void findTheProductsAndUpdateStock(List<CartItem> items, Customer customer){
        for(CartItem c: items){
            Product p = c.getProduct();
            int quantity = c.getQuantity();
            Product realProduct = StoreEngine.getInstance().findProductByNameAndCategory(p.getName(), p.getCategory());
            realProduct.decreaseStock(quantity);
        }
        customer.getCart().clear();
        notifyObservers();
    }

    /**
     * Get all orders in the store.
     * @return list of all orders
     */
    public List<Order> getAllOrders(){
        return this.allOrders;
    }

    /**
     * Get the list of all registered customers.
     * @return list of all customers
     */
    public List<Customer> getCustomers(){
        return this.customers;
    }

    /**
     * String representation of the StoreEngine instance.
     * @return string representation of the StoreEngine instance
     */
    public String toString(){
        return "this is the StoreEngine singleton instance";
    }

    /**
     * Get the list of all products in the store.
     * @return list of all products
     */
    public List<Product> getProducts(){
        return this.products;
    }

    /**
     * Find a product by its name and category.
     * @param name the name of the product
     * @param category the category of the product
     * @return the found product, or null if not found
     */
    public Product findProductByNameAndCategory(String name, Category category){
        for(Product p: this.products){
            if(p.getName().equals(name) && p.getCategory() == category){
                return p;
            }
        }
        return null;
    }

    /**
     * Update the stock quantity of a product.
     * @param product the product to update
     * @param quantity the new stock quantity
     */
    public Boolean updateProductStock(Product product, int quantity){
        for(Product p: this.products){
            if(p.equals(product)){
                p.setStock(quantity);
                notifyObservers();
                return true;
            }
        }
        return false;
    }
}

