/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.core;

import store.orders.Order;

/**
 * The ShippingAdapter class adapts the FastShipAPI to the ShippingProvider interface.
 */
public class ShippingAdapter implements ShippingProvider {
    private FastShipAPI fastShipAPI;

    public ShippingAdapter(){
        fastShipAPI = new FastShipAPI();
    }

    /**
     * Ships the given order using the FastShipAPI.
     * @param order the order to be shipped
     */
    @Override
    public void shipOrder(Order order) {
        fastShipAPI.executeDelivery(order.getOrderDetails());
    }
}
