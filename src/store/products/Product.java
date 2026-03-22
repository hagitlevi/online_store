/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the Product class representing a basic product in the store.
 */
package store.products;

import store.core.StoreEntity;
import store.core.Persistable;
import store.io.SaveProduct;
import java.awt.Color;
import java.io.IOException;

/**
 * The Product class represents a basic product in the store.
 * It implements StoreEntity, PricedItem, StockManageable, and Persistable interfaces.
 * It contains attributes such as name, price, stock, description, category, and color.
 */
public abstract class Product implements StoreEntity, PricedItem, StockManageable, Persistable {
    private String name;
    private double price;
    private int stock;
    private String description;
    private Category category;
    transient private Color color;
    private String imagePath;

    /**
     * Constructor for Product class.
     * @param name product name
     * @param price product price
     * @param stock initial stock quantity
     * @param description product description
     * @param category product category
     * @param color product color
     */
    public Product(String name, double price, int stock, String description, Category category, Color color) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.color = color;
        this.imagePath = "/images/" + category.toString().toLowerCase() + ".jpg";
    }

    /**
     * Set the product name.
     * @param name new product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the product category.
     * @param category new product category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Set the product color.
     * @param color new product color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Set the image path of the product.
     * @param imagePath new image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Copy constructor for Product class.
     * @param p product to copy
     */
    public Product(Product p){
        this.name = p.name;
        this.price = p.price;
        this.stock = p.stock;
        this.description = p.description;
        this.category = p.category;
        this.color = p.color;
        this.imagePath = p.imagePath;
    }

    /**
     * Get the product name.
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the product description.
     * @return product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the product description.
     * @param description new product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the product category.
     * @return product category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Get the image path of the product.
     * @return image path
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * Get the product color.
     * @return product color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return string representation of the product
     */
    @Override
    public String toString() {
        String str = "Product Name: " + getName() + ", \n";
        //str+=  "Color: " + color.toString() + "\n";
        str += "Price: $" + getPrice() + ", \n";
        str += "Stock: " + getStock() + ", \n";
        str += "Category: " + getCategory() + ", \n";
        return str;
    }

    /**
     * Compares this product to the specified object for equality.
     * @param obj   the reference object with which to compare.
     * @return true if this product is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;     // Check for reference equality
        if (obj == null || getClass() != obj.getClass()) return false;    // Check for null and class type

        Product other = (Product) obj;    // Cast to Product(just because we in equals)

        return this.getCategory() == other.getCategory() &&
                this.getName().equals(other.getName());
    }

    /**
     * Get the display name of the product.
     * @return display name
     */
    @Override// StoreEntity
    public String getDisplayName(){
        return getName();
    }

    /**
     * Get the display details of the product.
     * @return display details
     */
    @Override// StoreEntity
    public String getDisplayDetails(){
        return this.toString() + "Description: " + getDescription() + "\n";
    }

    /**
     * Get the product price.
     * @return product price
     */
    @Override// PricedItem
    public double getPrice(){
        return this.price;
    }

    /**
     * Set the product price.
     * @param price new product price
     * @return true if the price was set successfully, false otherwise
     */
    @Override// PriceItem
    public boolean setPrice(double price){
        if(price < 0) return false;
        this.price = price;
        return true;
    }

    /**
     * Get the current stock quantity of the product.
     * @return current stock quantity
     */
    @Override// StockManageable
    public int getStock() {
        return this.stock;
    }

    /**
     * Increase the stock quantity of the product.
     * @param amount amount to increase
     * @return true if the stock was increased successfully, false otherwise
     */
    @Override// StockManageable
    public boolean increaseStock(int amount){
        if(amount < 0) return false;
        this.stock += amount;
        return true;
    }

    /**
     * Decrease the stock quantity of the product.
     * @param amount amount to decrease
     * @return true if the stock was decreased successfully, false otherwise
     */
    @Override// StockManageable
    public boolean decreaseStock(int amount) {
        if (amount < 0 || amount > getStock()) return false;
        this.stock -= amount;
        return true;
    }

    /**
     * Set the stock quantity of the product.
     * @param stock new stock quantity
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * Save the product to a file.
     * @param path file path
     */
    public void saveToFile(String path){
        SaveProduct sp = new SaveProduct(path);
        try{
            boolean flag = (sp.SaveProductToFile(this));
        } catch (IOException e){
            throw new RuntimeException("Error saving the product", e);
        }
    }

    /**
     * Get the CSV representation of the product.
     * @return CSV string
     */
    public abstract String getCSV();
}
