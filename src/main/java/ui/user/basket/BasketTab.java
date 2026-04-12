package ui.user.basket;

import app.store.Product;
import ui.card.BasketCard;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;

public final class BasketTab extends JPanel {
    private final UserUi userUi;
    private final JPanel basketContainer = new JPanel();

    public final LinkedHashSet<Product> basketList = new LinkedHashSet<>();

    public BasketTab(UserUi userUi) {
        this.userUi = userUi;
        this.setOpaque(false);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(1, 1));
        this.add(getBasketTitle());
        this.add(Box.createHorizontalStrut(128));
        this.add(getBasketDescription());
        this.add(getScrollPane());
    }

    private JLabel getBasketTitle() {
        JLabel queueTitle = new JLabel("BASKET");
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return queueTitle;
    }

    private JLabel getBasketDescription() {
        JLabel orderNumber = new JLabel("EDIT ORDER");
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return orderNumber;
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(basketContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(420, 840));
        return scrollPane;
    }

    public void updateBasket() {
        basketContainer.removeAll();
        if (basketList.isEmpty()) {
            basketContainer.setLayout(new BorderLayout());
            JLabel emptyResultTitle = new JLabel("BASKET IS EMPTY");
            emptyResultTitle.setOpaque(true);
            emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
            emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
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
