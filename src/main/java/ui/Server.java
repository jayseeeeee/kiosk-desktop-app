package ui;

import ui.admin.AdminUi;
import ui.user.UserUi;
import util.CSVParser;
import util.FileHandler;

import javax.swing.*;

public class Server {
    private final AdminUi adminUi;
    private final UserUi userUi;

    public Server() {
        FileHandler.setDirectories();
        CSVParser.readAllCsvFiles();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            IO.println(e);
        }

        userUi = new UserUi(this);
        adminUi = new AdminUi(this);
    }

    public void updateOrder() {
        if (adminUi != null) {
            adminUi.ordersTab.updateQueue();
        }
    }
}
