package ui.user.queue.controller;

import payment.Discount;
import payment.Payment;
import product.Product;
import ui.user.basket.controller.BasketController;
import ui.user.queue.CostContainer;
import ui.user.queue.PaymentContainer;
import ui.user.queue.QueueTab;

import java.util.Objects;

public class CostController {
    private final CostContainer costContainer;
    private final BasketController basketController;
    private final PaymentController paymentController;
    private double initialCost;
    private double finalCost;

    public CostController(QueueTab queueTab) {
        this.costContainer = queueTab.costContainer;
        this.basketController = queueTab.userUi.basketTab.basketController;
        this.paymentController = queueTab.paymentController;
    }

    public void updateInitialCost() {
        initialCost = 0.00;
        for (Product product : basketController.basketList) {
            int quantity = (int) Objects.requireNonNull(product.basket.quantity.getSelectedItem());
            initialCost += (product.cost * quantity);
        }
        costContainer.initialCost.setText(String.format("₱%.2f", initialCost));
        updateFinalCost();
    }

    public void updateFinalCost() {
        finalCost = initialCost;
        costContainer.discountTitle.setVisible(paymentController.getDiscount() != null);
        costContainer.discount.setVisible(paymentController.getDiscount() != null);

        // Compute for Full Price Order
        if (paymentController.getDiscount() == null) {
            costContainer.finalCost.setText(String.format("₱%.2f", finalCost));
            return;
        }

        // Compute for Discounted Order
        if (paymentController.getDiscount().isPercentage) {
            finalCost = finalCost * (1 - paymentController.getDiscount().discountCost / 100.00);
            costContainer.discount.setText(String.format("– %d%%", (int) paymentController.getDiscount().discountCost));
        } else {
            finalCost = finalCost - paymentController.getDiscount().discountCost;
            costContainer.discount.setText(String.format("– ₱%.2f", paymentController.getDiscount().discountCost));
        }
        finalCost = Math.max(finalCost, 0.00);
        costContainer.finalCost.setText(String.format("₱%.2f", finalCost));
    }

    public double getFinalCost() {
        return finalCost;
    }
}
