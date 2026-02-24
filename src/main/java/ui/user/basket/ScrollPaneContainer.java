package ui.user.basket;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public class ScrollPaneContainer extends JPanel {
    public final JPanel basketContainer = getBasketContainer();

    ScrollPaneContainer() {
        this.setBackground(UserUi.MAIN_COLOR);
        this.add(new JScrollPane(basketContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    }

    private JPanel getBasketContainer() {
        JPanel basketContainer = new JPanel();
        basketContainer.setBackground(Color.white);
        basketContainer.setPreferredSize(new Dimension(420, 870));
        return  basketContainer;
    }
}
