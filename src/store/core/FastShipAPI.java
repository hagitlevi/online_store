/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.core;

/**
 * FastShipAPI class simulates interaction with an external shipping service.
 */
public class FastShipAPI {

    public FastShipAPI() {}

    /**
     * Simulates executing a delivery with the given details.
     * @param details the delivery details
     */
    public void executeDelivery(String details){
        System.out.print("Delivering the order: " + details);
    }
}
