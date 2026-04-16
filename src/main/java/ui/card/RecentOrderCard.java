package ui.card;

import app.store.Order;
import ui.admin.orders.OrdersTab;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public final class RecentOrderCard extends Card {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");
    private static final int widthGap = 30;

    public RecentOrderCard(Order order) {
        super(String.format("%03d", order.orderCount), null, new Dimension(60, 40));
        this.setPreferredSize(new Dimension(400, 80));
        this.cardTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 32));
        this.remove(this.cardImage);
        this.add(Box.createVerticalStrut(60));
        this.add(this.cardTitle);
        this.add(Box.createHorizontalStrut(widthGap));
        this.add(getAmount(order));
        this.add(Box.createHorizontalStrut(widthGap));
        this.add(getTime(order));
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
}
