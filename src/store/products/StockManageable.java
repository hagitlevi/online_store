/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the StockManageable interface representing items with stock management capabilities.
 */
package store.products;

/**
 * The StockManageable interface represents items with stock management capabilities.
 */
public interface StockManageable {
    public int getStock();
    public boolean increaseStock(int amount);
    public boolean decreaseStock(int amount);
}
