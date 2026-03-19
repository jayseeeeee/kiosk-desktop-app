package app;

import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.LinkedHashSet;

public class Order implements Serializable {
    public static final int PAYMENT = 0;
    public static final int PREPARING = 1;
    public static final int SERVING = 2;

    public static final int MAX_ORDER_COUNT = 999;
    public static int currentOrderCount = 0;
    public final int orderCount;
    private final LinkedHashSet<Product> products;
    private final double finalCost;
    private final int status;

    public Order(double finalCost, LinkedHashSet<Product> products, String paymentMethod) {
        this.status = PAYMENT;
        this.finalCost = finalCost;
        this.products = products;
        orderCount = ++currentOrderCount;
        try (
            FileOutputStream orderFile = new FileOutputStream(FileHandler.ORDER_FOLDER + "\\" + orderCount);
            ObjectOutputStream orderObject = new ObjectOutputStream(orderFile)
        ) {
            orderObject.writeObject(this);
        } catch (RuntimeException e) {
            IO.println("Error: Unexpected runtime error occurred.\n" + e);
        } catch (FileNotFoundException e) {
            IO.println("Error: File not found: Please check the file path or name.\n" + e);
        } catch (IOException e) {
            IO.println("Error: I/O error encountered while processing the file.\n" + e);
        }
    }

    public JLabel getOrderLabel() {
        JLabel orderLabel = new JLabel();
        orderLabel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        orderLabel.setBackground(Color.white);
        orderLabel.setBorder(UserUi.BORDER_STYLE);
        orderLabel.setPreferredSize(new Dimension(420, 96));
        orderLabel.setMaximumSize(new Dimension(420, 96));

        orderLabel.setText(String.format("%03d", orderCount));
        orderLabel.setFont(new Font(UserUi.FONT, Font.BOLD, 32));

        ImageIcon statusIcon = null;
        switch (status) {
            case PAYMENT -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "payment_status.png", 48);
            case PREPARING -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "preparing_status.png", 48);
            case SERVING -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "serving_status.png", 48);
        }
        orderLabel.setIcon(statusIcon);
        orderLabel.setHorizontalAlignment(JLabel.CENTER);
        orderLabel.setVerticalAlignment(JLabel.CENTER);
        orderLabel.setHorizontalTextPosition(JLabel.LEFT);
        orderLabel.setIconTextGap(75);
        return orderLabel;
    }
}
