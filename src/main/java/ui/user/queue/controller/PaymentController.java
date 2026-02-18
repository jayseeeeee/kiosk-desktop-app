package ui.user.queue.controller;

import payment.Discount;
import payment.Payment;
import product.Product;
import ui.admin.order.controller.OrderController;
import ui.user.basket.BasketTab;
import ui.user.basket.controller.BasketController;
import ui.card.Order;
import ui.user.queue.PaymentContainer;
import ui.user.queue.QueueTab;

import javax.swing.*;

public class PaymentController {
    private final QueueTab queueTab;
    private final OrderController orderController;
    private final BasketTab basketTab;
    private final BasketController basketController;
    private final PaymentContainer paymentContainer;
    private final CostController costController;
    private Discount discount;

    public PaymentController(QueueTab queueTab) {
        this.queueTab = queueTab;
        this.orderController = queueTab.userUi.uiController.adminUi.orderContainer.orderController;
        this.basketTab = queueTab.userUi.basketTab;
        this.basketController = queueTab.userUi.basketTab.basketController;
        this.paymentContainer = queueTab.paymentContainer;
        this.costController = queueTab.costController;
    }

    public void validateDiscount() {
        Discount discount = Discount.validate(paymentContainer.promoCode.getText().trim());
        if (discount == null && !paymentContainer.promoCode.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "The voucher code you entered is invalid or no longer available.", "Voucher", JOptionPane.WARNING_MESSAGE);
        }
        costController.updateFinalCost();
    }

    public void checkout(String paymentMethod) {
        if (basketController.basketList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your basket is currently empty.", "Checkout", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Order order = Payment.pay(paymentMethod, costController.getFinalCost(), basketController.basketList.toArray(new Product[0]));
        if (order != null) {
            basketController.basketList.clear();
            orderController.orderList.offer(order);

            if (discount != null) {
                discount.apply();
                discount = null;
                paymentContainer.promoCode.setText("");
            }

            queueTab.queueContainer.add(order);
            queueTab.queueContainer.revalidate();
            queueTab.queueContainer.repaint();

            basketTab.basketContainer.removeAll();
            basketTab.basketContainer.revalidate();
            basketTab.basketContainer.repaint();

            costController.updateInitialCost();
        }
    }

    public Discount getDiscount() {
        return discount;
    }
}
