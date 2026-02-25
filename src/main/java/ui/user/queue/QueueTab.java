package ui.user.queue;

import payment.Discount;
import payment.Payment;
import product.Product;
import ui.card.Order;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class QueueTab extends JPanel {
    public final UserUi userUi;
    public final JPanel queueContainer = getQueueContainer();
    public final CostContainer costContainer = new CostContainer(this);
    public final PaymentContainer paymentContainer = new PaymentContainer(this);
    private double initialCost;
    private double finalCost;
    private Discount discount;

    public QueueTab(UserUi userUi) {
        this.userUi = userUi;
        this.setBackground(UserUi.MAIN_COLOR);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setPreferredSize(new Dimension(1, 1));
        this.add(getQueueTitle());
        this.add(getOrderNumber());
        this.add(getStatusTitle());
        this.add(queueContainer);
        this.add(paymentContainer);
        this.add(costContainer);
    }

    private JLabel getQueueTitle() {
        JLabel queueTitle = new JLabel("QUEUE");
        queueTitle.setPreferredSize(new Dimension(256, 50));
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        return queueTitle;
    }

    private JLabel getOrderNumber() {
        JLabel orderNumber = new JLabel("ORDER NUMBER");
        orderNumber.setPreferredSize(new Dimension(128, 16));
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 12));
        return orderNumber;
    }

    private JLabel getStatusTitle() {
        JLabel statusTitle = new JLabel("STATUS");
        statusTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 12));
        return statusTitle;
    }

    private JPanel getQueueContainer() {
        JPanel queueContainer = new JPanel();
        queueContainer.setLayout(new BoxLayout(queueContainer, BoxLayout.Y_AXIS));
        JScrollPane listPane = new JScrollPane(queueContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listPane.setPreferredSize(new Dimension(320, 240));
        return queueContainer;
    }

    public void updateInitialCost() {
        initialCost = 0.00;
        for (Product product : userUi.basketTab.basketList) {
            initialCost += (product.cost * product.quantity);
        }
        costContainer.initialCost.setText(String.format("₱%.2f", initialCost));
        updateFinalCost();
    }

    public void updateFinalCost() {
        finalCost = initialCost;
        costContainer.discountTitle.setVisible(getDiscount() != null);
        costContainer.discount.setVisible(getDiscount() != null);

        // Compute for Full Price Order
        if (getDiscount() == null) {
            costContainer.finalCost.setText(String.format("₱%.2f", finalCost));
            return;
        }

        // Compute for Discounted Order
        if (getDiscount().isPercentage) {
            finalCost = finalCost * (1 - getDiscount().discountCost / 100.00);
            costContainer.discount.setText(String.format("– %d%%", (int) getDiscount().discountCost));
        } else {
            finalCost = finalCost - getDiscount().discountCost;
            costContainer.discount.setText(String.format("– ₱%.2f", getDiscount().discountCost));
        }
        finalCost = Math.max(finalCost, 0.00);
        costContainer.finalCost.setText(String.format("₱%.2f", finalCost));
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void validateDiscount() {
        Discount discount = Discount.validate(paymentContainer.promoCode.getText().trim());
        if (discount == null && !paymentContainer.promoCode.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "The voucher code you entered is invalid or no longer available.", "Voucher", JOptionPane.WARNING_MESSAGE);
        }
        updateFinalCost();
    }

    public void checkout(String paymentMethod) {
        if (userUi.basketTab.basketList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your basket is currently empty.", "Checkout", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Order order = Payment.pay(paymentMethod, getFinalCost(), userUi.basketTab.basketList.toArray(new Product[0]));
        if (order != null) {
            userUi.basketTab.basketList.clear();

            if (discount != null) {
                discount.apply();
                discount = null;
                paymentContainer.promoCode.setText("");
            }

            queueContainer.add(order);
            queueContainer.revalidate();
            queueContainer.repaint();

            userUi.basketTab.scrollPaneContainer.basketContainer.removeAll();
            userUi.basketTab.scrollPaneContainer.basketContainer.revalidate();
            userUi.basketTab.scrollPaneContainer.basketContainer.repaint();

            updateInitialCost();
        }
    }

    public Discount getDiscount() {
        return discount;
    }
}
