package store.gui.view;

import store.cart.CartItem;
import store.gui.controller.StoreListener;
import store.products.Product;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * DisplayCartItem class representing a panel to display a cart item in the shopping cart.
 * Shows product details, quantity, price, and provides a delete button.
 */
public class DisplayCartItem extends JPanel {
    private JButton delete;
    private JLabel details, image;
    private GridBagConstraints gbc = new GridBagConstraints();
    private static final int DETAILS_ICON_SIZE = 150;
    private StoreListener listener;
    private ShoppingCartPage cartPage;
    private CustomerWindow window;

    /**
     * Constructor to create a DisplayCartItem panel.
     * @param window the CustomerWindow
     * @param item the CartItem to display
     * @param listener the StoreListener for handling events
     * @param cartPage the ShoppingCartPage containing this item
     */
    public DisplayCartItem(CustomerWindow window, CartItem item, StoreListener listener, ShoppingCartPage cartPage){
        setLayout(new GridBagLayout());
        this.window = window;
        this.cartPage = cartPage;
        this.listener = listener;
        gbc.insets = new Insets(5,5,5,5);
        Product p = item.getProduct();
        int quantity = item.getQuantity();

        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(p.getDisplayName()),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        delete = new JButton("delete");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(delete, gbc);
        delete.addActionListener(e -> {
            if (listener != null) {
                if(!listener.onDeleteClicked(window, p, cartPage)){
                    JOptionPane.showMessageDialog(this,
                            "Error deleting product!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        details = new JLabel("Quantity: " + quantity + " | Price: " + p.getPrice());
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(details, gbc);


        ImageIcon productIcon = createScaleIcon(p);
        image = new JLabel(productIcon);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(image, gbc);

        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 210));

    }

    /**
     * Creates a scaled ImageIcon for the product.
     * @param p the Product
     * @return the scaled ImageIcon
     */
    private ImageIcon createScaleIcon(Product p){
        ImageIcon rawIcon =
                new ImageIcon(Objects.requireNonNull(
                        MainPage.class.getResource(p.getImagePath())
                ));
        Image img = rawIcon.getImage()
                .getScaledInstance(DETAILS_ICON_SIZE, DETAILS_ICON_SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

}
