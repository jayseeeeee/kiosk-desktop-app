package ui.admin.orders.list;

import ui.admin.AdminUi;
import ui.admin.orders.OrdersTab;
import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public final class SearchContainer extends JPanel {
    private final OrdersTab ordersTab;

    public SearchContainer(OrdersTab ordersTab) {
        this.ordersTab = ordersTab;
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setPreferredSize(new Dimension(850, 120));
        this.add(Box.createVerticalStrut(80));
        this.add(getSummaryTitle());
        this.add(Box.createHorizontalStrut(250));
        this.add(getSearchField());
        this.add(getSearchButton());
        this.add(getCategories());
        this.add(getLine());
        this.add(Box.createHorizontalStrut(60));
        this.add(getOrderLabel("NUMBER"));
        this.add(Box.createHorizontalStrut(100));
        this.add(getOrderLabel("STATUS"));
        this.add(Box.createHorizontalStrut(140));
        this.add(getOrderLabel("TIME"));
        this.add(Box.createHorizontalStrut(100));
        this.add(getOrderLabel("AMOUNT"));
        this.add(Box.createVerticalStrut(40));
    }

    private JLabel getSummaryTitle() {
        JLabel summaryTitle = new JLabel("LIST OF ORDERS");
        summaryTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        summaryTitle.setForeground(UserUi.SELECTED_COLOR);
        return summaryTitle;
    }

    private JTextField getSearchField() {
        JTextField searchField = new JTextField();
        searchField.setBorder(AdminUi.BORDER_STYLE);
        searchField.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        searchField.setPreferredSize(new Dimension(205, 40));
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
        categories.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        categories.setPreferredSize(new Dimension(120, 40));
        categories.setFocusable(false);
        categories.addActionListener(_ -> ordersTab.updateQueue());
        return categories;
    }

    private JSeparator getLine() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setBackground(new Color(0xFFCFCFCF));
        line.setPreferredSize(new Dimension(850, 5));
        return line;
    }

    private JLabel getOrderLabel(String label) {
        JLabel summaryTitle = new JLabel(label);
        summaryTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        summaryTitle.setForeground(UserUi.DEFAULT_COLOR);
        return summaryTitle;
    }
}
