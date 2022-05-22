package PresentationLayer.Controller;

import BusinessLogicLayer.AppUsers;
import BusinessLogicLayer.User;
import PresentationLayer.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private RegisterView view;
    private AppUsers appUsers;
    private boolean role;
    public RegisterController(RegisterView view, AppUsers appUsers, boolean role)
    {
        this.view = view;
        this.appUsers = appUsers;
        this.role = role;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == view.getRegisterBtn())
        {
            String username = view.getUsername();
            String password = view.getPassword();
            if(username.equals("") || password.equals("")) {
                view.showMessage("Username and password fields are mandatory!");
                return;
            }
            User user;
            if(role == false) {
                user = new User(username, password, "Client");
            }
            else{
                String usrRole = view.getRole();
                user = new User(username, password, usrRole);
            }
            if (appUsers.addUser(user)) {
                view.showMessage("The account was successfully created!");
                appUsers.saveUsers();
                view.close();
            }
            else {
                view.showMessage("This username already exists!");
            }
        }
        else {
            view.close();
        }
    }
}
