package finish;

import furniture.Furniture;

public class VanishWoodFinish extends FurnitureFinish {
    private final static double PRICE = 80.50;
    private final static double FINISH_TIME = 30;

    public VanishWoodFinish(Furniture furniture) {
        super(furniture);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Vanish wood finish";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + PRICE;
    }

    @Override
    public void finish() {
        super.setStatus(true);
    }

    @Override
    public double getEstimateFinishTime() {
        return super.getEstimateFinishTime() + FINISH_TIME;
    }
}
