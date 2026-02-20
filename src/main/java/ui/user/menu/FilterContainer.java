package ui.user.menu;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FilterContainer extends JPanel {
    private final MenuTab menuTab;

    public final JTextField searchField = getSearchField();
    public final JButton searchButton = getSearchButton();
    public final JComboBox<String> categories = getCategories();

    public FilterContainer(MenuTab menuTab) {
        this.menuTab = menuTab;
//        this.setBackground(UserUi.MAIN_COLOR);
        this.setBackground(Color.green);
        this.setPreferredSize(new Dimension(580, 40));
        this.add(searchField);
        this.add(searchButton);
        this.add(categories);
    }

    private JTextField getSearchField() {
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(256, 32));
        searchField.addActionListener(_ -> menuTab.updateMenuContainer());
        return searchField;
    }

    private JButton getSearchButton() {
        JButton searchButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/search.png"))));
        searchButton.setContentAreaFilled(false);
        searchButton.setPreferredSize(new Dimension(32, 32));
        searchButton.addActionListener(_ -> menuTab.updateMenuContainer());
        return searchButton;
    }

    private JComboBox<String> getCategories() {
        JComboBox<String> categories = new JComboBox<>(new String[] {"Show All", "Coffee", "Tea", "Pastry", "Sandwich"});
        categories.setPreferredSize(new Dimension(128, 32));
        categories.setFocusable(false);
        categories.addActionListener(_ -> menuTab.updateMenuContainer());
        return categories;
    }
}
