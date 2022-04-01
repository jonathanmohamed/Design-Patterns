package store.order;

import Exceptions.ItemDoesNotExistException;
import payment.Payment;
import furniture.*;
import finish.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Order implements WoodOrder {
    private final FurnitureBuilder furnitureBuilder;
    private final HashMap<String, Furniture> woodOrders;
    private Payment payment;
    private boolean orderStatus;
    private boolean isPaid;

    public Order() {
        this.furnitureBuilder = new FurnitureBuilder();
        this.woodOrders = new HashMap<>();
        this.orderStatus = false;
        this.isPaid = false;
    }

    public void addFurniture(String typeOfFurniture, String itemName) {
        Furniture newFurniture = this.furnitureBuilder.buildFurniture(typeOfFurniture);
        woodOrders.put(itemName, newFurniture);
    }

    public Furniture findFurniture(String itemName) {
        return woodOrders.get(itemName);
    }

    public double getOrderEstimatedPreparationTime() {
        double totalTime = 0;
        for(Map.Entry<String, Furniture> orderItem : this.getWoodOrders().entrySet()) {
            totalTime += orderItem.getValue().getEstimateFinishTime();
        }
        return totalTime;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public HashMap<String, Furniture> getWoodOrders() {
        return this.woodOrders;
    }

    public void addDecorationToFurniture(String itemName, String finishType) throws ItemDoesNotExistException {
        Furniture furniture = woodOrders.get(itemName);
        Furniture decoratedFurniture;
        switch (finishType) {
            case "wax":
                decoratedFurniture = new WaxWoodFinish(furniture);
                break;
            case "water":
                decoratedFurniture = new WaterBasedFinish(furniture);
                break;
            case "shellac":
                decoratedFurniture = new ShellacWoodFinish(furniture);
                break;
            case "vanish":
                decoratedFurniture = new VanishWoodFinish(furniture);
                break;
            default:
                throw new ItemDoesNotExistException();
        }
        woodOrders.put(itemName, decoratedFurniture);
    }

    public double calculateBill() {
        double totalCost = 0.0;
        for (Map.Entry<String, Furniture> pizzas : woodOrders.entrySet()) {
                totalCost += pizzas.getValue().getPrice();
        }
        return totalCost;

    }

    public void payment(Payment payment) {
        this.payment = payment;
        double amount = calculateBill();
        this.payment.pay(amount);
        setPaid(true);
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public void prepareOrder() throws InterruptedException {
        for(Map.Entry<String, Furniture> orderItem : this.getWoodOrders().entrySet()) {
            orderItem.getValue().finish();
            TimeUnit.SECONDS.sleep((long) orderItem.getValue().getEstimateFinishTime());
        }
        this.setOrderStatus(true);
    }

    @Override
    public boolean getFurnitureStatus(String itemName) {
        return this.findFurniture(itemName).getStatus();
    }
}
