package store.order;

public interface WoodOrder {
    void prepareOrder() throws InterruptedException;
    boolean getFurnitureStatus(String itemName);
}
