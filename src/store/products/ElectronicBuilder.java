/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.products;

/**
 * ElectronicBuilder class for building ElectronicProduct instances.
 */
public class ElectronicBuilder extends AbstractProductBuilder {
    private int warrantMonths;
    private String brand;

    /**
     *  Sets the warranty period in months for the electronic product.
     * @param months the warranty period in months
     */
    public void setWarrantMonths(int months){
        this.warrantMonths = months;
    }

    /**
     * Sets the brand of the electronic product.
     * @param brand the brand of the product
     */
    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * Builds and returns an ElectronicProduct instance with the specified attributes.
     * @return the constructed ElectronicProduct
     */
    @Override
    public Product buildProduct(){
        ElectronicProduct p = new ElectronicProduct(name, price, stock, description, category, color, brand, warrantMonths);
        return p;
    }
}
