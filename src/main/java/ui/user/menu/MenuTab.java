package ui.user.menu;

import app.store.Allergy;
import app.store.Product;
import ui.card.MenuCard;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public final class MenuTab extends JPanel{
    private final UserUi userUi;
    private final FilterContainer filterContainer = new FilterContainer(this);
    private final AllergyContainer allergyContainer = new AllergyContainer(this);
    private final SelectionContainer selectionContainer = new SelectionContainer(this);
    private final JPanel menuContainer = getMenuContainer();

    private final HashMap<String, JCheckBox> allergyFilterResult = new HashMap<>();
    private final ArrayList<Product> menuList = new ArrayList<>();

    public MenuTab(UserUi userUi) {
        this.userUi = userUi;
        this.setOpaque(false);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(575, 1));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.add(filterContainer);
        this.add(allergyContainer);
        this.add(menuContainer);
        this.add(selectionContainer);
    }

    private JPanel getMenuContainer() {
        JPanel menuContainer = new JPanel();
        menuContainer.setOpaque(false);
        menuContainer.setPreferredSize(new Dimension(1000, 800));
        return menuContainer;
    }

    void updateAllergy(JCheckBox checkBox, String allergy) {
        if (checkBox.isSelected()) {
            allergyFilterResult.put(allergy, checkBox);
        } else {
            allergyFilterResult.remove(allergy);
        }
        updateMenuContainer();
    }

    private boolean isAllergyMatch(Allergy allergy) {
        for (String allergyFilter : allergyFilterResult.keySet()) {
            if (allergy == null) {
                break;
            } else if (allergy.productAllergies.contains(allergyFilter)) {
                return true;
            }
        }
        return false;
    }

    private void showMenu() {
        menuContainer.removeAll();
        menuContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 20));
        int currentPage = Integer.parseInt(selectionContainer.pageSelection.getText()) - 1;
        int currentItem = currentPage * 6;
        for (int i = currentItem; i < (Math.min(currentItem + 6, (menuList.size()))); i++) {
            menuContainer.add(new MenuCard(menuList.get(i), userUi));
        }
        menuContainer.revalidate();
        menuContainer.repaint();
    }

    public void updateMenuContainer() {
        selectionContainer.pageSelection.setText("1");
        menuList.clear();
        String selectedCategory = (String) filterContainer.categories.getSelectedItem();
        String searchText = filterContainer.searchField.getText().trim();
        System.out.println(selectedCategory);
        for (Product product : Product.getListOfProducts()) {
            boolean categoryMatch = product.category.equals(selectedCategory) || Objects.equals(selectedCategory, "Show All");
            boolean searchMatch = product.name.toLowerCase().contains(searchText.toLowerCase()) || searchText.isBlank();
            boolean allergyMatch = isAllergyMatch(product.allergies);
            if (!searchMatch || !categoryMatch || allergyMatch) {
                continue;
            }
            menuList.add(product);
        }
        if (menuList.isEmpty()) {
            showEmptyMenu();
        } else {
            menuList.sort(Comparator.comparing(p -> p.name));
            showMenu();
        }
        selectionContainer.setVisible(menuList.size() > 6);
    }

    private void showEmptyMenu() {
        menuContainer.removeAll();
        menuContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        menuContainer.add(getEmptyResultTitle());
        menuContainer.add(getEmptyResultTip());
        menuContainer.revalidate();
        menuContainer.repaint();
    }

    private JLabel getEmptyResultTitle() {
        JLabel emptyResultTitle = new JLabel("NO MATCHING PRODUCTS FOUND");
        emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 32));
        emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
        emptyResultTitle.setVerticalAlignment(JLabel.BOTTOM);
        emptyResultTitle.setPreferredSize(new Dimension(1000, 400));
        emptyResultTitle.setForeground(UserUi.DEFAULT_COLOR);
        return emptyResultTitle;
    }

    private JLabel getEmptyResultTip() {
        JLabel emptyResultTip = new JLabel("TRY ADJUSTING YOUR SEARCH FILTER");
        emptyResultTip.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        emptyResultTip.setHorizontalAlignment(JLabel.CENTER);
        emptyResultTip.setForeground(UserUi.SELECTED_COLOR);
        return emptyResultTip;
    }

    public void changeSelection() {
        try {
            int clampedValue = Math.clamp(Integer.parseInt(selectionContainer.pageSelection.getText()), 1, (int) Math.ceil((double) menuList.size() / 6));
            selectionContainer.pageSelection.setText(String.valueOf(clampedValue));
            showMenu();
        } catch(Exception e) {
            selectionContainer.pageSelection.setText("1");
        }
    }

    public void prevSelection() {
        selectionContainer.pageSelection.setText(String.valueOf(Math.max(Integer.parseInt(selectionContainer.pageSelection.getText()) - 1, 1)));
        showMenu();
    }

    public void nextSelection() {
        selectionContainer.pageSelection.setText(String.valueOf(Math.min(Integer.parseInt(selectionContainer.pageSelection.getText()) + 1, (int) Math.ceil((double) menuList.size() / 6))));
        showMenu();
    }
}
