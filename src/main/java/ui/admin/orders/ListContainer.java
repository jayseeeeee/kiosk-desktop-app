package ui.admin.orders;

import javax.swing.*;
import java.awt.*;

class ListContainer extends JPanel {
    JPanel orderContainer = getOrderContainer();

    private final OrdersTab ordersTab;

    ListContainer(OrdersTab ordersTab) {
        this.ordersTab = ordersTab;
        this.setOpaque(false);
        this.add(Box.createRigidArea(new Dimension(1000, 20)));
        this.add(new InfoContainer());
        this.add(new SearchContainer(ordersTab));
        this.add(getScrollPane());
    }

    private JPanel getOrderContainer() {
        JPanel orderContainer = new JPanel();
        orderContainer.setLayout(new BoxLayout(orderContainer, BoxLayout.Y_AXIS));
        orderContainer.setOpaque(false);
        return orderContainer;
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(orderContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(850, 450));
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }
}