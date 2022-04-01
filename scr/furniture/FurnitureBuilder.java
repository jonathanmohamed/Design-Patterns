package furniture;

public class FurnitureBuilder {

    public FurnitureBuilder() {
    }

    public Furniture buildFurniture(String furnitureName) {
        Furniture furniture;
        switch (furnitureName) {
            case "stool":
                furniture = new Stool();
                break;
            case "table":
                furniture = new Table();
                break;
            case "chair":
               furniture = new Chair();
               break;
            case "standard furniture":
                furniture = new StandardFurniture();
                break;
            default:
                furniture = null;
        }
        return furniture;
    }
}
