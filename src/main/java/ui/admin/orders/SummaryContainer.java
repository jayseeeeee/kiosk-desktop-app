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

class SummaryContainer extends JPanel {
    Counter preparing = new Counter("preparing_icon.png", "Preparing");
    Counter payment = new Counter("payment_icon.png", "Payment");
    Counter serving = new Counter("serving_icon.png", "Serving");

    SummaryContainer() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        this.setPreferredSize(new Dimension(900, 160));
        this.add(preparing);
        this.add(payment);
        this.add(serving);
    }
}
