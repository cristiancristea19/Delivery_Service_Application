package PresentationLayer.View;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;
import PresentationLayer.Controller.ReportsController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Report1View extends JFrame {
    private JPanel panel;
    private JLabel titleLbl;
    private JLabel startHourLbl;
    private JLabel endHourLbl;
    private JTextField startHourTxtFld;
    private JTextField endHourTxtFld;
    private JButton generateBtn;
    private JButton backBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private DeliveryService deliveryService;
    private ReportsController controller;

    public Report1View(DeliveryService deliveryService) {
        super("Report 1");
        this.setSize(new Dimension(800, 530));
        this.setLocation(400, 200);

        panel = new JPanel();
        panel.setLayout(null);

        this.deliveryService = deliveryService;
        controller = new ReportsController(this, deliveryService);

        createButtons();
        createTextFields();
        createLabels();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void createLabels() {
        titleLbl = new JLabel("Report 1");
        titleLbl.setFont(new Font("Lucia Grande", Font.BOLD, 25));
        Dimension sizeLbl = titleLbl.getPreferredSize();
        titleLbl.setBounds(345, 20, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(titleLbl);

        startHourLbl = new JLabel("Start hour:");
        startHourLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeStart = startHourLbl.getPreferredSize();
        startHourLbl.setBounds(300, 70, sizeStart.width+20 ,sizeStart.height);
        panel.add(startHourLbl);

        endHourLbl = new JLabel("End hour:");
        endHourLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeEnd = endHourLbl.getPreferredSize();
        endHourLbl.setBounds(300, 100, sizeEnd.width+20 ,sizeEnd.height);
        panel.add(endHourLbl);
    }

    public void createTextFields() {
        startHourTxtFld = new JTextField(10);
        startHourTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        startHourTxtFld.setBounds(400, 70, 70,25);
        panel.add(startHourTxtFld);

        endHourTxtFld = new JTextField(10);
        endHourTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        endHourTxtFld.setBounds(400, 100, 70,25);
        panel.add(endHourTxtFld);
    }

    public void createButtons(){
        generateBtn = new JButton("Generate Report");
        generateBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeGenerateBtn = generateBtn.getPreferredSize();
        generateBtn.setBounds(500, 80, sizeGenerateBtn.width+20 , sizeGenerateBtn.height);
        generateBtn.addActionListener(controller);
        panel.add(generateBtn);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBackBtn = backBtn.getPreferredSize();
        backBtn.setBounds(660, 440, sizeBackBtn.width+20 , sizeBackBtn.height);
        backBtn.addActionListener(controller);
        panel.add(backBtn);
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
        table = new JTable();
        table.setModel(tableModel);
        ((DefaultTableCellRenderer)table.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        table.setPreferredScrollableViewportSize(new Dimension(30, 70));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 130, 750, 300);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);
    }

    public void updateTable(Map<Order, List<MenuItem>> data){
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
        table.setModel(tableModel);
    }

    public JButton getGenerateBtn() {
        return generateBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public String getStartHour(){
        return startHourTxtFld.getText();
    }

    public String getEndHour(){
        return endHourTxtFld.getText();
    }

    public JTable getTable(){
        return table;
    }

    public void close(){
        this.setVisible(false);
    }

    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
}
