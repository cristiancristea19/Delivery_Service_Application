package BusinessLogicLayer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IDeliveryServiceProcessing {
    void importProducts();
    boolean addProduct(MenuItem baseProduct);
    boolean modifyProduct(BaseProduct baseProduct);
    boolean deleteProduct(String title);
    void addOrder(List<MenuItem> menuItems, String client, int total);
    List<MenuItem> search(String keyword, float rating, int calories, int proteins, int fats, int sodium, int price);
    Map<Order, List<MenuItem>> getOrdersInInterval(int startHour, int endHour);
    List<MenuItem> getOrderedProducts(int noOfTimes);
    List<String> getLoyalClients(int noOfTimes, int amount);
    Map<MenuItem, Long> getProductsByDay(LocalDateTime date);
}
