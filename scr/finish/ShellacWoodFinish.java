package finish;

import furniture.Furniture;

public class ShellacWoodFinish extends FurnitureFinish {
    private final static double PRICE = 100.50;
    private final static double FINISH_TIME = 15;

    public ShellacWoodFinish(Furniture furniture) {
        super(furniture);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Shellac Wood Finish";
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
