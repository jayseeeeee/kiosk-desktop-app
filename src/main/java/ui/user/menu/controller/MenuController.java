package ui.user.menu.controller;

import product.Product;
import ui.user.menu.MenuTab;
import ui.user.UserUi;
import ui.user.menu.FilterContainer;
import ui.user.menu.SelectionContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MenuController {;
    public final ArrayList<Product> menuList = new ArrayList<>();
    private final AllergyController allergyController;
    private final SelectionContainer selectionContainer;
    private final FilterContainer filterContainer;

    public MenuController(MenuTab menuTab) {
        this.allergyController = menuTab.allergyController;
        this.selectionContainer = menuTab.selectionContainer;
        this.filterContainer = menuTab.filterContainer;
    }

    public void updateMenuContainer() {
        selectionContainer.pageSelection.setText("1");
        menuList.clear();
        String selectedCategory = (String) filterContainer.categories.getSelectedItem();
        String searchText = filterContainer.searchField.getText().trim();
        System.out.println(selectedCategory);
        for (Product product : Product.listOfProducts) {
            boolean categoryMatch = product.category.equals(selectedCategory) || Objects.equals(selectedCategory, "Show All");
            boolean searchMatch = product.name.toLowerCase().contains(searchText.toLowerCase()) || searchText.isBlank();
            boolean allergyMatch = allergyController.isAllergyMatch(product.allergies);
            if (!searchMatch || !categoryMatch || allergyMatch) {
                continue;
            }
            menuList.add(product);
        }
        if (menuList.isEmpty()) {
            showEmptyMenu();
            showMenu();
        } else {
            sortProductByName(menuList, 0, menuList.size() - 1);
        }
        selectionContainer.setVisible(menuList.size() > 6);
    }

    private void showMenu() {
        selectionContainer.removeAll();
        selectionContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 18, 18));
        int currentPage = Integer.parseInt(selectionContainer.pageSelection.getText()) - 1;
        int currentItem = currentPage * 6;
        for (int i = currentItem; i < (Math.min(currentItem + 6, (menuList.size()))); i++) {
            selectionContainer.add(menuList.get(i).item);
        }
        selectionContainer.revalidate();
        selectionContainer.repaint();
    }

    public void showEmptyMenu() {
        selectionContainer.removeAll();
        selectionContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        selectionContainer.add(getEmptyResultPanel());
        selectionContainer.add(getEmptyResultTitle());
        selectionContainer.add(getEmptyResultTip());
        selectionContainer.revalidate();
        selectionContainer.repaint();
    }

    private JPanel getEmptyResultPanel() {
        JPanel emptyResultPanel = new JPanel();
        emptyResultPanel.setBackground(UserUi.MAIN_COLOR);
        emptyResultPanel.setPreferredSize(new Dimension(1000,210));
        return emptyResultPanel;
    }

    private JLabel getEmptyResultTitle() {
        JLabel emptyResultTitle = new JLabel("NO MATCHING PRODUCTS FOUND");
        emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 14));
        emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
        emptyResultTitle.setPreferredSize(new Dimension(512, 14));
        return emptyResultTitle;
    }

    private JLabel getEmptyResultTip() {
        JLabel emptyResultTip = new JLabel("TRY ADJUSTING YOUR SEARCH FILTER.");
        emptyResultTip.setFont(new Font(UserUi.FONT, Font.BOLD, 12));
        emptyResultTip.setHorizontalAlignment(JLabel.CENTER);
        emptyResultTip.setPreferredSize(new Dimension(512,  12));
        return emptyResultTip;
    }

    private static void sortProductByName(ArrayList<Product> list, int firstIndex, int lastIndex) {
        if (firstIndex < lastIndex) {
            int pivotIndex = partition(list, firstIndex, lastIndex);
            sortProductByName(list, firstIndex, pivotIndex - 1);   // Left side
            sortProductByName(list, pivotIndex + 1, lastIndex);  // Right side
        }
    }

    private static int partition(ArrayList<Product> list, int firstIndex, int lastIndex) {
        Product lastProduct = list.get(lastIndex);  // pivot = last element
        int lowIndex = lastIndex - 1;        // index of smaller element

        for (int i = firstIndex; i < lastIndex; i++) {
            if (list.get(i).name.compareTo(lastProduct.name) < 0) {
                lowIndex++;
                Collections.swap(list, lowIndex, i);
            }
        }

        Collections.swap(list, lowIndex + 1, lastIndex);
        return lowIndex + 1;
    }
}
