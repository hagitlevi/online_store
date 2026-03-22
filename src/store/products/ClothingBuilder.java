/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.products;

/**
 * The ClothingBuilder class is responsible for building ClothingProduct objects.
 */
public class ClothingBuilder extends AbstractProductBuilder{
    private String size;

    /**
     * Set the size attribute for the ClothingProduct.
     * @param size - the size of the clothing item
     */
    public void setSize(String size){
        this.size = size;
    }

    /**
     * Build and return a ClothingProduct using the set attributes.
     * @return Product - the constructed ClothingProduct
     */
    @Override
    public Product buildProduct(){
        ClothingProduct p = new ClothingProduct(name, price, stock, description, category, color, size);
        return p;
    }
}
