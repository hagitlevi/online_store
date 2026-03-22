/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the ElectronicProduct class representing an electronic product in the store.
 */
package store.products;

import java.awt.Color;

/**
 * The ElectronicProduct class represents an electronic product in the store.
 * It extends the Product class and adds specific attributes for electronics such as brand and warranty months.
 */
public class ElectronicProduct extends Product{
    private int warrantMonths;
    private String brand;

    /**
     * Constructor for ElectronicProduct class.
     * @param name electronic product name
     * @param price electronic product price
     * @param stock initial stock quantity
     * @param description electronic product description
     * @param category electronic product category
     * @param color electronic product color
     * @param brand electronic product brand
     * @param warrantMonths warranty period in months
     */
    public ElectronicProduct(String name, double price, int stock, String description, Category category, Color color, String brand, int warrantMonths){
        super(name, price, stock, description, category, color);
        try{
            if(warrantMonths < 0){
                throw new IllegalArgumentException("Warranty months cannot be negative");
            }
            this.warrantMonths = warrantMonths;
            this.brand = brand;
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            warrantMonths = 0;
        }

    }

    public void setWarrantMonths(int warrantMonths) {
        this.warrantMonths = warrantMonths;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the CSV representation of the ElectronicProduct.
     * @return CSV string of the ElectronicProduct
     */
    @Override
    public String getCSV(){
        return this.getName() + ","
                + this.getPrice() + ","
                + this.getStock() + ","
                + this.getDescription() + ","
                + this.getCategory() + ","
                + this.getColor().getRGB() + ","
                + this.brand + ","
                + this.warrantMonths;
    }

    /**
     * Returns a string representation of the ElectronicProduct.
     * @return string representation of the ElectronicProduct
     */
    public String toString() {
        String str = "electronic product: \n";
        str += super.toString();
        str += "Brand: " + brand + ", \n";
        str += "Warranty (months): " + warrantMonths + "\n";
        return str;
    }

}
