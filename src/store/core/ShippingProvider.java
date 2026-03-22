/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.core;

import store.orders.Order;

/**
 * The ShippingProvider interface defines the contract for shipping providers to implement.
 */
public interface ShippingProvider {
    void shipOrder(Order order);
}
