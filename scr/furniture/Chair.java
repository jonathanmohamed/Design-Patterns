package furniture;

public class Chair implements Furniture {

    private boolean status;
    private final static double COST = 20;
    private final static double ESTIMATED_FINISH_TIME = 140;


    public Chair() {
        this.status = false;
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
        return "This is a chair";
    }
}
