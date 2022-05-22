package PresentationLayer.View;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.IDeliveryServiceProcessing;
import PresentationLayer.Controller.AddProductController;

import javax.swing.*;
import java.awt.*;

public class AddProductView extends JFrame{
    private JPanel panel;
    private JLabel lblAddProduct;
    private JLabel lblTitle;
    private JLabel lblRating;
    private JLabel lblCalories;
    private JLabel lblProtein;
    private JLabel lblFat;
    private JLabel lblSodium;
    private JLabel lblPrice;
    private JTextField txtTitle;
    private JTextField txtRating;
    private JTextField txtCalories;
    private JTextField txtProtein;
    private JTextField txtFat;
    private JTextField txtSodium;
    private JTextField txtPrice;
    private JButton addBtn;
    private JButton cancelBtn;
    private AddProductController controller;

    public AddProductView(IDeliveryServiceProcessing deliveryService)
    {
        super("Add new product");
        this.setSize(new Dimension(800, 530));
        this.setLocation(400, 200);
        controller = new AddProductController(this, deliveryService);
        panel = new JPanel();
        panel.setLayout(null);

        createLabels();
        createTextFields();
        createButtons();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void createLabels()
    {
        lblAddProduct = new JLabel("Add new product");
        lblAddProduct.setFont(new Font("Lucia Grande", Font.BOLD, 25));
        Dimension sizeLbl = lblAddProduct.getPreferredSize();
        lblAddProduct.setBounds(300, 40, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(lblAddProduct);

        lblTitle = new JLabel("Title:");
        lblTitle.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeUser = lblTitle.getPreferredSize();
        lblTitle.setBounds(300, 100, sizeUser.width+20 ,sizeUser.height);
        panel.add(lblTitle);

        lblRating = new JLabel("Rating:");
        lblRating.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizePass = lblRating.getPreferredSize();
        lblRating.setBounds(300, 140, sizePass.width+20 ,sizePass.height);
        panel.add(lblRating);

        lblCalories = new JLabel("Calories:");
        lblCalories.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeCall = lblRating.getPreferredSize();
        lblCalories.setBounds(300, 180, sizeCall.width+30 ,sizeCall.height);
        panel.add(lblCalories);

        lblProtein = new JLabel("Proteins:");
        lblProtein.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeProtein = lblRating.getPreferredSize();
        lblProtein.setBounds(300, 220, sizeProtein.width+40 ,sizeProtein.height);
        panel.add(lblProtein);

        lblFat = new JLabel("Fat:");
        lblFat.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeFat = lblFat.getPreferredSize();
        lblFat.setBounds(300, 260, sizeFat.width+40 ,sizeFat.height);
        panel.add(lblFat);

        lblSodium = new JLabel("Sodium:");
        lblSodium.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeSodium = lblFat.getPreferredSize();
        lblSodium.setBounds(300, 300, sizeSodium.width+40 ,sizeSodium.height);
        panel.add(lblSodium);

        lblPrice = new JLabel("Price:");
        lblPrice.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizePrice = lblPrice.getPreferredSize();
        lblPrice.setBounds(300, 340, sizePrice.width+40 ,sizePrice.height);
        panel.add(lblPrice);
    }

    private void createTextFields()
    {
        txtTitle = new JTextField(45);
        txtTitle.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        Dimension sizeTxtUser = lblTitle.getPreferredSize();
        txtTitle.setBounds(380, 100, sizeTxtUser.width*2+50,25);
        panel.add(txtTitle);

        txtRating = new JTextField(45);
        txtRating.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        txtRating.setBounds(380, 140, sizeTxtUser.width*2+50, 25);
        panel.add(txtRating);

        txtCalories = new JTextField(45);
        txtCalories.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        txtCalories.setBounds(380, 180, sizeTxtUser.width*2+50, 25);
        panel.add(txtCalories);

        txtProtein = new JTextField(45);
        txtProtein.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        txtProtein.setBounds(380, 220, sizeTxtUser.width*2+50, 25);
        panel.add(txtProtein);

        txtFat = new JTextField(45);
        txtFat.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        txtFat.setBounds(380, 260, sizeTxtUser.width*2+50, 25);
        panel.add(txtFat);

        txtSodium = new JTextField(45);
        txtSodium.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        txtSodium.setBounds(380, 300, sizeTxtUser.width*2+50, 25);
        panel.add(txtSodium);

        txtPrice = new JTextField(45);
        txtPrice.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        txtPrice.setBounds(380, 340, sizeTxtUser.width*2+50, 25);
        panel.add(txtPrice);
    }

    public void createButtons()
    {
        addBtn = new JButton("Add");
        addBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeLogInBtn = addBtn.getPreferredSize();
        addBtn.setBounds(650, 420, sizeLogInBtn.width+20 , sizeLogInBtn.height);
        addBtn.addActionListener(controller);
        panel.add(addBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeRegisterBtn = addBtn.getPreferredSize();
        cancelBtn.setBounds(30, 420, sizeRegisterBtn.width+20 , sizeRegisterBtn.height);
        cancelBtn.addActionListener(controller);
        panel.add(cancelBtn);
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public String getTitle(){
        return txtTitle.getText();
    }

    public String getRating(){
        return txtRating.getText();
    }

    public String getCalories(){
        return txtCalories.getText();
    }

    public String getProtein(){
        return txtProtein.getText();
    }

    public String getFat(){
        return txtFat.getText();
    }

    public String getSodium(){
        return txtSodium.getText();
    }

    public String getPrice(){
        return txtPrice.getText();
    }

    public void close()
    {
        this.setVisible(false);
    }

    public void showMessage(String msg)
    {
        JOptionPane.showMessageDialog(this, msg);
    }
}
