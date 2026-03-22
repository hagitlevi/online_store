/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the Cart class representing a shopping cart in the store.
 */
package store.cart;

import store.products.Product;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Cart class represents a shopping cart in the store.
 * It contains a list of CartItem objects and provides methods to add, remove, and manage items in the cart.
 */
public class Cart {
    private List<CartItem> items;

    /**
     * Constructor for Cart class.
     */
    public Cart(){
        this.items = new ArrayList<>();
    }

    /**
     * Copy constructor for Cart class.
     * @param other the Cart object to copy
     */
    public Cart(Cart other){
        this.items = new ArrayList<>();
        for(CartItem item: other.items){
            this.items.add(new CartItem(item.getProduct(), item.getQuantity()));
        }
    }

    /**
     * Add an item to the cart.
     * @param p the product to add
     * @param quantity the quantity of the product to add
     * @return true if the item was added successfully, false otherwise
     */
    public boolean addItem(Product p, int quantity){
        if(quantity <= 0){
            return false;
        }
        if(items.isEmpty()){
            if(quantity > p.getStock()) return false;
            CartItem newItem = new CartItem(p, quantity);
            items.add(newItem);
            return true;
        }
        for(CartItem item: items){
            if(item.getProduct().equals(p)){
                int newQuantity = item.getQuantity() + quantity;
                if(newQuantity > p.getStock()) return false;
                return item.setQuantity(newQuantity);
            }
        }
        CartItem newItem = new CartItem(p, quantity);
        items.add(newItem);
        return true;
    }

    /**
     * Remove an item from the cart.
     * @param p the product to remove
     * @return true if the item was removed successfully, false otherwise
     */
    public boolean removeItem(Product p){
        if(items.isEmpty()){
            return false;
        }
        for(CartItem item: items){
            if(item.getProduct().equals(p)){
                items.remove(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Calculate the total price of the items in the cart.
     * @return the total price
     */
    public double calculateTotal(){
        double total = 0.0;
        if(items.isEmpty()){
            return total;
        }
        for(CartItem item: items){
            total += item.getTotalPrice();
        }
        return total;
    }

    /**
     * Clear all items from the cart.
     */
    public void clear(){
        items.clear();
    }

    /**
     * Get the list of items in the cart.
     * @return the list of CartItem objects
     */
    public List<CartItem> getItems(){
        return items;
    }

    /**
     * String representation of the cart.
     * @return a string representing the cart and its items
     */
    @Override
    public String toString(){
        String str;
        if(!items.isEmpty()){
            str = "Cart items:\n";
            for(CartItem ci: items){
                str += ci + "\n";
            }
        }
        else{
            return "Your cart is empty";
        }
        return str;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj   the reference object with which to compare.
     * @return true if this cart is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null ||  getClass() != obj.getClass()) return false;
        Cart c =(Cart) obj;
        if(this.items.size() != c.items.size()) return false;
        for(int i=0; i< this.items.size(); i++) {
            if (!this.items.get(i).equals(c.items.get(i))) {
                return false;
            }
        }
        return true;
    }

}
