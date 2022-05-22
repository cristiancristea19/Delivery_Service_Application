package BusinessLogicLayer;

import java.util.Objects;

public class BaseProduct extends MenuItem{

    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, price);
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
    }

    @Override
    public float computeRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int computeCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public int computeProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    @Override
    public int computeFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    @Override
    public int computeSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseProduct that = (BaseProduct) o;
        return Float.compare(that.rating, rating) == 0 && calories == that.calories && protein == that.protein && fat == that.fat && sodium == that.sodium;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rating, calories, protein, fat, sodium);
    }

    @Override
    public String toString() {
        return title + ',' + rating + ',' + calories + ',' + protein + ',' + fat + ',' + sodium + ',' + price + ',' + '\n';
    }
}
