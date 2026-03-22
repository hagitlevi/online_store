package store.gui.view;

import store.core.SystemUpdatable;
import store.gui.controller.StoreListener;
import store.products.Product;
import javax.swing.*;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * CustomerWindow class representing the main window for customers.
 * Displays products and provides customer-specific controls.
 */
public class CustomerWindow extends JFrame implements StoreObserver {
    private final TopPanel topPanel;
    private final ProductGridPanel imageGrid;
    private final UserRole role = UserRole.CUSTOMER;
    private StoreListener listener;

    /**
     * Constructor to create the CustomerWindow.
     * @param parent the parent JFrame
     * @param listener the StoreListener for handling events
     * @param productList the list of products to display
     */
    public CustomerWindow(JFrame parent, StoreListener listener, List<Product> productList) {
        super("Customer Window");
        this.listener = listener;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        add(new TitlePanel("Customer Page!"));

        topPanel = new TopPanel(this, listener, this, UserRole.CUSTOMER);
        topPanel.showAdminControls(false);
        topPanel.showCustomerControls(true);
        add(topPanel);

        imageGrid = new ProductGridPanel(productList, listener, role, this);
        add(imageGrid);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                listener.removeCustomerWindow(CustomerWindow.this);
            }
        });


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    @Override
    public void refreshProducts() {
        imageGrid.updateProducts(listener.getAllAvailableProducts());
    }

    public void refreshToSpecificProducts(List<Product> products){
        imageGrid.updateProducts(products);
    }

}
