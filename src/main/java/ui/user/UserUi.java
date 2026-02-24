package ui.user;

import product.Product;
import ui.FrameUi;
import ui.user.basket.BasketTab;
import ui.user.menu.MenuTab;
import ui.user.queue.QueueTab;

public class UserUi extends FrameUi {
    public final BasketTab basketTab = new BasketTab(this);
    public final MenuTab menuTab = new MenuTab(this);
    public final QueueTab queueTab = new QueueTab(this);

    public UserUi() {
        this.mainPanel.add(basketTab);
        this.mainPanel.add(menuTab);
        this.mainPanel.add(queueTab);
        menuTab.updateMenuContainer();
        basketTab.updateBasket();
    }
}
