package PresentationLayer.View;

import BusinessLogicLayer.BaseProduct;
import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;
import PresentationLayer.Controller.EmployeeController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderNotificationView extends JFrame {
    private JPanel panel;
    private JLabel newOrderLbl;
    private JLabel clientLbl;
    private JLabel dateLbl;
    private JLabel totalLbl;
    private JButton processOrderBtn;
    private EmployeeController controller;

    public OrderNotificationView(Object o)
    {
        super("Order Notification");
        this.setSize(new Dimension(330, 350));
        this.setLocation(900, 300);
        panel = new JPanel();
        panel.setLayout(null);

        controller = new EmployeeController(this);

        List<Object> list = (List<Object>) o;
        Order order = (Order)list.get(0);
        List<MenuItem> menuItemList = (List<MenuItem>)list.get(1);

        createNewOrderLbl();
        createLblClient(order.getClient());
        createLblDate(order.getDate());
        createLblTotal(order.getTotal());
        createTable(menuItemList);
        createButton();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void createNewOrderLbl()
    {
        newOrderLbl = new JLabel("New Order");
        newOrderLbl.setFont(new Font("Lucia Grande", Font.BOLD, 20));
        Dimension sizeLbl = newOrderLbl.getPreferredSize();
        newOrderLbl.setBounds(100, 10, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(newOrderLbl);
    }

    public void createLblClient(String client)
    {
        clientLbl = new JLabel("Client: " + client);
        clientLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeLbl = clientLbl.getPreferredSize();
        clientLbl.setBounds(25, 40, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(clientLbl);
    }

    public void createLblDate(LocalDateTime date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        dateLbl = new JLabel("Date&time: " + date.format(formatter));
        dateLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeLbl = dateLbl.getPreferredSize();
        dateLbl.setBounds(25, 70, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(dateLbl);
    }

    public void createLblTotal(int total)
    {
        totalLbl = new JLabel("Total: " + total);
        totalLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeLbl = totalLbl.getPreferredSize();
        totalLbl.setBounds(25, 100, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(totalLbl);
    }

    public void createTable(List<MenuItem> data)
    {
        String[] columns = {"Products"};
        Object[][] objs = new Object[data.size()][1];
        int i=0;
        for(MenuItem menuItem: data)
        {
            objs[i++][0]=menuItem.getTitle();
        }
        DefaultTableModel tableModel = new DefaultTableModel(objs, columns) {

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
        scrollPane.setBounds(25, 115, 280, 130);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);
    }

    public void createButton()
    {
        processOrderBtn = new JButton("Process order");
        processOrderBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBtn = processOrderBtn.getPreferredSize();
        processOrderBtn.setBounds(120, 260, 180, sizeBtn.height);
        processOrderBtn.addActionListener(controller);
        panel.add(processOrderBtn);
    }

    public void close()
    {
        this.setVisible(false);
    }
}
