package ui.user.basket;

import ui.user.UserUi;
import ui.user.basket.controller.BasketController;

import javax.swing.*;
import java.awt.*;

public class BasketTab extends JPanel {
    public final UserUi userUi;
    public final JPanel basketContainer = new JPanel();
    public final BasketController basketController;

    public BasketTab(UserUi userUi) {
        this.userUi = userUi;
        this.basketController = new BasketController(this);
        this.setBackground(UserUi.MAIN_COLOR);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(1, 1));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.add(getTitle());
        this.add(getDescription());
        this.add(getScrollPane());
    }

    private JLabel getTitle() {
        JLabel basketTitle = new JLabel("BASKET");
        basketTitle.setPreferredSize(new Dimension(225, 50));
        basketTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        return basketTitle;
    }

    private JLabel getDescription() {
        JLabel basketDescription = new JLabel("EDIT ORDER");
        basketDescription.setFont(new Font(UserUi.FONT, Font.BOLD, 10));
        return basketDescription;
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(basketContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(320, 540));
        return scrollPane;
    }
}
