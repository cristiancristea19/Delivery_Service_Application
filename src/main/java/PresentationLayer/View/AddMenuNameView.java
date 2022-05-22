package PresentationLayer.View;

import BusinessLogicLayer.IDeliveryServiceProcessing;
import PresentationLayer.Controller.CreateMenuController;
import PresentationLayer.Controller.DeleteProductController;

import javax.swing.*;
import java.awt.*;

public class AddMenuNameView extends JFrame {
    private JPanel panel;
    private JButton btnCreate;
    private JButton btnCancel;
    private JLabel lbl;
    private JTextField titleFld;
    private CreateMenuController controller;

    public AddMenuNameView(CreateMenuController controller)
    {
        super("CreateMenu");
        this.setSize(new Dimension(330, 350));
        this.setLocation(630, 300);
        panel = new JPanel();
        panel.setLayout(null);
        this.controller = controller;

        createLabels();
        createTextFields();
        createButtons();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void createLabels()
    {
        lbl = new JLabel("Add menu's name");
        lbl.setFont(new Font("Lucia Grande", Font.BOLD, 20));
        Dimension sizeLbl = lbl.getPreferredSize();
        lbl.setBounds(70, 10, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(lbl);

    }

    private void createTextFields()
    {
        titleFld = new JTextField(45);
        titleFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        titleFld.setBounds(50, 70, 220, 25);
        panel.add(titleFld);
    }

    private void createButtons()
    {
        btnCreate = new JButton("Create menu");
        btnCreate.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnCreate = btnCreate.getPreferredSize();
        btnCreate.setBounds(160, 240, 140, sizeBtnCreate.height);
        btnCreate.addActionListener(controller);
        panel.add(btnCreate);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnCancel = btnCancel.getPreferredSize();
        btnCancel.setBounds(20, 240, 120 , sizeBtnCancel.height);
        btnCancel.addActionListener(controller);
        panel.add(btnCancel);
    }

    public JButton getBtnCreate() {
        return btnCreate;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void showMessage(String msg)
    {
        JOptionPane.showMessageDialog(this, msg);
    }

    public String getTitle()
    {
        return titleFld.getText();
    }

    public void close()
    {
        this.setVisible(false);
    }
}
