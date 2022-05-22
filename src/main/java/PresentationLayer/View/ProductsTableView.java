package PresentationLayer.View;

import BusinessLogicLayer.BaseProduct;
import PresentationLayer.Controller.TablesController;
import BusinessLogicLayer.MenuItem;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;


public class ProductsTableView extends JFrame {

    private final JPanel panel;
    private JButton btnBack;
    private JLabel lbl;
    private TablesController controller;

    public ProductsTableView()
    {
        super("Products table");
        controller = new TablesController(this);
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
        lbl = new JLabel("Products table");
        lbl.setFont(new Font("Lucia Grande", Font.BOLD, 20));
        Dimension sizeUser = lbl.getPreferredSize();
        lbl.setBounds(330, 15, sizeUser.width+20 ,sizeUser.height);
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

    public void createTable(List<MenuItem> data)
    {
        String[] columns = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        List<Object[]> objList = new ArrayList<>();
        for(MenuItem menuItem: data)
        {
            if(menuItem instanceof BaseProduct)
            {
                Object[] productInfo = new Object[7];
                productInfo[0] = menuItem.getTitle();
                productInfo[1] = ((BaseProduct)menuItem).computeRating();
                productInfo[2] = ((BaseProduct)menuItem).computeCalories();
                productInfo[3] = ((BaseProduct)menuItem).computeProtein();
                productInfo[4] = ((BaseProduct)menuItem).computeFat();
                productInfo[5] = ((BaseProduct)menuItem).computeSodium();
                productInfo[6] = menuItem.getPrice();
                objList.add(productInfo);
            }
        }
        Object[][] objs = new Object[objList.size()][7];
        objList.toArray(objs);
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
        scrollPane.setBounds(20, 50, 750, 350);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);
        this.setContentPane(panel);

    }

    public void close()
    {
        this.setVisible(false);
    }
}

