package ui.admin.dashboard;

import ui.admin.AdminUi;
import ui.admin.TabContainer;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public final class DashboardTab extends TabContainer {
    public DashboardTab(AdminUi adminUi) {
        super(adminUi);
    }

    @Override
    public void setContainerLayout() {
        this.add(Box.createRigidArea(new Dimension(1600, 20)));
        this.add(Box.createHorizontalStrut(20));
        this.add(getDashboardTitleContainer());
    }

    private JPanel getDashboardTitleContainer() {
        JPanel dashboardTitleContainer = new JPanel();
        dashboardTitleContainer.setOpaque(false);
        dashboardTitleContainer.setLayout(new FlowLayout(FlowLayout.LEADING));
        dashboardTitleContainer.setPreferredSize(new Dimension(700, 150));
        dashboardTitleContainer.add(getDashboardTitle());
        dashboardTitleContainer.add(getDashboardTip());
        return dashboardTitleContainer;
    }

    private JLabel getDashboardTitle() {
        JLabel dashboardTitle = new JLabel("WELCOME TO ADMIN DASHBOARD");
        dashboardTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return dashboardTitle;
    }

    private JLabel getDashboardTip() {
        JLabel dashboardTip = new JLabel("Manage your orders, store settings, and analytics here!");
        dashboardTip.setForeground(AdminUi.DEFAULT_COLOR);
        dashboardTip.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        return dashboardTip;
    }
}
