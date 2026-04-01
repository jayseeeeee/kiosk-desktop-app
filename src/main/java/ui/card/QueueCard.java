package ui.card;

import app.store.Order;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public class QueueCard extends Card {
    public QueueCard(Order order) {
        ImageIcon statusIcon = null;
        switch (order.getStatus()) {
            case Order.PAYMENT_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "payment_status.png", 48);
            case Order.PREPARING_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "preparing_status.png", 48);
            case Order.SERVING_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "serving_status.png", 48);
        }
        super(String.format("%03d", order.orderCount), statusIcon, new Dimension(96, 42));
        this.setPreferredSize(new Dimension(420, 96));
        this.setMaximumSize(new Dimension(420, 96));

        this.cardTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 32));

        this.add(Box.createVerticalStrut(84));
        this.add(textContainer);
        this.add(cardImage);
    }
}
