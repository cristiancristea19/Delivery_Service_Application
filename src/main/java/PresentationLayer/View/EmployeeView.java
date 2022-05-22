package PresentationLayer.View;

import BusinessLogicLayer.*;
import BusinessLogicLayer.MenuItem;
import PresentationLayer.Controller.EmployeeController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class EmployeeView extends JFrame implements Observer {
    private JPanel panel;
    private JLabel lbl;
    private JButton btnBack;
    private EmployeeController controller;
    private DefaultTableModel tableModel;
    private DeliveryService deliveryService;

    public EmployeeView(DeliveryService deliveryService)
    {
        super("Employee Interface");
        this.setSize(new Dimension(800, 530));
        this.setLocation(650, 200);

        panel = new JPanel();
        panel.setLayout(null);

        this.deliveryService = deliveryService;
        controller = new EmployeeController(this);

        createButtons();
        createLabels();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    @Override
    public void update(Object o) {
        new OrderNotificationView(o);
        updateOrdersTable(o);
    }

    public void createLabels()
    {
        lbl = new JLabel("Orders table");
        lbl.setFont(new Font("Lucia Grande", Font.BOLD, 20));
        Dimension sizeUser = lbl.getPreferredSize();
        lbl.setBounds(335, 15, sizeUser.width+20 ,sizeUser.height);
        panel.add(lbl);
    }

    public void createButtons()
    {
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtnBack = btnBack.getPreferredSize();
        btnBack.setBounds(635, 420, 120 , sizeBtnBack.height);
        btnBack.addActionListener(controller);
        panel.add(btnBack);
    }

    public void createTable(Map<Order, List<MenuItem>> data)
    {
        String[] columns = {"Client", "Date&Time", "Total", "Products"};
        java.util.List<Object[]> objList = new ArrayList<>();
        Set<Order> keys = data.keySet();
        for(Order key: keys)
        {
                List<MenuItem> productList = data.get(key);
                Object[] orderInfo = new Object[4];
                orderInfo[0] = key.getClient();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                orderInfo[1] = key.getDate().format(formatter);
                orderInfo[2] = key.getTotal();
                orderInfo[3] = productList.get(0).getTitle();
                objList.add(orderInfo);
                for(int j=1; j<productList.size(); j++)
                {
                    Object[] prodInfo = new Object[4];
                    prodInfo[0] = null;
                    prodInfo[1] = null;
                    prodInfo[2] = null;
                    prodInfo[3] = productList.get(j).getTitle();
                    objList.add(prodInfo);
                }
        }
        Object[][] objs = new Object[objList.size()][4];
        objList.toArray(objs);
        tableModel = new DefaultTableModel(objs, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTable table = new JTable();
        table.setModel(tableModel);
        ((DefaultTableCellRenderer)table.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        table.setPreferredScrollableViewportSize(new Dimension(30, 70));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 750, 350);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);
    }

    public void updateOrdersTable(Object o)
    {
        List<Object> list = (List<Object>) o;
        Order order = (Order)list.get(0);
        List<MenuItem> menuItemList = (List<MenuItem>)list.get(1);

        Object[] objs = new Object[4];
        objs[0] = order.getClient();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        objs[1] = order.getDate().format(formatter);
        objs[2] = order.getTotal();
        objs[3] = menuItemList.get(0).getTitle();
        tableModel.addRow(objs);
        for(int i=1; i<menuItemList.size(); i++)
        {
            objs[0] = null;
            objs[1] = null;
            objs[2] = null;
            objs[3] = menuItemList.get(i).getTitle();
            tableModel.addRow(objs);
        }
    }

    public void close()
    {
        this.setVisible(false);
        deliveryService.detach(this);
    }

}
