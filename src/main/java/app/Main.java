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

        new UserUi();
        new AdminUi();
    }
}