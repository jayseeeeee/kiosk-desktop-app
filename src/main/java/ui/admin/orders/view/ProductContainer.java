package ui.admin.orders.view;

import app.store.Order;
import app.store.Product;
import ui.admin.AdminUi;
import ui.card.ItemCard;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public class ProductContainer extends JPanel {
    public final JScrollPane scrollPane = getScrollPane();

    public ProductContainer() {
        this.setOpaque(false);
        setOrder(null);
    }

    private JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(450, 500));
        scrollPane.setOpaque(false);
        scrollPane.setBorder(AdminUi.BORDER_STYLE);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }

    public void setOrder(Order order) {
        this.removeAll();
        if (order == null) {
            this.setLayout(new BorderLayout());
            this.add(getEmptyTextLabel());
        } else {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            for (Product product : order.getProducts()) {
                this.add(new ItemCard(product));
            }
        }
    }

    private JLabel getEmptyTextLabel() {
        JLabel emptyLabel = new JLabel("NO ORDER SELECTED");
        emptyLabel.setFont(new Font(UserUi.FONT,  Font.BOLD, 16));
        emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emptyLabel.setForeground(UserUi.DEFAULT_COLOR);
        return emptyLabel;
    }
}
