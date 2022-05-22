package PresentationLayer.Controller;

import BusinessLogicLayer.IDeliveryServiceProcessing;
import PresentationLayer.View.DeleteProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductController implements ActionListener {
    private DeleteProductView view;
    private IDeliveryServiceProcessing deliveryService;

    public DeleteProductController(DeleteProductView view, IDeliveryServiceProcessing deliveryService)
    {
        this.view = view;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == view.getBtnDelete())
        {
            String title = view.getTitle();
            if(title.equals(""))
            {
                view.showMessage("Title field is mandatory!");
                return;
            }

            if(deliveryService.deleteProduct(title) == true)
            {
                view.showMessage("The product was successfully deleted!");
                view.close();
            }
            else
            {
                view.showMessage("The product was not found!");
            }
        }
        if(source == view.getBtnCancel())
        {
            view.close();
        }
    }
}
