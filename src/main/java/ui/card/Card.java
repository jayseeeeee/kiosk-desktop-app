package ui.card;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

abstract class Card extends JPanel {
    JLabel cardImage;
    JLabel cardTitle;
    JPanel textContainer;

    Card(String title, ImageIcon imageIcon, Dimension textDimension) {
        setupCard(title, imageIcon, textDimension);
        this.add(cardTitle);
    }

    Card(String title, ImageIcon imageIcon, Dimension textDimension, Dimension containerDimension) {
        setupCard(title, imageIcon, textDimension);
        textContainer = new JPanel(new FlowLayout(FlowLayout.LEADING));
        textContainer.setOpaque(false);
        textContainer.setPreferredSize(containerDimension);
        textContainer.add(cardTitle);
        this.add(textContainer);
    }

    private void setupCard(String title, ImageIcon imageIcon, Dimension textDimension) {
        cardImage = new JLabel(imageIcon);
        cardTitle = new JLabel(String.format("<html>%s</html>", title));
        cardTitle.setFont(new Font("Helvetica", Font.BOLD, 18));
        cardTitle.setPreferredSize(textDimension);

        this.setBackground(Color.white);
        this.setBorder(UserUi.BORDER_STYLE);
        this.add(cardImage);
    }
}