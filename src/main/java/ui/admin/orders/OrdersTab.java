package ui.admin.orders;

import ui.admin.AdminUi;
import ui.admin.TabContainer;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public final class OrdersTab extends TabContainer {
    private final JPanel queueContainer = new JPanel();

    public OrdersTab(AdminUi adminUi) {
        super(adminUi);
    }

    @Override
    public void setContainerLayout() {

    }

    private JLabel getQueueTitle() {
        JLabel queueTitle = new JLabel("QUEUE");
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return queueTitle;
    }

    private JLabel getOrderNumber() {
        JLabel orderNumber = new JLabel("ORDER NUMBER");
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return orderNumber;
    }

    private JLabel getStatusTitle() {
        JLabel statusTitle = new JLabel("STATUS");
        statusTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return statusTitle;
    }

    private JScrollPane getScrollPane() {
        queueContainer.setLayout(new BoxLayout(queueContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(queueContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(420, 440));
        return scrollPane;
    }
}
