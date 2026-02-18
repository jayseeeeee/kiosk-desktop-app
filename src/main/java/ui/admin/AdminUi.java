package ui.admin;

import ui.UiController;
import ui.admin.order.OrderContainer;
import ui.card.Order;

import java.util.LinkedList;

public class AdminUi {
    public final UiController uiController;
    public final OrderContainer orderContainer = new OrderContainer();

    public AdminUi(UiController uiController) {
        this.uiController = uiController;
    }
}
