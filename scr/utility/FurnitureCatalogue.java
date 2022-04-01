package utility;

import java.util.ArrayList;

/**
 * Use for adding available pizza available.
 */
public class FurnitureCatalogue {

    public static ArrayList<String> availableFurniture() {
        ArrayList<String> nameOfFurnitures = new ArrayList<>();

        nameOfFurnitures.add("stool");
        nameOfFurnitures.add("table");
        nameOfFurnitures.add("chair");
        nameOfFurnitures.add("standard furniture");

        return nameOfFurnitures;
    }

    public static ArrayList<String> availableFurnitureFinish() {
        ArrayList<String> nameOfToppings = new ArrayList<>();

        nameOfToppings.add("wax");
        nameOfToppings.add("water");
        nameOfToppings.add("shellac");
        nameOfToppings.add("vanish");

        return nameOfToppings;
    }
}
