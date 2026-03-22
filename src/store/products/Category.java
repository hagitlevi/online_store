/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the Category enum representing product categories in the store.
 */
package store.products;

/**
 * The Category enum represents product categories in the store.
 */
public enum Category {
    BOOKS("/images/books.jpg"),
    CLOTHING("/images/clothing.jpg"),
    ELECTRONICS("/images/electronics.jpg");

    private final String imagePath;

    /**
     * Constructor for Category enum.
     * @param imagePath the path to the image representing the category
     */
    Category(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets the image path associated with the category.
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }



}

