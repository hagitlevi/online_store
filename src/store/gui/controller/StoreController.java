/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the StoreController class which manages the store's GUI interactions.
 */
package store.gui.controller;

import store.cart.CartItem;
import store.core.Customer;
import store.engine.StoreEngine;
import store.gui.view.*;
import store.io.LoadOrders;
import store.io.LoadProducts;
import store.io.SaveProduct;
import store.orders.Order;
import store.products.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * The StoreController class manages the store's GUI interactions.
 * It implements the StoreListener interface to handle user actions.
 */
public class StoreController implements StoreListener {
    //private MainPage homePage;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Map<CustomerWindow, Customer> customersByWindow = Collections.synchronizedMap(new HashMap<>());
    private ManagerWindow managerWindow;
    private final Object dataLock = new Object();
    private final StoreEngine engine;
    private StartPage startPage;


    /**
     * Constructor for StoreController class.
     * Initializes the store engine, shopping cart, and loads products and orders from files.
     */
    public StoreController(){

        engine = StoreEngine.getInstance();
        LoadProducts lp= new LoadProducts("src/resources/store_products.csv");
        LoadOrders lo = new LoadOrders();
        List<Product> products;
        List<Order> orders;

        try{
            //try to load products
            products = lp.iOLoadFromFile();
            if ((products) != null) {
                for (Product p : products) {
                    engine.addProduct(p);
                }

            }
            else products = List.of();

            //try to load orders
            orders = lo.loadOrdersFromFile();
            if(orders == null || orders.isEmpty()){
                 orders = List.of();
            }
            else{
                for(Order o: orders){
                    engine.getAllOrders().add(o);
                }
            }
        }
        catch (IOException e){
            //homePage = new MainPage(List.of(), this);
            System.out.println("no such file" + e);
        }
        finally {
            startPage = new StartPage(this);
        }



    }

    /**
     * Update the customer's order history from the store engine.
     */
    private void updateCustomerOrderHistory(Customer c){
        List<Order> allOrders = engine.getAllOrders();
        for(Order o: allOrders){
            System.out.print("show the order history to the customer");  //*
        }
    }

    /**
     * Handle product click event.
     *
     * @param window The customer window where the event occurred.
     * @param product The product that was clicked.
     * @param role The role of the user (customer or manager).
     */
    @Override
    public void onProductClicked(CustomerWindow window, Product product, UserRole role) {
        new ProductDetailsPage(product, this, role, window);
    }

    /**
     * Handle add to cart event.
     * @param window The customer window where the event occurred.
     * @param p The product to be added to the cart.
     * @param amount The amount of the product to be added.
     * @return true if the product was successfully added to the cart, false otherwise.
     */
    @Override
    public boolean onAddToCart(CustomerWindow window, Product p, int amount) {
        synchronized (dataLock) {
            Customer c = customersByWindow.get(window);
            if (c == null) return false;

            boolean ok = c.addToCart(p, amount);
            if (ok) {
                window.refreshProducts();
            }
            return ok;
        }
    }

    /**
     * Check if a product is in the customer's cart.
     * @param window The customer window.
     * @param p The product to check.
     * @return true if the product is in the cart, false otherwise.
     */
    @Override
    public boolean IsInCart(CustomerWindow window, Product p){
        Customer c = customersByWindow.get(window);
        if (c == null) return false;
        for(CartItem item: c.getCart().getItems()){
            if(item.getProduct().equals(p)){
                return true;
            }
        }
        return false;
    }

