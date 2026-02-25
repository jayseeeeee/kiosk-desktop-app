package ui.user.basket;

import product.Product;
import ui.card.Basket;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;

public class BasketTab extends JPanel {
    public final UserUi userUi;
    public final LabelContainer labelContainer = new LabelContainer();
    public final ScrollPaneContainer scrollPaneContainer = new ScrollPaneContainer();

    public final LinkedHashSet<Product> basketList = new LinkedHashSet<>();

    public BasketTab(UserUi userUi) {
        this.userUi = userUi;
        this.setBackground(UserUi.MAIN_COLOR);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(1, 1));
        this.add(labelContainer);
        this.add(scrollPaneContainer);
    }

    public void updateBasket() {
        scrollPaneContainer.basketContainer.removeAll();
        if (basketList.isEmpty()) {
            scrollPaneContainer.basketContainer.setLayout(new BorderLayout());
            JLabel emptyResultTitle = new JLabel("BASKET IS EMPTY");
            emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
            emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
            emptyResultTitle.setPreferredSize(new Dimension(256,  256));
            scrollPaneContainer.basketContainer.add(emptyResultTitle);
        } else {
            scrollPaneContainer.basketContainer.setLayout(new BoxLayout(scrollPaneContainer.basketContainer, BoxLayout.Y_AXIS));
            for (Product product : basketList) {
                scrollPaneContainer.basketContainer.add(new Basket(product, userUi));
            }
        }
        scrollPaneContainer.basketContainer.revalidate();
        scrollPaneContainer.basketContainer.repaint();
        userUi.queueTab.updateInitialCost();
    }

    public void addBasket(Product product) {
        product.quantity = Math.min(9, ++product.quantity);
        basketList.addFirst(product);
        updateBasket();
    }

    public void removeBasket(Product product) {
        product.quantity = 0;
        basketList.remove(product);
        System.out.println(basketList);
        updateBasket();
    }
}
