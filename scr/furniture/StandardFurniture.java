package furniture;

public class StandardFurniture implements Furniture {

    private boolean status;
    private final static double COST = 100;
    private final static double ESTIMATED_FINISH_TIME = 110;

    public StandardFurniture() {
    }

    @Override
    public double getPrice() {
        return COST;
    }

    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void finish() {
        setStatus(true);
    }

    @Override
    public double getEstimateFinishTime() {
        return ESTIMATED_FINISH_TIME;
    }

    @Override
    public String getDescription() {
       return "This is a standard furniture";
    }
}
