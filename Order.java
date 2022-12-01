import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;


public class Order {
    private final int orderID;
    private static final AtomicInteger counter = new AtomicInteger(1000);
    private ArrayList<Item> listofItems;
    // private Item item;
    private double total;
    private Date date;

    public Order(ArrayList<Item> itemsList, double total, Date date){
        this.listofItems = itemsList;
        this.total = total;
        this.date = date;
        orderID = counter.incrementAndGet();
    }

    public Order(){
        orderID = counter.incrementAndGet();
    }

    public ArrayList<Item> getItemsList() {
        return listofItems;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setItems(ArrayList<Item> itemsList) {
        this.listofItems = itemsList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double addTax(){
        double taxPercent = 0.15;
        double amtTaxed = total * taxPercent;
        double newTotal = amtTaxed + this.total;
        return newTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return "Order: "+ getOrderID() + "\n";
    }
}
