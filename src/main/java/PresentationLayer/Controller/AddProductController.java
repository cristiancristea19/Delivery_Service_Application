package PresentationLayer.Controller;

import BusinessLogicLayer.BaseProduct;
import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.IDeliveryServiceProcessing;
import PresentationLayer.View.AddProductView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductController implements ActionListener {
    private AddProductView view;
    private IDeliveryServiceProcessing deliveryService;
    public AddProductController(AddProductView view, IDeliveryServiceProcessing deliveryService)
    {
        this.view = view;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == view.getAddBtn())
        {
            String title = view.getTitle();
            if(title.equals(""))
            {
                view.showMessage("Title field is mandatory!");
                return;
            }
            float rating=0;
            try{
                rating = Float.parseFloat(view.getRating());
                if(rating < 0 || rating > 5)
                {
                    view.showMessage("Invalid rating!The value must be in range 0-5.");
                    return;
                }
            }catch (NumberFormatException ex)
            {
                view.showMessage("Invalid rating!");
                return;
            }
            int calories=0;
            try{
                calories = Integer.parseInt(view.getCalories());
                if(calories < 0)
                {
                    view.showMessage("The calories value must be positive!");
                    return;
                }
            }catch (NumberFormatException ex)
            {
                view.showMessage("Invalid number of calories!");
                return;
            }
            int protein=0;
            try{
                protein = Integer.parseInt(view.getProtein());
                if(protein < 0)
                {
                    view.showMessage("The protein value must be positive!");
                    return;
                }
            }catch(NumberFormatException ex)
            {
                view.showMessage("Invalid number of proteins!");
                return;
            }
            int fat=0;
            try {
                fat = Integer.parseInt(view.getFat());
                if(fat < 0)
                {
                    view.showMessage("The fats value must be positive!");
                    return;
                }
            }catch (NumberFormatException ex)
            {
                view.showMessage("Invalid number of fats!");
                return;
            }
            int sodium=0;
            try{
                sodium = Integer.parseInt(view.getSodium());
                if(sodium < 0)
                {
                    view.showMessage("The sodium value must be positive!");
                    return;
                }
            }catch (NumberFormatException ex)
            {
                view.showMessage("Invalid value for sodium!");
                return;
            }
            int price=0;
            try{
                price = Integer.parseInt(view.getPrice());
                if(price < 0)
                {
                    view.showMessage("Price must be positive!");
                    return;
                }
            }catch (NumberFormatException ex)
            {
                view.showMessage("Invalid price!");
                return;
            }
            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            if(deliveryService.addProduct(baseProduct) == true)
            {
                view.showMessage("The product was successfully added!");
            }
            else
            {
                view.showMessage("This product already exists!");
            }
            view.close();
        }
        if(source == view.getCancelBtn())
        {
            view.close();
        }
    }
}
