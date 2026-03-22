package ui.admin;

import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

final class Tab extends JPanel {
    public final static Color DEFAULT_COLOR = new Color(0x686868);
    public final static Color SELECTED_COLOR = new Color(0x88584e);

    private final static ArrayList<Tab> tabs = new ArrayList<>();

    private final String title;
    private final String imageName;
    private final String selectedImageName;

    private final JLabel tabIcon;
    private final JLabel tabTitle;
    private final JButton tabButton;
    private final AdminUi adminUi;


    Tab(AdminUi adminUi, String title, String imageName, String selectedImageName) {
        this.adminUi = adminUi;
        this.title = title;
        this.imageName = imageName;
        this.selectedImageName = selectedImageName;
        this.tabIcon = getTabIcon();
        this.tabTitle = getTabTitle();
        this.tabButton = getTabButton();
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(600, 96));
        this.setMaximumSize(new Dimension(600, 96));

        tabButton.add(tabIcon);
        tabButton.add(Box.createHorizontalStrut(24));
        tabButton.add(tabTitle);

        if (tabs.isEmpty()) {
            tabButton.doClick();
        }
        tabs.add(this);

        this.add(Box.createRigidArea(new Dimension(64, 86)));
        this.add(tabButton);
    }

    private JLabel getTabIcon() {
        return new JLabel(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, imageName, 48, 48));
    }

    private JLabel getTabTitle() {
        JLabel tabTitle = new JLabel(title);
        tabTitle.setForeground(DEFAULT_COLOR);
        tabTitle.setFont(new Font(AdminUi.FONT, Font.BOLD, 24));
        return tabTitle;
    }

    private JButton getTabButton() {
        JButton tabButton = new JButton();
        tabButton.setFocusable(false);
        tabButton.setContentAreaFilled(false);
        tabButton.setLayout(new FlowLayout());
        tabButton.addActionListener(_ -> {
            tabIcon.setIcon(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, selectedImageName, 48, 48));
            tabTitle.setForeground(SELECTED_COLOR);
            for (Tab tab : tabs) {
                if (tab != this) {
                    tab.tabIcon.setIcon(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, tab.imageName, 48, 48));
                    tab.tabTitle.setForeground(DEFAULT_COLOR);
                }
            }
            adminUi.repaint();
            adminUi.revalidate();
        });
        return tabButton;
    }
}
