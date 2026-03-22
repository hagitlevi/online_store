package store.gui.view;

import store.gui.controller.StoreListener;
import store.products.Product;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

/**
 * ProductGridPanel class representing a grid panel to display products.
 * Each product is displayed as a button with an image.
 */
public class ProductGridPanel extends JPanel {

    private static final int ICON_SIZE = 120;
    private final StoreListener listener;
    private UserRole role;
    private CustomerWindow customerWindow;

    /**
     * Constructor to create the ProductGridPanel.
     * @param products the list of products to display
     * @param listener the StoreListener for handling events
     * @param role the UserRole of the current user
     * @param window the CustomerWindow (can be null for non-customer roles)
     */
    public ProductGridPanel(List<Product> products, StoreListener listener, UserRole role, CustomerWindow window) {
        this.customerWindow = window;
        this.role = role;
        this.listener = listener;
        setLayout(new GridLayout(0, 4, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        updateProducts(products);
    }

    /**
     * Updates the product grid with the given list of products.
     * @param products the list of products to display
     */
    public void updateProducts(List<Product> products) {
        removeAll();

        if (!products.isEmpty()) {
            for (Product p : products) {
                URL url = getClass().getResource(p.getImagePath());

                if (url == null) {
                    System.err.println("Missing image: " + p.getImagePath());
                    continue;
                }

                ImageIcon image = new ImageIcon(url);
                JButton btn = new JButton(scaleIcon(image, ICON_SIZE));

                btn.addActionListener(e -> {
                    if (listener != null) {
                        listener.onProductClicked(customerWindow, p, role);
                    }
                });

                if (listener.IsInCart(customerWindow ,p)) {
                    btn.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                }

                btn.setContentAreaFilled(false);
                btn.setFocusPainted(true);
                btn.setToolTipText(p.toString());

                JPanel cell = new JPanel(new GridBagLayout());
                cell.add(btn);
                add(cell);
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Scales the given ImageIcon to the specified size.
     * @param icon the ImageIcon to scale
     * @param size the desired size (width and height)
     * @return the scaled ImageIcon
     */
    private ImageIcon scaleIcon(ImageIcon icon, int size) {
        Image img = icon.getImage()
                .getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
