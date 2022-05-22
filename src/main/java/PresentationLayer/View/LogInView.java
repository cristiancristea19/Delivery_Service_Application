package PresentationLayer.View;

import BusinessLogicLayer.AppUsers;
import PresentationLayer.Controller.LogInController;

import javax.swing.*;
import java.awt.*;

public class LogInView extends JFrame {
    private JPanel panel;
    private JLabel lblLogin;
    private JLabel lblUser;
    private JLabel lblPass;
    private JLabel lblRole;
    private JTextField userTxtFld;
    private JPasswordField passTxtFld;
    private JButton logInBtn;
    private JButton registerBtn;
    private JComboBox<String> comboBox;
    private LogInController controller;

    public LogInView()
    {
        super("Log In");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 530));
        this.setLocation(400, 200);

        controller = new LogInController(this);

        panel = new JPanel();
        panel.setLayout(null);

        createLabels();
        createTextFields();
        createButtons();
        createComboBox();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void createLabels()
    {
        lblLogin = new JLabel("Log In");
        lblLogin.setFont(new Font("Lucia Grande", Font.BOLD, 25));
        Dimension sizeLbl = lblLogin.getPreferredSize();
        lblLogin.setBounds(350, 40, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(lblLogin);

        lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeUser = lblUser.getPreferredSize();
        lblUser.setBounds(300, 100, sizeUser.width+20 ,sizeUser.height);
        panel.add(lblUser);

        lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizePass = lblUser.getPreferredSize();
        lblPass.setBounds(300, 170, sizePass.width+20 ,sizePass.height);
        panel.add(lblPass);

        lblRole = new JLabel("Role");
        lblRole.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeRole = lblUser.getPreferredSize();
        lblRole.setBounds(300, 240, sizeRole.width+20 ,sizeRole.height);
        panel.add(lblRole);
    }

    private void createTextFields()
    {
        userTxtFld = new JTextField(45);
        userTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        Dimension sizeTxtUser = lblUser.getPreferredSize();
        userTxtFld.setBounds(300, 130, sizeTxtUser.width*2+20,25);
        panel.add(userTxtFld);

        passTxtFld = new JPasswordField(45);
        passTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        passTxtFld.setBounds(300, 200, sizeTxtUser.width*2+20, 25);
        panel.add(passTxtFld);
    }

    public void createComboBox()
    {
        String roles[] = {"Administrator", "Employee", "Client"};
        comboBox = new JComboBox<>(roles);
        comboBox.setBounds(300, 270, 177, 30);
        panel.add(comboBox);
    }

    public void createButtons()
    {
        logInBtn = new JButton("Log In");
        logInBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeLogInBtn = logInBtn.getPreferredSize();
        logInBtn.setBounds(650, 400, sizeLogInBtn.width+20 , sizeLogInBtn.height);
        logInBtn.addActionListener(controller);
        panel.add(logInBtn);

        registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeRegisterBtn = logInBtn.getPreferredSize();
        registerBtn.setBounds(30, 400, sizeRegisterBtn.width+20 , sizeRegisterBtn.height);
        registerBtn.addActionListener(controller);
        panel.add(registerBtn);
    }

    public JButton getLogInBtn() {
        return logInBtn;
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }

    public String getUsername() {
        return userTxtFld.getText();
    }

    public String getPassword(){
        return passTxtFld.getText();
    }

    public String getRole()
    {
        return (String)comboBox.getSelectedItem();
    }

    public void showMessage(String msg)
    {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void close()
    {
        this.setVisible(false);
    }
}
