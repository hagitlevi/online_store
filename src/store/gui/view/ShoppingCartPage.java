package store.gui.view;

import store.cart.CartItem;
import store.gui.controller.StoreListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * ShoppingCartPage class representing a dialog to display the shopping cart of a customer.
 * Shows a list of items in the cart, total price, and a checkout button.
 */
public class ShoppingCartPage extends JDialog {

    private final StoreListener listener;

    /**
     * Constructor to create the ShoppingCartPage dialog.
     * @param window the CustomerWindow for which to display the shopping cart
     * @param listener the StoreListener for retrieving cart data
     */
    public ShoppingCartPage(CustomerWindow window, StoreListener listener) {
        this.listener = listener;
        this.setModal(true);
        this.setLayout(new BoxLayout(getContentPane() ,BoxLayout.PAGE_AXIS));
        this.add(new TitlePanel("Shopping Cart"));

        //product panel
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.PAGE_AXIS));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        List<CartItem> cartItems = listener.getItems(window);
        if(!cartItems.isEmpty()){
            for(CartItem item: cartItems){
                DisplayCartItem i = new DisplayCartItem(window, item, listener, this);
                cartPanel.add(i);
                cartPanel.add(Box.createVerticalStrut(10));
            }
        } else{
            JLabel massage = new JLabel("Your cart is empty!");
            massage.setAlignmentX(Component.CENTER_ALIGNMENT);
            cartPanel.add(massage);
        }

        //scrolling panel
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        this.add(scrollPane);
        JLabel amount = new JLabel(listener.getTotalPrice(window) + "$");
        amount.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(Box.createVerticalStrut(20));
        this.add(amount);

        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(Box.createVerticalStrut(20));
        checkoutBtn.addActionListener(e -> {
            if(listener.onCheckoutClicked(window, this)){
                JOptionPane.showMessageDialog(
                        this,
                        "The order was created successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else{
                JOptionPane.showMessageDialog(
                        this,
                        "Error with creating the order, check if the cart is empty or try again",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        this.add(checkoutBtn);

        pack();
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
