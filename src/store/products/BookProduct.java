/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.products;

import java.awt.*;

/**
 * The BookProduct class represents a book product in the store.
 * It extends the Product class and adds specific attributes for books such as author and pages.
 */
public class BookProduct extends Product{
    private String author;
    private int pages;

    /**
     * Constructor for BookProduct class.
     * @param name book name
     * @param price book price
     * @param stock initial stock quantity
     * @param description book description
     * @param category book category
     * @param color book color
     * @param author book author
     * @param pages number of pages in the book
     */
    public BookProduct(String name, double price, int stock, String description, Category category, Color color, String author, int pages){
        super(name, price, stock, description, category, color);
        this.author = author;
        this.pages = pages;
    }

    /**
     * Sets the author of the book.
     * @param author book author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the number of pages in the book.
     * @param pages number of pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Returns the author of the book.
     * @return author of the book
     */
    @Override
    public String getCSV(){
        return this.getName() + ","
                + this.getPrice() + ","
                + this.getStock() + ","
                + this.getDescription() + ","
                + this.getCategory() + ","
                + this.getColor().getRGB() + ","
                + this.author + ","
                + this.pages;
    }

    /**
     * Returns a string representation of the BookProduct.
     * @return string representation of the BookProduct
     */
    @Override
    public String toString() {
        String str = "book product: \n";
        str += super.toString();
        str += "Author: " + author + ", \n";
        str += "Pages: " + pages + "\n";
        return str;
    }
}
