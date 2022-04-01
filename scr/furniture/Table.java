package furniture;

public class Table implements Furniture {

    private boolean status;
    private final static double COST = 300;
    private final static double ESTIMATED_FINISH_TIME = 130;

    public Table() {
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
        return "New york pizza";
    }
}
