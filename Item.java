public class Item {
    private String upc;
    private String name;
    private double unitPrice;
    private int quantity;
    private String manufacturer;
    private String expirationDate;
    private String category;

    public Item(String upc, String name, double unitPrice, int quantity, String manufacturer, String expirationDate, String category) {
        this.upc = upc;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public Item(){

    }

    public String getUPC() {
        return upc;
    }

    public void setUPC(String upc) {
        this.upc = upc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity() {
        this.quantity -=1;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getExpiration() {
        return expirationDate;
    }

    public void setExpiration(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void modify(int colIndex, String newValue){
        switch(colIndex){
            case 0:
                this.upc = newValue;
                break;
            case 1:
                this.name = newValue;
                break;
            case 2:
                this.unitPrice = Double.parseDouble(newValue);
                break;
            case 3:
                this.quantity = Integer.parseInt(newValue);
                break;
            case 4:
                this.manufacturer = newValue;
                break;
            case 5:
                this.expirationDate = newValue;
                break;
            case 6:
                this.category = newValue;
                break;
        }
    }

    // public void remove(){
    //     this.upc ="";
    //     this.name = "";
    //     this.unitPrice = 0;
    //     this.quantity = 0;
    //     this.manufacturer = "";
    //     this.expirationDate = "";
    //     this.category = "";
    // }

    @Override    
    public String toString(){
        return "Name: " + name + "\nManufacturer: " + manufacturer + "\nUPC: " + upc + "\nCategory: " + category + "\nUnits: " + quantity + "\nPrice: " + unitPrice + "\nExpiration: " + expirationDate+"\n";
    }
}
