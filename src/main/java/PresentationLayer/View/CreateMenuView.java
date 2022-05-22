package PresentationLayer.View;

import BusinessLogicLayer.BaseProduct;
import BusinessLogicLayer.IDeliveryServiceProcessing;
import BusinessLogicLayer.MenuItem;
import PresentationLayer.Controller.CreateMenuController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CreateMenuView extends JFrame {

    private final JPanel panel;
    private JButton btnCancel;
    private JButton btnAdd;
    private JLabel lbl;
    private JTable tableProducts;
    private JTable tableMenu;
    private JButton btnCreate;
    private CreateMenuController controller;
    private DefaultTableModel tableModelProducts;
    private DefaultTableModel tableModelMenu;

    public CreateMenuView(IDeliveryServiceProcessing deliverySerice)
    {
        super("Create Menu");
        controller = new CreateMenuController(this, deliverySerice);
        this.setSize(new Dimension(800, 530));
        this.setLocation(400, 200);
        panel = new JPanel();
        panel.setLayout(null);

        createLabels();
        createButtons();

        this.add(panel);
        this.setVisible(true);
    }

    public void createLabels()
    {
        lbl = new JLabel("Create menu");
        lbl.setFont(new Font("Lucia Grande", Font.BOLD, 20));
        Dimension sizeUser = lbl.getPreferredSize();
        lbl.setBounds(330, 15, sizeUser.width+20 ,sizeUser.height);
        panel.add(lbl);
    }

    public void createButtons()
    {
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnCancel = btnCancel.getPreferredSize();
        btnCancel.setBounds(30, 430, 120 , sizeBtnCancel.height);
        btnCancel.addActionListener(controller);
        panel.add(btnCancel);

        btnAdd = new JButton("Add product");
        btnAdd.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnAdd = btnAdd.getPreferredSize();
        btnAdd.setBounds(637, 220, 120 , sizeBtnAdd.height);
        btnAdd.addActionListener(controller);
        panel.add(btnAdd);

        btnCreate = new JButton("Create");
        btnCreate.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnCreate = btnCancel.getPreferredSize();
        btnCreate.setBounds(637, 430, 120 , sizeBtnCreate.height);
        btnCreate.addActionListener(controller);
        panel.add(btnCreate);
    }

    public void createTableProducts(List<BusinessLogicLayer.MenuItem> data)
    {
        String[] columns = {"Title", "Price", "Rating", "Calories", "Protein", "Fat", "Sodium"};
        Object[][] objs = new Object[data.size()][7];
        int i=0;
        for(MenuItem menuItem: data)
        {
            objs[i][0] = menuItem.getTitle();
            objs[i][1] = menuItem.getPrice();
            objs[i][2] = menuItem.computeRating();
            objs[i][3] = menuItem.computeCalories();
            objs[i][4] = menuItem.computeProtein();
            objs[i][5] = menuItem.computeFat();
            objs[i++][6] = menuItem.computeSodium();
        }
        tableModelProducts = new DefaultTableModel(objs, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tableProducts = new JTable();
        tableProducts.setModel(tableModelProducts);
        ((DefaultTableCellRenderer) tableProducts.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        tableProducts.setPreferredScrollableViewportSize(new Dimension(30, 70));
        JScrollPane scrollPane = new JScrollPane(tableProducts);
        scrollPane.setBounds(20, 50, 750, 170);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);
    }

    public void createTableMenu()
    {
        String[] columns = {"Title", "Price", "Rating", "Calories", "Protein", "Fat", "Sodium"};
        tableModelMenu = new DefaultTableModel(null, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tableMenu = new JTable();
        tableMenu.setModel(tableModelMenu);
        ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        tableMenu.setPreferredScrollableViewportSize(new Dimension(30, 70));
        JScrollPane scrollPane = new JScrollPane(tableMenu);
        scrollPane.setBounds(20, 250, 750, 170);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);
    }

    public boolean isTableCreated()
    {
        return (tableMenu != null);
    }

    public MenuItem getSelectedProduct(){
        int row = tableProducts.getSelectedRow();
        if(row != -1) {
            String title = (String) tableProducts.getValueAt(row, 0);
            int price = (int)tableProducts.getValueAt(row, 1);
            float rating = (float)tableProducts.getValueAt(row, 2);
            int calories = (int)tableProducts.getValueAt(row, 3);
            int protein = (int)tableProducts.getValueAt(row, 4);
            int fat = (int)tableProducts.getValueAt(row, 5);
            int sodium = (int)tableProducts.getValueAt(row, 6);
            MenuItem menuItem = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            tableModelProducts.removeRow(row);
            return menuItem;
        }
        return null;
    }

    public void addProductToMenu(MenuItem menuItem)
    {
        if(tableModelMenu == null) createTableMenu();
        Object[] data = new Object[7];
        data[0] = menuItem.getTitle();
        data[1] = menuItem.getPrice();
        data[2] = menuItem.computeRating();
        data[3] = menuItem.computeCalories();
        data[4] = menuItem.computeProtein();
        data[5] = menuItem.computeFat();
        data[6] = menuItem.computeSodium();
        tableModelMenu.addRow(data);
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnCreate() {
        return btnCreate;
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
