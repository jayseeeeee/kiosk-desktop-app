package ui.user;

import ui.FrameUi;
import ui.user.basket.BasketTab;
import ui.user.menu.MenuTab;
import ui.user.queue.QueueTab;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public final class UserUi extends FrameUi {
    public final static int MAIN_MENU_UI = 0;
    public final static int EMPTY_BASKET_UI = 1;
    public final static int QR_CODE_UI = 2;
    public final static int INVALID_VOUCHER_UI = 3;

    public final BasketTab basketTab = new BasketTab(this);
    public final MenuTab menuTab = new MenuTab(this);
    public final QueueTab queueTab = new QueueTab(this);

    @Override
    public void setUi(int ui) {
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        switch (ui) {
            case EMPTY_BASKET_UI -> this.setDefaultUi("YOUR BASKET IS EMPTY", "empty_basket.png", MAIN_MENU_UI);
            case QR_CODE_UI -> this.setQrCodeUi();
            case INVALID_VOUCHER_UI -> this.setDefaultUi("YOUR VOUCHER IS EXPIRED OR INVALID", "invalid_voucher.png", MAIN_MENU_UI);
            default -> setMainMenuUi();
        }
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }

    private void setMainMenuUi() {
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        this.mainPanel.add(basketTab);
        this.mainPanel.add(menuTab);
        this.mainPanel.add(queueTab);
        menuTab.updateMenuContainer();
        basketTab.updateBasket();
        queueTab.updateQueue();
    }

    private void setQrCodeUi() {
        JLabel qrCodeLabel = new JLabel("SCAN TO PAY", FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "qr_code.png", 400), JLabel.CENTER);
        qrCodeLabel.setFont(new Font(UserUi.FONT, Font.BOLD, 64));
        qrCodeLabel.setForeground(new Color(50, 50, 50));
        qrCodeLabel.setHorizontalTextPosition(JLabel.CENTER);
        qrCodeLabel.setVerticalTextPosition(JLabel.TOP);
        this.mainPanel.add(Box.createRigidArea(new Dimension(1920, 150)));
        this.mainPanel.add(qrCodeLabel);
        this.mainPanel.add(Box.createRigidArea(new Dimension(1920, 50)));
        this.mainPanel.add(this.getReturnMenuButton(MAIN_MENU_UI));
    }
}
