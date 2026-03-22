/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.products;

import java.awt.*;

/**
 * The ProductBuilder interface defines the methods for building a Product object.
 */
public interface ProductBuilder {
    void setName(String name);
    void setPrice(double price);
    void setStock(int stock);
    void setDescription(String description);
    void setCategory(Category category);
    void setColor(Color color);
    void setImagePath(String imagePath);
    Product buildProduct();
}
