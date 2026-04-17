package ui.admin.orders.list;

import ui.admin.AdminUi;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;


class Counter extends JPanel {
    JLabel count = getCounter();

    Counter(String imageName, String title) {
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(250, 150));
        this.setBorder(AdminUi.BORDER_STYLE);
        this.add(Box.createVerticalStrut(130));
        this.add(getIcon());
        this.add(Box.createHorizontalStrut(20));
        this.add(getTextContainer(title));
    }

    private JLabel getIcon() {
        return new JLabel(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "clock.png", 64, 64));
    }

    private JPanel getTextContainer(String title) {
        JPanel textContainer = new JPanel();
        textContainer.setOpaque(false);
        textContainer.setPreferredSize(new Dimension(100, 60));
        textContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        textContainer.add(getTitle(title));
        textContainer.add(getCounter());
        return textContainer;
    }

    private JLabel getTitle(String title) {
        JLabel counterTitle = new JLabel(title);
        counterTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        return counterTitle;
    }

    private JLabel getCounter() {
        JLabel counter = new JLabel("999");
        counter.setFont(new Font(UserUi.FONT, Font.BOLD, 32));
        return counter;
    }
}
