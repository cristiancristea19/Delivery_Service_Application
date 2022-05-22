package BusinessLogicLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompositeProduct extends MenuItem{

    private List<MenuItem> menuItems;

    public CompositeProduct()
    {
        super(null, 0);
        menuItems = new ArrayList<>();
    }

    public void addProduct(MenuItem menuItem)
    {
        menuItems.add(menuItem);
        increasePrice(menuItem.getPrice());
    }

    @Override
    public float computeRating(){
        float sum = 0;
        for(MenuItem menuItem: menuItems){
            sum += menuItem.computeRating();
        }
        return sum/menuItems.size();
    }

    @Override
    public int computeCalories(){
        int sum = 0;
        for(MenuItem menuItem: menuItems){
            sum += menuItem.computeCalories();
        }
        return sum;
    }

    @Override
    public int computeProtein(){
        int sum = 0;
        for(MenuItem menuItem: menuItems){
            sum += menuItem.computeProtein();
        }
        return sum;
    }

    @Override
    public int computeFat(){
        int sum = 0;
        for(MenuItem menuItem: menuItems){
            sum += menuItem.computeFat();
        }
        return sum;
    }

    @Override
    public int computeSodium(){
        int sum = 0;
        for(MenuItem menuItem: menuItems){
            sum += menuItem.computeSodium();
        }
        return sum;
    }

    public List<MenuItem> getMenuItems()
    {
        return menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompositeProduct that = (CompositeProduct) o;
        return Objects.equals(menuItems, that.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), menuItems);
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "title='" + title + '\'' +
                ", menuItems=" + menuItems +
                '}';
    }
}
