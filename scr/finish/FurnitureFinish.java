package finish;

import furniture.Furniture;

public abstract class FurnitureFinish implements Furniture {
    private Furniture furniture;
    private boolean status;

    public FurnitureFinish(Furniture furniture) {
        this.furniture = furniture;
    }

    public Furniture getPizza() {
        return this.furniture;
    }

    public void setPizza(Furniture furniture) {
        this.furniture = furniture;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return this.furniture.getDescription();
    }

    public double getEstimateFinishTime(){
        return this.furniture.getEstimateFinishTime();
    }

    @Override
    public double getPrice() {
        return this.furniture.getPrice();
    }
}
