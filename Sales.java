import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Sales {
    private final int id;
    private static final AtomicInteger counter = new AtomicInteger(1000);
    private ArrayList<Order> orders;
    private Date date;
    private int salesPerItem;

    public Sales(){
        id = counter.incrementAndGet();
        orders = new ArrayList<Order>();
    }

    public int getId() {
        return id;
    }

    public Order getOrder(int id){
        Order order = new Order();
        if(orders.size() != 0){
            for(Order o: orders){
                if(o.getOrderID() == id){
                    order = o;
                }
            }
        }
        return order;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public ArrayList<Order> getOrdersList() {
        return orders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSalesPerItem() {
        return salesPerItem;
    }

    public void setSalesPerItem(int salesPerItem) {
        this.salesPerItem = salesPerItem;
    }

    @Override
    public String toString(){
        String str = "Sale: " + id + "\n";
        for(Order o: orders){
            str += o;
            str += "\n"; 
        }
        return str;
    }}
