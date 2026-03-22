package ui.user.card;

import ui.user.UserUi;
import app.Product;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;

abstract class Card extends JPanel {
    JLabel cardImage;
    JLabel cardTitle;
    JPanel textContainer;

    Card(String title, ImageIcon imageIcon, Dimension textDimension) {
        this.setBackground(Color.white);
        this.setBorder(UserUi.BORDER_STYLE);

        cardImage = new JLabel(imageIcon);
        cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("Helvetica", Font.BOLD, 18));

        textContainer = new JPanel(new FlowLayout(FlowLayout.LEADING));
        textContainer.setOpaque(false);
        textContainer.setPreferredSize(textDimension);
        textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
        textContainer.add(cardTitle);

        this.add(cardImage);
        this.add(textContainer);
    }
}