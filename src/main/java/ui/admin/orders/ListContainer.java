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
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setOpaque(false);
//        this.setPreferredSize(new Dimension(800, 1000));
//        this.setMaximumSize(new Dimension(200, 1000));
//        this.add(Box.createRigidArea(new Dimension(100000, 20)));
        this.add(Box.createHorizontalStrut(20));
        this.add(getOrderTitleContainer());
        this.add(getScrollPane());
    }


    private JPanel getOrderTitleContainer() {
        JPanel orderTitleContainer = new JPanel();
        orderTitleContainer.setOpaque(false);
        orderTitleContainer.setLayout(new FlowLayout(FlowLayout.LEADING));
        orderTitleContainer.setPreferredSize(new Dimension(350, 150));
        orderTitleContainer.add(getOrderTitle());
        orderTitleContainer.add(getOrderTip());
        return orderTitleContainer;
    }

    private JLabel getOrderTitle() {
        JLabel orderTitle = new JLabel("CURRENT ORDERS");
        orderTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return orderTitle;
    }

    private JLabel getOrderTip() {
        JLabel orderTip = new JLabel("Track and manage current orders here!");
        orderTip.setForeground(AdminUi.DEFAULT_COLOR);
        orderTip.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        return orderTip;
    }


    private JPanel getOrderContainer() {
        JPanel orderContainer = new JPanel();
        orderContainer.setLayout(new BoxLayout(orderContainer, BoxLayout.Y_AXIS));
        orderContainer.setOpaque(false);
        return orderContainer;
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(orderContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(950, 400));
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }

}
