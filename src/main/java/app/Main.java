package app;

import ui.admin.AdminUi;
import ui.user.UserUi;
import util.CSVParser;
import util.FileHandler;

import javax.swing.*;

public final class Main {
    static void main() {

        FileHandler.setDirectories();
        CSVParser.readAllCsvFiles();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            IO.println(e);
        }

        UserUi userUi = new UserUi();
        userUi.setUi(UserUi.MAIN_MENU_UI);
        AdminUi adminUi = new AdminUi();
        adminUi.setUi(AdminUi.ORDER_MENU_UI);
    }
}