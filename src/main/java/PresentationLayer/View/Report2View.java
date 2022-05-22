package PresentationLayer.View;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import PresentationLayer.Controller.ReportsController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Report2View extends JFrame {
    private JPanel panel;
    private JLabel titleLbl;
    private JLabel timesLbl;
    private JLabel infoLbl;
    private JTextField timesTxtFld;
    private JButton generateBtn;
    private JButton backBtn;

    private ReportsController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public Report2View(DeliveryService deliveryService){
        super("Report 1");
        this.setSize(new Dimension(800, 530));
        this.setLocation(400, 200);

        panel = new JPanel();
        panel.setLayout(null);

        controller = new ReportsController(this, deliveryService);

        createTextField();
        createLabels();
        createButtons();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void createLabels(){
        titleLbl = new JLabel("Report 2");
        titleLbl.setFont(new Font("Lucia Grande", Font.BOLD, 25));
        Dimension sizeLbl = titleLbl.getPreferredSize();
        titleLbl.setBounds(345, 20, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(titleLbl);

        timesLbl = new JLabel("Number of times:");
        timesLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeTimes = timesLbl.getPreferredSize();
        timesLbl.setBounds(280, 70, sizeTimes.width+20 ,sizeTimes.height);
        panel.add(timesLbl);

        infoLbl = new JLabel("The products ordered more than x times:");
        infoLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeInfo = infoLbl.getPreferredSize();
        infoLbl.setBounds(20, 100, sizeInfo.width*3 ,sizeInfo.height);
        infoLbl.setVisible(false);
        panel.add(infoLbl);
    }

    public void createTextField(){
        timesTxtFld = new JTextField(10);
        timesTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        timesTxtFld.setBounds(420, 70, 70,25);
        panel.add(timesTxtFld);
    }

    public void createButtons(){
        generateBtn = new JButton("Generate Report");
        generateBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeGenerateBtn = generateBtn.getPreferredSize();
        generateBtn.setBounds(520, 70, sizeGenerateBtn.width+20 , 25);
        generateBtn.addActionListener(controller);
        panel.add(generateBtn);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBackBtn = backBtn.getPreferredSize();
        backBtn.setBounds(660, 440, sizeBackBtn.width+20 , sizeBackBtn.height);
        backBtn.addActionListener(controller);
        panel.add(backBtn);
    }

    public void createTable(List<MenuItem> data)
    {
        String[] columns = {"Title", "Price"};
        Object[][] objs = new Object[data.size()][2];
        int i=0;
        for(MenuItem menuItem: data)
        {
            objs[i][0] = menuItem.getTitle();
            objs[i++][1] = menuItem.getPrice();
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
        scrollPane.setBounds(20, 120, 750, 320);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);
    }

    public void updateTable(List<MenuItem> data){
        String[] columns = {"Title", "Price"};
        Object[][] objs = new Object[data.size()][2];
        int i=0;
        for(MenuItem menuItem: data)
        {
            objs[i][0] = menuItem.getTitle();
            objs[i++][1] = menuItem.getPrice();
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

    public void setTextInfo(int noOfTimes) {
        infoLbl.setText("The products ordered more than " + noOfTimes + " times:");
        infoLbl.setVisible(true);
    }

    public void setInfoInvisible(){
        infoLbl.setVisible(false);
    }

    public JButton getGenerateBtn() {
        return generateBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public JTable getTable(){
        return table;
    }

    public String getNoOfTimes(){
        return timesTxtFld.getText();
    }

    public void close(){
        this.setVisible(false);
    }

    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
}
