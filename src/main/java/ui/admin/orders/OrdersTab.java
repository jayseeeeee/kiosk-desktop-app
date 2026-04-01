package ui.admin.orders;

import app.store.Order;
import ui.admin.AdminUi;
import ui.admin.TabContainer;
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

    public void updateListContainer(Order order) {

    }
}
