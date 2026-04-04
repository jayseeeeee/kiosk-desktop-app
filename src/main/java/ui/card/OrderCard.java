package ui.card;

import app.store.Order;
import ui.admin.orders.OrdersTab;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class OrderCard extends Card {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");

    public OrderCard(Order order, OrdersTab ordersTab) {
        ImageIcon statusIcon = null;
        switch (order.getStatus()) {
            case Order.PAYMENT_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "payment_status.png", 48);
            case Order.PREPARING_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "preparing_status.png", 48);
            case Order.SERVING_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "serving_status.png", 48);
        }
        super(String.format("%03d", order.orderCount), statusIcon, new Dimension(96, 42), false);
        this.setPreferredSize(new Dimension(900, 96));
        this.setMaximumSize(new Dimension(900, 96));
        this.cardTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 32));

        this.add(Box.createVerticalStrut(84));
        this.add(getSelectCheckBox());
        this.add(Box.createHorizontalStrut(10));
        this.add(this.cardTitle);
        this.add(Box.createHorizontalStrut(20));
        this.add(this.cardImage);
        this.add(Box.createHorizontalStrut(50));
        this.add(getTime(order));
        this.add(Box.createHorizontalStrut(40));
        this.add(getAmount(order));
        this.add(Box.createHorizontalStrut(40));
        this.add(getDetailsButton(order, ordersTab));
    }

    private JCheckBox getSelectCheckBox() {
        JCheckBox selectCheckBox = new JCheckBox();
        selectCheckBox.setBorderPainted(false);
        selectCheckBox.setContentAreaFilled(false);
        selectCheckBox.setFocusable(false);
        return selectCheckBox;
    }

    private JLabel getTime(Order order) {
        JLabel time = new JLabel(order.getDateTime().format(dateTimeFormatter));
        time.setFont(new Font("Helvetica", Font.PLAIN, 24));
        time.setPreferredSize(new Dimension(128, 18));
        return time;
    }

    private JLabel getAmount(Order order) {
        JLabel amount = new JLabel(String.format("₱%.0f", order.finalCost));
        amount.setFont(new Font("Helvetica", Font.BOLD, 24));
        amount.setPreferredSize(new Dimension(64, 18));
        return amount;
    }

    private JButton getDetailsButton(Order order, OrdersTab ordersTab) {
        JButton detailsButton = new JButton("DETAILS");
        detailsButton.setFocusable(false);
        detailsButton.setPreferredSize(new Dimension(128, 48));
        detailsButton.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        detailsButton.addActionListener(_ -> ordersTab.updateList(order));
        return detailsButton;
    }
}
