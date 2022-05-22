package PresentationLayer.View;

import BusinessLogicLayer.AppUsers;
import PresentationLayer.Controller.RegisterController;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    private JPanel panel;
    private JLabel lblLogin;
    private JLabel lblUser;
    private JLabel lblPass;
    private JLabel lblRole;
    private JTextField userTxtFld;
    private JPasswordField passTxtFld;
    private JButton registerBtn;
    private JButton btnBack;
    private RegisterController controller;
    private JComboBox<String> comboBox;
    private boolean role;
    public RegisterView(AppUsers appUsers, boolean role)
    {
        super("Register");
        this.setSize(new Dimension(800, 530));
        this.setLocation(400, 200);
        controller = new RegisterController(this, appUsers, role);

        panel = new JPanel();
        panel.setLayout(null);

        createLabels();
        createTextFields();
        createButtons();
        if(role == true)
        {
            createComboBox();
        }

        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void createComboBox()
    {
        String roles[] = {"Administrator", "Employee", "Client"};
        comboBox = new JComboBox<>(roles);
        comboBox.setBounds(300, 270, 177, 30);
        panel.add(comboBox);

        lblRole = new JLabel("Role");
        lblRole.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeRole = lblUser.getPreferredSize();
        lblRole.setBounds(300, 240, sizeRole.width+20 ,sizeRole.height);
        panel.add(lblRole);
    }

    private void createLabels()
    {
        lblLogin = new JLabel("Register");
        lblLogin.setFont(new Font("Lucia Grande", Font.BOLD, 25));
        Dimension sizeLbl = lblLogin.getPreferredSize();
        lblLogin.setBounds(330, 40, sizeLbl.width+20 ,sizeLbl.height);
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

    private void createButtons()
    {
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnBack = btnBack.getPreferredSize();
        btnBack.setBounds(30, 420, sizeBtnBack.width+20 , sizeBtnBack.height);
        btnBack.addActionListener(controller);
        panel.add(btnBack);

        registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeRegisterBtn = registerBtn.getPreferredSize();
        registerBtn.setBounds(650, 420, sizeRegisterBtn.width+20 , sizeRegisterBtn.height);
        registerBtn.addActionListener(controller);
        panel.add(registerBtn);
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }

    public String getUsername(){
        return userTxtFld.getText();
    }

    public String getPassword(){
        return passTxtFld.getText();
    }

    public String getRole(){
        return (String) comboBox.getSelectedItem();
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void close() {
        this.setVisible(false);
    }
}
