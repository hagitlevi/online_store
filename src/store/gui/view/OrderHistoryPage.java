package store.gui.view;

import store.gui.controller.StoreController;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * OrderHistoryPage class representing a dialog to display the order history of a customer.
 * Shows a list of past orders or a message if there are no orders.
 */
public class OrderHistoryPage extends JDialog {

    /**
     * Constructor to create the OrderHistoryPage dialog.
     * @param window the CustomerWindow for which to display order history
     * @param listener the StoreController for retrieving order data
     */
    public OrderHistoryPage(CustomerWindow window, StoreController listener){
        this.setModal(true);
        this.setLayout(new BoxLayout(this.getContentPane() ,BoxLayout.PAGE_AXIS));
        this.add(new TitlePanel("Orders history"));

        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.PAGE_AXIS));
        ordersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        List<Order> orders = listener.getAllCustomerOrders(window);
        if(orders == null || orders.isEmpty()){
            JLabel massage = new JLabel("No orders history");
            massage.setAlignmentX(Component.CENTER_ALIGNMENT);
            ordersPanel.add(massage);
        }
        else{
            for(Order o: orders){
                JPanel order = new DisplayOrder(o, listener);
                ordersPanel.add(order);
            }
        }

        this.add(ordersPanel);

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }
}
