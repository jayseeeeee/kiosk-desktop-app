package ui;

import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Objects;

public abstract class FrameUi extends JFrame {
    private final static int LOGO_HEIGHT = 128;
    public final static LineBorder BORDER_STYLE = new LineBorder(new Color(0xFFCFCFCF, true), 1, true);
    public final static Color MAIN_COLOR = new Color(0xFAFAFA);
    public final static Color DEFAULT_COLOR = new Color(0x686868);
    public final static Color SELECTED_COLOR = new Color(0x88584e);
    public final static String FONT = "Helvetica";

    public final JPanel mainPanel = getMainPanel();

    public abstract void setUi(int ui);

    public FrameUi() {
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/brand_icon.png"))).getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
//        this.setAlwaysOnTop(true);
        this.getContentPane().setBackground(MAIN_COLOR);
//        this.getContentPane().setBackground(Color.PINK);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.add(getLogo(), BorderLayout.NORTH);
        this.add(mainPanel);
        this.setVisible(true);
    }

    private JPanel getLogo() {
        JLabel logo = new JLabel(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "brand_logo.png", LOGO_HEIGHT));
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setOpaque(false);
        logoPanel.setPreferredSize(new Dimension(1280, LOGO_HEIGHT));
        logoPanel.add(logo);
        return logoPanel;
    }

    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        return mainPanel;
    }

    protected void setDefaultUi(String text, String assetImageName, int returnMenuUi) {
        JLabel defaultLabel = new JLabel(text, FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, assetImageName, 256), JLabel.CENTER);
        defaultLabel.setFont(new Font(UserUi.FONT, Font.BOLD, 32));
        defaultLabel.setForeground(new Color(50, 50, 50));
        defaultLabel.setIconTextGap(25);
        defaultLabel.setHorizontalTextPosition(JLabel.CENTER);
        defaultLabel.setVerticalTextPosition(JLabel.BOTTOM);
        this.mainPanel.add(Box.createRigidArea(new Dimension(1920, 250)));
        this.mainPanel.add(defaultLabel);
        this.mainPanel.add(Box.createRigidArea(new Dimension(1920, 300)));
        this.mainPanel.add(getReturnMenuButton(returnMenuUi));
    }

    protected JButton getReturnMenuButton(int returnMenuUi) {
        JButton returnMenu = new JButton("RETURN TO MENU");
        returnMenu.setFocusable(false);
        returnMenu.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        returnMenu.setForeground(new Color(50, 50, 50));
        returnMenu.addActionListener(_ -> setUi(returnMenuUi));
        return returnMenu;
    }
}
