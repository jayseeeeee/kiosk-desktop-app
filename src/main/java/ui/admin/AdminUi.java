package ui.admin;

import ui.FrameUi;
import ui.Server;
import ui.admin.analytics.AnalyticsTab;
import ui.admin.history.HistoryTab;
import ui.admin.orders.OrdersTab;
import ui.admin.settings.SettingsTab;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public final class AdminUi extends FrameUi {
    public final static int ORDER_MENU_UI = 0;

    private final JPanel navigationContainer = getNavigationContainer();
    private final JScrollPane navigationPane = getNavigationPane();

    public final OrdersTab ordersTab = new OrdersTab(this);
    public final HistoryTab historyTab = new HistoryTab(this);
    public final AnalyticsTab analyticsTab = new AnalyticsTab(this);
    public final SettingsTab settingsTab = new SettingsTab(this);

    final Tab[] listOfTabs = {
        new Tab(this, "ORDERS", "orders.png", "orders_selected.png"),
        new Tab(this, "HISTORY", "history.png", "history_selected.png"),
        new Tab(this, "ANALYTICS", "analytics.png", "analytics_selected.png"),
        new Tab(this, "SETTINGS", "settings.png", "settings_selected.png")
    };

    private TabContainer tabContainer;
    private final Server server;

    public AdminUi(Server server) {
        this.server = server;
        listOfTabs[1].tabButton.doClick();
    }

    @Override
    public void setUi(int ui) {
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        switch (ui) {
            case ORDER_MENU_UI -> setOrderMenuUi();
        }
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }

    public void setTabContainer(Tab tab) {
        switch (tab.title) {
            case "ORDERS" -> tabContainer = ordersTab;
            case "HISTORY" -> tabContainer = historyTab;
            case "ANALYTICS" -> tabContainer = analyticsTab;
            case "SETTINGS" -> tabContainer = settingsTab;
        }
    }

    private void setOrderMenuUi() {
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        navigationContainer.removeAll();
        navigationContainer.add(Box.createVerticalStrut(128));
        for (Tab tab : listOfTabs) {
            navigationContainer.add(tab);
        }
        this.mainPanel.add(navigationPane);
        this.mainPanel.add(tabContainer);
        ordersTab.updateQueue();
    }

    private JPanel getNavigationContainer() {
        JPanel navigationContainer = new JPanel();
        navigationContainer.setOpaque(false);
        navigationContainer.setLayout(new BoxLayout(navigationContainer, BoxLayout.Y_AXIS));
        return navigationContainer;
    }

    private JScrollPane getNavigationPane() {
        JScrollPane navigationPane = new JScrollPane(navigationContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        navigationPane.setOpaque(false);
        navigationPane.setPreferredSize(new Dimension(500, 1200));
        navigationPane.setMaximumSize(new Dimension(500, 1200));
        navigationPane.getViewport().setOpaque(false);
        navigationPane.setBorder(UserUi.BORDER_STYLE);
        return navigationPane;
    }
}
