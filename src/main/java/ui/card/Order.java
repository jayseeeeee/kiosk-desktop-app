package ui.card;

import ui.user.UserUi;
import product.Product;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Order extends JPanel {
    public static final int PAYMENT = 0;
    public static final int PREPARING = 1;
    public static final int SERVING = 2;
    private static int orderCount = 0;
    private final Product[] products;
    private final double finalCost;
    private final int status;
    private JLabel orderStatus;

    public Order(int status, double finalCost, Product... products) {
        this.status = status;
        this.finalCost = finalCost;
        this.products = products;
        this.orderStatus = new JLabel();
        this.setPanel();
        this.setStatus(status);
        ++orderCount;
    }

    private void setPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(Color.white);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(320, 64));
        this.setMaximumSize(new Dimension(320, 64));

        JLabel orderNumber = new JLabel(String.format("%03d", orderCount));
        orderNumber.setPreferredSize(new Dimension(120, 48));
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 16));

        this.add(orderNumber);
        this.add(orderStatus);
    }

    private void setStatus(int status) {
        ImageIcon statusIcon = null;
        switch (status) {
            case PAYMENT -> statusIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/payment.png")));
            case PREPARING -> statusIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/preparing.png")));
            case SERVING -> statusIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/serving.png")));
        }
        this.orderStatus.setIcon(statusIcon);
    }
}
