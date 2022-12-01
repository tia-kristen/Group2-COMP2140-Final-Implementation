import java.util.Date;
import java.util.ArrayList;

public class Report {
    private Date[] timePeriod;
    private ArrayList<Sales> sales;

    public Report(Date[] timePeriod) {
        this.timePeriod = timePeriod;
        this.sales = new ArrayList<Sales>();
    }

    public Date[] getTimePeriod() {
        return timePeriod;
    }

    public ArrayList<Sales> getSalesList() {
        return sales;
    }

    public void setTimePeriod(Date[] timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void lowStock(){
        System.out.println("Report for low stock items");
    }

    public void productPerformance(){
        System.out.println("Report for product performance");
    }

    public void inventoryReplenishment(){
        System.out.println("Report for inventory replenishment");
    }
}
