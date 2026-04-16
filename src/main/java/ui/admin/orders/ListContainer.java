package ui.admin.orders;

import ui.admin.AdminUi;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

class ListContainer extends JPanel {
    JPanel orderContainer = getOrderContainer();
    RecentContainer recentContainer = new RecentContainer();

    private final OrdersTab ordersTab;

    ListContainer(OrdersTab ordersTab) {
        this.ordersTab = ordersTab;
        this.setOpaque(false);
        this.add(Box.createRigidArea(new Dimension(1000, 20)));
        this.add(new InfoContainer());
        this.add(new SummaryContainer());
        this.add(getRecentTitle());
        this.add(recentContainer);
        this.add(getSummaryTitle());
        this.add(new SearchContainer(ordersTab));
        this.add(getScrollPane());
    }

    private JLabel getRecentTitle() {
        JLabel recentTitle = new JLabel("RECENTLY PLACED");
        recentTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        recentTitle.setPreferredSize(new Dimension(850, 40));
        recentTitle.setForeground(UserUi.SELECTED_COLOR);
        return recentTitle;
    }

    private JLabel getSummaryTitle() {
        JLabel summaryTitle = new JLabel("LIST OF ORDERS");
        summaryTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        summaryTitle.setPreferredSize(new Dimension(850, 40));
        summaryTitle.setForeground(UserUi.SELECTED_COLOR);
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
        scrollPane.setPreferredSize(new Dimension(850, 400));
        scrollPane.setBorder(AdminUi.BORDER_STYLE);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        return scrollPane;
    }
}