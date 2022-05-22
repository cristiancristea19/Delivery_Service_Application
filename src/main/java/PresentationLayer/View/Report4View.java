package PresentationLayer.View;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import PresentationLayer.Controller.ReportsController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class Report4View extends JFrame {
    public JPanel panel;

    private JLabel titleLbl;
    private JLabel dayLbl;
    private JLabel monthLbl;
    private JLabel yearLbl;

    private JTextField dayTxtFld;
    private JTextField monthTxtFld;
    private JTextField yearTxtFld;

    private JButton generateBtn;
    private JButton backBtn;


    private DefaultTableModel tableModel;
    private JTable table;
    private ReportsController controller;

    public Report4View(DeliveryService deliveryService) {
        super("Report 3");
        this.setSize(new Dimension(800, 530));
        this.setLocation(400, 200);

        panel = new JPanel();
        panel.setLayout(null);

        controller = new ReportsController(this, deliveryService);

        createButtons();
        createTextFields();
        createLabels();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void createLabels(){
        titleLbl = new JLabel("Report 4");
        titleLbl.setFont(new Font("Lucia Grande", Font.BOLD, 25));
        Dimension sizeLbl = titleLbl.getPreferredSize();
        titleLbl.setBounds(345, 20, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(titleLbl);

        dayLbl = new JLabel("Day:");
        dayLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeDay = dayLbl.getPreferredSize();
        dayLbl.setBounds(310, 70, sizeDay.width+20 ,sizeDay.height);
        panel.add(dayLbl);

        monthLbl = new JLabel("Month:");
        monthLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeMonth = monthLbl.getPreferredSize();
        monthLbl.setBounds(310, 100, sizeMonth.width+20 ,sizeMonth.height);
        panel.add(monthLbl);

        yearLbl = new JLabel("Year:");
        yearLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeYear = yearLbl.getPreferredSize();
        yearLbl.setBounds(310, 130, sizeYear.width+20 ,sizeYear.height);
        panel.add(yearLbl);
    }

    public void createButtons(){
        generateBtn = new JButton("Generate Report");
        generateBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeGenerateBtn = generateBtn.getPreferredSize();
        generateBtn.setBounds(520, 100, sizeGenerateBtn.width+20 , 25);
        generateBtn.addActionListener(controller);
        panel.add(generateBtn);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBackBtn = backBtn.getPreferredSize();
        backBtn.setBounds(660, 440, sizeBackBtn.width+20 , sizeBackBtn.height);
        backBtn.addActionListener(controller);
        panel.add(backBtn);
    }

    public void createTextFields(){
        dayTxtFld = new JTextField(10);
        dayTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        dayTxtFld.setBounds(420, 70, 70,25);
        panel.add(dayTxtFld);

        monthTxtFld = new JTextField(10);
        monthTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        monthTxtFld.setBounds(420, 100, 70,25);
        panel.add(monthTxtFld);

        yearTxtFld = new JTextField(10);
        yearTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        yearTxtFld.setBounds(420, 130, 70,25);
        panel.add(yearTxtFld);
    }

    public void createTable(Map<MenuItem, Long> data)
    {
        String[] columns = {"Products", "Frequency"};
        Object[][] objs = new Object[data.size()][2];
        int i=0;
        for(Map.Entry<MenuItem, Long> entry: data.entrySet())
        {
            objs[i][0] = entry.getKey().getTitle();
            objs[i++][1] = entry.getValue();
        }
        tableModel = new DefaultTableModel(objs, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table = new JTable();
        table.setModel(tableModel);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        table.setPreferredScrollableViewportSize(new Dimension(30, 70));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 160, 750, 270);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);
    }

    public void updateTable(Map<MenuItem, Long> data){
        String[] columns = {"Products", "Frequency"};
        Object[][] objs = new Object[data.size()][2];
        int i=0;
        for(Map.Entry<MenuItem, Long> entry: data.entrySet())
        {
            objs[i][0] = entry.getKey().getTitle();
            objs[i++][1] = entry.getValue();
        }
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

    public String getDay(){
        return dayTxtFld.getText();
    }

    public String getMonth(){
        return monthTxtFld.getText();
    }

    public String getYear(){
        return yearTxtFld.getText();
    }

    public JTable getTable() {
        return table;
    }

    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }

    public void close(){
        this.setVisible(false);
    }
}