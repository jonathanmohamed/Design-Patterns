package store;

import store.order.WoodOrder;

public class WoodProcessor {
    private boolean readyStatus;

    public WoodProcessor() {
        this.readyStatus = false;
    }

    public void prepareOrder(WoodOrder order) throws InterruptedException {
        this.setReadyStatus(false);
        order.prepareOrder();
        this.setReadyStatus(true);
    }

    public void setReadyStatus(boolean readyStatus) {
        this.readyStatus = readyStatus;
    }

    public boolean isReady() {
        return this.readyStatus;
    }



}
