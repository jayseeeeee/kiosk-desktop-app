package ui.user.queue;

import ui.user.UserUi;
import ui.user.queue.controller.PaymentController;

import javax.swing.*;
import java.awt.*;

public class CostContainer extends JPanel {
    private final PaymentContainer paymentContainer;
    private final PaymentController paymentController;
    public final JLabel initialCost = getInitialCost();
    public final JLabel discountTitle = getDiscountTitle();
    public final JLabel discount = getDiscount();
    public final JLabel finalCost = getFinalCost();

    public CostContainer(QueueTab queueTab) {
        this.paymentContainer = queueTab.paymentContainer;
        this.paymentController = queueTab.paymentController;
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        this.setBackground(UserUi.MAIN_COLOR);
        this.setPreferredSize(new Dimension(320, 64));
        this.add(getInitialCostTitle());
        this.add(initialCost);
        this.add(discountTitle);
        this.add(discount);
        this.add(getLine());
        this.add(getFinalCostTitle());
        this.add(finalCost);
        this.add(getCheckoutPanel());
    }

    public JLabel getInitialCostTitle() {
        JLabel initialCostLabel = new JLabel("INITIAL COST");
        initialCostLabel.setPreferredSize(new Dimension(128, 12));
        initialCostLabel.setFont(new Font(UserUi.FONT, Font.PLAIN, 12));
        return initialCostLabel;
    }

    private JLabel getInitialCost() {
        JLabel initialCost = new JLabel("₱0.00");
        initialCost.setHorizontalAlignment(JLabel.RIGHT);
        initialCost.setPreferredSize(new Dimension(164, 12));
        initialCost.setFont(new Font(UserUi.FONT, Font.PLAIN, 12));
        return initialCost;
    }

    private JLabel getDiscountTitle() {
        JLabel discountTitle = new JLabel("DISCOUNT");
        discountTitle.setPreferredSize(new Dimension(128, 12));
        discountTitle.setFont(new Font(UserUi.FONT, Font.ITALIC, 12));
        return discountTitle;
    }

    private JLabel getDiscount() {
        JLabel discount = new JLabel("–₱0.00");
        discount.setHorizontalAlignment(JLabel.RIGHT);
        discount.setPreferredSize(new Dimension(150, 12));
        discount.setFont(new Font(UserUi.FONT, Font.ITALIC, 12));
        return discount;
    }

    private JSeparator getLine() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setBackground(new Color(0xFFCFCFCF));
        line.setPreferredSize(new Dimension(328, 2));
        return line;
    }

    private JLabel getFinalCostTitle() {
        JLabel finalCostTitle = new JLabel("FINAL COST");
        finalCostTitle.setPreferredSize(new Dimension(128, 16));
        finalCostTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return finalCostTitle;
    }

    private JLabel getFinalCost() {
        JLabel finalCost = new JLabel("₱0.00");
        finalCost.setHorizontalAlignment(JLabel.RIGHT);
        finalCost.setPreferredSize(new Dimension(180, 16));
        finalCost.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return finalCost;
    }

    private JPanel getCheckoutPanel() {
        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setBackground(UserUi.MAIN_COLOR);
        checkoutPanel.setPreferredSize(new Dimension(320, 52));
        checkoutPanel.add(getCheckoutButton());
        return checkoutPanel;
    }

    private JButton getCheckoutButton() {
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFocusable(false);
        checkoutButton.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        checkoutButton.setPreferredSize(new Dimension(168, 46));
        checkoutButton.addActionListener(_ -> paymentController.checkout((String) paymentContainer.paymentMethod.getSelectedItem()));
        return checkoutButton;
    }
}