    /**
     * Handle loading products from a file.
     * @param file The file to load products from.
     */
    @Override
    public boolean onLoadFromFile(File file) {
        try {
            LoadProducts loader = new LoadProducts(file.getAbsolutePath());
            List<Product> loaded = loader.iOLoadFromFile();

            for (Product p : loaded) {
                if (!engine.getProducts().contains(p)){
                    engine.addProduct(p);
                }
            }
            managerWindow.refreshProducts();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Handle saving products to a file.
     * @param file The file to save products to.
     * @return true if the products were successfully saved, false otherwise.
     */
    @Override
    public boolean onSaveToFile(File file) {
        try {
            String path = file.getAbsolutePath();
            for (Product p : engine.getProducts()) {
                p.saveToFile(path);
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Handle shopping cart button click event.
     * @param window The customer window where the event occurred.
     */
    @Override
    public void onShoppingCartBtn(CustomerWindow window){
        new ShoppingCartPage(window, this);
    }

    /**
     * Get the total price of items in the customer's cart.
     * @param window The customer window.
     * @return The total price as a string.
     */
    @Override
    public String getTotalPrice(CustomerWindow window){
        Customer customer = customersByWindow.get(window);
        return String.valueOf(customer.getCart().calculateTotal());
    }

    /**
     * Get the items in the customer's cart.
     * @param window The customer window.
     * @return A list of cart items.
     */
    @Override
    public List<CartItem> getItems(CustomerWindow window){
        Customer customer = customersByWindow.get(window);
        return customer.getCart().getItems();
    }

    /**
     * Handle delete item from cart event.
     * @param window The customer window where the event occurred.
     * @param p The product to be deleted.
     * @param cartPage The shopping cart page.
     * @return true if the product was successfully deleted from the cart, false otherwise.
     */
    @Override
    public boolean onDeleteClicked(CustomerWindow window, Product p, ShoppingCartPage cartPage){
        Customer customer = customersByWindow.get(window);
        if (customer.removeFromCart(p)){
            cartPage.dispose();
            window.refreshProducts();
            new ShoppingCartPage(window, this);
            return true;
        }
        return false;
    }

    /**
     * Handle checkout event.
     * @param window The customer window where the event occurred.
     * @param page The shopping cart page.
     * @return true if the checkout was successful, false otherwise.
     */
    @Override
    public boolean onCheckoutClicked(CustomerWindow window, ShoppingCartPage page){
        synchronized (dataLock) {
            Customer customer = customersByWindow.get(window);
            if (!customer.checkout()) return false;
            page.dispose();
            try{
                updateStockInFile();
            }
            catch (IOException e){
                System.out.println("Error updating stock in file: " + e.getMessage());
            }
            return true;
        }
    }

    /**
     * Handle category filter event.
     * @param window The customer window where the event occurred.
     * @param category The category to filter by.
     * @param role The role of the user (customer or manager).
     */
    @Override
    public void onCategoryFilter(CustomerWindow window, String category, UserRole role){
        if (!category.equals("Filter")){
            if (role == UserRole.CUSTOMER){
                List<Product> products = engine.getAvailableProducts();
                Category c = Category.valueOf(category);
                List<Product> filtered = products.stream().filter(p -> p.getCategory() == c).toList();
                window.refreshToSpecificProducts(filtered);

            } else if (role == UserRole.MANAGER){
                List<Product> products = getAllProducts();
                Category c = Category.valueOf(category);
                List<Product> filtered = products.stream().filter(p -> p.getCategory() == c).toList();
                managerWindow.refreshToSpecificProducts(filtered);
            }
        }
    }

    /**
     * Get all orders of a customer.
     * @param window The customer window.
     * @return A list of orders.
     */
    @Override
    public List<Order> getAllCustomerOrders(CustomerWindow window){
        Customer customer = customersByWindow.get(window);
        return customer.getOrderHistory();
    }

    /**
     * Get the details of an order.
     * @param o The order.
     * @return The order details as a string.
     */
    @Override
    public String getOrderDetails(Order o){
        return o.getOrderDetails();
    }

    /**
     * Get all products in the store.
     * @return A list of products.
     */
    @Override
    public List<Product> getAllProducts(){
        return engine.getProducts();
    }

    /**
     * Get all available products in the store.
     * @return A list of available products.
     */
    @Override
    public List<Product> getAllAvailableProducts(){
        return engine.getAvailableProducts();
    }

    /**
     * Handle order history button click event.
     * @param window The customer window where the event occurred.
     */
    @Override
    public void onOrdersHistoryCartBtn(CustomerWindow window){
        new OrderHistoryPage(window, this);
    }

    /**
     * Handle search product event.
     * @param cWindow The customer window where the event occurred.
     * @param mWindow The manager window where the event occurred.
     * @param name The name of the product to search for.
     * @param role The role of the user (customer or manager).
     */
    @Override
    public void onSearchProductBtn(CustomerWindow cWindow, ManagerWindow mWindow, String name, UserRole role){
        if(!name.equals("all")){
            List<Product> products = engine.getProducts();
            List<Product> product = products.stream().filter(p -> Objects.equals(p.getDisplayName().toLowerCase(), name.toLowerCase())).toList();

            if (role == UserRole.CUSTOMER) {
                cWindow.refreshToSpecificProducts(products);
            }

            else if (role == UserRole.MANAGER) {
                managerWindow.refreshToSpecificProducts(product);
            }
        }
        else{
            if (role == UserRole.CUSTOMER) {
                cWindow.refreshProducts();
            }

            else if (role == UserRole.MANAGER) {
                managerWindow.refreshProducts();
            }
        }
    }

    /**
     * Save a reference to the manager window.
     * @param m The manager window.
     */
    @Override
    public void saveReferenceToManagerWindow(ManagerWindow m) {
        engine.addObserver(m);
        this.managerWindow = m;
    }

    /**
     * Handle update stock event.
     * @param product The product to update.
     * @param quantity The new stock quantity.
     * @return true if the stock was successfully updated, false otherwise.
     */
    @Override
    public boolean onUpdateStock(Product product, int quantity) throws IOException {
        synchronized (dataLock) {
            engine.updateProductStock(product, quantity);
            updateStockInFile();
        }
        return true;
    }

    /**
     * Handle add product event.
     */
    @Override
    public void onAddProduct() {
        new AddProductDialog(startPage, this);
    }

    /**
     * Get the available product types.
     * @return An array of available product types.
     */
    @Override
    public String[] getAvailableProductTypes() {
        return new String[] { "BOOK", "CLOTHING", "ELECTRONICS" };
    }

    /**
     * Create a new product and add it to the store.
     * @param category The category of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The stock quantity of the product.
     * @param description The description of the product.
     * @param author The author of the book (if applicable).
     * @param pages The number of pages in the book (if applicable).
     * @param size The size of the clothing (if applicable).
     * @param color The color of the product.
     * @param brand The brand of the electronic product (if applicable).
     * @param warranty The warranty period of the electronic product (if applicable).
     */
    @Override
    public void createProduct(Category category, String name, double price, int stock, String description, String author, Integer pages, String size, Color color, String brand, int warranty) {
        ProductFactory factory = new ProductFactory();
        Product product = factory.getProduct(category, name, price, stock, description, color, size, brand, warranty, author, pages);
        engine.addProduct(product);
    }

    /**
     * Open a new customer window.
     * @param userName The name of the customer.
     * @param email The email of the customer.
     */
    @Override
    public void openCustomerWindow(String userName, String email) {
        executor.execute(() -> {
            SwingUtilities.invokeLater(() -> {
                CustomerWindow window = new CustomerWindow(startPage, this, engine.getAvailableProducts());
                Customer customer = new Customer(userName, email);
                if (!engine.registerCustomer(customer)){
                    for(Customer cust: engine.getCustomers()){
                        if(cust.equals(customer)){
                            customer = cust;
                            break;
                        }
                    }
                }
                engine.addObserver(window);
                customersByWindow.put(window, customer);
            });
        });
    }

    /**
     * Open the manager window.
     */
    @Override
    public void openManagerWindow() {
        executor.execute(() -> {
            SwingUtilities.invokeLater(() -> {
                managerWindow = ManagerWindow.getInstance(startPage, this, getAllProducts());
            });
        });
    }

    /**
     * Remove a customer window from the controller.
     * @param window The customer window to remove.
     */
    @Override
    public void removeCustomerWindow(CustomerWindow window) {
        engine.removeObserver(window);
        customersByWindow.remove(window);
    }

    @Override
    public void updateStockInFile() throws IOException {
        SaveProduct sp = new SaveProduct("src/resources/store_products.csv");
        sp.saveAllProducts(new HashSet<>(engine.getProducts()));
    }

    /**
     * Get the manager window.
     * @return The manager window.
     */
    public ManagerWindow getManagerWindow(){
        return managerWindow;
    }



}
