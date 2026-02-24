package ui.card;

import ui.user.UserUi;
import product.Product;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public abstract class Card extends JPanel {
    JLabel cardImage;
    JLabel cardTitle;
    JPanel textContainer;
    Product product;

    Card(Product product, int imageWidth, int imageHeight, int textContainerWidth, int textContainerHeight) {
        this.product = product;
        this.setBackground(Color.white);
        this.setBorder(UserUi.BORDER_STYLE);

        cardImage = new JLabel(FileHandler.scaleImage(FileHandler.IMAGE_FOLDER, product.imagePath, imageHeight, imageWidth));

        cardTitle = new JLabel(product.name);
//        cardTitle.setPreferredSize(new Dimension(textContainerWidth, 16));
        cardTitle.setFont(new Font("Helvetica", Font.BOLD, 16));

        textContainer = new JPanel(new FlowLayout(FlowLayout.LEADING));
        textContainer.setBackground(Color.pink);
        textContainer.setPreferredSize(new Dimension(textContainerWidth, textContainerHeight));
        textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
        textContainer.add(cardTitle);

        this.add(cardImage);
        this.add(textContainer);
    }
}