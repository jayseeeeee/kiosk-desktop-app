package ui.user.basket;

import app.store.Product;
import ui.card.BasketCard;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;

public final class BasketTab extends JPanel {
    private final UserUi userUi;
    private final JPanel basketContainer = getBasketContainer();

    public final LinkedHashSet<Product> basketList = new LinkedHashSet<>();

    public BasketTab(UserUi userUi) {
        this.userUi = userUi;
        this.setOpaque(false);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(1, 1));
        this.add(getBasketTitle());
        this.add(Box.createHorizontalStrut(140));
        this.add(getBasketDescription());
        this.add(Box.createHorizontalStrut(20));
        this.add(getScrollPane());
    }

    private JLabel getBasketTitle() {
        JLabel queueTitle = new JLabel("BASKET");
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        queueTitle.setForeground(UserUi.SELECTED_COLOR);
        return queueTitle;
    }

    private JLabel getBasketDescription() {
        JLabel orderNumber = new JLabel("EDIT ORDER");
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        orderNumber.setForeground(UserUi.DEFAULT_COLOR);
        return orderNumber;
    }

    private JPanel getBasketContainer() {
        JPanel basketContainer = new JPanel();
        basketContainer.setOpaque(false);
        return basketContainer;
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(basketContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(420, 840));
        scrollPane.setBorder(UserUi.BORDER_STYLE);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        return scrollPane;
    }

    public void updateBasket() {
        basketContainer.removeAll();
        if (basketList.isEmpty()) {
            basketContainer.setLayout(new BorderLayout());
            JLabel emptyResultTitle = new JLabel("BASKET IS EMPTY");
            emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
            emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
            emptyResultTitle.setForeground(UserUi.DEFAULT_COLOR);
            basketContainer.add(emptyResultTitle);
        } else {
            basketContainer.setLayout(new BoxLayout(basketContainer, BoxLayout.Y_AXIS));
            for (Product product : basketList) {
                basketContainer.add(new BasketCard(product, userUi));
            }
        }
        userUi.queueTab.updateInitialCost();
        userUi.revalidate();
        userUi.repaint();
    }

    public void addBasket(Product product) {
        if (product.quantity < 9) {
            ++product.quantity;
            basketList.addFirst(product);
            updateBasket();
        }
    }

    public void removeBasket(Product product) {
        product.quantity = 0;
        basketList.remove(product);
        System.out.println(basketList);
        updateBasket();
    }
}
