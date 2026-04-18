package ui.admin.orders;

import ui.admin.AdminUi;
import ui.admin.orders.view.ManageContainer;
import ui.admin.orders.view.ProductContainer;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

class ViewContainer extends JPanel {
    ProductContainer productContainer = new ProductContainer();
    ManageContainer manageContainer = new ManageContainer();

    private final OrdersTab ordersTab;

    ViewContainer(OrdersTab ordersTab) {
        this.ordersTab = ordersTab;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(500, 1));
        this.setBorder(AdminUi.BORDER_STYLE);
        this.add(Box.createRigidArea(new Dimension(1000, 20)));
        this.add(getItemContainer());
        this.add(productContainer.scrollPane);
        this.add(manageContainer);
    }

    private JPanel getItemContainer() {
        JPanel itemContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemContainer.setPreferredSize(new Dimension(450, 50));
        itemContainer.setOpaque(false);
        itemContainer.add(getItemTitle());
        itemContainer.add(Box.createHorizontalStrut(130));
        itemContainer.add(getItemQuantityLabel());
        itemContainer.add(Box.createHorizontalStrut(10));
        itemContainer.add(getItemPriceLabel());
        return itemContainer;
    }

    private JLabel getItemTitle() {
        JLabel queueTitle = new JLabel("ITEMS");
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        queueTitle.setForeground(AdminUi.SELECTED_COLOR);
        return queueTitle;
    }

    private JLabel getItemQuantityLabel() {
        JLabel quantity = new JLabel("QUANTITY");
        quantity.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        quantity.setForeground(AdminUi.DEFAULT_COLOR);
        return quantity;
    }

    private JLabel getItemPriceLabel() {
        JLabel price = new JLabel("PRICE");
        price.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        price.setForeground(AdminUi.DEFAULT_COLOR);
        return price;
    }
}
