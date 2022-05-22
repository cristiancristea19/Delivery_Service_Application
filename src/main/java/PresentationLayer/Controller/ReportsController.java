package PresentationLayer.Controller;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;
import PresentationLayer.View.Report1View;
import PresentationLayer.View.Report2View;
import PresentationLayer.View.Report3View;
import PresentationLayer.View.Report4View;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Map;

public class ReportsController implements ActionListener {

    private Report1View view1;
    private DeliveryService deliveryService;
    private Report2View view2;
    private Report3View view3;
    private Report4View view4;

    public ReportsController(Report1View view, DeliveryService deliveryService){
        this.view1 = view;
        this.deliveryService = deliveryService;
    }

    public ReportsController(Report2View view, DeliveryService deliveryService){
        this.view2 = view;
        this.deliveryService = deliveryService;
    }

    public ReportsController(Report3View view, DeliveryService deliveryService){
        this.view3 = view;
        this.deliveryService = deliveryService;
    }

    public ReportsController(Report4View view, DeliveryService deliveryService){
        this.view4 = view;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(view1 != null) {
            report1(source);
        }
        if(view2 != null){
            report2(source);
        }
        if(view3 != null) {
            report3(source);
        }
        if(view4 != null) {
            report4(source);
        }
    }

    private void report1(Object source){
        if (source == view1.getBackBtn()) {
            view1.close();
        }
        if (source == view1.getGenerateBtn()) {
            try {
                int startHour = Integer.parseInt(view1.getStartHour());
                int endHour = Integer.parseInt(view1.getEndHour());
                if (startHour < 0 || startHour > 24 || endHour < 0 || endHour > 24) {
                    view1.showMessage("The hours should be in range 0-24!");
                    return;
                }
                if (startHour > endHour) {
                    view1.showMessage("End hour should be greater than start hour!");
                    return;
                }
                Map<Order, List<MenuItem>> orders = deliveryService.getOrdersInInterval(startHour, endHour);
                if (orders.size() == 0) {
                    view1.showMessage("No order was found!");
                    return;
                }
                if (view1.getTable() == null)
                    view1.createTable(orders);
                else
                    view1.updateTable(orders);
            } catch (NumberFormatException ex) {
                view1.showMessage("Please insert valid numbers!");
            }
        }
    }

    public void report2(Object source){
        if(source == view2.getGenerateBtn()){
            try{
                int noOfTimes = Integer.parseInt(view2.getNoOfTimes());
                if(noOfTimes < 0) {
                    view2.showMessage("The number should be greater than 0.");
                    return;
                }
                List<MenuItem> foundMenuItems = deliveryService.getOrderedProducts(noOfTimes);
                if(foundMenuItems.size() == 0) {
                    view2.showMessage("No product was found!");
                    view2.setInfoInvisible();
                }
                if(view2.getTable() == null){
                    view2.createTable(foundMenuItems);
                }
                else {
                    view2.updateTable(foundMenuItems);
                }
                view2.setTextInfo(noOfTimes);
            }catch (NumberFormatException ex){
                view2.showMessage("Please insert a valid number!");
            }
        }
        if(source == view2.getBackBtn()){
            view2.close();
        }
    }

    private void report3(Object source){
        if(source == view3.getGenerateBtn()){
            try{
                int noOfTimes = Integer.parseInt(view3.getNoOfTimes());
                int amount = Integer.parseInt(view3.getAmount());
                if(noOfTimes < 0 || amount < 0){
                    view3.showMessage("The numbers should be greater than zero!");
                    return;
                }
                List<String> loyalClients = deliveryService.getLoyalClients(noOfTimes, amount);
                if(loyalClients.size() == 0){
                    view3.showMessage("No client was found!");
                }
                if(view3.getTable() == null){
                    view3.createTable(loyalClients);
                }
                else {
                    view3.updateTable(loyalClients);
                }
            }catch (NumberFormatException ex){
                view3.showMessage("Please insert valid numbers!");
            }
        }
        if(source == view3.getBackBtn()){
            view3.close();
        }
    }

    public void report4(Object source){
        if(source == view4.getGenerateBtn()){
            try{
                int day = Integer.parseInt(view4.getDay());
                if(day < 0 || day > 31){
                    view4.showMessage("Invalid day.");
                    return;
                }
                int month = Integer.parseInt(view4.getMonth());
                if(month < 0 || month > 12){
                    view4.showMessage("Invalid month.");
                    return;
                }
                int year = Integer.parseInt(view4.getYear());
                if(year < 0) {
                    view4.showMessage("Invalid year.");
                }
                LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);
                Map<MenuItem, Long> products = deliveryService.getProductsByDay(date);
                if(products.size() == 0){
                    view4.showMessage("No product was ordered in that day");
                    return;
                }
                if(view4.getTable() == null){
                    view4.createTable(products);
                }
                else{
                    view4.updateTable(products);
                }
            }catch (NumberFormatException e){
                view4.showMessage("Please insert valid numbers");
            }
        }
        if(source == view4.getBackBtn()){
            view4.close();
        }
    }
}
