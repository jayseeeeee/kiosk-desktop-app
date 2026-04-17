package ui.admin.orders.list;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public class RecentContainer extends JPanel {
    public RecentContainer() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(getRecentTitle());
        this.setPreferredSize(new Dimension(850, 120));
    }

    private JLabel getRecentTitle() {
        JLabel recentTitle = new JLabel("RECENTLY PLACED");
        recentTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        recentTitle.setPreferredSize(new Dimension(850, 40));
        recentTitle.setForeground(UserUi.SELECTED_COLOR);
        return recentTitle;
    }
}
