package store.gui.view;

import store.gui.controller.StoreListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * TopPanel class representing the top panel of the GUI.
 * Contains buttons and controls for loading, saving, filtering, searching, and user actions.
 */
public class TopPanel extends JPanel {

    private final StoreListener listener;
    private JButton loadBtn;
    private JButton saveBtn;
    private JButton cartBtn;
    private JButton orderHistoryBtn;
    private JComboBox<String> combo;
    private JTextField searchField;
    private JButton addProductBtn;
    private JPanel adminPanel;
    private JPanel customerPanel;
    private CustomerWindow window;

    /**
     * Constructor to create the TopPanel.
     * @param window the CustomerWindow
     * @param listener the StoreListener for handling events
     * @param parent the parent component for file choosers
     * @param role the UserRole of the current user
     */
    public TopPanel(CustomerWindow window, StoreListener listener, Component parent, UserRole role) {
        this.listener = listener;
        this.window = window;

        // ===== layout ראשי =====
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));

        // ===== Add product =====
        addProductBtn = new JButton("Add Product");
        addProductBtn.addActionListener(e -> {
            if (listener != null) {
                listener.onAddProduct();
            }
        });

        // ===== Load =====
        loadBtn = new JButton("load products from a file");
        loadBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter(
                            "CSV files", "csv"
                    )
            );
            if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (listener != null) {
                    if(!listener.onLoadFromFile(selectedFile)){
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to load products from file",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });

        // ===== Save =====
        saveBtn = new JButton("Save products to a file");
        saveBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter(
                            "CSV files", "csv"
                    )
            );
            if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getName().toLowerCase().endsWith(".csv")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".csv");
                }
                if (listener != null) {
                    if (!listener.onSaveToFile(selectedFile)) {
                        JOptionPane.showMessageDialog(parent,
                                "Error saving to file!",
                                "File Save Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // ===== Filter =====
        String[] categories = {"Filter", "BOOKS", "CLOTHING", "ELECTRONICS"};
        combo = new JComboBox<>(categories);
        combo.setSelectedIndex(0);
        combo.addActionListener(e -> {
            if (listener != null) {
                if(role == UserRole.MANAGER) {
                    listener.onCategoryFilter(null, (String) combo.getSelectedItem(), role);
                }
                else if(role == UserRole.CUSTOMER){
                    listener.onCategoryFilter(window, (String) combo.getSelectedItem(), role);
                }
            }
        });

        // ===== Search =====
        searchField = new JTextField("Enter product name or 'all'", 15);
        searchField.addActionListener(e -> {
            if (listener != null) {
                if(role == UserRole.MANAGER) {
                    listener.onSearchProductBtn(null, listener.getManagerWindow(), searchField.getText(), role);
                }
                else if(role == UserRole.CUSTOMER){
                    listener.onSearchProductBtn(window, null, searchField.getText(), role);
                }
            }
        });

        // ===== Cart =====
        cartBtn = new JButton("Cart");
        cartBtn.addActionListener(e -> {
            if (listener != null) {
                listener.onShoppingCartBtn(window);
            }
        });

        // ===== Order History =====
        orderHistoryBtn = new JButton("Order History");
        orderHistoryBtn.addActionListener(e -> {
            if (listener != null) {
                listener.onOrdersHistoryCartBtn(window);
            }
        });

        // ===== Panels פנימיים =====
        adminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        adminPanel.add(addProductBtn);
        adminPanel.add(loadBtn);
        adminPanel.add(saveBtn);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        searchPanel.add(combo);
        searchPanel.add(searchField);

        customerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        customerPanel.add(cartBtn);
        customerPanel.add(orderHistoryBtn);

        // ===== הוספה ל־TopPanel =====
        add(adminPanel);
        add(Box.createHorizontalGlue());
        add(searchPanel);
        add(Box.createHorizontalGlue());
        add(customerPanel);
    }

    /**
     * Shows or hides the admin controls panel.
     * @param show true to show, false to hide
     */
    public void showAdminControls(boolean show) {
        adminPanel.setVisible(show);
    }

    /**
     * Shows or hides the customer controls panel.
     * @param show true to show, false to hide
     */
    public void showCustomerControls(boolean show) {
        customerPanel.setVisible(show);
    }
}
