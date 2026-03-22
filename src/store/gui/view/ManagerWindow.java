package store.gui.view;

import store.gui.controller.StoreListener;
import store.products.Product;

import javax.swing.*;
import java.util.List;

/**
 * ManagerWindow class representing the main window for managers.
 * Displays products and provides manager-specific controls.
 */
public class ManagerWindow extends JFrame implements StoreObserver{
    private static ManagerWindow instance = null;
    private final TopPanel topPanel;
    private final ProductGridPanel imageGrid;
    private final UserRole role = UserRole.MANAGER;
    private StoreListener listener;

    /**
     * Constructor to create the ManagerWindow.
     * @param parent the parent JFrame
     * @param listener the StoreListener for handling events
     * @param productList the list of products to display
     */
    private ManagerWindow(JFrame parent, StoreListener listener, List<Product> productList) {
        super("Manager Window");
        listener.saveReferenceToManagerWindow(this);
        this.listener = listener;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        add(new TitlePanel("Management page"));

        topPanel = new TopPanel(null, listener, this, UserRole.MANAGER);
        topPanel.showAdminControls(true);
        topPanel.showCustomerControls(false);
        add(topPanel);

        imageGrid = new ProductGridPanel(productList, listener, role, null);
        add(imageGrid);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    /**
     * Get the singleton instance of ManagerWindow.
     * If an instance already exists, it brings it to the front.
     * @param parent the parent JFrame
     * @param listener the StoreListener for handling events
     * @param productList the list of products to display
     * @return the singleton instance of ManagerWindow
     */
    public static ManagerWindow getInstance(JFrame parent, StoreListener listener, List<Product> productList){
        if(instance != null){
            instance.toFront();
            instance.requestFocus();
            return instance;
        }

        instance =  new ManagerWindow(parent, listener, productList);
        return instance;
    }


    @Override
    public void refreshProducts() {
        imageGrid.updateProducts(listener.getAllProducts());
    }

    public void refreshToSpecificProducts(List<Product> products){
        imageGrid.updateProducts(products);
    }
}
