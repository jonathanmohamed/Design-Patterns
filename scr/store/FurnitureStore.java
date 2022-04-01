package store;

import store.order.Order;

import java.util.HashMap;
import java.util.Map;

public class FurnitureStore {
   private final HashMap<String, Order> orders;
   private WoodProcessor woodProcessor;

   public FurnitureStore() {
      this.orders = new HashMap<>();
      this.woodProcessor = new WoodProcessor();
   }

   public Order createOrder(String userName) {
      Order order = new Order();
      orders.put(userName, order);
      return order;
   }

   public Order findOrder(String customerName) {
      return orders.get(customerName);
   }

   public void deleteOrder(String customerName) {
      this.orders.remove(customerName);
   }

   public double getStoreRevenue() {
      double storeRevenue = 0.0;
      for (Map.Entry<String, Order> order : this.orders.entrySet()) {
         if(order.getValue().isPaid()) {
           storeRevenue += order.getValue().calculateBill();
         }
      }
      return storeRevenue;
   }
}
