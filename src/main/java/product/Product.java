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

    public String name;
    public String category;
    public String description;
    public String imagePath;
    public double cost;
    public Allergy allergies;
    public Item item;
    public Basket basket;

    public Product(String name, String category, String description, String imagePath, double cost, Allergy allergies) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.allergies = allergies;
        this.imagePath = imagePath;
        listOfProducts.add(this);
    }
}
