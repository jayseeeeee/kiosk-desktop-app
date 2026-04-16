package ui.admin.orders;

import javax.swing.*;
import java.awt.*;

class SummaryContainer extends JPanel {
    Counter preparing = new Counter("preparing_icon.png", "Preparing");
    Counter payment = new Counter("payment_icon.png", "Payment");
    Counter serving = new Counter("serving_icon.png", "Serving");

    SummaryContainer() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setPreferredSize(new Dimension(850, 160));
        this.add(preparing);
        this.add(Box.createHorizontalStrut(50));
        this.add(payment);
        this.add(Box.createHorizontalStrut(50));
        this.add(serving);
    }
}
