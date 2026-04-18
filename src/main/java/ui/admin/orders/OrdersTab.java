package ui.admin.orders;

import app.store.Order;
import app.store.Product;
import ui.admin.AdminUi;
import ui.admin.TabContainer;
import ui.card.ItemCard;
import ui.card.OrderCard;
import ui.card.RecentOrderCard;

import javax.swing.*;
import java.awt.*;

public final class OrdersTab extends TabContainer {
    private final ListContainer listContainer = new ListContainer(this);
    private final ViewContainer viewContainer = new ViewContainer(this);
    private Order order;

    public OrdersTab(AdminUi adminUi) {
        super(adminUi);
        this.add(listContainer);
        this.add(viewContainer, BorderLayout.EAST);
    }

    @Override
    public void setContainerLayout() {
        this.setLayout(new BorderLayout());
    }

    public void updateQueue() {
        listContainer.orderContainer.removeAll();

        for (Order order : Order.getListOfOrders()){
            listContainer.orderContainer.add(new OrderCard(order, this));
            listContainer.recentContainer.add(new RecentOrderCard(order));
            listContainer.recentContainer.add(Box.createHorizontalStrut(50));
        }

        this.adminUi.revalidate();
        this.adminUi.repaint();
    }

    public void viewOrder(Order order) {
        viewContainer.productContainer.removeAll();
        if (order == null || order == this.order) {
            this.order = null;
        } else {
            this.order = order;
            for (Product product : order.getProducts()) {
                viewContainer.productContainer.add(new ItemCard(product));
            }
        }
        viewContainer.manageContainer.setOrder(this.order);
        this.adminUi.revalidate();
        this.adminUi.repaint();
    }
}
