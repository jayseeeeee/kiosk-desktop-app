package ui.user.queue;

import ui.user.UserUi;
import ui.user.queue.controller.CostController;
import ui.user.queue.controller.PaymentController;

import javax.swing.*;
import java.awt.*;

public class QueueTab extends JPanel {
    public final UserUi userUi;
    public final JPanel queueContainer = getQueueContainer();
    public final CostContainer costContainer = new CostContainer(this);
    public final PaymentContainer paymentContainer = new PaymentContainer(this);
    public final PaymentController paymentController = new PaymentController(this);
    public final CostController costController = new CostController(this);

    public QueueTab(UserUi userUi) {
        this.userUi = userUi;
        this.setBackground(UserUi.MAIN_COLOR);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setPreferredSize(new Dimension(1, 1));
        this.add(getQueueTitle());
        this.add(getOrderNumber());
        this.add(getStatusTitle());
        this.add(queueContainer);
        this.add(paymentContainer);
        this.add(costContainer);
    }

    private JLabel getQueueTitle() {
        JLabel queueTitle = new JLabel("QUEUE");
        queueTitle.setPreferredSize(new Dimension(256, 50));
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        return queueTitle;
    }

    private JLabel getOrderNumber() {
        JLabel orderNumber = new JLabel("ORDER NUMBER");
        orderNumber.setPreferredSize(new Dimension(128, 16));
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 12));
        return orderNumber;
    }

    private JLabel getStatusTitle() {
        JLabel statusTitle = new JLabel("STATUS");
        statusTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 12));
        return statusTitle;
    }

    private JPanel getQueueContainer() {
        JPanel queueContainer = new JPanel();
        queueContainer.setLayout(new BoxLayout(queueContainer, BoxLayout.Y_AXIS));
        JScrollPane listPane = new JScrollPane(queueContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listPane.setPreferredSize(new Dimension(320, 240));
        return queueContainer;
    }
}
