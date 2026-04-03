package ui.card;

import app.store.Product;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public class ItemCard extends Card {

    public ItemCard(Product product) {
        ImageIcon menuImage = FileHandler.scaleImage(FileHandler.IMAGE_FOLDER, product.imagePath, 128, 128);
        if (menuImage == null) {
            menuImage = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "no-image.png", 128);
        }
        super(product.name, menuImage, new Dimension(128, 24));
        this.setPreferredSize(new Dimension(420, 164));
        this.setMaximumSize(new Dimension(420, 164));

        textContainer.add(this.cardTitle);
        textContainer.setOpaque(true);
        this.add(textContainer);
        this.add(Box.createHorizontalStrut(20));
        this.add(getItemQuantity(product));
        this.add(Box.createHorizontalStrut(20));
        this.add(getItemPrice(product));
//        this.add(getQuantityComboBox(product, userUi));
//        this.add(getTrashButton(product, userUi));
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
