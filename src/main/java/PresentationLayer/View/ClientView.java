package PresentationLayer.View;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import PresentationLayer.Controller.ClientController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientView extends JFrame {
    private JPanel panel;
    private JTextField keywordFld;
    private JButton searchBtn;
    private JButton addToCartBtn;
    private JButton orderBtn;
    private JButton logOutBtn;
    private JButton advancedSearchBtn;
    private JLabel totalLbl;
    private JTable tableProducts;
    private JTable cartTable;
    private DefaultTableModel tableModelProducts;
    private DefaultTableModel tableModelCart;
    private ClientController controller;

    public ClientView(String client, DeliveryService deliveryService)
    {
        super("Client Interface");

        this.setSize(new Dimension(800, 530));
        this.setLocation(50, 200);
        panel = new JPanel();
        panel.setLayout(null);
        controller = new ClientController(this, client, deliveryService);

        createTextField();
        createButtons();
        createLabel();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void createTextField()
    {
        keywordFld = new JTextField(45);
        keywordFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        keywordFld.setBounds(30, 20, 400,25);
        panel.add(keywordFld);
    }

    public void createButtons()
    {
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeSearchBtn = searchBtn.getPreferredSize();
        searchBtn.setBounds(450, 20, sizeSearchBtn.width+20 , 25);
        searchBtn.addActionListener(controller);
        panel.add(searchBtn);

        advancedSearchBtn = new JButton("Advanced Search");
        advancedSearchBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeAdvancedSearchBtn = searchBtn.getPreferredSize();
        advancedSearchBtn.setBounds(590, 20, sizeAdvancedSearchBtn.width+100 , 25);
        advancedSearchBtn.addActionListener(controller);
        panel.add(advancedSearchBtn);

        addToCartBtn = new JButton("Add to cart");
        addToCartBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeAddToCart = searchBtn.getPreferredSize();
        addToCartBtn.setBounds(637, 220, sizeAddToCart.width+40 , 25);
        addToCartBtn.addActionListener(controller);
        panel.add(addToCartBtn);

        orderBtn = new JButton("Order");
        orderBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnOrder = orderBtn.getPreferredSize();
        orderBtn.setBounds(637, 430, 120 , sizeBtnOrder.height);
        orderBtn.addActionListener(controller);
        panel.add(orderBtn);

        logOutBtn = new JButton("Log Out");
        logOutBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnLogOut = logOutBtn.getPreferredSize();
        logOutBtn.setBounds(30, 430, 120 , sizeBtnLogOut.height);
        logOutBtn.addActionListener(controller);
        panel.add(logOutBtn);
    }

    public void createLabel()
    {
        totalLbl = new JLabel("Total: 0");
        totalLbl.setFont(new Font("Lucia Grande", Font.BOLD, 20));
        Dimension sizeLbl = totalLbl.getPreferredSize();
        totalLbl.setBounds(637, 400, sizeLbl.width+50 ,sizeLbl.height);
        panel.add(totalLbl);
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

    public void updateTable(List<MenuItem> data){
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
        tableProducts.setModel(tableModelProducts);
    }

    public void createCartTable()
    {
        String[] columns = {"Title", "Price"};
        tableModelCart = new DefaultTableModel(null, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        cartTable = new JTable();
        cartTable.setModel(tableModelCart);
        ((DefaultTableCellRenderer) cartTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        cartTable.setPreferredScrollableViewportSize(new Dimension(30, 70));
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBounds(20, 250, 750, 150);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);
    }

    public void addProductToCart(MenuItem menuItem)
    {
        if(cartTable == null)
        {
            createCartTable();
        }
        Object[] data = new Object[2];
        data[0] = menuItem.getTitle();
        data[1] = menuItem.getPrice();
        tableModelCart.addRow(data);
    }

    public MenuItem getSelectedProduct(){
        int row = tableProducts.getSelectedRow();
        if(row != -1) {
            String title = (String) tableProducts.getValueAt(row, 0);
            int price = (int)tableProducts.getValueAt(row, 1);
            MenuItem menuItem = new MenuItem(title, price);
            return menuItem;
        }
        return null;
    }

    public JButton getAddToCartBtn() {
        return addToCartBtn;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public JButton getOrderBtn() {
        return orderBtn;
    }

    public JButton getLogOutBtn() {
        return logOutBtn;
    }

    public String getKeyword(){
        return keywordFld.getText();
    }

    public JButton getAdvancedSearchBtn() {
        return advancedSearchBtn;
    }

    public void setTotalLbl(int sum) {
        totalLbl.setText("Total: " + sum);
    }

    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }

    public void close(){
        this.setVisible(false);
    }
}
