package finish;

import furniture.Furniture;

public class WaxWoodFinish extends FurnitureFinish {
    private final static double PRICE = 90.50;
    private final static double FINISH_TIME = 40;

    public WaxWoodFinish(Furniture furniture) {
        super(furniture);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Wax Wood Finish";
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
    public double getEstimateFinishTime(){
        return super.getEstimateFinishTime() + FINISH_TIME;
    }
}
