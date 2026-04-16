package ui.user.queue;

import app.store.Discount;
import app.store.Product;
import app.store.Order;
import ui.user.UserUi;
import ui.card.QueueCard;

import javax.swing.*;
import java.awt.*;

public final class QueueTab extends JPanel {
    private final UserUi userUi;

    private final CostContainer costContainer = new CostContainer();
    private final PaymentContainer paymentContainer = new PaymentContainer(this);
    private final JPanel queueContainer = new JPanel();

    private double initialCost;
    private double finalCost;
    private Discount discount;

    public QueueTab(UserUi userUi) {
        this.userUi = userUi;
        this.setOpaque(false);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(1, 1));
        this.add(getQueueTitle());
        this.add(Box.createHorizontalStrut(32));
        this.add(getOrderNumber());
        this.add(Box.createHorizontalStrut(16));
        this.add(getStatusTitle());
        this.add(getScrollPane());
        this.add(paymentContainer);
        this.add(costContainer);
        this.add(getCheckoutButton());
    }

    private JLabel getQueueTitle() {
        JLabel queueTitle = new JLabel("QUEUE");
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return queueTitle;
    }

    private JLabel getOrderNumber() {
        JLabel orderNumber = new JLabel("ORDER NUMBER");
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return orderNumber;
    }

    private JLabel getStatusTitle() {
        JLabel statusTitle = new JLabel("STATUS");
        statusTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return statusTitle;
    }

    private JScrollPane getScrollPane() {
        queueContainer.setLayout(new BoxLayout(queueContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(queueContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(420, 440));
        scrollPane.setBorder(UserUi.BORDER_STYLE);
        return scrollPane;
    }

    private JButton getCheckoutButton() {
        JButton checkoutButton = new JButton("CHECKOUT");
        checkoutButton.setFocusable(false);
        checkoutButton.setFont(new Font(UserUi.FONT, Font.BOLD, 28));
        checkoutButton.setForeground(new Color(30,30,30));
        checkoutButton.setPreferredSize(new Dimension(200, 64));
        checkoutButton.addActionListener(_ -> checkout((String) paymentContainer.paymentMethod.getSelectedItem()));
        return checkoutButton;
    }

    public void updateQueue() {
        queueContainer.removeAll();

        for (Order order : Order.getListOfOrders()){
            queueContainer.add(new QueueCard(order));
        }

        userUi.revalidate();
        userUi.repaint();
        userUi.server.updateOrder();
    }

    public void updateInitialCost() {
        initialCost = 0.00;
        for (Product product : userUi.basketTab.basketList) {
            initialCost += (product.cost * product.quantity);
        }
        costContainer.initialCost.setText(String.format("₱%.2f", initialCost));
        updateFinalCost();
    }

    private void updateFinalCost() {
        finalCost = initialCost;
        costContainer.discountTitle.setVisible(discount != null);
        costContainer.discount.setVisible(discount != null);

        // Compute for Full Price Order
        if (discount == null) {
            costContainer.finalCost.setText(String.format("₱%.2f", finalCost));
            return;
        }

        // Compute for Discounted Order
        if (discount.isPercentage) {
            finalCost = finalCost * (1 - discount.discountCost / 100.00);
            costContainer.discount.setText(String.format("– %d%%", (int) discount.discountCost));
        } else {
            finalCost = finalCost - discount.discountCost;
            costContainer.discount.setText(String.format("– ₱%.2f", discount.discountCost));
        }
        finalCost = Math.max(finalCost, 0.00);
        costContainer.finalCost.setText(String.format("₱%.2f", finalCost));
    }

    void validateDiscount() {
        discount = Discount.validate(paymentContainer.promoCode.getText().trim());
        if (discount == null && !paymentContainer.promoCode.getText().isBlank()) {
            userUi.setUi(UserUi.INVALID_VOUCHER_UI);
        }
        updateFinalCost();
    }

    private void checkout(String paymentMethod) {
        if (userUi.basketTab.basketList.isEmpty()) {
            userUi.setUi(UserUi.EMPTY_BASKET_UI);
            return;
        }

        Order order;
        if (paymentMethod.equals("Cashless")) {
            userUi.setUi(UserUi.QR_CODE_UI);
            order = Order.createNewOrder(finalCost, userUi.basketTab.basketList, "Cashless");
        } else if (paymentMethod.equals("Pay on Counter")) {
            order = Order.createNewOrder(finalCost, userUi.basketTab.basketList, "Pay on Counter");
        } else {
            return;
        }

        if (order == null) {
            return;
        }

        for (Product product : userUi.basketTab.basketList) {
            product.quantity = 0;
        }

        if (discount != null) {
            discount.apply();
            discount = null;
            paymentContainer.promoCode.setText("");
        }

        userUi.basketTab.basketList.clear();
        userUi.basketTab.updateBasket();
        updateQueue();
    }
}
