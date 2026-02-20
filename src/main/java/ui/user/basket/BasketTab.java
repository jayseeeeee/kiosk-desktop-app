package ui.user.basket;

import product.Product;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BasketTab extends JPanel {
    public final UserUi userUi;
    public final JPanel basketContainer = new JPanel();

    public final LinkedList<Product> basketList = new LinkedList<>();

    public BasketTab(UserUi userUi) {
        this.userUi = userUi;
        this.setBackground(UserUi.MAIN_COLOR);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(1, 1));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(getTitle());
        this.add(getDescription());
        this.add(getScrollPane());
    }

    private JLabel getTitle() {
        JLabel basketTitle = new JLabel("BASKET");
        basketTitle.setPreferredSize(new Dimension(225, 50));
        basketTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        return basketTitle;
    }

    private JLabel getDescription() {
        JLabel basketDescription = new JLabel("EDIT ORDER");
        basketDescription.setFont(new Font(UserUi.FONT, Font.BOLD, 10));
        return basketDescription;
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(basketContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(320, 640));
        return scrollPane;
    }

    private void updateBasket() {
        basketContainer.removeAll();
        if (basketList.isEmpty()) {
            basketContainer.setLayout(new BorderLayout());
            JLabel emptyResultTitle = new JLabel("BASKET IS EMPTY");
            emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 12));
            emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
            emptyResultTitle.setPreferredSize(new Dimension(256,  256));
            basketContainer.add(emptyResultTitle);
        } else {
            basketContainer.setLayout(new BoxLayout(basketContainer, BoxLayout.Y_AXIS));
            for (Product product : basketList) {
                basketContainer.add(product.basket);
            }
        }
        basketContainer.revalidate();
        basketContainer.repaint();
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
