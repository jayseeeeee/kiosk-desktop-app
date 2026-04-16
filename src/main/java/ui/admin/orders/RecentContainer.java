package ui.admin.orders;

import javax.swing.*;
import java.awt.*;

public class RecentContainer extends JPanel {
    RecentContainer() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setPreferredSize(new Dimension(850, 80));
    }
}
