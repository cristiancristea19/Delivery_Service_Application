package PresentationLayer.View;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.IDeliveryServiceProcessing;
import PresentationLayer.Controller.DeleteProductController;

import javax.swing.*;
import java.awt.*;

public class DeleteProductView extends JFrame {
    private JPanel panel;

    private JButton btnDelete;
    private JButton btnCancel;
    private JLabel lbl;
    private JLabel lblTitle;
    private JTextField titleFld;
    private DeleteProductController controller;

    public DeleteProductView(IDeliveryServiceProcessing deliveryService)
    {
        super("Delete Client");
        this.setSize(new Dimension(330, 350));
        this.setLocation(630, 300);
        panel = new JPanel();
        panel.setLayout(null);
        controller = new DeleteProductController(this, deliveryService);

        createLabels();
        createTextFields();
        createButtons();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void createLabels()
    {
        lbl = new JLabel("Delete a product");
        lbl.setFont(new Font("Lucia Grande", Font.BOLD, 20));
        Dimension sizeLbl = lbl.getPreferredSize();
        lbl.setBounds(90, 10, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(lbl);

        lblTitle = new JLabel("Title:");
        lblTitle.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeID = lblTitle.getPreferredSize();
        lblTitle.setBounds(20, 70, sizeID.width+20 ,sizeID.height);
        panel.add(lblTitle);
    }

    private void createTextFields()
    {
        titleFld = new JTextField(45);
        titleFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        titleFld.setBounds(140, 70, 150, 25);
        panel.add(titleFld);
    }

    private void createButtons()
    {
        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnDelete = btnDelete.getPreferredSize();
        btnDelete.setBounds(180, 240, 120, sizeBtnDelete.height);
        btnDelete.addActionListener(controller);
        panel.add(btnDelete);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnCancel = btnCancel.getPreferredSize();
        btnCancel.setBounds(20, 240, 120 , sizeBtnCancel.height);
        btnCancel.addActionListener(controller);
        panel.add(btnCancel);
    }

    public JButton getBtnCancel(){
        return btnCancel;
    }

    public JButton getBtnDelete(){
        return btnDelete;
    }

    public void showMessage(String msg)
    {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void close()
    {
        this.setVisible(false);
    }

    public String getTitle()
    {
        return titleFld.getText();
    }
}

