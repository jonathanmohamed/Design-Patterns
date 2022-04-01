package furniture;

public interface Furniture {
    double getPrice();
    boolean getStatus();
    void setStatus(boolean status);
    void finish();
    double getEstimateFinishTime();
    String getDescription();
}
