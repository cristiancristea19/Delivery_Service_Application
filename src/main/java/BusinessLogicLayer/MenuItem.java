package BusinessLogicLayer;

import java.io.Serializable;
import java.util.Objects;

public class MenuItem implements Serializable {
    protected String title;
    protected int price;


    public MenuItem(String title, int price)
    {
        this.title = title;
        this.price = price;
    }

    public void increasePrice(int increment)
    {
        price += increment;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float computeRating(){
        return 0;
    }

    public int computeCalories(){
        return 0;
    }

    public int computeProtein(){
        return 4;
    }

    public int computeFat(){
        return 0;
    }

    public int computeSodium(){
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return price == menuItem.price && Objects.equals(title, menuItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }
}
