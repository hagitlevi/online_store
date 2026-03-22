/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.products;

/**
 * The BookBuilder class is responsible for building BookProduct instances.
 */
public class BookBuilder extends AbstractProductBuilder{
    private String author;
    private int pages;

    /**
     * Sets the author of the book.
     * @param author the author of the book
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * Sets the number of pages in the book.
     * @param pages the number of pages
     */
    public void setPages(int pages){
        this.pages = pages;
    }

    /**
     * Builds and returns a BookProduct instance.
     * @return the constructed BookProduct
     */
    @Override
    public Product buildProduct(){
        BookProduct p = new BookProduct(name, price, stock, description, category, color, author, pages);
        return p;
    }
}
