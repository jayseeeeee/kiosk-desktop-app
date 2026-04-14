package ui.admin.orders;

import ui.admin.AdminUi;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

class ViewContainer extends JPanel {
    JPanel productContainer = getProductContainer();
    JPanel summaryContainer = getSummaryContainer();

    private final OrdersTab ordersTab;

    ViewContainer(OrdersTab ordersTab) {
        this.ordersTab = ordersTab;
        this.setPreferredSize(new Dimension(700, 1));
        this.setOpaque(false);
        this.add(getItemTitle());
        this.add(Box.createHorizontalStrut(20));
        this.add(getItemQuantityLabel());
        this.add(Box.createHorizontalStrut(20));
        this.add(getItemPriceLabel());
        this.add(getScrollPane());
        this.add(summaryContainer);
    }

    private JLabel getItemTitle() {
        JLabel queueTitle = new JLabel("ITEMS");
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return queueTitle;
    }

    private JLabel getItemQuantityLabel() {
        JLabel quantity = new JLabel("QUANTITY");
        quantity.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return quantity;
    }

    private JLabel getItemPriceLabel() {
        JLabel price = new JLabel("PRICE");
        price.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return price;
    }

    private JPanel getProductContainer() {
        JPanel orderContainer = new JPanel();
        orderContainer.setLayout(new BoxLayout(orderContainer, BoxLayout.Y_AXIS));
        orderContainer.setOpaque(false);
        return orderContainer;
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(productContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(450, 500));
        scrollPane.setOpaque(false);
        scrollPane.setBorder(AdminUi.BORDER_STYLE);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }

    private JPanel getSummaryContainer() {
        JPanel summaryContainer = new JPanel();
        summaryContainer.setOpaque(false);
        return summaryContainer;
    }
}
