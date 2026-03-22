package ui.admin;

import ui.FrameUi;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public final class AdminUi extends FrameUi {
    public final static int ORDER_MENU_UI = 0;

    public final JPanel navigationContainer = getNavigationContainer();
    private final JScrollPane navigationPane = getNavigationPane();
    public final JPanel tabContainer = getTabContainer();

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

    private void setOrderMenuUi() {
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        navigationContainer.add(Box.createVerticalStrut(128));
        navigationContainer.add(new Tab(this, "DASHBOARD", "dashboard.png", "dashboard_selected.png"));
        navigationContainer.add(new Tab(this, "ORDERS", "orders.png", "orders_selected.png"));
        navigationContainer.add(new Tab(this, "HISTORY", "history.png", "history_selected.png"));
        navigationContainer.add(new Tab(this, "ANALYTICS", "analytics.png", "analytics_selected.png"));
        navigationContainer.add(new Tab(this, "SETTINGS", "settings.png", "settings_selected.png"));
        this.mainPanel.add(navigationPane);
        this.mainPanel.add(tabContainer);
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
        navigationPane.getViewport().setOpaque(false);
        navigationPane.setBorder(UserUi.BORDER_STYLE);
        return navigationPane;
    }

    private JPanel getTabContainer() {
        JPanel tabContainer = new JPanel();
        tabContainer.setOpaque(false);
        tabContainer.setBorder(UserUi.BORDER_STYLE);
        tabContainer.setPreferredSize(new Dimension(1600, 1));
        tabContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        return tabContainer;
    }
}
