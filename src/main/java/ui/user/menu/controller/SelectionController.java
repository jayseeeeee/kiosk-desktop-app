package ui.user.menu.controller;

import ui.user.menu.MenuTab;
import ui.user.menu.SelectionContainer;

public class SelectionController {
    private final SelectionContainer selectionContainer;
    private final MenuController menuController;

    public SelectionController(MenuTab menuTab) {
        this.selectionContainer = menuTab.selectionContainer;
        this.menuController = menuTab.menuController;
    }

    public void changeSelection() {
        try {
            int clampedValue = Math.clamp(Integer.parseInt(selectionContainer.pageSelection.getText()), 1, (int) Math.ceil((double) menuController.menuList.size() / 6));
            selectionContainer.pageSelection.setText(String.valueOf(clampedValue));
            menuController.showEmptyMenu();
        } catch(Exception e) {
            selectionContainer.pageSelection.setText("1");
        }
    }

    public void prevSelection() {
        selectionContainer.pageSelection.setText(String.valueOf(Math.max(Integer.parseInt(selectionContainer.pageSelection.getText()) - 1, 1)));
        menuController.showEmptyMenu();
    }

    public void nextSelection() {
        selectionContainer.pageSelection.setText(String.valueOf(Math.min(Integer.parseInt(selectionContainer.pageSelection.getText()) + 1, (int) Math.ceil((double) menuController.menuList.size() / 6))));
        menuController.showEmptyMenu();
    }
}
