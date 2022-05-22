package PresentationLayer.Controller;

import BusinessLogicLayer.AppUsers;
import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.User;
import DataAccessLayer.Serializator;
import PresentationLayer.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController implements ActionListener {
    private LogInView view;
    private AppUsers appUsers;
    private DeliveryService deliveryService;

    public LogInController(LogInView view){
        this.view = view;
        appUsers = (AppUsers) Serializator.deserialize("users.dat");
        deliveryService =  (DeliveryService) Serializator.deserialize("deliveryService.dat");
        deliveryService.createObserverList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getLogInBtn()) {
            String name = view.getUsername();
            String password = view.getPassword();
            String role = view.getRole();
            User user = new User(name, password, role);
            if (appUsers.doesUserExit(user)) {
                if (role.equals("Administrator")) {
                    new AdministratorView(appUsers, deliveryService);
                }
                else
                {
                    if(role.equals("Client")){
                        new ClientView(name, deliveryService);
                    }
                    else{
                        EmployeeView view = new EmployeeView(deliveryService);
                        view.createTable(deliveryService.getOrders());
                        deliveryService.attach(view);
                    }
                }
            } else {
                view.showMessage("Username, password or role incorrect! Please try again!");
            }
        }
        if (source == view.getRegisterBtn())
        {
            new RegisterView(appUsers, false);
        }
    }
}
