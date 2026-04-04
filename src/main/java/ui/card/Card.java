package ui.card;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

abstract class Card extends JPanel {
    JLabel cardImage;
    JLabel cardTitle;
    JPanel textContainer;

    Card(String title, ImageIcon imageIcon, Dimension textDimension, boolean isTextContainer) {
        this.setBackground(Color.white);
        this.setBorder(UserUi.BORDER_STYLE);

        cardImage = new JLabel(imageIcon);
        cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("Helvetica", Font.BOLD, 18));
        this.add(cardImage);

        if (isTextContainer) {
            textContainer = new JPanel(new FlowLayout(FlowLayout.LEADING));
            textContainer.setOpaque(false);
            textContainer.setPreferredSize(textDimension);
            textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
            textContainer.add(cardTitle);
            this.add(textContainer);
        } else {
            cardTitle.setPreferredSize(textDimension);
            this.add(cardTitle);
        }
    }
}