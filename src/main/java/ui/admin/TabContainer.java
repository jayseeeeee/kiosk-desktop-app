package ui.admin;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public abstract class TabContainer extends JPanel {
    protected final AdminUi adminUi;

    protected TabContainer(AdminUi adminUi) {
        this.adminUi = adminUi;
        this.setOpaque(false);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(1000, 1));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        setContainerLayout();
    }

    public void setContainerLayout() {
        this.setLayout(new FlowLayout());
        this.add(getEmptyResultTitle());
        this.add(getEmptyResultTip());
    }

    private JLabel getEmptyResultTitle() {
        JLabel emptyResultTitle = new JLabel("THIS FEATURE IS NOT AVAILABLE YET");
        emptyResultTitle.setForeground(AdminUi.DEFAULT_COLOR);
        emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
        emptyResultTitle.setVerticalAlignment(JLabel.BOTTOM);
        emptyResultTitle.setPreferredSize(new Dimension(1400, 500));
        return emptyResultTitle;
    }

    private JLabel getEmptyResultTip() {
        JLabel emptyResultTip = new JLabel("UPDATES COMING SOON!");
        emptyResultTip.setForeground(AdminUi.SELECTED_COLOR);
        emptyResultTip.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        emptyResultTip.setHorizontalAlignment(JLabel.CENTER);
        return emptyResultTip;
    }
}
