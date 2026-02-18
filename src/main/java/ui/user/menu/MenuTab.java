package ui.user.menu;

import ui.user.UserUi;
import ui.user.menu.controller.AllergyController;
import ui.user.menu.controller.MenuController;
import ui.user.menu.controller.SelectionController;

import javax.swing.*;
import java.awt.*;

public class MenuTab extends JPanel{
    public final UserUi userUi;
    public final FilterContainer filterContainer = new FilterContainer(this);
    public final AllergyContainer allergyContainer = new AllergyContainer(this);
    public final SelectionContainer selectionContainer = new SelectionContainer(this);
    public final AllergyController allergyController = new AllergyController(this);
    public final MenuController menuController = new MenuController(this);
    public final SelectionController selectionController = new SelectionController(this);
    public final JPanel menuContainer = getMenuContainer();

    public MenuTab(UserUi userUi) {
        this.userUi = userUi;
        this.setBackground(UserUi.MAIN_COLOR);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(256, 1));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(filterContainer);
        this.add(allergyContainer);
        this.add(menuContainer);
        this.add(selectionContainer);
    }

    private JPanel getMenuContainer() {
        JPanel menuContainer = new JPanel();
        menuContainer.setBackground(UserUi.MAIN_COLOR);
        menuContainer.setPreferredSize(new Dimension(Integer.MIN_VALUE, 720));
        return menuContainer;
    }
}
