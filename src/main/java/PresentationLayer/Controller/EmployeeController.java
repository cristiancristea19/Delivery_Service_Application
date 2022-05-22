package PresentationLayer.Controller;

import BusinessLogicLayer.DeliveryService;
import PresentationLayer.View.EmployeeView;
import PresentationLayer.View.OrderNotificationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController implements ActionListener {
    private EmployeeView employeeView;
    private OrderNotificationView notificationView;

    public EmployeeController(EmployeeView view)
    {
        this.employeeView = view;

    }

    public EmployeeController(OrderNotificationView view)
    {
        this.notificationView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(employeeView != null) {

            employeeView.close();
        }
        else
            notificationView.close();
    }


}
