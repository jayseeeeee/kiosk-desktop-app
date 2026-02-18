package ui.user;

import ui.FrameUi;
import ui.UiController;
import ui.user.basket.BasketTab;
import ui.user.basket.controller.BasketController;
import ui.user.menu.MenuTab;
import ui.user.queue.QueueTab;

public class UserUi extends FrameUi {
    public final UiController uiController;
    public final BasketController
    public final BasketTab basketTab = new BasketTab();
    public final MenuTab menuTab = new MenuTab();
    public final QueueTab queueTab = new QueueTab();

    public UserUi(UiController uiController) {
        this.uiController = uiController;

        this.mainPanel.add(basketTab);
        this.mainPanel.add(menuTab);
        this.mainPanel.add(queueTab);
    }
}
