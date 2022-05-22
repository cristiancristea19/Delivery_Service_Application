package PresentationLayer.Controller;

import BusinessLogicLayer.BaseProduct;
import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import DataAccessLayer.WriteToFile;
import PresentationLayer.View.ClientView;
import PresentationLayer.View.SearchView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientController implements ActionListener {
    private ClientView clientView;
    private SearchView searchView;
    private DeliveryService deliveryService;
    private List<MenuItem> menuItemList;
    private int sum;
    private String client;
    public ClientController(ClientView view, String client, DeliveryService deliveryService)
    {
        this.clientView = view;
        this.client = client;
        menuItemList = new ArrayList<>();
        this.deliveryService = deliveryService;
        view.createTableProducts(deliveryService.getMenuItems());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == clientView.getAddToCartBtn())
        {
            MenuItem menuItem = clientView.getSelectedProduct();
            if(menuItem == null){
                clientView.showMessage("Select a product!");
                return;
            }
            menuItemList.add(menuItem);
            sum+=menuItem.getPrice();
            clientView.setTotalLbl(sum);
            clientView.addProductToCart(menuItem);
        }
        if(source == clientView.getOrderBtn())
        {
            if(sum == 0)
            {
                clientView.showMessage("Please select at least one product!");
                return;
            }
            deliveryService.addOrder(menuItemList, client, sum);
            clientView.showMessage("Ordered placed successfully! Thank you!");
            deliveryService.saveState();
            createBill(client, sum, menuItemList);
            new ClientView(client, deliveryService);
            clientView.close();
        }

        if(source == clientView.getSearchBtn())
        {
            String keyword = clientView.getKeyword();
            if(keyword.equals("")) {
                clientView.updateTable(deliveryService.getMenuItems());
                return;
            }
            if(Character.isLowerCase(keyword.charAt(0)))
                keyword = Character.toUpperCase(keyword.charAt(0)) + keyword.substring(1);
            List<MenuItem> foundItems = deliveryService.search(keyword, -1, -1, -1, -1, -1, -1);
            clientView.updateTable(foundItems);
        }

        if(source == clientView.getLogOutBtn())
        {
            clientView.close();
        }

        if(source == clientView.getAdvancedSearchBtn())
        {
            searchView = new SearchView(this);
        }

        if(searchView != null)
        {
            if(source == searchView.getSearchBtn()){
                String title = searchView.getTitle();
                float rating;
                try{
                    rating = Float.parseFloat(searchView.getRating());
                    if(rating < 0 || rating > 5)
                    {
                        searchView.showMessage("Invalid rating!The value must be in range 0-5.");
                        return;
                    }
                }catch(NumberFormatException ex)
                {
                    rating=-1;
                }
                int calories=0;
                try{
                    calories = Integer.parseInt(searchView.getCalories());
                    if(calories < 0)
                    {
                        searchView.showMessage("The calories value must be positive!");
                        return;
                    }
                }catch (NumberFormatException ex)
                {
                    calories = -1;
                }
                int protein=0;
                try{
                    protein = Integer.parseInt(searchView.getProtein());
                    if(protein < 0)
                    {
                        searchView.showMessage("The protein value must be positive!");
                        return;
                    }
                }catch(NumberFormatException ex)
                {
                    protein = -1;
                }
                int fat=0;
                try {
                    fat = Integer.parseInt(searchView.getFat());
                    if(fat < 0)
                    {
                        searchView.showMessage("The fats value must be positive!");
                        return;
                    }
                }catch (NumberFormatException ex)
                {
                    fat=-1;
                }
                int sodium=0;
                try{
                    sodium = Integer.parseInt(searchView.getSodium());
                    if(sodium < 0)
                    {
                        searchView.showMessage("The sodium value must be positive!");
                        return;
                    }
                }catch (NumberFormatException ex)
                {
                    sodium = -1;
                }
                int price=0;
                try{
                    price = Integer.parseInt(searchView.getPrice());
                    if(price < 0)
                    {
                        searchView.showMessage("Price must be positive!");
                        return;
                    }
                }catch (NumberFormatException ex)
                {
                    price = -1;
                }
                List<MenuItem> foundItems = deliveryService.search(title, rating, calories, protein, fat, sodium, price);
                clientView.updateTable(foundItems);
                searchView.close();
                searchView = null;
            }
            else if(source == searchView.getCancelBtn()){
                searchView.close();
            }
        }
    }

    private void createBill(String client, int total, List<MenuItem> menuItems){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        String path = "./OrderBills/order_" + LocalDateTime.now().format(formatter) + ".txt";
        String info = "Client: " + client + '\n' +
                "Date: " + LocalDateTime.now().format(formatter) + '\n' +
                "Total: " + total + '\n' +
                "List of products:" + '\n';
        for(MenuItem menuItem: menuItems){
            info += menuItem.getTitle() + " - " + menuItem.getPrice() + '\n';
        }
        WriteToFile.write(path, info);
    }

}
