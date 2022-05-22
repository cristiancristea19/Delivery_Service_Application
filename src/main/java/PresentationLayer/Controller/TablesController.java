package PresentationLayer.Controller;

import PresentationLayer.View.MenusView;
import PresentationLayer.View.ProductsTableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablesController implements ActionListener {
    private ProductsTableView productsView;
    private MenusView menusView;

    public TablesController(ProductsTableView productsView)
    {
        this.productsView = productsView;
    }

    public TablesController(MenusView menusView){
        this.menusView = menusView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(productsView != null)
            productsView.close();
        else
            menusView.close();
    }
}
