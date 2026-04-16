package ui.admin.orders;

import ui.admin.AdminUi;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

final class InfoContainer extends JPanel {

    InfoContainer() {
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(900, 90));
        this.add(getOrderContainer());
        this.add(Box.createHorizontalStrut(280));
        this.add(getTimeContainer());
    }

    private JPanel getOrderContainer() {
        JPanel orderTitleContainer = new JPanel();
        orderTitleContainer.setOpaque(false);
        orderTitleContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        orderTitleContainer.setPreferredSize(new Dimension(350, 80));
        orderTitleContainer.add(getOrderTitle());
        orderTitleContainer.add(getOrderTip());
        return orderTitleContainer;
    }

    private JLabel getOrderTitle() {
        JLabel orderTitle = new JLabel("ORDER TAB");
        orderTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        orderTitle.setForeground(AdminUi.SELECTED_COLOR);
        return orderTitle;
    }

    private JLabel getOrderTip() {
        JLabel orderTip = new JLabel("Track and manage current orders here!");
        orderTip.setForeground(AdminUi.DEFAULT_COLOR);
        orderTip.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        return orderTip;
    }

    private JPanel getTimeContainer() {
        JPanel timeContainer = new JPanel();
        timeContainer.setOpaque(false);
        timeContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        timeContainer.add(getTextContainer());
        timeContainer.add(Box.createHorizontalStrut(5));
        timeContainer.add(getTimeIcon());
        return timeContainer;
    }

    private JPanel getTextContainer() {
        JPanel textContainer = new JPanel();
        textContainer.setOpaque(false);
        textContainer.setPreferredSize(new Dimension(150, 50));
        textContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        textContainer.add(getTimeLabel("dd MMM yyyy"));
        textContainer.add(getTimeLabel("h:mm a"));
        return textContainer;
    }

    private JLabel getTimeLabel(String format) {
        JLabel timeLabel = new JLabel();
        timeLabel.setForeground(AdminUi.DEFAULT_COLOR);
        timeLabel.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalDateTime dateTime = LocalDateTime.now();
                timeLabel.setText(dateTime.format(formatter));
            }
        };
        new Timer().schedule(task, 0, 1000);
        return timeLabel;
    }

    private JLabel getTimeIcon() {
        return new JLabel(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "clock.png", 32, 32));
    }
}
