package store.gui.view;

import store.gui.controller.StoreListener;
import javax.swing.*;
import java.awt.*;

/**
 * StartPage class representing the initial login window for the store.
 * Allows users to enter as a customer or admin.
 */
public class StartPage extends JFrame {

    private JPanel customerFormPanel;
    private JTextField usernameField;
    private JTextField emailField;
    private StoreListener listener;

    /**
     * Constructor to create the StartPage for store login.
     * @param listener the StoreListener for handling events
     */
    public StartPage(StoreListener listener) {
        super("Store Login");
        this.listener = listener;

        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        // ===== Customer button =====
        JButton customerBtn = new JButton("Enter as Customer");
        customerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ===== Customer form (hidden at start) =====
        customerFormPanel = new JPanel();
        customerFormPanel.setLayout(new GridLayout(3, 2, 10, 10));
        customerFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        customerFormPanel.setVisible(false);

        customerFormPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        customerFormPanel.add(usernameField);

        customerFormPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        customerFormPanel.add(emailField);

        JButton enterCustomerBtn = new JButton("Enter");
        customerFormPanel.add(new JLabel()); // spacer
        customerFormPanel.add(enterCustomerBtn);

        // ===== Admin button =====
        JButton adminBtn = new JButton("Enter as Admin");
        adminBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ===== Actions =====
        customerBtn.addActionListener(e -> {
            customerFormPanel.setVisible(!customerFormPanel.isVisible());
        });

        enterCustomerBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();

            if (username.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                listener.openCustomerWindow(username, email);
            }
        });

        adminBtn.addActionListener(e -> {
            listener.openManagerWindow();
        });

        // ===== Add components =====
        add(Box.createVerticalStrut(20));
        add(customerBtn);
        add(customerFormPanel);
        add(Box.createVerticalStrut(20));
        add(adminBtn);

        setVisible(true);
    }
}
