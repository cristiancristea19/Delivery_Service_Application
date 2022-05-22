package PresentationLayer.Controller;

import BusinessLogicLayer.BaseProduct;
import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.IDeliveryServiceProcessing;
import PresentationLayer.View.ModifyProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductController implements ActionListener {
    private ModifyProductView view;
    private IDeliveryServiceProcessing deliveryService;

    public ModifyProductController(ModifyProductView view, IDeliveryServiceProcessing deliveryService)
    {
        this.view = view;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == view.getModifyBtn())
        {
            String title = view.getTitle();
            if(title.equals(""))
            {
                view.showMessage("Title field is mandatory!");
                return;
            }
            float rating;
            try{
                rating = Float.parseFloat(view.getRating());
                if(rating < 0 || rating > 5)
                {
                    view.showMessage("Invalid rating!The value must be in range 0-5.");
                    return;
                }
            }catch(NumberFormatException ex)
            {
                rating=-1;
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
                calories = -1;
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
                protein = -1;
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
                fat=-1;
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
                sodium = -1;
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
                price = -1;
            }
            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            if(deliveryService.modifyProduct(baseProduct) == true)
            {
                view.showMessage("The product was successfully modified!");
                view.close();
            }
            else
            {
                view.showMessage("The product was not found!");
            }

        }
        if(source == view.getCancelBtn())
        {
            view.close();
        }
    }
}
