package ui.admin.orders.view;

import app.store.Order;
import app.store.Product;
import ui.admin.AdminUi;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public final class ManageContainer extends JPanel {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");
    private Order order;
    private double initialCost;

    public ManageContainer() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setPreferredSize(new Dimension(450, 350));
        this.setOpaque(false);
        this.setBorder(AdminUi.BORDER_STYLE);
        setOrder(null);
    }

    public void setOrder(Order order) {
        this.removeAll();
        this.add(getPaymentTitle());
        if (order == null) {
            this.order = null;
            this.add(Box.createVerticalStrut(62));
            this.add(getEmptyTextLabel());
        } else {
            this.order = order;
            getOrderDetails();
        }
    }

    private JLabel getEmptyTextLabel() {
        JLabel emptyLabel = new JLabel("NO ORDER SELECTED");
        emptyLabel.setPreferredSize(new Dimension(450, 250));
        emptyLabel.setFont(new Font(UserUi.FONT,  Font.BOLD, 16));
        emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emptyLabel.setForeground(UserUi.DEFAULT_COLOR);
        return emptyLabel;
    }

    private void getOrderDetails() {
        this.add(Box.createHorizontalStrut(150));
        this.add(getOrderNumber());

        this.add(getTimeTitle());
        this.add(Box.createHorizontalStrut(30));
        this.add(getTime());

        this.add(getOrderStatus());

        this.add(getInitialCostTitle());
        this.add(Box.createHorizontalStrut(170));
        this.add(getInitialCost());

        this.add(getDiscountTitle());
        this.add(getDiscount());

        this.add(getFinalCostTitle());
        this.add(Box.createHorizontalStrut(80));
        this.add(getFinalCost());
    }

    private JLabel getPaymentTitle() {
        JLabel paymentTitle = new JLabel("SUMMARY");
        paymentTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 32));
        paymentTitle.setForeground(AdminUi.SELECTED_COLOR);
        return paymentTitle;
    }

    private JLabel getOrderNumber() {
        JLabel orderNumber = new JLabel(String.format("%03d", order.orderCount));
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 48));
        return orderNumber;
    }

    private JLabel getTimeTitle() {
        JLabel timeTitle = new JLabel("ORDER PLACED");
        timeTitle.setForeground(AdminUi.DEFAULT_COLOR);
        timeTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return timeTitle;
    }

    private JLabel getTime() {
        JLabel time = new JLabel(order.getDateTime().format(dateTimeFormatter));
        time.setForeground(AdminUi.DEFAULT_COLOR);
        time.setFont(new Font("Helvetica", Font.BOLD, 16));
        return time;
    }

    private JLabel getOrderStatus() {
        ImageIcon statusIcon = null;
        switch (order.getStatus()) {
            case Order.PAYMENT_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "payment_status.png", 48);
            case Order.PREPARING_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "preparing_status.png", 48);
            case Order.SERVING_STATUS -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "serving_status.png", 48);
        }
        return new JLabel(statusIcon);
    }

    public JLabel getInitialCostTitle() {
        JLabel initialCostLabel = new JLabel("INITIAL COST");
        initialCostLabel.setFont(new Font(UserUi.FONT, Font.BOLD, 20));
        return initialCostLabel;
    }

    private JLabel getInitialCost() {
        initialCost = 0;
        for (Product product : order.getProducts()) {
            initialCost += product.cost * product.quantity;
        }
        JLabel initialCostLabel = new JLabel(String.format("₱%.2f", initialCost));
        initialCostLabel.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        initialCostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        initialCostLabel.setPreferredSize(new Dimension(100, 18));
        return initialCostLabel;
    }

    private JLabel getDiscountTitle() {
        JLabel discountTitle = new JLabel("DISCOUNT");
        discountTitle.setForeground(AdminUi.DEFAULT_COLOR);
        discountTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        return discountTitle;
    }

    private JComponent getDiscount() {
        double discount = initialCost - order.finalCost;
        System.out.println(discount);
        if (discount > 0) {
            this.add(Box.createHorizontalStrut(205));
            JLabel discountLabel = new JLabel(String.format("-₱%.2f", discount));
            discountLabel.setForeground(UserUi.DEFAULT_COLOR);
            discountLabel.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
            discountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            discountLabel.setPreferredSize(new Dimension(100, 41));
            return discountLabel;
        } else {
            this.add(Box.createHorizontalStrut(230));
            JButton discountButton = new JButton(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "discount.png", 32));
            discountButton.setFocusable(false);
            discountButton.setContentAreaFilled(false);
            return discountButton;
        }
    }

    private JSeparator getLine() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setBackground(new Color(0xFFCFCFCF));
        line.setPreferredSize(new Dimension(400, 5));
        return line;
    }

    private JLabel getFinalCostTitle() {
        JLabel finalCostTitle = new JLabel("FINAL COST");
        finalCostTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 28));
        return finalCostTitle;
    }

    private JLabel getFinalCost() {
        JLabel finalCost = new JLabel(String.format("₱%.2f", order.finalCost));
        finalCost.setPreferredSize(new Dimension(150, 30));
        finalCost.setHorizontalAlignment(SwingConstants.RIGHT);
        finalCost.setFont(new Font(UserUi.FONT, Font.BOLD, 28));
        return finalCost;
    }
}
