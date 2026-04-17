package ui.admin.orders;

import ui.admin.AdminUi;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

final class SearchContainer extends JPanel {
    private final OrdersTab ordersTab;

    SearchContainer(OrdersTab ordersTab) {
        this.ordersTab = ordersTab;
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setPreferredSize(new Dimension(850, 60));
        this.add(getSearchField());
        this.add(getSearchButton());
        this.add(Box.createHorizontalStrut(430));
        this.add(getCategories());
    }

    private JTextField getSearchField() {
        JTextField searchField = new JTextField();
        searchField.setBorder(AdminUi.BORDER_STYLE);
        searchField.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        searchField.setPreferredSize(new Dimension(200, 48));
        searchField.addActionListener(_ -> ordersTab.updateQueue());
        return searchField;
    }

    private JButton getSearchButton() {
        JButton searchButton = new JButton(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "search.png", 40));
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusable(false);
        searchButton.addActionListener(_ -> ordersTab.updateQueue());
        return searchButton;
    }

    private JComboBox<String> getCategories() {
        JComboBox<String> categories = new JComboBox<>(new String[] {"Show All", "Payment", "Preparing", "Finished"});
        categories.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        categories.setPreferredSize(new Dimension(150, 48));
        categories.setFocusable(false);
        categories.addActionListener(_ -> ordersTab.updateQueue());
        return categories;
    }
}
