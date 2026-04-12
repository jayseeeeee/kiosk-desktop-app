package ui.card;

import ui.user.UserUi;
import app.store.Product;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public final class BasketCard extends Card{
    public BasketCard(Product product, UserUi userUi) {
        ImageIcon menuImage = FileHandler.scaleImage(FileHandler.IMAGE_FOLDER, product.imagePath, 128, 128);
        if (menuImage == null) {
            menuImage = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "no-image.png", 128);
        }
        super(product.name, menuImage, new Dimension(128, 48), new Dimension(128, 84));
        this.setPreferredSize(new Dimension(420, 164));
        this.setMaximumSize(new Dimension(420, 164));
        textContainer.add(getItemPrice(product));
        this.add(getQuantityComboBox(product, userUi));
        this.add(getTrashButton(product, userUi));
    }

    private JLabel getItemPrice(Product product) {
        JLabel itemPrice = new JLabel(String.format("₱%.2f", product.cost));
        itemPrice.setFont(new Font(UserUi.FONT, Font.PLAIN, 18));
        return itemPrice;
    }

    private JComboBox<Integer> getQuantityComboBox(Product product, UserUi userUi) {
        JComboBox<Integer> quantityComboBox = new JComboBox<>(new Integer[] {1,2,3,4,5,6,7,8,9});
        quantityComboBox.setSelectedIndex(product.quantity - 1);
        quantityComboBox.setFont(new Font(UserUi.FONT, Font.PLAIN, 16));
        quantityComboBox.setPreferredSize(new Dimension(48,48));
        quantityComboBox.setFocusable(false);
        quantityComboBox.addActionListener(_ -> {
            product.quantity = quantityComboBox.getSelectedIndex() + 1;
            userUi.queueTab.updateInitialCost();
        });
        return quantityComboBox;
    }

    private JButton getTrashButton(Product product, UserUi userUi) {
        JButton trashButton = new JButton(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "trash.png", 48));
        trashButton.setBorderPainted(false);
        trashButton.setContentAreaFilled(false);
        trashButton.setFocusable(false);
        trashButton.addActionListener(_ -> userUi.basketTab.removeBasket(product));
        return trashButton;
    }
}