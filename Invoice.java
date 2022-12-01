import java.util.concurrent.atomic.AtomicInteger;

public class Invoice {
    private final int id;
    private static final AtomicInteger counter = new AtomicInteger(1000);
    private Order order;

    public Invoice(){
        id = counter.incrementAndGet();
    }

    public Invoice(Order order){
        id = counter.incrementAndGet();        
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String displayInvoice() {

        String str = "";
        str = "===================================================\n";
            str += "       Dawn's Grocery Store Invoice\n";
            str += "         dawnsgroceries@gmail.com\n";
            str += "         876-499-0999\n";
            str += "       \n";
            str += "       Invoice#: " + getId()+ "\n";
            str += "       Order#: " + getOrder().getOrderID()+ "\n";
            str += "       Date: " + getOrder().getDate()+ "\n";
            str += "       \n";
            str += "       Items: " +  "\n";
                    for(Item i: getOrder().getItemsList()){
                        str += "===================================================\n";
                        str += "           Item: " + i.getName()+ "\n";
                        str += "           Qty: 1" + "\n";
                        str += "           Price: " + i.getUnitPrice() + "\n";
                        str += "===================================================\n";
                    }
            str += "       Subtotal: " + getOrder().getTotal() + "\n";
            str += "       Tax Rate: 15%" + "\n";
            str += "       Total: " + getOrder().addTax() + "\n";
        return str;
    }
}
