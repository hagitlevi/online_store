/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the CartItem class representing an item in a shopping cart.
 */
package store.cart;

import store.products.Product;

/**
 * The CartItem class represents an item in a shopping cart.
 * It contains a product and the quantity of that product.
 */
public class CartItem {
    private final Product product;
    private int quantity;

    /**
     * Constructor for CartItem class.
     * @param product the product in the cart item
     * @param quantity the quantity of the product
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Set the quantity of the product in the cart item.
     * @param q the new quantity
     * @return true if the quantity is valid and set, false otherwise
     */
    public boolean setQuantity(int q) {
        if (quantity < 0) { return false;}
        this.quantity = q;
        return true;
    }

    /**
     * Get the total price of the cart item.
     * @return the total price
     */
    public double getTotalPrice(){
        return product.getPrice() * getQuantity();
    }

    /**
     * Get the quantity of the product in the cart item.
     * @return the quantity
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Get the product in the cart item.
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj   the reference object with which to compare.
     * @return true if this cart item is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CartItem other = (CartItem) obj;
        return this.getProduct().equals(other.getProduct()) && this.getQuantity() == other.getQuantity();
    }

    /**
     * String representation of the CartItem.
     * @return string representation
     */
    @Override
    public String toString(){
        String str = "Amount: " + this.getQuantity() + " | ";
        str += this.getProduct();
        return str;

    }
}
