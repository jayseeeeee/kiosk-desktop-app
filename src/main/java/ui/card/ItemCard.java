package ui.card;

import app.store.Product;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public final class ItemCard extends Card {

    public ItemCard(Product product) {
        ImageIcon menuImage = FileHandler.scaleImage(FileHandler.IMAGE_FOLDER, product.imagePath, 128, 128);
        if (menuImage == null) {
            menuImage = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "no-image.png", 128);
        }
        super(product.name, menuImage, new Dimension(128, 48));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setMaximumSize(new Dimension(450, 164));
        this.add(Box.createHorizontalStrut(20));
        this.add(getItemQuantity(product));
        this.add(Box.createHorizontalStrut(20));
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
