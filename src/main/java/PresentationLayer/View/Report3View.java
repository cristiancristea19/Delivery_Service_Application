package PresentationLayer.View;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import PresentationLayer.Controller.ReportsController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Report3View extends JFrame {
    private JPanel panel;
    private JLabel titleLbl;
    private JLabel noOfTimesLbl;
    private JLabel amountLbl;
    private JTextField noOfTimesTxtFld;
    private JTextField amountTxtFld;
    private JButton generateBtn;
    private JButton backBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private ReportsController controller;

    public Report3View(DeliveryService deliveryService) {
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

    public void createLabels() {
        titleLbl = new JLabel("Report 3");
        titleLbl.setFont(new Font("Lucia Grande", Font.BOLD, 25));
        Dimension sizeLbl = titleLbl.getPreferredSize();
        titleLbl.setBounds(345, 20, sizeLbl.width+20 ,sizeLbl.height);
        panel.add(titleLbl);

        noOfTimesLbl = new JLabel("Number of times:");
        noOfTimesLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeStart = noOfTimesLbl.getPreferredSize();
        noOfTimesLbl.setBounds(280, 70, sizeStart.width+20 ,sizeStart.height);
        panel.add(noOfTimesLbl);

        amountLbl = new JLabel("Amount:");
        amountLbl.setFont(new Font("Lucia Grande", Font.BOLD, 16));
        Dimension sizeEnd = amountLbl.getPreferredSize();
        amountLbl.setBounds(280, 100, sizeEnd.width+20 ,sizeEnd.height);
        panel.add(amountLbl);
    }

    public void createTextFields() {
        noOfTimesTxtFld = new JTextField(10);
        noOfTimesTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        noOfTimesTxtFld.setBounds(420, 70, 70,25);
        panel.add(noOfTimesTxtFld);

        amountTxtFld = new JTextField(10);
        amountTxtFld.setFont(new Font("Lucia Grande", Font.PLAIN, 15));
        amountTxtFld.setBounds(420, 100, 70,25);
        panel.add(amountTxtFld);
    }

    public void createButtons(){
        generateBtn = new JButton("Generate Report");
        generateBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeGenerateBtn = generateBtn.getPreferredSize();
        generateBtn.setBounds(520, 80, sizeGenerateBtn.width+20 , sizeGenerateBtn.height);
        generateBtn.addActionListener(controller);
        panel.add(generateBtn);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Lucia Grande", Font.PLAIN, 16));
        Dimension sizeBackBtn = backBtn.getPreferredSize();
        backBtn.setBounds(660, 440, sizeBackBtn.width+20 , sizeBackBtn.height);
        backBtn.addActionListener(controller);
        panel.add(backBtn);
    }

    public void createTable(List<String> data)
    {
        String[] columns = {"Client"};
        Object[][] objs = new Object[data.size()][2];
        int i=0;
        for(String client: data)
        {
            objs[i++][0] = client;
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



    public void updateTable(List<String> data){
        String[] columns = {"Client"};
        Object[][] objs = new Object[data.size()][2];
        int i=0;
        for(String client: data)
        {
            objs[i++][0] = client;
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

    public String getNoOfTimes(){
        return noOfTimesTxtFld.getText();
    }

    public String getAmount(){
        return amountTxtFld.getText();
    }

    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }

    public JTable getTable(){
        return table;
    }

    public void close(){
        this.setVisible(false);
    }
}
