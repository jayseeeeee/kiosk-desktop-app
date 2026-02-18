package ui.user.basket.controller;

import product.Product;
import ui.user.UserUi;
import ui.user.basket.BasketTab;
import ui.user.queue.controller.CostController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BasketController {
    public final LinkedList<Product> basketList = new LinkedList<>();
    public final BasketTab basketTab;
    public final CostController costController;

    public BasketController(BasketTab basketTab) {
        this.basketTab = basketTab;
        this.costController = basketTab.userUi.queueTab.costController;
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

    private void updateBasket() {
        basketTab.basketContainer.removeAll();
        if (basketList.isEmpty()) {
            basketTab.basketContainer.setLayout(new BorderLayout());
            JLabel emptyResultTitle = new JLabel("BASKET IS EMPTY");
            emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 12));
            emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
            emptyResultTitle.setPreferredSize(new Dimension(256,  256));
            basketTab.basketContainer.add(emptyResultTitle);
        } else {
            basketTab.basketContainer.setLayout(new BoxLayout(basketTab.basketContainer, BoxLayout.Y_AXIS));
            for (Product product : basketList) {
                basketTab.basketContainer.add(product.basket);
            }
        }
        basketTab.basketContainer.revalidate();
        basketTab.basketContainer.repaint();
        costController.updateInitialCost();
    }
}
