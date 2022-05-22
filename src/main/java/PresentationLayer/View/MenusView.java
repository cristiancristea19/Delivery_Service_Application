package PresentationLayer.View;

import BusinessLogicLayer.CompositeProduct;
import BusinessLogicLayer.MenuItem;
import PresentationLayer.Controller.TablesController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenusView extends JFrame {
    private final JPanel panel;
    private JButton btnBack;
    private JLabel lbl;
    private TablesController controller;

    public MenusView()
    {
        super("Menus table");
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
        lbl = new JLabel("Menus table");
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

    public void createTable(List<BusinessLogicLayer.MenuItem> data)
    {
        String[] columns = {"Title", "Composed of", "Price", "Rating", "Calories", "Protein", "Fat", "Sodium"};
        List<Object[]> objList = new ArrayList<>();
        for(MenuItem menuItem: data)
        {
            if(menuItem instanceof CompositeProduct)
            {
                List<MenuItem> productList = ((CompositeProduct)menuItem).getMenuItems();
                Object[] menuInfo = new Object[8];
                menuInfo[0] = menuItem.getTitle();
                menuInfo[1] = productList.get(0).getTitle();
                menuInfo[2] = menuItem.getPrice();
                menuInfo[3] = menuItem.computeRating();
                menuInfo[4] = menuItem.computeCalories();
                menuInfo[5] = menuItem.computeProtein();
                menuInfo[6] = menuItem.computeFat();
                menuInfo[7] = menuItem.computeSodium();
                objList.add(menuInfo);
                for(int j=1; j<productList.size(); j++)
                {
                    Object[] prodInfo = new Object[3];
                    prodInfo[0] = null;
                    prodInfo[1] = productList.get(j).getTitle();
                    prodInfo[2] = null;
                    objList.add(prodInfo);
                }
            }
        }
        Object[][] objs = new Object[objList.size()][3];
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
