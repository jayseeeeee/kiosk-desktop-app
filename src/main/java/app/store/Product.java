package app.store;

import java.io.Serializable;
import java.util.ArrayList;

public final class Product implements Serializable {
    private final static ArrayList<Product> listOfProducts = new ArrayList<>();

    public final String name;
    public final String category;
    public final String description;
    public int quantity;
    public final double cost;
    public final String imagePath;
    public final transient Allergy allergies;

    public Product(String name, String category, String description, String imagePath, double cost, Allergy allergies) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.allergies = allergies;
        this.imagePath = imagePath;
        listOfProducts.add(this);
    }

    public static Product[] getListOfProducts() {
        return listOfProducts.toArray(new Product[0]);
    }
}
