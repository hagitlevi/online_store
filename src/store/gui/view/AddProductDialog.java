/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.gui.view;

import store.gui.controller.StoreController;
import store.products.Category;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog for adding a new product to the store.
 * Supports different product categories with specific fields.
 * Uses StoreController to handle product creation.
 */
public class AddProductDialog extends JDialog {

    private static final Color[] AVAILABLE_COLORS = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.GRAY};
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JTextField descriptionField;
    private JComboBox<Category> typeCombo;
    private JComboBox<Color> colorCombo;
    private final StoreController controller;
    private JTextField authorField;
    private JTextField pagesField;
    private JTextField sizeField;
    private JTextField brandField;
    private JTextField warrantyField;
    private JPanel formPanel;


    /**
     * Constructor to create the AddProductDialog.
     * @param parent the parent JFrame
     * @param controller the StoreController to handle product creation
     */
    public AddProductDialog(JFrame parent, StoreController controller) {
        super(parent, "Add Product", true);
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ===== Form Panel =====
        formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        formPanel.add(new JLabel("Product Type:"));
        typeCombo = new JComboBox<>(Category.values());
        formPanel.add(typeCombo);


        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        formPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        formPanel.add(stockField);

        formPanel.add(new JLabel("Color:"));
        colorCombo = new JComboBox<>(AVAILABLE_COLORS);
        formPanel.add(colorCombo);
        colorCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                if (value instanceof Color c) {
                    label.setText(
                            c == Color.BLACK ? "Black" :
                                    c == Color.RED ? "Red" :
                                            c == Color.BLUE ? "Blue" :
                                                    c == Color.GREEN ? "Green" :
                                                            "Gray"
                    );
                }
                return label;
            }
        });


        formPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        formPanel.add(descriptionField);

        typeCombo.addActionListener(e -> updateExtraFields());
        updateExtraFields();

        add(formPanel, BorderLayout.CENTER);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton addBtn = new JButton("Add");
        JButton cancelBtn = new JButton("Cancel");

        buttonPanel.add(cancelBtn);
        buttonPanel.add(addBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // ===== Actions =====
        cancelBtn.addActionListener(e -> dispose());

        addBtn.addActionListener(e -> handleAdd());

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    /**
     * Handles the addition of a new product when the "Add" button is clicked.
     * Validates input fields and calls the controller to create the product.
     */
    private void handleAdd() {
        try {
            Category category = (Category) typeCombo.getSelectedItem();
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int stock = Integer.parseInt(stockField.getText().trim());
            String description = descriptionField.getText().trim();
            Color color = (Color) colorCombo.getSelectedItem();

            if (name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }

            String author = null;
            int pages = -1;
            String size = null;
            String brand = null;
            int warranty = -1;

            // read extra fields based on category
            if (category == Category.BOOKS) {
                author = authorField.getText().trim();
                pages = Integer.parseInt(pagesField.getText().trim());

            } else if (category == Category.CLOTHING) {
                size = sizeField.getText().trim();

            } else if (category == Category.ELECTRONICS) {
                brand = brandField.getText().trim();
                warranty = Integer.parseInt(warrantyField.getText().trim());
            }

            controller.createProduct(category, name, price, stock, description, author, pages, size, color, brand, warranty);

            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Updates the extra fields in the form based on the selected product category.
     */
    private void updateExtraFields() {
        while (formPanel.getComponentCount() > 12) {
            formPanel.remove(formPanel.getComponentCount() - 1);
        }

        Category category = (Category) typeCombo.getSelectedItem();

        if (category == Category.BOOKS) {
            formPanel.add(new JLabel("Author:"));
            authorField = new JTextField();
            formPanel.add(authorField);

            formPanel.add(new JLabel("Pages:"));
            pagesField = new JTextField();
            formPanel.add(pagesField);

        } else if (category == Category.CLOTHING) {
            formPanel.add(new JLabel("Size:"));
            sizeField = new JTextField();
            formPanel.add(sizeField);


        } else if (category == Category.ELECTRONICS) {
            formPanel.add(new JLabel("Brand:"));
            brandField = new JTextField();
            formPanel.add(brandField);

            formPanel.add(new JLabel("Warranty (months):"));
            warrantyField = new JTextField();
            formPanel.add(warrantyField);
        }

        formPanel.revalidate();
        formPanel.repaint();
        pack();
    }

}
