package store.gui.view;

import store.gui.controller.StoreListener;
import store.products.Product;

import javax.swing.*;
import java.util.List;

/**
 * MainPage class representing the main page of the department store.
 * Displays a welcome title, top panel, and product grid.
 */
public class MainPage extends JFrame {

    private final TopPanel topPanel;
    private final ProductGridPanel imageGrid;

    /**
     * Constructor to create the MainPage for the department store.
     * @param products the list of products to display
     * @param listener the StoreListener for handling events
     */
    public MainPage(List<Product> products, StoreListener listener) {
        super("Department store");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        add(new TitlePanel("WELCOME!"));

        topPanel = new TopPanel(null, listener, this, UserRole.MANAGER);
        add(topPanel);

        imageGrid = new ProductGridPanel(products, listener, UserRole.MANAGER, null);
        add(imageGrid);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Gets the top panel of the main page.
     * @return the TopPanel instance
     */
    public TopPanel getTopPanel() {
        return topPanel;
    }

}
