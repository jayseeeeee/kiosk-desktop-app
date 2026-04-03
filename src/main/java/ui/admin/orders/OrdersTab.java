package ui.admin.orders;

import app.store.Order;
import app.store.Product;
import ui.admin.AdminUi;
import ui.admin.TabContainer;
import ui.card.ItemCard;
import ui.card.OrderCard;

import javax.swing.*;

public final class OrdersTab extends TabContainer {
    private final ListContainer listContainer = new ListContainer(this);
    private final ViewContainer viewContainer = new ViewContainer(this);

    public OrdersTab(AdminUi adminUi) {
        super(adminUi);
        this.add(listContainer);
        this.add(viewContainer);
    }

    @Override
    public void setContainerLayout() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void updateQueue() {
        listContainer.orderContainer.removeAll();

        for (Order order : Order.getListOfOrders()){
            listContainer.orderContainer.add(new OrderCard(order, this));
        }

        this.adminUi.revalidate();
        this.adminUi.repaint();
    }

    public void updateList(Order order) {
        viewContainer.productContainer.removeAll();

        for (Product product : order.getProducts()) {
            viewContainer.productContainer.add(new ItemCard(product));
        }

        this.adminUi.revalidate();
        this.adminUi.repaint();
    }
}
