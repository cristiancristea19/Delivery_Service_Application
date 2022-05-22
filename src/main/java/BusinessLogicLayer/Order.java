package BusinessLogicLayer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


public class Order implements Serializable {

    private String client;
    private LocalDateTime date;
    private int total;

    public Order(String client, LocalDateTime date, int total)
    {
        this.client = client;
        this.date = date;
        this.total = total;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return total == order.total && Objects.equals(client, order.client) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, date, total);
    }

    @Override
    public String toString() {
        return "Order{" +
                "client='" + client + '\'' +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
