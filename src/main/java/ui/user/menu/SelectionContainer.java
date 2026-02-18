package ui.user.menu;

import ui.user.menu.controller.SelectionController;

import javax.swing.*;
import java.awt.*;

public class SelectionContainer extends JPanel {
    private final SelectionController selectionController;
    public final JButton prevButton = getPrevButton();
    public final JTextField pageSelection = getPageSelection();
    public final JButton nextButton = getNextButton();

    public SelectionContainer(MenuTab menuTab) {
        this.selectionController = menuTab.selectionController;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(1, 32));
        this.add(prevButton);
        this.add(pageSelection);
        this.add(nextButton);
    }

    private JButton getPrevButton() {
        JButton prevButton = new JButton("<");
        prevButton.setFocusable(false);
        prevButton.addActionListener(_ -> selectionController.prevSelection());
        return prevButton;
    }

    private JTextField getPageSelection() {
        JTextField pageSelection = new JTextField("1");
        pageSelection.addActionListener(_ -> selectionController.changeSelection());
        return pageSelection;
    }

    private JButton getNextButton() {
        JButton nextButton = new JButton(">");
        nextButton.setFocusable(false);
        nextButton.addActionListener(_ -> selectionController.nextSelection());
        return nextButton;
    }

}
