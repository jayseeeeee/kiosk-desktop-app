package ui.admin.orders;

import ui.admin.AdminUi;
import ui.admin.orders.list.InfoContainer;
import ui.admin.orders.list.RecentContainer;
import ui.admin.orders.list.SearchContainer;
import ui.admin.orders.list.SummaryContainer;

import javax.swing.*;
import java.awt.*;

class ListContainer extends JPanel {
    private final OrdersTab ordersTab;

    public final InfoContainer infoContainer;
    public final SummaryContainer summaryContainer;
    public final RecentContainer recentContainer;
    public final SearchContainer searchContainer;
    public final JPanel orderContainer = getOrderContainer();

    ListContainer(OrdersTab ordersTab) {
        this.ordersTab = ordersTab;
        this.infoContainer = new InfoContainer();
        this.summaryContainer = new SummaryContainer();
        this.recentContainer = new RecentContainer();
        this.searchContainer = new SearchContainer(ordersTab);
        this.setOpaque(false);
        this.add(Box.createRigidArea(new Dimension(1000, 20)));
        this.add(infoContainer);
        this.add(summaryContainer);
        this.add(recentContainer);
        this.add(searchContainer);
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
        scrollPane.setPreferredSize(new Dimension(850, 400));
        scrollPane.setBorder(AdminUi.BORDER_STYLE);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        return scrollPane;
    }
}