/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the ClothingProduct class representing a clothing product in the store.
 */
package store.products;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * The ClothingProduct class represents a clothing product in the store.
 * It extends the Product class and adds specific attributes for clothing such as size.
 */
public class ClothingProduct extends Product{
    String size;

    /**
     * Constructor for ClothingProduct class.
     *
     * @param name        clothing name
     * @param price       clothing price
     * @param stock       initial stock quantity
     * @param description clothing description
     * @param category    clothing category
     * @param color       clothing color
     */
    public ClothingProduct(String name, double price, int stock, String description, Category category, Color color, String size){
        super(name, price, stock, description, category, color);
        this.size = size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Returns a CSV representation of the ClothingProduct.
     * @return CSV string of the ClothingProduct
     */
    @Override
    public String getCSV(){
        return this.getName() + ","
                + this.getPrice() + ","
                + this.getStock() + ","
                + this.getDescription() + ","
                + this.getCategory() + ","
                + this.getColor().getRGB() + ","
                + this.size;
    }

    /**
     * Returns a string representation of the ClothingProduct.
     * @return string representation of the ClothingProduct
     */
    @Override
    public String toString() {
        String str = "Clothing Product: \n";
        str += super.toString();
        str += "Size: " + size + "\n";
        return str;
    }
}
