package store.gui.view;

import store.engine.StoreEngine;
import store.gui.controller.StoreController;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;

/**
 * DisplayOrder class representing a panel to display an order's details.
 * Shows order information in a titled border.
 */
public class DisplayOrder extends JPanel {

    /**
     * Constructor to create the DisplayOrder panel.
     * @param order the Order to display
     * @param listener the StoreController for retrieving order details
     */
    public DisplayOrder(Order order , StoreController listener){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Order"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JLabel details = new JLabel(listener.getOrderDetails(order));
        this.add(details);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 210));
    }
}
