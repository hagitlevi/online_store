/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.products;

import java.awt.*;

/**
 * The AbstractProductBuilder class provides a base implementation for building products.
 */
public abstract class AbstractProductBuilder implements ProductBuilder {
    protected String name;
    protected double price;
    protected int stock;
    protected String description;
    protected Category category;
    protected Color color;
    protected String imagePath;

    @Override
    public void setName(String name){
        this.name = name;
    }
    @Override
    public void setPrice(double price){
        this.price = price;
    }
    @Override
    public void setStock(int stock){
        this.stock = stock;
    }
    @Override
    public void setDescription(String description){
        this.description = description;
    }
    @Override
    public void setCategory(Category category) {
        this.category = category;
    }
    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
