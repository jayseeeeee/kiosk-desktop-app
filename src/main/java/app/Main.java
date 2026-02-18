package app;

import product.Product;
import ui.UiController;
import ui.admin.AdminUi;
import ui.user.UserUi;
import util.CSVParser;
import util.FileHandler;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        FileHandler.setDirectories();
        CSVParser.readAllCsvFiles();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            IO.println(e);
        }

        UiController uiController = new UiController();
    }
}