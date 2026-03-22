/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.products;

import java.awt.*;

/**
 * The ProductFactory class is responsible for creating Product objects based on the specified category and attributes.
 */
public class ProductFactory {
    public ProductFactory(){}

    /**
     * Creates a Product object based on the specified category and attributes.
     * @param type the category of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the stock quantity of the product
     * @param description the description of the product
     * @param color the color of the product
     * @param extraClothing the size for clothing products
     * @param extra1Electronic the brand for electronic products
     * @param extra2Electronic the warranty months for electronic products
     * @param extra1Book the author for book products
     * @param extra2Book the number of pages for book products
     * @return the created Product object
     */
    public Product getProduct(Category type, String name, double price, int stock, String description, Color color,String extraClothing, String extra1Electronic, int extra2Electronic, String extra1Book, int extra2Book){
        switch(type){

            case CLOTHING -> {
                if (extraClothing == null)
                    throw new IllegalArgumentException("Size is required for clothing");
                ClothingBuilder builder = new ClothingBuilder();
                builder.setName(name);
                builder.setPrice(price);
                builder.setStock(stock);
                builder .setDescription(description);
                builder .setCategory(type);
                builder .setColor(color);
                builder.setSize(extraClothing);
                return builder.buildProduct();
            }

            case BOOKS -> {
                if (extra1Book == null || extra2Book <= 0) {
                    throw new IllegalArgumentException("Author and pages are required for books");
                }
                BookBuilder builder = new BookBuilder();
                builder.setName(name);
                builder.setPrice(price);
                builder.setStock(stock);
                builder .setDescription(description);
                builder .setCategory(type);
                builder .setColor(color);
                builder.setAuthor(extra1Book);
                builder.setPages(extra2Book);
                return builder.buildProduct();
            }

            case ELECTRONICS -> {
                if (extra1Electronic == null || extra2Electronic <= 0) {
                    throw new IllegalArgumentException("Brand and warranty are required for electronics");
                }
                ElectronicBuilder builder = new ElectronicBuilder();
                builder.setName(name);
                builder.setPrice(price);
                builder.setStock(stock);
                builder.setDescription(description);
                builder.setCategory(type);
                builder.setColor(color);
                builder.setBrand(extra1Electronic);
                builder.setWarrantMonths(extra2Electronic);
                return builder.buildProduct();
            }
        }

        throw new IllegalArgumentException("Unknown category: " + type);
    }
}
