package ui.user.basket;

import product.Product;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BasketTab extends JPanel {
    public final UserUi userUi;
    public final LabelContainer labelContainer = new LabelContainer();
    public final ScrollPaneContainer scrollPaneContainer = new ScrollPaneContainer();

    public final LinkedList<Product> basketList = new LinkedList<>();

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
                scrollPaneContainer.basketContainer.add(product.basket);
            }
        }
        scrollPaneContainer.basketContainer.revalidate();
        scrollPaneContainer.basketContainer.repaint();
        userUi.queueTab.updateInitialCost();
    }

    public void addBasket(Product product) {
        if (basketList.contains(product)) {
            JComboBox<Integer> quantityComboBox = product.basket.quantity;
            int selectedIndex = quantityComboBox.getSelectedIndex();
            quantityComboBox.setSelectedIndex(Math.min(quantityComboBox.getItemCount() - 1, ++selectedIndex));
        } else {
            basketList.push(product);
        }
        updateBasket();
    }

    public void removeBasket(Product product) {
        basketList.remove(product);
        product.basket.quantity.setSelectedIndex(0);
        System.out.println(basketList);
        updateBasket();
    }
}
