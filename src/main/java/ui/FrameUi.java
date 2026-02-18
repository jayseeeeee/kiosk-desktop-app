package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class FrameUi extends JFrame {
    public final static LineBorder BORDER_STYLE = new LineBorder(new Color(0xFFCFCFCF, true), 1, true);
    public final static Color MAIN_COLOR = new Color(0xFAFAFA);
    public final static String FONT = "Helvetica";

    public final JPanel mainPanel = getMainPanel();

    public FrameUi() {
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/brand_icon.png"))).getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(getLogoPanel(), BorderLayout.NORTH);
        this.add(mainPanel);
    }

    public JPanel getLogoPanel() {
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(new Color(0xF3F3F3));
        logoPanel.setPreferredSize(new Dimension(1280, 72));
        logoPanel.add(getLogo());
        return logoPanel;
    }

    public JLabel getLogo() {
        ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/brand_logo.png")));
        JLabel logo = new JLabel(image);
        return logo;
    }

    public JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        return mainPanel;
    }
}
