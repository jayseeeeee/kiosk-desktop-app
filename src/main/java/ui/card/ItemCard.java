package ui.card;

import app.store.Product;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public final class ItemCard extends Card {

    public ItemCard(Product product) {
        ImageIcon menuImage = FileHandler.scaleImage(FileHandler.IMAGE_FOLDER, product.imagePath, 100, 100);
        if (menuImage == null) {
            menuImage = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "no-image.png", 100);
        }
        super(product.name, menuImage, new Dimension(140, 48));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setMaximumSize(new Dimension(450, 120));
        this.add(Box.createHorizontalStrut(10));
        this.add(this.cardImage);
        this.add(Box.createHorizontalStrut(10));
        this.add(this.cardTitle);
        this.add(Box.createHorizontalStrut(10));
        this.add(getItemQuantity(product));
        this.add(Box.createHorizontalStrut(30));
        this.add(getItemPrice(product));
    }

    private JLabel getItemQuantity(Product product) {
        JLabel itemPrice = new JLabel(String.valueOf(product.quantity));
        itemPrice.setFont(new Font(UserUi.FONT, Font.PLAIN, 18));
        return itemPrice;
    }

    private JLabel getItemPrice(Product product) {
        JLabel itemPrice = new JLabel(String.format("₱%.2f", product.cost));
        itemPrice.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        return itemPrice;
    }
}
