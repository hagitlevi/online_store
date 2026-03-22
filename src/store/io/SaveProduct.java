package store.io;

import store.products.Product;

import java.awt.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The SaveProduct class handles saving Product objects to a file.
 * It ensures that duplicate products (based on name and category) are not saved.
 */
public class SaveProduct{
    private String path;

    /**
     * Constructor to create SaveProduct with the specified file path.
     * @param path the path to the file where product data will be saved
     */
    public SaveProduct(String path){
        this.path = path;
    }

    /**
     * Saves a Product object to the specified file.
     * Ensures that duplicate products (based on name and category) are not saved.
     * @param p the Product object to save
     * @return true if the product was saved successfully
     * @throws IOException if an I/O error occurs
     */
    public boolean SaveProductToFile(Product p)throws IOException {
        File file = new File(path);

        Set<String> existingProducts = new HashSet<>();

        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));

        if (file.exists()){
            BufferedReader br = new BufferedReader(new FileReader(file));
            try(br){
                String l;
                while((l = br.readLine()) != null){
                    String[] parts = l.split(",");
                    String name = parts[0];
                    String category = parts[4];
                    existingProducts.add(name + "|" + category);
                }
            }
        }
        try(bw) {
            String check = p.getName() + "|" + p.getCategory();
            if (!existingProducts.contains(check)) {
                bw.write(p.getCSV());
                bw.newLine();
            }
        }
        return true;
    }

    /**
     * Saves a set of Product objects to the specified file.
     * @param products the set of Product objects to save
     * @throws IOException if an I/O error occurs
     */
    public void saveAllProducts(Set<Product> products) throws IOException {
        File file = new File(path);

        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Product p : products) {
                bw.write(p.getCSV());
                bw.newLine();
            }
        }
    }

}

