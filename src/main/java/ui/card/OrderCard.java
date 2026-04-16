package ui.card;

import app.store.Order;
import ui.admin.orders.OrdersTab;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public final class OrderCard extends Card {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");
    private static final int widthGap = 30;

    public OrderCard(Order order, OrdersTab ordersTab) {
        ImageIcon statusIcon = null;
        switch (order.getStatus()) {
            case Order.PAYMENT_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "payment_status.png", 48);
            case Order.PREPARING_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "preparing_status.png", 48);
            case Order.SERVING_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "serving_status.png", 48);
        }
        super(String.format("%03d", order.orderCount), statusIcon, new Dimension(60, 40));
        this.setMaximumSize(new Dimension(900, 96));
        this.cardTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 32));
        this.add(Box.createVerticalStrut(80));
        this.add(this.cardTitle);
        this.add(Box.createHorizontalStrut(widthGap));
        this.add(this.cardImage);
        this.add(Box.createHorizontalStrut(widthGap));
        this.add(getTime(order));
        this.add(Box.createHorizontalStrut(widthGap));
        this.add(getAmount(order));
        this.add(Box.createHorizontalStrut(widthGap));
        this.add(getDetailsButton(order, ordersTab));
    }

    private JLabel getTime(Order order) {
        JLabel time = new JLabel(order.getDateTime().format(dateTimeFormatter));
        time.setFont(new Font("Helvetica", Font.PLAIN, 24));
        time.setPreferredSize(new Dimension(100, 18));
        return time;
    }

    private JLabel getAmount(Order order) {
        JLabel amount = new JLabel(String.format("₱%.0f", order.finalCost));
        amount.setFont(new Font("Helvetica", Font.BOLD, 24));
        amount.setPreferredSize(new Dimension(72, 18));
        return amount;
    }

    private JButton getDetailsButton(Order order, OrdersTab ordersTab) {
        JButton detailsButton = new JButton("DETAILS");
        detailsButton.setFocusable(false);
        detailsButton.setPreferredSize(new Dimension(128, 48));
        detailsButton.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        detailsButton.addActionListener(_ -> ordersTab.viewOrder(order));
        return detailsButton;
    }
}
