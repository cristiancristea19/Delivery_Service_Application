package BusinessLogicLayer;

import DataAccessLayer.Serializator;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class DeliveryService implements IDeliveryServiceProcessing, Serializable, Observable {
    private static final long serialVersionUID = 6529685098267757690L;

    private List<MenuItem> menuItems;
    private Map<Order, List<MenuItem>> orders;
    private transient List<Observer> observers;

    public DeliveryService() {
        super();
        menuItems = new ArrayList<>();
        orders = new LinkedHashMap<>();
        observers = new ArrayList<>();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public void importProducts() {
        try {
            File file = new File("products.csv");
            Files.lines(file.toPath()).skip(1).map(l -> l.split(",")).filter(distinctByKey(a -> a[0])).forEach(a -> menuItems.add(new BaseProduct(a[0].trim(), Float.parseFloat(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[6]))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addProduct(MenuItem newProduct) {
        for(MenuItem menuItem: menuItems)
        {
            if(menuItem.getTitle().equals(newProduct.getTitle())) {
                return false;
            }
        }
        menuItems.add(newProduct);
        return true;
    }

    @Override
    public boolean modifyProduct(BaseProduct baseProduct) {
        Iterator<MenuItem> iterator = menuItems.listIterator();
        while(iterator.hasNext())
        {
            MenuItem menuItem = iterator.next();
            if(menuItem instanceof BaseProduct) {
                BaseProduct current = (BaseProduct) menuItem;
                if (current.getTitle().equals(baseProduct.getTitle())) {
                    if (baseProduct.computeRating() != -1.0f) {
                        current.setRating(baseProduct.computeRating());
                    }
                    if (baseProduct.computeCalories() != -1) {
                        current.setCalories(baseProduct.computeCalories());
                    }
                    if (baseProduct.computeProtein() != -1) {
                        current.setProtein(baseProduct.computeProtein());
                    }
                    if (baseProduct.computeFat() != -1) {
                        current.setFat(baseProduct.computeFat());
                    }
                    if (baseProduct.computeSodium() != -1) {
                        current.setSodium(baseProduct.computeSodium());
                    }
                    if (baseProduct.getPrice() != -1) {
                        current.setPrice(baseProduct.getPrice());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteProduct(String title) {
        Iterator<MenuItem> iterator = menuItems.listIterator();
        while(iterator.hasNext())
        {
            MenuItem current = iterator.next();
            if(current.getTitle().equals(title))
            {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void addOrder(List<MenuItem> menuItems, String client, int total) {
        Order order = new Order(client, LocalDateTime.now(), total);
        orders.put(order, menuItems);
        List<Object> list = new ArrayList<>();
        list.add(order);
        list.add(menuItems);
        notifyObservers(list);
    }

    @Override
    public List<MenuItem> search(String keyword, float rating, int calories, int proteins, int fat, int sodium, int price) {
        List<MenuItem> foundItems = menuItems;
        if(keyword != null) {
            foundItems = foundItems.stream().filter(menuItem -> menuItem.getTitle().contains(keyword)).collect(Collectors.toList());
        }
        if(rating != -1){
            foundItems = foundItems.stream().filter(menuItem -> menuItem.computeRating() == rating).collect(Collectors.toList());
        }
        if(calories != -1){
            foundItems = foundItems.stream().filter(menuItem -> menuItem.computeCalories() == calories).collect(Collectors.toList());
        }
        if(proteins != -1){
            foundItems = foundItems.stream().filter(menuItem -> menuItem.computeProtein() == proteins).collect(Collectors.toList());
        }
        if(fat != -1){
            foundItems = foundItems.stream().filter(menuItem -> menuItem.computeFat() == fat).collect(Collectors.toList());
        }
        if(sodium != -1){
            foundItems = foundItems.stream().filter(menuItem -> menuItem.computeSodium() == sodium).collect(Collectors.toList());
        }
        if(price != -1){
            foundItems = foundItems.stream().filter(menuItem -> menuItem.getPrice() == price).collect(Collectors.toList());
        }
        return foundItems;
    }

    @Override
    public Map<Order, List<MenuItem>> getOrdersInInterval(int startHour, int endHour) {
        Map<Order, List<MenuItem>> foundOrders = orders.entrySet().stream().filter(entry -> entry.getKey().getDate().getHour() >= startHour && entry.getKey().getDate().getHour() < endHour).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return foundOrders;
    }


    @Override
    public List<MenuItem> getOrderedProducts(int noOfTimes) {
        BiFunction<List<MenuItem>, MenuItem, Long> nbFound = (items, searchedItem) -> items.stream().filter(item -> item.getTitle().equals(searchedItem.getTitle())).count();
        List<MenuItem> items = orders.values().stream().flatMap(List::stream).collect(Collectors.toList());
        List<MenuItem> foundItems = items.stream().filter(item -> nbFound.apply(items, item) >= noOfTimes).distinct().collect(Collectors.toList());
        return foundItems;
    }

    @Override
    public List<String> getLoyalClients(int noOfTimes, int amount) {
        BiFunction<List<Order>, Order, Long> noFound = (orders, searchedOrder) -> orders.stream().filter(order -> order.getClient().equals(searchedOrder.getClient())).count();
        List<Order> ordersList = orders.keySet().stream().collect(Collectors.toList());
        List<String> clients = ordersList.stream().filter(order -> noFound.apply(ordersList, order) >= noOfTimes).filter(order -> order.getTotal() >= amount).map(order -> order.getClient()).distinct().collect(Collectors.toList());
        return clients;
    }

    @Override
    public Map<MenuItem, Long> getProductsByDay(LocalDateTime date) {
        List<MenuItem> foundItems = orders.entrySet().stream()
                .filter(entry -> entry.getKey().getDate().getYear() == date.getYear() && entry.getKey().getDate().getMonth() == date.getMonth() && entry.getKey().getDate().getDayOfMonth() == date.getDayOfMonth())
                .map(Map.Entry::getValue).flatMap(List::stream).collect(Collectors.toList());
        Map<MenuItem, Long> products = foundItems.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return products;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void saveState() {
        Serializator.serialize(this, "deliveryService.dat");
    }

    public Map<Order, List<MenuItem>> getOrders() {
        return orders;
    }

    public void createObserverList()
    {
        observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object o) {
        for(Observer observer: observers)
        {
            observer.update(o);
        }
    }
}
