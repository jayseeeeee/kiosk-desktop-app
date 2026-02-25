package ui.card;

import ui.user.UserUi;
import product.Product;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class Basket extends Card{
    public JComboBox<Integer> quantity;
    JButton trash;

    public Basket(Product product, UserUi userUi) {
        super(product, 128, 128, 96, 48);
        setPreferredSize(new Dimension(420, 164));
        setMaximumSize(new Dimension(420, 164));

        JLabel itemPrice = new JLabel(String.format("₱%.2f", product.cost));
        itemPrice.setFont(new Font(UserUi.FONT, Font.BOLD, 12));

        JPanel editContainer = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        editContainer.setBackground(Color.white);
        editContainer.setPreferredSize(new Dimension(136, 42));

        quantity = new JComboBox<>(new Integer[] {1,2,3,4,5,6,7,8,9});
        quantity.setSelectedIndex(product.quantity - 1);
        quantity.setPreferredSize(new Dimension(64,32));
        quantity.setFocusable(false);
        quantity.addActionListener(_ -> {
            product.quantity = quantity.getSelectedIndex() + 1;
            userUi.queueTab.updateInitialCost();
        });

        trash = new JButton(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "trash.png", 32));
        trash.setBorderPainted(false);
        trash.setContentAreaFilled(false);
        trash.setFocusable(false);
        trash.addActionListener(_ -> userUi.basketTab.removeBasket(product));

        textContainer.add(this.cardTitle);
        textContainer.add(itemPrice);

        editContainer.add(quantity);
        editContainer.add(trash);

        add(textContainer);
        add(editContainer);
    }
}