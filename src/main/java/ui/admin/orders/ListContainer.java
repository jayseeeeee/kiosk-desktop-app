package ui.admin.orders;

import ui.admin.AdminUi;
import ui.user.UserUi;

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
        this.add(new SummaryContainer());
        this.add(getSummaryTitle());
        this.add(new SearchContainer(ordersTab));
        this.add(getScrollPane());
    }

    private JLabel getSummaryTitle() {
        JLabel summaryTitle = new JLabel("LIST OF ORDERS");
        summaryTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        summaryTitle.setPreferredSize(new Dimension(850, 40));
        summaryTitle.setForeground(AdminUi.SELECTED_COLOR);
        return summaryTitle;
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
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        return scrollPane;
    }
}