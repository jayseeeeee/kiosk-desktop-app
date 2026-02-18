package product;

import ui.user.UserUi;
import ui.card.Basket;
import ui.card.Item;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Product {
    public static ArrayList<Product> listOfProducts = new ArrayList<>();
    public static UserUi shop;

    public String name;
    public String category;
    public String description;
    public Image image;
    public double cost;
    public Allergy allergies;
    public Item item;
    public Basket basket;

    public Product(String name, String category, String description, String imagePath, double cost, Allergy allergies) {
        String imageLocation = FileHandler.IMAGE_FOLDER + "\\" + imagePath;
        this.name = name;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.allergies = allergies;
        this.image = new ImageIcon(imageLocation).getImage();
        if (!new File(imageLocation).exists()) {
            this.image = new ImageIcon(getClass().getResource("assets/no-image.png")).getImage();
        }
        listOfProducts.add(this);
        item = new Item(this);
        basket = new Basket(this);
    }

    public static void setShop(UserUi shop) {
        Product.shop = shop;
    }
}
