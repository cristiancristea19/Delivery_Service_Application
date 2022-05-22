package PresentationLayer.View;

import BusinessLogicLayer.AppUsers;
import BusinessLogicLayer.DeliveryService;
import PresentationLayer.Controller.AdministratorController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AdministratorView extends JFrame {
    private JPanel panel;
    private JPanel operationsPanel;
    private AdministratorController controller;
    private JButton btnInsertProduct;
    private JButton btnUpdateProduct;
    private JButton btnViewProducts;
    private JButton btnDeleteProduct;
    private JButton btnCreateMenu;
    private JButton btnViewMenus;
    private JButton btnCreateAccount;
    private JButton btnGenerateRep1;
    private JButton btnGenerateRep2;
    private JButton btnGenerateRep3;
    private JButton btnGenerateRep4;
    private JButton btnLogOut;

    public AdministratorView(AppUsers appUsers, DeliveryService deliveryService)
    {
        super("Administrator page");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(new Dimension(800, 530));
        this.setLocation(400, 200);
        panel = new JPanel();
        panel.setLayout(null);
        controller = new AdministratorController(this, appUsers, deliveryService);

        createPanels();
        createButtons();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void createPanels()
    {
        operationsPanel = new JPanel();
        operationsPanel.setLayout(null);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        operationsPanel.setBorder(BorderFactory.createTitledBorder(border, "Administrator page" , TitledBorder.LEFT, TitledBorder.TOP,new Font("Lucia Grande", Font.BOLD, 18), Color.BLACK));
        operationsPanel.setBounds(225, 10, 350, 470);
        panel.add(operationsPanel);
    }

    private void createButtons()
    {

        btnCreateAccount = new JButton("Create account");
        btnCreateAccount.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnCreateAcc = btnCreateAccount.getPreferredSize();
        btnCreateAccount.setBounds(50, 30, 250, sizeBtnCreateAcc.height);
        btnCreateAccount.addActionListener(controller);
        operationsPanel.add(btnCreateAccount);

        btnViewProducts = new JButton("View products");
        btnViewProducts.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnViewPr = btnViewProducts.getPreferredSize();
        btnViewProducts.setBounds(50, 70, 250, sizeBtnViewPr.height);
        btnViewProducts.addActionListener(controller);
        operationsPanel.add(btnViewProducts);

        btnViewMenus = new JButton("View menus");
        btnViewMenus.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnViewMenus = btnViewMenus.getPreferredSize();
        btnViewMenus.setBounds(50, 110, 250, sizeBtnViewMenus.height);
        btnViewMenus.addActionListener(controller);
        operationsPanel.add(btnViewMenus);

        btnInsertProduct = new JButton("Add product");
        btnInsertProduct.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnInsert = btnInsertProduct.getPreferredSize();
        btnInsertProduct.setBounds(50, 150, 250 , sizeBtnInsert.height);
        btnInsertProduct.addActionListener(controller);
        operationsPanel.add(btnInsertProduct);

        btnUpdateProduct = new JButton("Modify product");
        btnUpdateProduct.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnUpdate = btnUpdateProduct.getPreferredSize();
        btnUpdateProduct.setBounds(50, 190, 250 , sizeBtnUpdate.height);
        btnUpdateProduct.addActionListener(controller);
        operationsPanel.add(btnUpdateProduct);

        btnDeleteProduct = new JButton("Delete a product");
        btnDeleteProduct.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnDeleteProduct = btnDeleteProduct.getPreferredSize();
        btnDeleteProduct.setBounds(50, 230, 250 , sizeBtnDeleteProduct.height);
        btnDeleteProduct.addActionListener(controller);
        operationsPanel.add(btnDeleteProduct);

        btnCreateMenu = new JButton("Create menu");
        btnCreateMenu.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnCreateProduct = btnCreateMenu.getPreferredSize();
        btnCreateMenu.setBounds(50, 270, 250 , sizeBtnCreateProduct.height);
        btnCreateMenu.addActionListener(controller);
        operationsPanel.add(btnCreateMenu);

        btnGenerateRep1 = new JButton("Generate report 1");
        btnGenerateRep1.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnGenerateRep1 = btnGenerateRep1.getPreferredSize();
        btnGenerateRep1.setBounds(50, 310, 250 , sizeBtnGenerateRep1.height);
        btnGenerateRep1.addActionListener(controller);
        operationsPanel.add(btnGenerateRep1);

        btnGenerateRep2 = new JButton("Generate report 2");
        btnGenerateRep2.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnGenerateRep2 = btnGenerateRep2.getPreferredSize();
        btnGenerateRep2.setBounds(50, 350, 250 , sizeBtnGenerateRep2.height);
        btnGenerateRep2.addActionListener(controller);
        operationsPanel.add(btnGenerateRep2);

        btnGenerateRep3 = new JButton("Generate report 3");
        btnGenerateRep3.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnGenerateRep3 = btnGenerateRep3.getPreferredSize();
        btnGenerateRep3.setBounds(50, 390, 250 , sizeBtnGenerateRep3.height);
        btnGenerateRep3.addActionListener(controller);
        operationsPanel.add(btnGenerateRep3);

        btnGenerateRep4 = new JButton("Generate report 4");
        btnGenerateRep4.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnGenerateRep4 = btnGenerateRep4.getPreferredSize();
        btnGenerateRep4.setBounds(50, 430, 250 , sizeBtnGenerateRep4.height);
        btnGenerateRep4.addActionListener(controller);
        operationsPanel.add(btnGenerateRep4);

        btnLogOut = new JButton("Log Out");
        btnLogOut.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnGenerateLogOut = btnLogOut.getPreferredSize();
        btnLogOut.setBounds(600, 440, 150 , sizeBtnGenerateLogOut.height);
        btnLogOut.addActionListener(controller);
        panel.add(btnLogOut);
    }

    public JButton getBtnInsertProduct() {
        return btnInsertProduct;
    }

    public JButton getBtnUpdateProduct() {
        return btnUpdateProduct;
    }

    public JButton getBtnViewProducts() {
        return btnViewProducts;
    }

    public JButton getBtnDeleteProduct() {
        return btnDeleteProduct;
    }

    public JButton getBtnCreateMenu() {
        return btnCreateMenu;
    }

    public JButton getBtnGenerateRep1() {
        return btnGenerateRep1;
    }

    public JButton getBtnGenerateRep2() {
        return btnGenerateRep2;
    }

    public JButton getBtnGenerateRep3() {
        return btnGenerateRep3;
    }

    public JButton getBtnGenerateRep4() {
        return btnGenerateRep4;
    }

    public JButton getBtnCreateAccount(){
        return btnCreateAccount;
    }
    public JButton getBtnViewMenus() {
        return btnViewMenus;
    }

    public JButton getBtnLogOut() {
        return btnLogOut;
    }

    public void close(){
        this.setVisible(false);
    }
}
