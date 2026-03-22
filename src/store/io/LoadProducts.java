/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.io;

import store.products.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * LoadProducts class responsible for loading products from a file.
 * Reads the file line by line, parses each line to create Product objects,
 * and returns a list of products.
 */
public class LoadProducts {

    private final String path;

    /**
     * Constructor to create LoadProducts with the specified file path.
     * @param path the path to the file containing product data
     */
    public LoadProducts(String path){
        this.path = path;
    }

    /**
     * Loads products from the specified file.
     * @return a list of Product objects loaded from the file
     * @throws IOException if the file is not found or cannot be read
     */
    public List<Product> iOLoadFromFile() throws IOException {
        File file = new File(path);
        if(!file.exists()){
            throw new IOException("File not found: " + path);
        }

        List<Product> products = new java.util.ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        try(br){
            String l;
            while((l = br.readLine()) != null){
                String[] parts_product = l.split(",");

                String name = parts_product[0];
                double price = Double.parseDouble(parts_product[1]);
                int stock = Integer.parseInt(parts_product[2]);
                String description = parts_product[3];
                Category category = Category.valueOf(parts_product[4]);
                Color color = new Color(Integer.parseInt(parts_product[5]));
                String extra1 = parts_product[6];
                int extra2 = -1;
                if (category != Category.CLOTHING){
                    extra2 = Integer.parseInt(parts_product[7]);
                }

                ProductFactory factory = new ProductFactory();
                Product p = factory.getProduct(category,
                        name,
                        price,
                        stock,
                        description,
                        color,
                        extra1,
                        extra1,
                        extra2,
                        extra1,
                        extra2
                        );

                products.add(p);

            }
        }
        return products;
    }
}
