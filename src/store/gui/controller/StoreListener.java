package store.gui.controller;

import store.cart.CartItem;
import store.core.Customer;
import store.gui.view.CustomerWindow;
import store.gui.view.ManagerWindow;
import store.gui.view.ShoppingCartPage;
import store.gui.view.UserRole;
import store.orders.Order;
import store.products.Category;
import store.products.Product;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Interface defining the listener for store events.
 */
public interface StoreListener {
    void onProductClicked(CustomerWindow window, Product product, UserRole role);
    boolean onAddToCart(CustomerWindow window, Product p, int amount);
    boolean IsInCart(CustomerWindow window, Product p);
    boolean onLoadFromFile(java.io.File file);
    boolean onSaveToFile(java.io.File file);
    void onShoppingCartBtn(CustomerWindow window);
    List<CartItem> getItems(CustomerWindow window);
    String getTotalPrice(CustomerWindow window);
    boolean onDeleteClicked(CustomerWindow window, Product p, ShoppingCartPage cartPage);
    boolean onCheckoutClicked(CustomerWindow window, ShoppingCartPage page);
    void onCategoryFilter(CustomerWindow window, String category, UserRole role);
    List<Order> getAllCustomerOrders(CustomerWindow window);
    String getOrderDetails(Order o);
    void onOrdersHistoryCartBtn(CustomerWindow window);
    void onSearchProductBtn(CustomerWindow cWindow, ManagerWindow mWindow, String name, UserRole role);
    List<Product> getAllProducts();
    List<Product> getAllAvailableProducts();
    void saveReferenceToManagerWindow(ManagerWindow m);
    boolean onUpdateStock(Product product, int quantity)throws IOException;
    void onAddProduct();
    void createProduct(Category category, String name, double price, int stock, String description, String author, Integer pages, String size, Color color, String brand, int warranty);
    String[] getAvailableProductTypes();
    void openCustomerWindow(String userName, String email);
    void openManagerWindow();
    void removeCustomerWindow(CustomerWindow window);
    ManagerWindow getManagerWindow();
    void updateStockInFile()throws IOException;
}

