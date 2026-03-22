package store.gui.view;

import store.gui.controller.StoreListener;
import store.products.Product;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ProductDetailsPage extends JDialog {
    private Product product;
    private final StoreListener listener;
    private static final int DETAILS_ICON_SIZE = 200;

    /**
     * Constructor to create a ProductDetailsPage dialog.
     * @param product the Product to display details for
     * @param listener the StoreListener for handling events
     * @param role the UserRole of the current user
     * @param window the CustomerWindow (if applicable)
     */
    public ProductDetailsPage(Product product, StoreListener listener, UserRole role, CustomerWindow window) {
        this.product = product;
        this.listener = listener;
        this.setModal(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        String text = "<html>" +
                product.getDisplayDetails().replace("\n", "<br>") +
                "</html>";
        ImageIcon rawIcon =
                new ImageIcon(Objects.requireNonNull(
                        MainPage.class.getResource(product.getImagePath())
                ));

        ImageIcon productIcon = scaleIcon(rawIcon, DETAILS_ICON_SIZE);

        JLabel details = new JLabel(text);
        details.setFont(new Font("Arial", Font.PLAIN, 25));

        JPanel toCart = new JPanel();
        toCart.setLayout(new BoxLayout(toCart, BoxLayout.LINE_AXIS ));

        JTextField quantityField = new JTextField("1", 10);
        JButton actionBtn = new JButton(
                (role == UserRole.MANAGER) ? "Update stock" : "Add to cart"
        );
        toCart.add(quantityField);
        toCart.add(actionBtn);
        actionBtn.addActionListener(e -> {
            try {
                int quantity = Integer.parseInt(quantityField.getText());

                if (role == UserRole.MANAGER) {
                    //manager: change stock
                    if (!listener.onUpdateStock(product, quantity)) {
                        throw new Exception();
                    }
                    listener.updateStockInFile();
                    JOptionPane.showMessageDialog(
                            this,
                            "Stock updated successfully",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    //customer: add to cart
                    if (!listener.onAddToCart(window, product, quantity)) {
                        throw new Exception();
                    }
                    JOptionPane.showMessageDialog(
                            this,
                            "Added " + quantity + " of " + product.getName() + " to cart.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid integer number",
                        "Invalid input",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Operation failed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });



        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.PAGE_AXIS));
        details.setHorizontalAlignment(SwingConstants.CENTER);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        detailsPanel.add(new JLabel(productIcon));
        detailsPanel.add(details);
        detailsPanel.add(toCart);
        detailsPanel.add(Box.createVerticalStrut(20));

        JPanel wrapper = new JPanel(new GridBagLayout());  //for details to be in the center
        wrapper.add(detailsPanel);


        this.add(wrapper);

        this.pack();
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * Scales an ImageIcon to the specified size.
     * @param icon the original ImageIcon
     * @param size the desired size (width and height)
     * @return the scaled ImageIcon
     */
    private ImageIcon scaleIcon(ImageIcon icon, int size) {
        Image img = icon.getImage()
                .getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

}
