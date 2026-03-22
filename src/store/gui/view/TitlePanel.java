package store.gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * TitlePanel class representing a panel that displays a title.
 */
public class TitlePanel extends JPanel {

    /**
     * Constructor to create a TitlePanel with the specified title text.
     * @param t the title text to display
     */
    public TitlePanel(String t) {
        JLabel title = new JLabel(t);
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 40, 0));
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(title);
    }
}
