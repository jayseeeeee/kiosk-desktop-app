package ui.card;

import ui.user.UserUi;
import app.store.Product;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public final class MenuCard extends Card {
    public MenuCard(Product product, UserUi userUi) {
        ImageIcon menuImage = FileHandler.scaleImage(FileHandler.IMAGE_FOLDER, product.imagePath, 240, 240);
        if (menuImage == null) {
            menuImage = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "no-image.png", 240);
        }
        super(product.name, menuImage, new Dimension(280, 40));
        this.setPreferredSize(new Dimension(300, 380));

        JLabel menuDescription = new JLabel(String.format("<html>%s</html>", product.description));
        menuDescription.setFont(new Font(UserUi.FONT, Font.ITALIC, 12));

        JLabel menuPrice = new JLabel(String.format("₱%.2f", product.cost));
        menuPrice.setForeground(new Color(0x68463C));
        menuPrice.setPreferredSize(new Dimension(164, 32));
        menuPrice.setFont(new Font(UserUi.FONT, Font.BOLD, 32));

        JButton addToCart = new JButton(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "add.png", 48));
        addToCart.setBorderPainted(false);
        addToCart.setContentAreaFilled(false);
        addToCart.setFocusable(false);
        addToCart.addActionListener(_ -> userUi.basketTab.addBasket(product));

        this.textContainer.add(menuDescription);
        this.add(menuPrice);
        this.add(addToCart);
    }
}