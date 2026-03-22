package app;

import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;

public final class Order implements Serializable {
    private static final ArrayList<Order> listOfOrders = new ArrayList<>();

    public static final int PAYMENT_STATUS = 0;
    public static final int PREPARING_STATUS = 1;
    public static final int SERVING_STATUS = 2;

    private static final int MAX_ORDER_COUNT = 999;
    private static int currentOrderCount = 0;

    public final int orderCount;
    public final double finalCost;
    private int status;
    private final LinkedHashSet<Product> products;

    private Order(double finalCost, LinkedHashSet<Product> products, String paymentMethod) {
        setStatus(PAYMENT_STATUS);
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        switch (status) {
            case PREPARING_STATUS, SERVING_STATUS -> this.status = status;
            default -> this.status = PAYMENT_STATUS;
        }
    }

    public static Order createNewOrder(double finalCost, LinkedHashSet<Product> products, String paymentMethod) {
        if (currentOrderCount >= MAX_ORDER_COUNT) {
            return null;
        } else {
            return new Order(finalCost, products, paymentMethod);
        }
    }

    public static Order[] getListOfOrders() {
        listOfOrders.clear();
        File[] files = FileHandler.ORDER_FOLDER.listFiles();
        if (files == null) {
            return new Order[0];
        }
        Arrays.sort(files, new Comparator<>() {
            @Override
            public int compare(File f1, File f2) {
                // Extract numbers from filenames
                int num1 = extractNumber(f1.getName());
                int num2 = extractNumber(f2.getName());
                return Integer.compare(num1, num2);
            }

            private int extractNumber(String name) {
                try {
                    // Remove non-digits, parse number
                    String num = name.replaceAll("\\D+", "");
                    return num.isEmpty() ? 0 : Integer.parseInt(num);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        });

        for (File file : files) {
            try (
                FileInputStream orderFile = new FileInputStream(file);
                ObjectInputStream orderObject = new ObjectInputStream(orderFile)
            ) {
                Order order = (Order) orderObject.readObject();
                currentOrderCount = Math.max(Order.currentOrderCount, order.orderCount);
                listOfOrders.add(order);
            } catch (RuntimeException e) {
                IO.println("Error: Unexpected runtime error occurred.\n" + e);
            } catch (FileNotFoundException e) {
                IO.println("Error: File not found: Please check the file path or name.\n" + e);
            } catch (IOException e) {
                IO.println("Error: I/O error encountered while processing the file.\n" + e);
            } catch (ClassNotFoundException e) {
                IO.println("Error: Required class definition not found.\n" + e);
            }
        }
        return listOfOrders.toArray(new Order[0]);
    }
}