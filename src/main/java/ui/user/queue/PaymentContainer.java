package ui.user.queue;

import ui.user.UserUi;
import ui.user.queue.controller.PaymentController;

import javax.swing.*;
import java.awt.*;

public class PaymentContainer extends JPanel {
    private final PaymentController paymentController;
    public final JComboBox<String> paymentMethod = getPaymentMethod();
    public final JTextField promoCode = getPromoCode();

    public PaymentContainer(QueueTab queueTab) {
        this.paymentController = queueTab.paymentController;
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(UserUi.MAIN_COLOR);
        this.setPreferredSize(new Dimension(320, 42));
        this.add(getPaymentTitle());
        this.add(getPaymentMethodContainer());
    }

    private JLabel getPaymentTitle() {
        JLabel paymentTitle = new JLabel("PAYMENT");
        paymentTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 24));
        return paymentTitle;
    }

    private JPanel getPaymentMethodContainer() {
        JPanel paymentMethodContainer = new JPanel();
        paymentMethodContainer.setBackground(UserUi.MAIN_COLOR);
        paymentMethodContainer.setPreferredSize(new Dimension(320, 92));
        paymentMethodContainer.add(paymentMethod);
        paymentMethodContainer.add(promoCode);
        paymentMethodContainer.add(getApplyButton());
        return paymentMethodContainer;
    }

    private JComboBox<String> getPaymentMethod() {
        JComboBox<String> paymentMethod = new JComboBox<>(new String[] {"Pay on Counter", "Cashless"});
        paymentMethod.setPreferredSize(new Dimension(256, 42));
        paymentMethod.setFont(new Font(UserUi.FONT, Font.PLAIN, 16));
        paymentMethod.setFocusable(false);
        return paymentMethod;
    }

    private JTextField getPromoCode() {
        JTextField promoCode = new JTextField();
        promoCode.setPreferredSize(new Dimension(172, 32));
        promoCode.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        promoCode.addActionListener(_ -> paymentController.validateDiscount());
        return promoCode;
    }

    private JButton getApplyButton() {
        JButton apply = new JButton("Apply");
        apply.setFocusable(false);
        apply.setPreferredSize(new Dimension(80, 32));
        apply.addActionListener(_ -> paymentController.validateDiscount());
        return apply;
    }
}
