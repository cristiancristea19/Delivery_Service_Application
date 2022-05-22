package PresentationLayer.Controller;

import BusinessLogicLayer.CompositeProduct;
import BusinessLogicLayer.IDeliveryServiceProcessing;
import BusinessLogicLayer.MenuItem;
import PresentationLayer.View.AddMenuNameView;
import PresentationLayer.View.CreateMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMenuController implements ActionListener {
    private CreateMenuView viewMenu;
    private IDeliveryServiceProcessing deliveryService;
    private MenuItem compositeProduct;
    private AddMenuNameView viewName;

    public CreateMenuController(CreateMenuView view, IDeliveryServiceProcessing deliveryService)
    {
        this.viewMenu = view;
        this.deliveryService = deliveryService;
        compositeProduct = new CompositeProduct();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == viewMenu.getBtnAdd())
        {
            MenuItem menuItem = viewMenu.getSelectedProduct();
            if(menuItem == null){
                viewMenu.showMessage("Select a product!");
                return;
            }
            viewMenu.addProductToMenu(menuItem);
            ((CompositeProduct)compositeProduct).addProduct(menuItem);
        }
        if(source == viewMenu.getBtnCreate())
        {
            if(viewMenu.isTableCreated() == false)
            {
                viewMenu.showMessage("Select at least one product!");
                return;
            }
            viewName = new AddMenuNameView(this);
        }
        if(source == viewMenu.getBtnCancel())
        {
            viewMenu.close();
        }
        if(viewName != null) {
            if (source == viewName.getBtnCancel()) {
                viewName.close();
            }
            if (source == viewName.getBtnCreate()) {
                String title = viewName.getTitle();
                if (title.equals("")) {
                    viewName.showMessage("Please add a title to the menu.");
                    return;
                }
                compositeProduct.setTitle(title);
                if(deliveryService.addProduct(compositeProduct) == false)
                {
                    viewName.showMessage("This product already exists!");
                    return;
                }
                viewName.showMessage("The menu was successfully created!");
                viewName.close();
                viewMenu.close();
            }
        }
    }
}
