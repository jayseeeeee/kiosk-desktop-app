package ui.admin.orders;

import javax.swing.*;
import java.awt.*;

class ViewContainer extends JPanel {
    JPanel productContainer = getProductContainer();
    JPanel summaryContainer = getSummaryContainer();

    private final OrdersTab ordersTab;

    ViewContainer(OrdersTab ordersTab) {
        this.ordersTab = ordersTab;
        this.setPreferredSize(new Dimension(1200, 1));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
        this.add(getScrollPane());
        this.add(summaryContainer);
    }

    private JPanel getProductContainer() {
        JPanel orderContainer = new JPanel();
        orderContainer.setLayout(new BoxLayout(orderContainer, BoxLayout.Y_AXIS));
        orderContainer.setOpaque(false);
        return orderContainer;
    }

    private JPanel getSummaryContainer() {
        JPanel summaryContainer = new JPanel();
        summaryContainer.setOpaque(false);
        return summaryContainer;
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(productContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }
}
