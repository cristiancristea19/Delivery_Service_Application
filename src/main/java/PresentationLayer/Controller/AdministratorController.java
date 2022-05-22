package PresentationLayer.Controller;

import BusinessLogicLayer.AppUsers;
import BusinessLogicLayer.DeliveryService;

import BusinessLogicLayer.IDeliveryServiceProcessing;
import DataAccessLayer.Serializator;
import PresentationLayer.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdministratorController implements ActionListener {
    private AdministratorView view;
    private IDeliveryServiceProcessing deliveryService;
    private AppUsers appUsers;
    public AdministratorController(AdministratorView view, AppUsers appUsers, DeliveryService deliveryService)
    {
        this.view = view;
        this.appUsers = appUsers;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getBtnCreateAccount())
        {
            new RegisterView(appUsers, true);
        }
        if (source == view.getBtnViewProducts()) {
            ProductsTableView view = new ProductsTableView();
            view.createTable(((DeliveryService)deliveryService).getMenuItems());
        }
        if (source == view.getBtnInsertProduct()) {
            new AddProductView(deliveryService);
        }
        if (source == view.getBtnUpdateProduct()) {
            new ModifyProductView(deliveryService);
        }
        if (source == view.getBtnDeleteProduct()) {
            new DeleteProductView(deliveryService);
        }
        if (source == view.getBtnCreateMenu()) {
            CreateMenuView view = new CreateMenuView(deliveryService);
            view.createTableProducts(((DeliveryService)deliveryService).getMenuItems());
        }
        if(source == view.getBtnViewMenus()){
            MenusView view = new MenusView();
            view.createTable(((DeliveryService)deliveryService).getMenuItems());
        }

        if (source == view.getBtnGenerateRep1()) {
            new Report1View((DeliveryService) deliveryService);
        }
        if (source == view.getBtnGenerateRep2()) {
            new Report2View((DeliveryService) deliveryService);
        }
        if (source == view.getBtnGenerateRep3()) {
            new Report3View((DeliveryService) deliveryService);
        }
        if (source == view.getBtnGenerateRep4()) {
            new Report4View((DeliveryService) deliveryService);
        }
        if(source == view.getBtnLogOut()){
            ((DeliveryService)deliveryService).saveState();
            view.close();
        }
    }
}
