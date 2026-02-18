package payment;

import ui.card.Order;
import product.Product;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Payment {
    UserUi shop;

    public static Order pay(String paymentMethod, double finalCost, Product[] basketList) {
        if (paymentMethod.equals("Cashless")) {
            boolean isSuccessful = getGCashNumber(finalCost);
            if (isSuccessful) {
                return new Order(Order.PAYMENT, finalCost, basketList);
            }
        } else {
            return new Order(Order.PAYMENT, finalCost, basketList);
        }
        return null;
    }

    private static boolean getGCashNumber(double finalCost) {
        while (true) {
            String gcashNumber = JOptionPane.showInputDialog(null, "<html>Enter your <b>GCash Number (09XXXXXXXXX)</b><html>", "GCash Number", JOptionPane.PLAIN_MESSAGE);
            if (gcashNumber == null) {
                return false;
            }

            boolean isMatch = gcashNumber.matches("09\\d{9}");
            if (!isMatch) {
                int confirm = JOptionPane.showConfirmDialog(null, "The phone number entered is invalid. Would you like to try again?", "GCash Number", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE); // 'ImageIcon' replaces 'MessageType' icon
                if (confirm == JOptionPane.YES_OPTION) {
                    continue;
                }
                return false;
            }
            showQrCode(finalCost);
            return true;
        }
    }

    private static void showQrCode(double finalCost) {
        JDialog dialog = new JDialog();
        dialog.setSize(512, 600);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        String text = String.format("<html>Please pay <b>%.2f</b> with the GCash QR Code</html>", finalCost);
        ImageIcon imageicon = new ImageIcon(Objects.requireNonNull(Payment.class.getResource("payment/gcash_qr.png")));
        ImageIcon qrCode = new ImageIcon(imageicon.getImage().getScaledInstance(480, 480, Image.SCALE_SMOOTH));
        JLabel image = new JLabel(text, qrCode, JLabel.CENTER);
        image.setBackground(Color.white);
        image.setOpaque(true);
        image.setFont(new Font(UserUi.FONT, Font.PLAIN, 14));
        image.setVerticalTextPosition(JLabel.BOTTOM);
        image.setHorizontalTextPosition(JLabel.CENTER);

        dialog.add(image);
        dialog.setVisible(true);
    }
}
